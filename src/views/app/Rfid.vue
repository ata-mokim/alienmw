<template>
  <div id="app">
    <div class="menu-header">
      <h2 class="menu">매장입고</h2>
    </div>
    <div class="center">
      <div class="box">
        <el-card>
          <el-row>
            <el-col :span="24">
            <div class="searchBody">
              <div class="searchBody_brand">
                브랜드
              </div>
              <div>
                <el-select v-model="brandOption" placeholder="Select">
                  <el-option
                      v-for="item in brandOptions"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                  </el-option>
                </el-select>
              </div>

              <div class="searchBody_year">
                스타일
              </div>
              <div>
                <el-input
                    size="mini"
                    placeholder=""
                    v-model="styleOption">
                </el-input>
              </div>
            </div><!-- <div class="searchBody">-->
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
            <div class="searchBody">


              <div class="searchBody_production">
                 일자
              </div>
              <div>
                <el-date-picker
                    id="storeInStartInputDate"
                    v-model="requestStartDateOption"
                    type="date"
                    placeholder="Pick a day"
                    value-format="yyyy-MM-dd"
                >
                </el-date-picker>
                ~
                <el-date-picker
                    id="storeInEndInputDate"
                    v-model="requestEndDateOption"
                    type="date"
                    placeholder="Pick a day"
                    value-format="yyyy-MM-dd"
                >
                </el-date-picker>


              </div>

            </div><!-- <div class="searchBody">-->
            </el-col>
          </el-row>

          <el-button
              :loading="loading"
              type="primary"
              @click="search"
          >조회</el-button>

          <el-row>
            <el-col :span="24">
              <div>
                <RfidRead v-for="RfidRead in RfidReads"  :key="RfidRead.id"
                          :RfidReads="RfidRead"/>
              </div>

            </el-col>
          </el-row>
        </el-card>
      </div>
    </div>
  </div>


</template>

<script>
import RfidRead from '@/components/RfidRead.vue';
import Axios from "axios";

export default {
  created() {
    window.getTag = this.addTodo
    window.reading = this.reading
  },

  components: {
    RfidRead
  },
  data(){
    return {
      show: true,
      RfidReads :[
      ],
    }
  },
  methods:{
    async search() {
      this.loading = true;

      let url = this.$url + '/rfidInfo/storeInGroupList';
      var reqJson = new Object();
      reqJson.brand = this.brandOption;
      reqJson.style = this.styleOption;
      reqJson.factory_name = this.factoryNameOption;
      reqJson.w_out_start_date = '2021-03-26'
      reqJson.w_out_end_date = '2021-04-26'

      await Axios.post(url, reqJson , this.$store.state.config ).then(res => {
        var resDataJson = res.data.storeInGroupListDTO;

        if (res.data.resultCode)
        {
          this.RfidReads = [];
          this.epcs = [];

          for(var resData in resDataJson){
            console.log(resDataJson[resData]['style']);

            this.addTodo(resDataJson[resData]['style'],
                resDataJson[resData]['color'],
                resDataJson[resData]['size'],
                resDataJson[resData]['order_degree'],
                resDataJson[resData]['count']
            );
            this.loading = false;
          }
        }else{
          this.$message.error("no data found");
          this.loading = false;
        }
      }) // Axios
      .catch(error => {
        console.log(error.response)
        this.$message.error(this.$store.state.serverErrorMessage);
        this.loading = false;
      }); // await Axios.post
    }, /// SEARCH ()

    addTodo(style, color, size, orderDegree,  count){
      this.RfidReads.push( {
        id: Math.random(),
        style: style  ,
        color: color,
        size:  size ,
        orderDegree: orderDegree ,
        count: count,
        readCount : 0,
        checked: false
      });
    },

    reading(tag, readEpc){
      console.log(tag)
      console.log(readEpc)
      window.AlienMwApp.playSound('success')
    }, //reading
  }
}
</script>




<style scoped>
.box {
  flex: 1;
  display: flex;
  justify-content: center;
}

.searchBody_brand{
  width:40px !important;
  /* // border: 1px solid #f29659 !important; */
}

.searchBody_production{
  width:40px !important;
  /* // border: 1px solid #f29659 !important; */
  margin-left: 0px !important;
}

.el-row {
  margin-bottom: 2px;
}

</style>
<style lang="scss">

.box .el-card {
  width: 340px;
  height: 100%;
}

#storeInStartInputDate{
  width :100px  !important;
}
</style>
