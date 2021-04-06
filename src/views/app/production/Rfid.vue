<template>
  <div id="app">
    <div class="menu-header">
      <h2 class="menu">RFID 입고</h2>
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

          <el-row>
            <el-col :span="24">
              <div class="searchBody">
              <el-button
                  :loading="loading"
                  type="primary"
                  @click="search"
              >조회</el-button>
              </div>

              <div class="searchBody">
              <div class="searchBody_publish_amount">
                발행수량
              </div>
              <div>
                <el-input
                    id="input_publish_amount"
                    size="mini"
                    placeholder=""
                    v-model="publishAmmount">
                </el-input>
              </div>



                <div class="searchBody_rfid_take_amount">
                  인수수량
                </div>
                <div>
                  <el-input
                      id="input_rfid_take_amount"
                      size="mini"
                      placeholder=""
                      v-model="rfidTakeAmount">
                  </el-input>
                </div>
              </div><!-- <div class="searchBody">-->


            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <div>
<!--                <RfidRead v-for="RfidRead in RfidReads"  :key="RfidRead.id"-->
<!--                          :RfidReads="RfidRead"/>-->

                <el-table
                    v-loading="loading"
                    ref="multipleTable"
                    :data="tableData"
                    style="width: 100%; height:372px"
                    border
                    @selection-change="handleSelectionChange"
                    :row-class-name="tableRowClassName"
                    size="small"
                    :row-style="{height:0+'px'}"
                    :cell-style="{padding:0+'px'}"
                    :header-cell-style="{background:'#f0f9eb',color:'#606266',  height:'10px', padding:'0px'}"
                >
                  <el-table-column
                      type="selection"
                      width="25"
                  >
                  </el-table-column>

                  <el-table-column
                      property="style"
                      label="style"
                      width="92">
                  </el-table-column>

                  <el-table-column
                      property="color"
                      label="color"
                      width="50">
                  </el-table-column>

                  <el-table-column
                      property="size"
                      label="size"
                      width="40">
                  </el-table-column>

                  <el-table-column
                      property="order_degree"
                      label="차수"
                      width="50">
                  </el-table-column>

                  <el-table-column
                      property="publish_amount"
                      label="발행수량"
                      width="80">
                  </el-table-column>


                </el-table>

              </div>

            </el-col>
          </el-row>

          <div class="searchBody">
          <el-button
              :loading="loading"
              type="primary"
              @click="save"
          >인수확정</el-button>
          </div>

        </el-card>
      </div>
    </div>
  </div>


</template>

<script>
// import RfidRead from '@/views/components/RfidRead.vue';
import Axios from "axios";

