<template xmlns:el-col="http://www.w3.org/1999/html">
  <!--  轮播图-->
  <div class="main-content">
    <div style="display: flex">
      <div style="flex: 12%; height: 350px; background-color: #0c0c0c"></div>
      <div style="flex:76%">
        <el-carousel height="350px">
          <el-carousel-item v-for="item in carouselData" >
            <img :src="item" alt="" style="width: 100%; height: 350px">
          </el-carousel-item>
        </el-carousel>
      </div>
      <div style="flex: 12%; height: 350px; background-color: #0c0c0c"></div>
    </div>

    <!--课程推荐-->
    <div style="width: 76%;margin: 50px auto;min-height: 1000px">
      <div style="display: flex ">

        <div style="flex: 1">
          <span style="font-size: 24px;color: #333333; margin-right: 50px">在线课程</span>
          <el-button type="primary" @click="initValue('VIDEO')">视频课程</el-button>
          <el-button type="success" @click="initValue('SCORE')">积分专区</el-button>
          <el-button type="warning" @click="initValue('TEXT')">图文课程</el-button>
        </div>
        <div style="width: 300px">
          <el-button type="info" @click="signin">签到</el-button>
          <span style="margin-left: 30px;color:#12b127;font-weight: 550">上次签到时间：</span>
          <span style="color: #666666">{{ signInData.time }}</span>
        </div>
      </div>
      <div style="display: flex;margin-top: 20px;height: 300px">
<!--        大图标推荐板块-->
        <div style="flex: 1;margin-right:10px;height: 270px;width: 0">
          <el-carousel height="280px" :interval="3000" arrow="">
            <el-carousel-item v-for="(recommendItem, index) in recommendList" :key="index">
              <img :src="recommendItem.img" style="width: 100%;border-radius: 5px;cursor: pointer" @click="navTo(recommendItem.id)">
              <div style="font-size: 15px" class="overflowShow" >{{ recommendItem.name }}</div>
            </el-carousel-item>
          </el-carousel>
        </div>

        <div style="flex: 2;margin-left:10px">
          <el-row :gutter="20">
            <el-col :span="6" style="margin-bottom: 35px" v-for="item in rightdata">
              <img :src="item.img" alt="" style="width: 100%;height: 100px;border-radius: 5px;cursor: pointer" @click="navTo(item.id)">
              <div style="color: #333333;margin-top: 10px" class="overflowShow" >{{ item.name }}</div>
            </el-col>
          </el-row>
        </div>
      </div>

      <div style="display: flex;margin-top: 10px ">
        <div style="flex: 1">
          <span style="font-size: 24px;color: #333333; margin-right: 50px">在线资源</span>
        </div>
      </div>
      <div style="display: flex;margin-top: 20px;height: 300px">
        <div style="flex: 2;margin-left:10px">
          <el-row :gutter="20">

            <el-col :span="6" style="margin-bottom: 35px" v-for="item in leftData">
              <img :src="item.img" alt="" style="width: 100%;height: 100px;border-radius: 5px;cursor: pointer" @click="navToinformation(item.id)">
              <div style="color: #333333;margin-top: 10px" class="overflowShow" >{{ item.name }}</div>
            </el-col>
          </el-row>
        </div>
        <div style="flex: 1;margin-left:10px;height: 270px;width: 0">
          <el-carousel height="280px" :interval="3000" arrow="">
            <el-carousel-item v-for="(recommenditem, index) in fileRecommend" :key="index">
              <img :src="recommenditem.img" style="width: 100%;border-radius: 5px;cursor: pointer" @click="navToinformation(recommenditem.id)">
              <div style="font-size: 15px" class="overflowShow" >{{ recommenditem.name }}</div>
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>

    </div>


  </div>
</template>

<script>

