<template>
<div class="main-content">
<div style="width: 70%;margin: 30px auto;min-height: 1000px">
  <div style="text-align: center">
    <el-button type="success" >{{scoreData.type === 'VIDEO'?'视频课' :'图片课 '}}</el-button>
    <span style="font-size: 20px;font-weight: 550;color: #333333;margin-left: 20px">{{scoreData.name}}</span>
  </div>
  <div style="text-align: center;margin-top: 15px">
    <span style="color: #dd2121" v-if="scoreData.price>0">{{scoreData.price }} /积分
    </span>
    <span style="color: #666666;margin-left: 50px">发布时间：{{scoreData.time}}</span>
  </div>
<!--  课程保密区域-->
  <div>
    <div style="font-size: 18px;margin-bottom: 10px">课程资料</div>
    <div v-if="scoreData.price === 0">
      <video :src ="scoreData.video" v-if="scoreData.type ==='VIDEO'"controls style="width: 65%;height: 400px"></video>
      <div style="margin-top: 10px">资料链接：<a :href="scoreData.file" target="_blank">{{scoreData.file}}</a></div>
    </div>
    <div e-else>
      <span style="color: #dd2121;margin-right: 20px">该课程为付费课程，购买后可解锁</span>
      <el-button type="warning" size="mini">购买课程</el-button>
    </div>
  </div>
<!--  课程介绍区域-->
  <div style="margin-top: 20px">
    <div style="font-size: 18px">课程介绍</div>
    <div style="margin-top: 10px" v-html="scoreData.content" class="w-e-text w-e-text-container"></div>
  </div>
</div>
</div>
</template>

<script>
import E from 'wangeditor'
export default {
  data() {
    let scoreId=this.$route.query.id

    return{
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      scoreId:scoreId,
      scoreData:{}
    }
  },
  mounted() {
    this.loadScore()

  },
  methods: {
    loadScore(){
      this.$request.get('/score/selectById/'+this.scoreId).then(res=>{
        if (res.code ==='200'){
          this.scoreData=res.data
          console.log(this.scoreData)
        }else {
          this.$message.error(res.msg)
        }
      })
    }
}}
</script>



