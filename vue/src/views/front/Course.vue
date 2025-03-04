<template xmlns:el-col="http://www.w3.org/1999/html">
  <!--  轮播图-->
  <div class="main-content">
    <div class="search" style="width: 70%;margin: 30px auto;">
      <el-input placeholder="请输入课程名称" style="width: 200px" v-model="name"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>
    <div class="table" style="width: 70%;margin: 30px auto;" >
      <el-table :data="tableData" stripe >
        <el-table-column prop="img" label="课程封面" show-overflow-tooltip width="100">
          <template v-slot="scope" >
            <div style="display: flex; align-items: center">
              <el-image style="width: 60px; height: 40px;border-radius:5px"  v-if="scope.row.img"
                        :src="scope.row.img" :preview-src-list="[scope.row.img]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="课程名称" show-overflow-tooltip width="300">
          <template v-slot="scope">
            <a :href="'/front/CourseDetail?id='+scope.row.id">{{scope.row.name}}</a>
          </template>
        </el-table-column>

        <el-table-column prop="type" label="课程类型">
          <template v-slot="scope">
            <span v-if="scope.row.type === 'VIDEO' "style="color:#b67259">视频课</span>
            <span v-else style="color: #448231">图片课</span>
          </template>
        </el-table-column>
        <el-table-column prop="price" label="课程价格">
          <template v-slot="scope">
            <span style="color: #dd2121" v-if="scope.row.price > 0">￥ {{scope.row.price}} /元</span>
            <el-button type="success" size="mini" v-else>公开课</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="discount" label="课程折扣">
          <template v-slot="scope">
            <span v-if="scope.row.discount <1" style="color: #13ce66">{{scope.row.discount * 10}}折</span>
            <span v-else>—</span>
          </template>
        </el-table-column>
        <el-table-column prop="time" label="发布时间" show-overflow-tooltip></el-table-column>
      </el-table>

      <div class="pagination" style="margin-top: 20px">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, prev, pager, next"
            :total="total">
        </el-pagination>
      </div>
    </div>

  </div>
</template>

<script>

export default {

  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 8,  // 每页显示的个数
      total: 0,
      name: null,
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
    }
  },
  mounted() {
    this.load(1)
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/course/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          recommend:this.recommend
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },reset() {
      this.name = null
      this.recommend = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
}}
</script>
