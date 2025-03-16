<template xmlns:el-col="http://www.w3.org/1999/html">
  <!--  轮播图-->
  <div class="main-content" style="width: 70%">
    <div>我解锁的资源{{total}}</div>
    <div class="table">
      <el-table :data="tableData" stripe  >
        <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="fileImg" label="资料封面" width="100" >
          <template v-slot="scope" >
            <div style="display: flex; align-items: center">
              <el-image style="width: 60px; height: 40px;border-radius:5px"  v-if="scope.row.fileImg"
                        :src="scope.row.fileImg" :preview-src-list="[scope.row.fileImg]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="courseName" label="资料名称" width="300" >
          <template v-slot="scope">
            <a :href="'/front/informationDetail?id='+scope.row.fileId">{{scope.row.fileName}}</a>
          </template>
        </el-table-column>

        <el-table-column prop="score" label="兑换积分" width="100"></el-table-column>
        <el-table-column prop="orderId" label="订单编号" width="200"></el-table-column>
        <el-table-column prop="time" label="兑换时间" width="200"></el-table-column>
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
      this.$request.get('/fileorder/selectPage', {
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