export default {
  created() {
    window.getTag = this.addTodo
    window.reading = this.reading

    this.$store.commit("dayBefore",0);
    this.requestEndDateOption = this.$store.state.dateOption; // 조회조건에서 현재 날짜
    this.$store.commit("dayBefore",7);
    this.requestStartDateOption = this.$store.state.dateOption; // 조회조건이서 현재보다 -7 일

    if (this.$store.state.userId != 'admin')
      this.deptNameOption = this.$store.state.deptName;


  },

  components: {
    // RfidRead
  },
  data(){
    return {
      show: true,
      RfidReads :[
      ],
      tableData: [],
      totalData: [],
      multipleSelection: [],
      brandOption: '',
      styleOption :'',
      deptNameOption:'',
      publishAmmount:'',
      rfidTakeAmount:0,

    }
  },
  methods:{
    // async search() {
    //   this.loading = true;
    //
    //   let url = this.$url + '/rfidInfo/storeInGroupList';
    //   var reqJson = new Object();
    //   reqJson.brand = this.brandOption;
    //   reqJson.style = this.styleOption;
    //   reqJson.dept_name = this.deptNameOption;
    //   reqJson.w_out_start_date = '2021-03-26'
    //   reqJson.w_out_end_date = '2021-04-26'
    //
    //   await Axios.post(url, reqJson , this.$store.state.config ).then(res => {
    //     var resDataJson = res.data.storeInGroupListDTO;
    //
    //     if (res.data.resultCode)
    //     {
    //       this.RfidReads = [];
    //       this.epcs = [];
    //
    //       for(var resData in resDataJson){
    //         console.log(resDataJson[resData]['style']);
    //
    //         this.addTodo(resDataJson[resData]['style'],
    //             resDataJson[resData]['color'],
    //             resDataJson[resData]['size'],
    //             resDataJson[resData]['order_degree'],
    //             resDataJson[resData]['count']
    //         );
    //         this.loading = false;
    //       }
    //     }else{
    //       this.$message.error("no data found");
    //       this.loading = false;
    //     }
    //   }) // Axios
    //   .catch(error => {
    //     console.log(error.response)
    //     this.$message.error(this.$store.state.serverErrorMessage);
    //     this.loading = false;
    //   }); // await Axios.post
    // }, /// SEARCH ()


    async search(){
      this.loading = true;

      this.loading = true;
      let url = this.$url + '/rfidOrder/list';

      var reqJson = new Object();
      reqJson.brand = this.brandOption;
      reqJson.style = this.styleOption;
      reqJson.dept_name = this.deptNameOption;

      reqJson.request_start_date = this.requestStartDateOption;
      reqJson.request_end_date = this.requestEndDateOption;
      reqJson.status = '4';

      await Axios.post(url, reqJson , this.$store.state.config  ).then(res => {
        var resDataJson = res.data.rfidOrderDTO;
        this.totalData=[];

        console.log (resDataJson);

        if (res.data.resultCode){
          for(var resData in resDataJson){
            this.totalData.push({
              seq : resData
              , rfid_order_seq: resDataJson[resData]['rfid_order_seq']
              , style: resDataJson[resData]['style']
              , color: resDataJson[resData]['color']
              , size: resDataJson[resData]['size']
              , order_degree: resDataJson[resData]['order_degree']
              , brand: resDataJson[resData]['brand']
              , product_amount: resDataJson[resData]['product_amount']
              , rfid_order_amount: resDataJson[resData]['rfid_order_amount']
              , publish_amount : resDataJson[resData]['publish_amount']
              , publish_date : resDataJson[resData]['publish_date']
              , rfid_take_amount: resDataJson[resData]['publish_amount'] // 조회시에는 인수수량에 발행수량을 넣어준다.
              , request_date: resDataJson[resData]['request_date']
              , dept_name: resDataJson[resData]['dept_name']
              , take_date: resDataJson[resData]['take_date']
              , address1: resDataJson[resData]['address1']
              , address2: resDataJson[resData]['address2']
              , rfid_get_order_seq: resDataJson[resData]['rfid_get_order_seq']
              , publish_start_number: resDataJson[resData]['publish_start_number']
              , publish_end_number: resDataJson[resData]['publish_end_number']
              , status: resDataJson[resData]['status']
            });

            this.publishAmmount = this.publishAmmount + resDataJson[resData]['publish_amount'];
          }

          this.rfidTakeAmount = 0;
          this.tableData = this.totalData;

          // 전체 데이터 사이즈
          // this.totalSize = this.totalData.length;
          // 보여주는 부분만 slice
          // this.tableData = this.totalData.slice(0, this.pageSize);
          // this.currentPage = 1;
          // this.$message.error(this.totalData.length);
          this.loading = false;
        }else{
          this.$message.error(this.$store.state.dataNotFound);
          // this.currentPage = 1;
          this.loading = false;
        }
      })//await Axios.post
          .catch(error => {
            console.log(error.response)
            this.$message.error(this.$store.state.serverErrorMessage);
            // this.currentPage = 1;
            this.loading = false;
          }); // await Axios.post
    },//search


    save(){

      if (this.multipleSelection.length <= 0)
      {
        this.$message.error("데이터를 선택하세요 ");
        return ;
      }

      for(var resData in this.multipleSelection){
        this.multipleSelection[resData]['reg_user_id'] = this.$store.state.userId
        this.multipleSelection[resData]['upd_user_id'] = this.$store.state.userId
      }

      this.loading = true;
      let url = this.$url + '/rfidOrder/rfidTakeComplete';

      Axios.post(url, this.multipleSelection , this.$store.state.config  ).then(res => {

        console.log(res );
        let saveCount = res.data;
        this.loading = false;
        this.$message.success(saveCount +'건 ' + this.$store.state.saveSuccess);
        this.search();
      }).catch(error => {
        console.log(error.response)
        this.$message.error(this.$store.state.serverErrorMessage);
        this.currentPage = 1;
        this.loading = false;
      }); // await Axios.post
    }, // save

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


      if(this.publishAmmount > this.rfidTakeAmount){
        this.rfidTakeAmount = this.rfidTakeAmount + 1;
        window.AlienMwApp.playSound('success')
      }
    }, //reading
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        //  return 'warning-row';
      } else if (rowIndex === 3) {
        //  return 'success-row';
      }
      console.log(row);
      return '';
    },
    handleSizeChange(val) {
      console.log(`${val} items per page`);
    },

    // pagging event
    handleCurrentChange(val) {
      this.tableData = this.totalData.slice( (this.pageSize * val - this.pageSize )   , this.pageSize * val);
    },
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
.searchBody_publish_amount{
  margin-left: 0px !important;
  margin-right: 10px !important;
}

#input_publish_amount{
  width:50px !important;
}

#input_rfid_take_amount{
  width:50px !important;
}

.searchBody_rfid_take_amount{
  margin-left: 10px !important;
  margin-right: 10px !important;
}
</style>
