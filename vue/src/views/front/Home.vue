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
      <el-button type="primary">视频课程</el-button>
      <el-button type="success">积分专区</el-button>
      <el-button type="warning">图文课程</el-button>
    </div>
    <div style="width: 300px">
      <el-button type="info">签到</el-button>
      <span style="margin-left: 30px;color:#12b127;font-weight: 550">上次签到时间：</span>
      <span style="color: #666666">21点15分</span>
    </div>
  </div>
    <div style="display: flex;margin-top: 20px;height: 300px">
      <div style="flex: 1;margin-right:10px;height: 270px;width: 0">
        <img :src="recommend.img" style="width: 100%;border-radius: 5px">
        <div style="margin-top: 5px;font-size: 15px" class="overflowShow">{{ recommend.name }}</div>
      </div>

      <div style="flex: 2;margin-left:10px">
        <el-row :gutter="20">
          <el-col :span="6" style="margin-bottom: 35px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
          <el-col :span="6" style="margin-bottom: 35px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
          <el-col :span="6" style="margin-bottom: 35px">
          <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
          <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
        </el-col>
          <el-col :span="6" style="margin-bottom: 35px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
          <el-col :span="6" style="margin-bottom: 35px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
          <el-col :span="6" style="margin-bottom: 35px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
          <el-col :span="6" style="margin-bottom: 35px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
          <el-col :span="6" style="margin-bottom: 35px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
        </el-row>
      </div>
    </div>

    <div style="display: flex;margin-top: 50px ">

      <div style="flex: 1">
        <span style="font-size: 24px;color: #333333; margin-right: 50px">在线资源</span>
      </div>
    </div>
    <div style="display: flex;margin-top: 20px;height: 300px">


      <div style="flex: 2;margin-left:10px">
        <el-row :gutter="20">
          <el-col :span="6" style="margin-bottom: 25px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
          <el-col :span="6" style="margin-bottom: 25px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
          <el-col :span="6" style="margin-bottom: 25px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
          <el-col :span="6" style="margin-bottom: 25px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
          <el-col :span="6" style="margin-bottom: 25px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
          <el-col :span="6" style="margin-bottom:25px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
          <el-col :span="6" style="margin-bottom: 25px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
          <el-col :span="6" style="margin-bottom: 25px">
            <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" alt="" style="width: 100%;height: 100px;border-radius: 5px">
            <div style="color: #333333;margin-top: 10px" class="overflowShow" >JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
          </el-col>
        </el-row>
      </div>
      <div style="flex: 1;margin-left:10px;height: 270px;width: 0">
        <img src="@/assets/imgs/JavaWeb实战学习课程.jpeg" style="width: 100%;border-radius: 5px">
        <div style="margin-top: 5px;font-size: 15px" class="overflowShow">JavaWeb实战学习课程,基于Spring Boot，从网站搭建开发</div>
      </div>
    </div>

  </div>


  </div>
</template>

<script>

export default {

  data() {
    return {
      carouselData:[
          require('@/assets/imgs/lun-1.jpg'),
          require('@/assets/imgs/lun-2.jpg'),
          require('@/assets/imgs/lun-3.jpg'),
          require('@/assets/imgs/lun-4.jpg'),
      ],
      recommend:{}
    }
  },
  mounted() {
    this.loadRecommend()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
  loadRecommend() {
    this.$request.get('/course/getRecommend').then(res=>{
      if(res.code === '200'){
        this.recommend=res.data
      }else {
        this.$message.error(res.meg)
      }
    })
  }
}}
</script>
