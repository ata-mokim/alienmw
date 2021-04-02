<template>

<div>
<el-row>
  <el-col :span="24">
    <div class="grid-content bg-purple-light">

      <div class="searchTitle">
       <b>RFID 인수 </b> 
      </div>

      <!-- 조회 조건 영역 -->
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


          
          <div class="searchBody_production">
          생산처  
          </div>
          <div>
            <el-input
              size="mini"
              placeholder=""
              v-model="factoryNameOption">
            </el-input>
          </div>

          <div class="searchBody_production">
          요청 일자 
          </div>
          <div>
          <el-date-picker
            v-model="requestStartDateOption"
            type="date"
            placeholder="Pick a day"
            value-format="yyyy-MM-dd"
            >
          </el-date-picker>
          ~ 
          <el-date-picker
            v-model="requestEndDateOption"
            type="date"
            placeholder="Pick a day"
            value-format="yyyy-MM-dd"
            >
          </el-date-picker>

          </div>
        
      </div>

     <!-- 조회 조건 영역 -->
      <div class="searchBody">
        
        <div class="searchBody_brand">
          진행상태  
          </div>
          <div>
            <el-input
              size="mini"
              placeholder="임시저장"
              v-model="statusOption"
              :disabled="true"
              >
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

    <!-- data 시작 -->
    <!-- <el-table-column
      label="Date"
      width="120">
      <template slot-scope="scope">{{ scope.row.date }}</template>
    </el-table-column> -->

    <el-table-column
      property="seq"
      label="seq"
      width="50">
    </el-table-column>

    <el-table-column
      property="rfid_get_order_seq"
      label="주문번호"
      width="100">
    </el-table-column>

    <el-table-column
      property="rfid_order_seq"
      label="발주번호"
      width="100">
    </el-table-column>




    <el-table-column
      property="request_date"
      label="요청일자"
      width="90">
    </el-table-column>

   <el-table-column
      property="factory_name"
      label="생산처"
      width="120">
    </el-table-column>


    <el-table-column
      property="brand"
      label="브랜드"
      width="55">
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
      property="order_degree"
      label="생산차수"
      width="65">
    </el-table-column>

    <el-table-column
      property="size"
      label="사이즈"
      width="60">
    </el-table-column>

    <el-table-column
      property="product_amount"
      label="제품생산수량"
      width="90">
    </el-table-column>

    <el-table-column
      property="rfid_order_amount"
      label="rfid 발주수량 "
      width="90">
    </el-table-column>


       <el-table-column
      label="발행 수량"
      width="90">
      
     <template slot-scope="scope">
          <div style="color:#f29659"> <b>{{scope.row.publish_amount}} </b></div>
       </template>
    </el-table-column>

        <el-table-column
      property="publish_date"
      label="발행 일자"
      width="90">
    </el-table-column>



        <el-table-column
      label="인수 수량"
      width="90">
      
     <template slot-scope="scope">
          <div style="color:#f29659"> <b>{{scope.row.rfid_take_amount}} </b></div>
       </template>
    </el-table-column>

        <el-table-column
      property="take_date"
      label="인수 일자"
      width="90">
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
  >인수확정</el-button>
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
        brandOptions: [{
          value: 'CW',
          label: 'CW'
        }, {
          value: 'KO',
          label: 'KO'
        }, {
          value: 'CM',
          label: 'CM'
        }, {
          value: 'KS',
          label: 'KS'
        },],

        brandOption: '',
        styleOption :'',
        factoryNameOption:'',
        requestStartDateOption:'',
        requestEndDateOption:'',
        statusOption:'발행완료',
      }
    },

    methods: {

      async search(){
        this.loading = true;

        this.loading = true;
        let url = this.$url + '/rfidOrder/list';

        var reqJson = new Object();
        reqJson.brand = this.brandOption;
        reqJson.style = this.styleOption;
        reqJson.factory_name = this.factoryNameOption;
        reqJson.request_start_date = this.requestStartDateOption;
        reqJson.request_end_date = this.requestEndDateOption;
        reqJson.status = '4';

        await Axios.post(url, reqJson , this.$store.state.config  ).then(res => { 
          var resDataJson = res.data.rfidOrderDTO;
          this.totalData=[];

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
                , factory_name: resDataJson[resData]['factory_name']
                , take_date: resDataJson[resData]['take_date']
                , address1: resDataJson[resData]['address1']
                , address2: resDataJson[resData]['address2']
                , rfid_get_order_seq: resDataJson[resData]['rfid_get_order_seq']
                , publish_start_number: resDataJson[resData]['publish_start_number']
                , publish_end_number: resDataJson[resData]['publish_end_number']
                , status: resDataJson[resData]['status']
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
    }, //methods
  }
</script>
