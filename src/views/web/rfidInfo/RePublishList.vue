<template>

  <div>
    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple-light">

          <div class="searchTitle">
            <b> RFID 재 발행 </b>
          </div>

          <!-- 조회 조건 영역 -->
          <div class="searchBody">
            <div class="searchBody_production">
              요청
            </div>
            <div>
              <el-input
                  size="mini"
                  placeholder=""
                  v-model="deptNameOption">
              </el-input>
            </div>
          </div>


          <div class="searchBody">

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


            <div class="searchBody_year">
              컬러
            </div>
            <div>
              <el-input
                  size="mini"
                  placeholder=""
                  v-model="colorOption">
              </el-input>
            </div>


            <div class="searchBody_year">
              사이즈
            </div>
            <div>
              <el-input
                  size="mini"
                  placeholder=""
                  v-model="sizeOption">
              </el-input>
            </div>

            <div class="searchBody_year">
              생산차수
            </div>
            <div>
              <el-input
                  size="mini"
                  placeholder=""
                  v-model="orderDegreeOption">
              </el-input>
            </div>

            <div class="searchBody_year">
              일련번호
            </div>
            <div>
              <el-input
                  size="mini"
                  placeholder=""
                  v-model="publishSeqOption">
              </el-input>
            </div>

          </div>

          <div class="searchBody">
            <div class="searchBody_year">
              진행상태
            </div>
            <div>
              <el-select v-model="statusOption" placeholder="Select">
                <el-option
                    v-for="item in statusOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </div>
          </div>

          <!-- 조회 조건 영역 -->
          <div class="searchBody">

            <div class="searchBody_brand">
              <el-button
                  :loading="loading"
                  type="primary"
                  @click="search"
              >조회</el-button>
            </div>
          </div>

        </div> <!-- div class="grid-content bg-purple-light"> -->
      </el-col>

    </el-row>





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
        :header-cell-style="{background:'#f0f9eb',color:'#606266', height:'10px', padding:'0px'}"
    >
      <el-table-column
          type="selection"
          width="35"
      >
      </el-table-column>

      <!-- data 시작 -->
      <!-- <el-table-column
        label="Date"
        width="120">
        <template slot-scope="scope">{{ scope.row.date }}</template>
      </el-table-column> -->

<!--      <el-table-column-->
<!--          property="seq"-->
<!--          label="seq"-->
<!--          width="50">-->
<!--      </el-table-column>-->

      <el-table-column
          property="epc"
          label="epc"
          width="200">
      </el-table-column>


      <el-table-column
          property="style"
          label="스타일"
          width="100">
      </el-table-column>

      <el-table-column
          property="color"
          label="컬러"
          width="50">
      </el-table-column>

      <el-table-column
          property="size"
          label="사이즈"
          width="60">
      </el-table-column>


      <el-table-column
          property="order_degree"
          label="생산차수"
          width="65">
      </el-table-column>

      <el-table-column
          property="publish_seq"
          label="일련 번호"
          width="65">
      </el-table-column>

      <el-table-column
          property="dept_name"
          label="요청"
          width="150">
      </el-table-column>
    </el-table>

    <el-row>
      <el-col :span="24"><div class="pagination"  >

        <el-pagination
            small

            layout="prev, pager, next"
            :total="totalSize"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-size="pageSize"
        >
        </el-pagination>

        <el-button
            :loading="loading"
            type="primary"
            @click="save"
        >발행</el-button>

        <el-button
            :loading="loading"
            type="primary"
            @click="remove"
        >삭제 </el-button>
      </div>
      </el-col>
    </el-row>
  </div>
</template>


