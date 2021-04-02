<template>
<div>
  
<el-row>
  <el-col :span="24">
    <div class="grid-content bg-purple-light">

      <div class="searchTitle">
       <b> 회원관리  </b>
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
              v-model="userIdOption">
            </el-input>
          </div>   

                  
        <div class="searchBody_year">
          이름  
          </div>
          <div>
            <el-input
              size="mini"
              placeholder=""
              v-model="userNameOption">
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
      property="user_seq"
      label="seq"
      width="50">
    </el-table-column>

    <el-table-column
      property="user_id"
      label="아이디"
      width="100">
    </el-table-column>

        <el-table-column
      property="user_name"
      label="이름"
      width="100">
    </el-table-column>



    <el-table-column
      property="last_login_date"
      label="마지막로그인날짜"
      width="150">
    </el-table-column>

     <el-table-column
      property="login_fail_count"
      label="로그인실패 횟수"
      width="100">
    </el-table-column>

   <el-table-column
      property="dept_name"
      label="부서명"
      width="150">
    </el-table-column>


    <el-table-column
          label="권한"
      width="300">

  <template slot-scope="scope">
    <el-radio v-model="scope.row.accessToken" label="1">생산처</el-radio>
    <el-radio v-model="scope.row.accessToken" label="2">발행처</el-radio>
    <el-radio v-model="scope.row.accessToken" label="100">전체</el-radio>
  </template>

    </el-table-column>


    <el-table-column
      label="사용여부"
      width="100">
  <template slot-scope="scope">
    <el-checkbox v-model="scope.row.use_yn">사용여부</el-checkbox>
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

    <el-button
    :loading="loading"
    type="primary"
    @click="initPwd"
  >비밀번호초기화</el-button>
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
        userIdOption: '',
        userNameOption :'',
        deptNameOption :''
      }
    },//data//

    methods: {
      async search(){
        this.loading = true;

        let url = this.$url + '/UserInfo/list';
        var reqJson = new Object();

        reqJson.user_id = this.userIdOption;
        reqJson.user_name = this.userNameOption;
        reqJson.dept_name = this.deptNameOption;

        
        await Axios.post(url, reqJson , this.$store.state.config ).then(res => { 
          var resDataJson = res.data.userInfoDTO
          this.totalData=[];
          if (res.data.resultCode){
            for(var resData in resDataJson){

                if (resDataJson[resData]['use_yn']=='Y')
                {
                  resDataJson[resData]['use_yn'] = true
                }else{
                  resDataJson[resData]['use_yn'] = false
                }

              this.totalData.push({ 
                  seq : resData
                , user_seq: resDataJson[resData]['user_seq']
                , user_id: resDataJson[resData]['user_id'] 
                , last_login_date: resDataJson[resData]['last_login_date']
                //resDataJson[resData]['use_yn']
                , use_yn:resDataJson[resData]['use_yn']
                , login_fail_count: resDataJson[resData]['login_fail_count'] 
                , user_name: resDataJson[resData]['user_name']
                , dept_seq: resDataJson[resData]['dept_seq']
                , accessToken: resDataJson[resData]['accessToken']
                , dept_name: resDataJson[resData]['dept_name']
    
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

        for(var resData in this.multipleSelection){
          if (this.multipleSelection[resData]['use_yn'])
          {
            this.multipleSelection[resData]['use_yn'] ='Y'
          }else{
            this.multipleSelection[resData]['use_yn'] ='N'
          }
          this.multipleSelection[resData]['reg_user_id'] = this.$store.state.userId
          this.multipleSelection[resData]['upd_user_id'] = this.$store.state.userId
        }

        this.loading = true;
        let url = "";
        url = this.$url + '/UserInfo/update';
        // const json = { 'user_id': this.$store.state.userId };

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

      initPwd(){

        if (this.multipleSelection.length <= 0)
        {
          this.$message.error(this.$store.state.noDataSelect);
          return ;
        }

        for(var resData in this.multipleSelection){
          if (this.multipleSelection[resData]['use_yn'])
          {
            this.multipleSelection[resData]['use_yn'] ='Y'
          }else{
            this.multipleSelection[resData]['use_yn'] ='N'
          }
        }

        this.loading = true;
        let url = "";
        url = this.$url + '/UserInfo/initPwd';
        // const json = { 'user_id': this.$store.state.userId };

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
      }, // initPwd
    }, //methods
  } // export default
</script>
