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
    <div v-if="scoreData.price === 0 || flag">
      <video :src ="scoreData.video" v-if="scoreData.type ==='VIDEO'"controls style="width: 65%;height: 400px"></video>
      <div style="margin-top: 10px">资料链接：<a :href="scoreData.file" target="_blank">{{scoreData.file}}</a></div>
    </div>
    <div e-else>
      <span style="color: #dd2121;margin-right: 20px">该课程需要积分，兑换后可解锁</span>
      <el-button type="warning" size="mini" @click="exchange">兑换课程</el-button>
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
import {post} from "axios";
export default {
  data() {
    let scoreId=this.$route.query.id

    return{
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      scoreId:scoreId,
      scoreData:{},
      flag:false
    }
  },
  mounted() {
    this.loadScore()
    this.checkOrder()
  },
  methods: {
    checkOrder(){
      this.$request.get('/scoreorder/selectAll',{
        params:{
          userId:this.user.id,
          scoreId:this.scoreId
        }
      }).then(res=>{
        if (res.code==='200'){
          if (res.data.length>0){
            this.flag=true
          }
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    exchange(){
      let data={
        scoreId:this.scoreId,
        score:this.scoreData.price,
        userId:this.user.id
      }
        this.$request.post('/scoreorder/add',data).then(res=>{
          if (res.code ==='200'){
            this.$message.success('兑换成功')
            this.loadScore()
            this.checkOrder()
          }else {
            this.$message.error(res.msg)
          }
        })

    },
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