<script>
import Axios from 'axios';  

  export default {
    created() {
      this.$store.commit("dayBefore",0);
      this.requestEndDateOption = this.$store.state.dateOption; // 조회조건에서 현재 날짜 
      this.$store.commit("dayBefore",7);
      this.requestStartDateOption = this.$store.state.dateOption; // 조회조건이서 현재보다 -7 일

      if (this.$store.state.userId != 'admin') {
        this.deptSeqOption = this.$store.state.deptSeq;
        this.deptNameOption = this.$store.state.deptName;
      }
    },//created
    data() {
      return {

        // table 관련 data 
        tableData: [],
        totalData: [],
        multipleSelection: [],
        currentPage: 1,
        pageSize:10,
        totalSize:0,

        // loading 변수 true 이면 loging 화면 보여줌
        loading:false,

        // 조회조건 관련 변수
        statusOptions: [{
          value: '0',
          label: '요청'
        }, {
          value: '1',
          label: '발행'
        }, ],

        styleOption :'',
        colorOption :'',
        sizeOption :'',
        orderDegreeOption :'',
        publishSeqOption : '',
        deptNameOption:'',
        deptSeqOption:0,
        requestStartDateOption:'',
        requestEndDateOption:'',
        statusOption:'0',
        epcOption:'',
        seq:0,
      }
    },

    methods: {

      async search(){

        this.loading = true;
        let url = this.$url + '/RePublishInfo/getList';

        var reqJson = new Object();

        reqJson.style = this.styleOption;
        reqJson.color = this.colorOption;
        reqJson.size = this.sizeOption;
        reqJson.order_degree = this.orderDegreeOption;
        reqJson.publish_seq = this.publishSeqOption;
        //reqJson.dept_seq = this.deptSeqOption;
        reqJson.dept_name = this.deptNameOption;
        if (this.statusOption == null || this.statusOption == '' )
        {
          reqJson.status = '0';
        }else {
          reqJson.status = this.statusOption;
        }

        reqJson.epc = this.epcOption; // 입고 조회 

        await Axios.post(url, reqJson , this.$store.state.config  ).then(res => { 
          var resDataJson = res.data.rePublishInfoDTO ;
          this.totalData=[];
          console.log ( resDataJson) ;

          if (res.data.resultCode){
            for(var resData in resDataJson){
              this.totalData.push({
                re_publish_info_seq : resDataJson[resData]['re_publish_info_seq']
                , epc: resDataJson[resData]['epc']
                , style: resDataJson[resData]['style']
                , color: resDataJson[resData]['color']
                , size : resDataJson[resData]['size']
                , order_degree : resDataJson[resData]['order_degree']
                , publish_seq : resDataJson[resData]['publish_seq']
                , dept_seq : resDataJson[resData]['dept_seq']
                , dept_name : resDataJson[resData]['dept_name']
              });
            }


            // 전체 데이터 사이즈 
            this.totalSize = this.totalData.length;
            // 보여주는 부분만 slice 
            this.tableData = this.totalData.slice(0, this.pageSize);
            this.currentPage = 1;
            this.loading = false;
          }else{
            this.$message.error(this.$store.state.dataNotFound);
            this.currentPage = 1;
            this.loading = false;
          }
        })//await Axios.post
        .catch(error => {
          console.log(error.response)
          this.$message.error(this.$store.state.serverErrorMessage);
          this.currentPage = 1;
          this.loading = false;
        }); // await Axios.post
      },//search

      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      }, //toggleSelection

      // check box click event 
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


      save(){

        if (this.multipleSelection.length <= 0)
        {
          this.$message.error("데이터를 선택하세요 ");
          return ;
        }


        for(var resData in this.multipleSelection){
          this.multipleSelection[resData]['status'] = '1'
          this.multipleSelection[resData]['reg_user_id'] = this.$store.state.userId
          this.multipleSelection[resData]['upd_user_id'] = this.$store.state.userId
        }

        this.loading = true;
        let url = this.$url + '/RePublishInfo/statusUpdate';

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

      remove(){
        if (this.multipleSelection.length <= 0)
        {
          this.$message.error("데이터를 선택하세요 ");
          return ;
        }
        this.loading = true;
        let url = "";
        url = this.$url + '/RePublishInfo/remove';
        Axios.post(url, this.multipleSelection , this.$store.state.config  ).then(res => {
          console.log(res );
          let saveCount = res.data;
          this.loading = false;
          this.$message.success(saveCount +'건 삭제 되었습니다.');
          this.search();
        }).catch(error => {
          console.log(error.response)
          this.$message.error(this.$store.state.serverErrorMessage);
          this.currentPage = 1;
          this.loading = false;
        }); // await Axios.post
      }, // remove

    }, //methods
  }
</script>


<style>
#epcInput {
   height: 20px !important;
   width : 200px  !important;
   border: 1px solid #f29659 !important;
   border-radius: 4px !important;
   box-sizing: border-box !important;
   /* // font-family: Segoe UI,Malgun Gothic !important;
   // font-weight: 400 !important; */
   font-size: 9px !important;
   cursor: pointer !important;
   padding: 0 6px !important;

}
</style>