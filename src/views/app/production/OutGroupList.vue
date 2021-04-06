<template>
  <div id="app">
    <div class="menu-header">
    제품 출고 리스트
    </div>
    <div class="center">
      <div class="box">
        <el-card>

          <el-row>
            <el-col :span="24">
              <div class="searchBody">
              <el-button
                  :loading="loading"
                  type="primary"
                  @click="search"
              >조회</el-button>
              </div>
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
                      width="30"
                  >
                  </el-table-column>

                  <el-table-column
                      property="w_in_box_barcode"
                      label="BARCODE"
                      width="150">
                  </el-table-column>

                  <el-table-column
                      property="style"
                      label="style"
                      width="90">
                  </el-table-column>

                  <el-table-column
                      property="amount"
                      label="수량"
                      width="50">
                  </el-table-column>

                </el-table>

              </div>

            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <div class="searchBody">

                <div>
                  <el-button
                      :loading="loading"
                      type="primary"
                      @click="remove"
                  >삭제</el-button>
                </div>

              <div>
              <el-button
                  :loading="loading"
                  type="primary"
                  @click="save"
              >출고완료</el-button>
              </div>

              </div>
            </el-col>
          </el-row>

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
      epc:1234567890,
      brandOption: '',
      styleOption :'',
      deptNameOption:'',
      publishAmmount:'',
      rfidTakeAmount:'',
      readMode:'1',
      barcode:'',
      seq:0,
    }
  },
  methods:{

    async search(){
      this.loading = true;

      this.loading = true;
      let url = this.$url + '/ProductionOutInfo/getGroupList';

      var reqJson = new Object();
      reqJson.dept_seq = this.$store.state.deptSeq;

      await Axios.post(url, reqJson , this.$store.state.config  ).then(res => {
        var resDataJson = res.data.productionOutInfoDTO ;
        this.totalData=[];

        console.log (resDataJson);

        if (res.data.resultCode){
          for(var resData in resDataJson){
            this.totalData.push({
              seq : resData
              , w_in_box_barcode: resDataJson[resData]['w_in_box_barcode']
              , amount: resDataJson[resData]['amount']
              , style :resDataJson[resData]['style']
              , reg_user_id :resDataJson[resData]['reg_user_id']
              , upd_user_id :resDataJson[resData]['upd_user_id']
              , dept_seq :resDataJson[resData]['dept_seq']
            });

          }

          this.tableData = this.totalData;
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
        this.multipleSelection[resData]['dept_seq'] = this.$store.state.deptSeq
      }

      this.loading = true;
      let url = this.$url + '/ProductionOutInfo/remove';

      Axios.post(url, this.multipleSelection , this.$store.state.config  ).then(res => {

        console.log(res );
        let saveCount = res.data;
        this.loading = false;
        this.$message.success(saveCount +'건 ' + this.$store.state.saveSuccess);
        this.remove();
        this.barcode = '';
        this.search();

      }).catch(error => {
        console.log(error.response)
        this.$message.error(this.$store.state.serverErrorMessage);
        this.currentPage = 1;
        this.loading = false;
      }); // await Axios.post
    }, // save


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

    remove(){
        if (this.multipleSelection.length <= 0)
        {
          this.$message.error("데이터를 선택하세요 ");
          return ;
        }

      for(var resData in this.multipleSelection) {

        for(var tableData in this.tableData) {

          if (this.multipleSelection[resData]['seq'] == this.tableData[tableData]['seq'] )
          {
            this.tableData.splice(tableData, 1);
          }
        }

        this.saveData = [];

      }

    }, // remove
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
  width:60px !important;
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

.menu-header {
  padding: 10px 20px;
  font-size: 15px !important;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
