<template>
<div class="main-content">
<div style="width: 70%;margin: 30px auto;min-height: 1000px">
  <div style="text-align: center">
    <span style="font-size: 20px;font-weight: 550;color: #333333;margin-left: 20px">{{informationData.name}}</span>
  </div>
  <div style="text-align: center;margin-top: 15px">
    <span style="color: #dd2121" v-if="informationData.score> 0 ">{{informationData.score }} /积分</span>
    <span style="color: #12b127" v-else>免费白嫖</span>
    <span style="color: #666666;margin-left: 50px">发布时间：{{informationData.time}}</span>
  </div>
<!--  课程保密区域-->
  <div>
    <div style="font-size: 18px;margin-bottom: 10px">课程资料</div>
    <div v-if="informationData.score === 0 || flag">
      <div style="margin-top: 10px">资料链接：<a :href="informationData.file" target="_blank">{{informationData.file}}</a></div>
    </div>
    <div e-else>
      <span style="color: #dd2121;margin-right: 20px">该课程需要积分，兑换后可解锁</span>
      <el-button type="warning" size="mini" @click="exchange">兑换课程</el-button>
    </div>
  </div>
<!--  课程介绍区域-->
  <div style="margin-top: 20px">
    <div style="font-size: 18px">课程介绍</div>
    <div style="margin-top: 10px" v-html="informationData.content" class="w-e-text w-e-text-container"></div>
  </div>
</div>
</div>
</template>

<script>
import E from 'wangeditor'
export default {
  data() {
    let informationId=this.$route.query.id

    return{
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      informationId:informationId,
      informationData:{},
      flag:false
    }
  },
  mounted() {
    this.loadInformation()
    this.check()
  },
  methods: {
    check(){
      this.$request.get('/fileorder/selectAll',{
        params:{
          userId:this.user.id,
          fileId:this.informationId
        }
      }).then(res=>{
        if (res.code ==='200'){
          if (res.data.length>0){
            this.flag=true
          }else {
            this.$message.error(res.msg)
          }
        }
      })
    },
    exchange(){
      let data={
        fileId:this.informationId,
        userId:this.user.id,
        score:this.informationData.score
      }
      this.$request.post('/fileorder/add',data).then(res=>{
        if(res.code ==='200'){
          this.$message.success('兑换成功')
          this.loadInformation()
          this.check()
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    loadInformation(){
      this.$request.get('/information/selectById/'+this.informationId).then(res=>{
        if (res.code ==='200'){
          this.informationData=res.data
          console.log(this.informationData)
        }else {
          this.$message.error(res.msg)
        }
      })
    }
}}
</script>



