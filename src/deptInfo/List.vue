<template>
<div>
  
<el-row>
  <el-col :span="24">
    <div class="grid-content bg-purple-light">

      <div class="searchTitle">
       <b> 업체관리  </b>
      </div>

      <!-- 조회 조건 영역 -->
      <div class="searchBody">

         <div class="searchBody_year">
           아이디
          </div>
          <div>
            <el-input
              size="mini"
              placeholder=""
              v-model="deptSeqOption">
            </el-input>
          </div>   


         <div class="searchBody_year">
          부서명  
          </div>
          <div>
            <el-input
              size="mini"
              placeholder=""
              v-model="deptNameOption">
            </el-input>
          </div>  
      </div>





      <!-- 조회 조건 영역 -->
      <div class="searchBody">
        
        <div class="searchBody_brand">
          <el-button
            :loading="loading"
            type="primary"
            @click="search"
          >Search</el-button>
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

    <el-table-column
      property="seq"
      label="seq"
      width="50">
    </el-table-column>

    <el-table-column
      property="dept_seq"
      label="dept_seq"
      width="100">
    </el-table-column>

        <el-table-column
      property="name"
      label="업체명"
      width="200">
    </el-table-column>

    <el-table-column
      label="주소1"
      width="300">

      <template slot-scope="scope">
        <el-input  size="medium" placeholder="Please input" v-model="scope.row.address1"></el-input>
      </template>

    </el-table-column>

   <el-table-column
       label="주소2"
       width="300">

     <template slot-scope="scope">
       <el-input size="medium" v-model="scope.row.address2"></el-input>
     </template>
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
  >저장</el-button>


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

      // this.$store.state.accessToken 
      // this.factoryNameOption = this.$store.state.deptName;
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
        deptSeqOption: '',
        deptNameOption:''

      }
    },//data//

    methods: {
      async search(){
        this.loading = true;

        let url = this.$url + '/DeptInfo/list';
        var reqJson = new Object();

        reqJson.dept_seq = this.deptSeqOption;
        reqJson.name = this.deptNameOption;

        
        await Axios.post(url, reqJson , this.$store.state.config ).then(res => { 
          var resDataJson = res.data.deptInfoDTO
          this.totalData=[];
          if (res.data.resultCode){
            for(var resData in resDataJson){

              this.totalData.push({ 
                  seq : resData
                , dept_seq: resDataJson[resData]['dept_seq']
                , address1: resDataJson[resData]['address1']
                , address2: resDataJson[resData]['address2']
                , business_code:resDataJson[resData]['business_code']
                , company_seq: resDataJson[resData]['company_seq']
                , country: resDataJson[resData]['country']
                , country_code: resDataJson[resData]['country_code']
                , production_code: resDataJson[resData]['production_code']
                , dept_code: resDataJson[resData]['dept_code']
                , dept_create_seq: resDataJson[resData]['dept_create_seq']
                , division: resDataJson[resData]['division']
                , location_type: resDataJson[resData]['location_type']
                , manager: resDataJson[resData]['manager']
                , manager_tel_no: resDataJson[resData]['manager_tel_no']
                , name: resDataJson[resData]['name']
                , tel_no: resDataJson[resData]['tel_no']
                , type: resDataJson[resData]['type']
                , use_yn: resDataJson[resData]['use_yn']
                , zip_code: resDataJson[resData]['zip_code']
              });
            }
            // 전체 데이터 사이즈 
            this.totalSize = this.totalData.length;
            // 보여주는 부분만 slice 
            this.tableData = this.totalData.slice(0, this.pageSize);
            this.currentPage = 1;
            this.loading = false;
            this.$message.success(this.$store.state.searchSuccess);
          }else {
            this.$message.error(this.$store.state.dataNotFound);
            this.currentPage = 1;
            this.loading = false;
          }
        }) // await Axios.post
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
          this.$message.error(this.$store.state.noDataSelect);
          return ;
        }

        this.loading = true;
        let url = "";
        url = this.$url + '/DeptInfo/update';
        // const json = { 'user_id': this.$store.state.userId };

        for(var resData in this.multipleSelection){
          this.multipleSelection[resData]['reg_user_id'] = this.$store.state.userId
          this.multipleSelection[resData]['upd_user_id'] = this.$store.state.userId
        }

        Axios.post(url, 
                      this.multipleSelection                  
                    , this.$store.state.config ).then(res => { 
          
          // console.log("=========================Axios.pos.SAVE================================" );
          // console.log(res.data);

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

    }, //methods
  } // export default
</script>

<style lang="scss">
.el-input--medium .el-input__inner {

  width: 280px !important;
}
</style>