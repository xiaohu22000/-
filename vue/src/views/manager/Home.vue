<template>
  <div>
    <div class="card" style="padding: 15px">
      您好，{{ user?.name }}！欢迎使用本系统
    </div>

    <div style="display: flex; margin: 10px 0">
      <div style="width: 50%;" class="card">
        <div style="margin-bottom: 30px; font-size: 20px; font-weight: bold">公告列表</div>
        <div >
          <el-timeline  reverse slot="reference">
            <el-timeline-item v-for="item in notices" :key="item.id" :timestamp="item.time">
              <el-popover
                  placement="right"
                  width="200"
                  trigger="hover"
                  :content="item.content">
                <span slot="reference">{{ item.title }}</span>
              </el-popover>
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>
      <div style="width: 50%;height: 300px" class="card" id="coursePie"></div>
    </div>
    <div style="display: flex;margin: 10px 0">
    <div style="width: 50%;height: 300px" class="card" id="userPie"></div>
<!--    <div style="width: 50%;height: 300px" class="card" id="bar1" ></div>-->
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts";
let pieCourseptions = {
  title: {
    text: '',
    subtext: '',
    left: 'center'
  },
  tooltip: {
    trigger: 'item',
    formatter:'{a} <br/>{b} : {c} ({d}%'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: '',
      type: 'pie',
      radius: '50%',
      center:['50%','60%'],
      data: [
        { value: 1048, name: 'Search Engine' },
        { value: 735, name: 'Direct' },
        { value: 580, name: 'Email' },
        { value: 484, name: 'Union Ads' },
        { value: 300, name: 'Video Ads' }
      ]
    }
  ]
};
let pieUserOptions = {
  title: {
    text: 'Referer of a Website',
    subtext: 'Fake Data',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: 'Access From',
      type: 'pie',
      radius: '50%',
      data: [
        { value: 1048, name: 'Search Engine' },
        { value: 735, name: 'Direct' },
        { value: 580, name: 'Email' },
        { value: 484, name: 'Union Ads' },
        { value: 300, name: 'Video Ads' }
      ],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
};
let bar = {
  title:{
    text:'',
    subtext:'',
    left:'center'
  },
  xAxis: {
    type: 'category',
    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
  },
  yAxis: {
    type: 'value'
  },
    tooltip:{
    trigger:'item'
    },
  series: [
    {
      data: [120, 200, 150, 80, 70, 110, 130],
      type: 'bar',
      itemStyle:{
        normal:{
          color:function (){return '#'+Math.floor(Math.random()*(256*256*256-1)).toString(16);}
        }
      }
    }
  ]
};
export default {
  name: 'Home',
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      notices: []
    }
  },
  created() {
    this.$request.get('/notice/selectAll').then(res => {
      this.notices = res.data || []
    })
    this.loadCourseOption()
    this.loadUserOption()
    // this.loadBar()
  },
  methods:{
    loadBar(){
      this.$request.get('/getBar').then(res=>{
        if (res.code ==='200'){
          let chartDom=document.getElementById('bar1');
          let myChart=echarts.init(chartDom);
          bar.title.text=res.data.text
          bar.title.subtext=res.data.subtext
          bar.xAxis.data=res.data.xAxis
          bar.series[0].data=res.data.yAxis
          myChart.setOption(bar)
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    loadUserOption(){
      this.$request.get('/user/getPie').then(res=>{
        if (res.code ==='200'){
          let chartDom=document.getElementById('userPie');
          let myChart=echarts.init(chartDom);
          pieUserOptions.title.text=res.data.text
          pieUserOptions.title.subtext=res.data.subtext
          pieUserOptions.series[0].name=res.data.name
          pieUserOptions.series[0].data=res.data.data
          myChart.setOption(pieUserOptions)
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    loadCourseOption(){
      this.$request.get('/orders/getPie').then(res=>{
        if (res.code ==='200'){
          let chartDom=document.getElementById('coursePie');
          let myChart=echarts.init(chartDom);
           pieCourseptions.title.text=res.data.text
           pieCourseptions.title.subtext=res.data.subtext
           pieCourseptions.series[0].name=res.data.name
           pieCourseptions.series[0].data=res.data.data
           myChart.setOption(pieCourseptions)
        }else {
          this.$message.error(res.msg)
        }
      })
    },
  }
}
</script>
