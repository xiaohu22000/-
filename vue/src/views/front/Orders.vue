<template xmlns:el-col="http://www.w3.org/1999/html">
  <!--  轮播图-->
  <div class="main-content" style="width: 70%">
    <div class="table">
      <el-table :data="tableData" stripe  >
        <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="courseImg" label="课程封面" width="100" >
          <template v-slot="scope" >
            <div style="display: flex; align-items: center">
              <el-image style="width: 60px; height: 40px;border-radius:5px"  v-if="scope.row.courseImg"
                        :src="scope.row.courseImg" :preview-src-list="[scope.row.courseImg]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="courseName" label="课程名称" width="300" >
          <template v-slot="scope">
            <a :href="'/front/CourseDetail?id='+scope.row.courseId">{{scope.row.courseName}}</a>
          </template>
        </el-table-column>
        <el-table-column prop="courseType" label="课程类型">
          <template v-slot="scope">
            <span v-if="scope.row.courseType === 'VIDEO' "style="color:#b67259">视频课</span>
            <span v-else style="color: #448231">图片课</span>
          </template>
        </el-table-column>
        <el-table-column prop="price" label="订单价格" width="100"></el-table-column>
        <el-table-column prop="orderId" label="订单编号" width="200"></el-table-column>
        <el-table-column prop="time" label="下单时间" width="200"></el-table-column>
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
      this.$request.get('/orders/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          userId: this.user.id,

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