export default {

  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      carouselData:[
        require('@/assets/imgs/lun-1.jpg'),
        require('@/assets/imgs/lun-2.jpg'),
        require('@/assets/imgs/lun-3.jpg'),
        require('@/assets/imgs/lun-4.jpg'),
      ],
      type:'',
      recommendList:[],
      rightdata:[],
      fileRecommend:[],
      leftData:[],
      signInData:{}
    }
  },
  mounted() {
    this.loadRecommend()
    this.loadRightdata()
    this.getSign()
    this.getInformation()
    this.getLeftdata()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    getSign(){
      this.$request.get('/signin/selectByUserId?id=' + this.user.id).then(res=>{
        if(res.code ==='200'){
          this.signInData=res.data
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    signin(){
      let data={
        userId:this.user.id
      }
      this.$request.post('/signin/add',data).then(res=>{
        if (res.code === '200'){
        this.$message.success('签到成功，恭喜你获得10个积分')
        this.getSign()
        }else {
          this.$message.error(res.msg)
        }
      }
      )
    },
    navTo(id) {
      if (this.type === 'SCORE'){
        //往积分课程详情页面跳
        location.href='/front/scoreDetail?id='+id
      }else {
        //往课程详情跳
        location.href='/front/courseDetail?id='+id
      }
    },
    navToinformation(id){
        location.href='/front/informationDetail?id='+id
      },

    initValue(type){
      this.type=type;
      this.getData()
    },
    getData(){
      if ( this.type === 'SCORE' ){
        //积分专区这边的数据
        // 1.获取推荐那个积分课程
        this.$request.get('/score/getRecommend').then(res=>{
          if(res.code ==='200'){
            this.recommendList=res.data
          }else {
            this.$message.error(res.msg)
          }
        })
        //2.获取推荐之外最新的8个课程
          this.$request.get('/score/selectTop8').then(res=>{
            if(res.code === '200'){
              this.rightdata=res.data
            }else {
              this.$message.error(res.msg)
            }
          })
      }
      if(this.type === 'VIDEO'){
        // 1.获取推荐那个视频课程
        this.$request.get('/course/getRecommendVideo').then(res=>{
          if(res.code ==='200'){
            this.recommendList=res.data
          }else {
            this.$message.error(res.msg)
          }
        })
        //2.获取推荐之外最新的8个视频课程
      this.$request('/course/selectTop8Video').then(res=>{
        if(res.code ==='200'){
          this.rightdata=res.data
        }
        else {
          this.$message.error(res.msg)
        }
      })}
      if(this.type === 'TEXT'){
        // 1.获取推荐那个图文课程
        this.$request.get('/course/getRecommendText').then(res=>{
          if(res.code ==='200'){
            this.recommendList=res.data
          }else {
            this.$message.error(res.msg)
          }
        })
      //2.获取推荐之外最新的8个图文课程
      this.$request('/course/selectTop8Text').then(res=>{
        if(res.code ==='200'){
          this.rightdata=res.data
        }
        else {
          this.$message.error(res.msg)
        }
      })
      }
    },
    loadRecommend() {
      this.$request.get('/course/getRecommend').then(res => {
        if (res.code === '200') {
          this.recommendList = res.data // 假设后端返回的推荐课程列表在 data 字段中
        }
      })
    },
  loadRightdata() {
    this.$request.get('/course/selectTop8').then(res=>{
      if(res.code === '200'){
        this.rightdata=res.data
      }else {
        this.$message.error(res.msg)
      }
    })
  },

  getInformation() {
    // 1.获取推荐的那个资料
    this.$request.get('/information/getRecommend').then(res=>{
      if (res.code ==='200'){
        this.fileRecommend=res.data
      }else {
        this.$message.error(res.msg)
      }
    })
  },
  getLeftdata() {
    this.$request.get('/information/getLeftdata').then(res=>{
      if (res.code ==='200'){
        this.leftData=res.data
      }else {
        this.$message.error(res.msg)
      }
    })
  }
  }
}
</script>
