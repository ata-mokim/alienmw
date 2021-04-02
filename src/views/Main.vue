<template>
<div>

<br>

<el-row>
  <el-col :span="11"><div class="grid-content bg-purple-light"><h1>일별 물류 입고 건수</h1>  <DailyValueWIn></DailyValueWIn></div></el-col> 
  <el-col :span="11"><div class="grid-content bg-purple-light"><h1>일별 물류 출고 건수</h1>  <DailyValueWOut></DailyValueWOut></div></el-col>
</el-row>

<el-row>
  <el-col :span="11"><div class="grid-content bg-purple-light"><h1>일별 매장 입고 건수</h1> <DailyValueSIn></DailyValueSIn></div></el-col>
  <el-col :span="11"><div class="grid-content bg-purple-light"><h1>일별 매장 출고 건수</h1> <DailyValueSOut></DailyValueSOut></div></el-col>
</el-row>


 </div>
</template>

<script>
  // import LineChart from '@/components/LineChart'
  import DailyValueWIn from '@/components/DailyValueWIn'
  import DailyValueWOut from '@/components/DailyValueWOut'
  import DailyValueSIn from '@/components/DailyValueSIn'
  import DailyValueSOut from '@/components/DailyValueSOut'
 
  export default {
    components: {
      // LineChart,
      DailyValueWOut,
      DailyValueWIn,
      DailyValueSIn,
      DailyValueSOut
    },
  }
</script>
