<template>

<div>
<el-row>
  <el-col :span="24">
    <div class="grid-content bg-purple-light">

      <div class="searchTitle">
       <b> Tag 이력 조회  </b> 
      </div>

      <!-- 조회 조건 영역 -->
      <div class="searchBody">
        
        <div class="searchBody_brand">
          EPC  
          </div>
          <div>
            <el-input
              size="small"
              placeholder=""
              v-model="epcOption">
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
      property="epc"
      label="epc"
      width="200">
    </el-table-column>


    <el-table-column
      property="rfid_tag_tid"
      label="rfid_tag_tid"
      width="140">
    </el-table-column>

   <el-table-column
      property="rfid_order_seq"
      label="주문일련번호"
      width="90">
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
      label="발행 번호"
      width="65">
    </el-table-column>

    <el-table-column
      property="w_in_date"
      label="물류 입고일자 "
      width="90">
    </el-table-column>

    <el-table-column
      property="w_in_box_barcode"
      label="입고 박스 바코드 "
      width="120">
    </el-table-column>

    <el-table-column
      property="w_in_specs"
      label="입고 거래 명세서 "
      width="120">
    </el-table-column>




    <el-table-column
      property="w_out_date"
      label="물류 출고 일자 "
      width="90">
    </el-table-column>

    <el-table-column
      property="w_out_box_barcode"
      label="입고 박스 바코드 "
      width="120">
    </el-table-column>

    <el-table-column
      property="w_out_specs"
      label="입고 거래 명세서 "
      width="120">
    </el-table-column>

    <el-table-column
      property="w_return_date"
      label="물류 반품 일자 "
      width="90">
    </el-table-column>

     <el-table-column
      property="store_in_date"
      label="매장 입고 일자 "
      width="90">
    </el-table-column>

     <el-table-column
      property="store_sale_date"
      label="매장 판매 일자 "
      width="90">
    </el-table-column>

     <el-table-column
      property="store_return_date"
      label="매장 반품 일자 "
      width="90">
    </el-table-column>

         <el-table-column
      property="store_code"
      label="매장 코드 "
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

  <!-- <el-button
    :loading="loading"
    type="primary"
    @click="save"
  >인수확정</el-button> -->
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
        epcOption:'',
      }
    },

    methods: {

      async search(){
        this.loading = true;

        this.loading = true;
        let url = this.$url + '/rfidInfo/read';

        var reqJson = new Object();
        reqJson.brand = this.brandOption;
        reqJson.style = this.styleOption;
        reqJson.factory_name = this.factoryNameOption;
        // reqJson.w_in_start_date = this.requestStartDateOption;
        // reqJson.w_in_end_date = this.requestEndDateOption;
        // reqJson.status = '3'; // 입고 조회 
        reqJson.epc = this.epcOption; // 입고 조회 

        await Axios.post(url, reqJson , this.$store.state.config  ).then(res => { 
          var resDataJson = res.data.rfidInfoDTO;
          this.totalData=[];

          if (res.data.resultCode){
            for(var resData in resDataJson){
              this.totalData.push({ 
                  seq : resData
                , epc: resDataJson[resData]['epc']
                , rfid_tag: resDataJson[resData]['rfid_tag']
                , rfid_tag_tid: resDataJson[resData]['rfid_tag_tid']
                , rfid_order_seq: resDataJson[resData]['rfid_order_seq']
                , rfid_get_order_seq: resDataJson[resData]['rfid_get_order_seq']
                , style: resDataJson[resData]['style'] 
                , color: resDataJson[resData]['color']
                , size: resDataJson[resData]['size']
                , order_degree: resDataJson[resData]['order_degree'] 
                , publish_seq: resDataJson[resData]['publish_seq'] 
                , brand: resDataJson[resData]['brand']
                , status: resDataJson[resData]['status']
                , create_date: resDataJson[resData]['create_date']
                , w_in_date: resDataJson[resData]['w_in_date']
                , w_out_date: resDataJson[resData]['w_out_date']
                , w_return_date: resDataJson[resData]['w_return_date']
                , w_in_box_barcode: resDataJson[resData]['w_in_box_barcode']
                , w_in_specs: resDataJson[resData]['w_in_specs']
                , w_out_box_barcode: resDataJson[resData]['w_out_box_barcode']
                , w_out_specs: resDataJson[resData]['w_out_specs']
                , store_in_date: resDataJson[resData]['store_in_date']          
                , store_sale_date: resDataJson[resData]['store_sale_date']   
                , store_return_date: resDataJson[resData]['store_return_date']   
                , store_code: resDataJson[resData]['store_code']
                , use_yn: resDataJson[resData]['use_yn']
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


<style>
.el-input--small .el-input__inner {
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