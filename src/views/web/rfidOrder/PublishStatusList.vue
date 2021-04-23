<template>

<div>
<el-row>
  <el-col :span="24">
    <div class="grid-content bg-purple-light">

      <div class="searchTitle">
       <b> RFID 상태조회 </b>
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
              v-model="deptNameOption"
              :disabled="deptNameDisabled"
            >
            </el-input>
          </div>

          <div class="searchBody_production">
          확정 일자 
          </div>
          <div>
          <el-date-picker
            v-model="confirmStartDateOption"
            type="date"
            placeholder="Pick a day"
            value-format="yyyy-MM-dd"
            >
          </el-date-picker>
          ~ 
          <el-date-picker
            v-model="confirmEndDateOption"
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

    <!-- <el-table-column
      property="rfid_get_order_seq"
      label="get_order_seq"
      width="100">
    </el-table-column> -->

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

       label="상태"
       width="100">

     <template slot-scope="scope">
       <div style="color:#f29659"> <b>{{scope.row.status}} </b></div>
     </template>

   </el-table-column>




    <el-table-column
      property="confirm_date"
      label="확정 일자"
      width="90">
    </el-table-column>

   <el-table-column
       property="confirm_id"
       label="확정 id"
       width="90">
   </el-table-column>

   <el-table-column
      property="dept_name"
      label="생산처"
      width="120">
    </el-table-column>

   <el-table-column
       property="publish_id"
       label="발행 id"
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
      
      label="RFID 발주수량"
      width="100"
      color="#FFFFFF"
      >
       <template slot-scope="scope">
          <div style="color:#f29659"> <b>{{scope.row.rfid_order_amount}} </b></div>
       </template>
    </el-table-column>

    <el-table-column
      property="publish_start_number"
      label="발행 시작 번호"
      width="90">
    </el-table-column>
    
    <el-table-column
      property="publish_end_number"
      label="발행 종료 번호"
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
      property="address1"
      label="주소1"
      width="150">
    </el-table-column>


     <el-table-column
      property="address2"
      label="주소2"
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
    @click="excelDownFunc"
  > 엑셀 다운로드 </el-button>




<el-dialog
  title="발행결과 업로드"
  :visible.sync="dialogVisible"
  width="30%"
  v-loading="loading"
  :before-close="Confirm" 
  >
    <span class="text-reader">
      <form ref="form">

      <input type="file" @change="filesChange" class="FileUpload__default" accept=".txt" multiple  />
      </form>
    </span>

    <span slot="footer" class="dialog-footer">
    <!-- <el-button @click="dialogVisible = false">Cancel</el-button> -->
    <el-button type="primary" @click="Confirm">Confirm</el-button>
  </span>


</el-dialog>


</div>
 </el-col>
 </el-row>

</div>
</template>


<script>
import Axios from 'axios'; 
import XLSX from 'xlsx'

  export default {
    created() {
      this.$store.commit("dayBefore",0);
      this.confirmEndDateOption = this.$store.state.dateOption; // 조회조건에서 현재 날짜 
      this.$store.commit("dayBefore",7);
      this.confirmStartDateOption = this.$store.state.dateOption; // 조회조건이서 현재보다 -7 일

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

        statusOptions: [
        {
          value: '',
          label: '전체'
        },
        {
          value: '1',
          label: '확정'
        }, {
          value: '2',
          label: '접수'
        }, {
          value: '3',
          label: '발행시작'
        }, {
          value: '4',
          label: '발행완료'
        },],
        brandOption: '',
        styleOption :'',
        deptNameOption:'',
        deptSeqOption:0,
        confirmStartDateOption:'',
        confirmEndDateOption:'',
        statusOption:'',
        status:'',     
        fileList: [],
        text :[],
        dialogVisible: false,
        deptNameDisabled:'false',
      } // return 
    },// data

    methods: {

      async search(){
        this.loading = true;

        // default data 
        let url = this.$url + '/rfidOrder/list';
        var reqJson = new Object();
        reqJson.brand = this.brandOption;
        reqJson.style = this.styleOption;
        // reqJson.dept_name = this.deptNameOption;
        reqJson.dept_seq = this.deptSeqOption;
        reqJson.confirm_start_date = this.confirmStartDateOption;
        reqJson.confirm_end_date = this.confirmEndDateOption;
      
        // if (this.statusOption == null || this.statusOption == '')
        // {
        //   reqJson.status = '1';
        // }else {
        //
        // }

        reqJson.status = this.statusOption;

        await Axios.post(url, reqJson , this.$store.state.config ).then(res => {  
          var resDataJson = res.data.rfidOrderDTO;
          this.totalData=[];

          if (res.data.resultCode){
            for(var resData in resDataJson){

              if ( resDataJson[resData]['status'] == 0)
              {
                resDataJson[resData]['status'] = '임시저장'
              }else if(resDataJson[resData]['status'] == 1){
                resDataJson[resData]['status'] = '주문확정'
              }else if(resDataJson[resData]['status'] == 2){
                resDataJson[resData]['status'] = '발행접수'
              }else if(resDataJson[resData]['status'] == 3){
                resDataJson[resData]['status'] = '발행시작'
              }else if(resDataJson[resData]['status'] == 4){
                resDataJson[resData]['status'] = '발행완료'
              }else if(resDataJson[resData]['status'] == 5){
                resDataJson[resData]['status'] = '인수완료'
              }


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
                , rfid_take_amount: resDataJson[resData]['rfid_take_amount']
                , confirm_date: resDataJson[resData]['confirm_date']
                , dept_name: resDataJson[resData]['dept_name']
                , take_date: resDataJson[resData]['take_date']
                , address1: resDataJson[resData]['address1']
                , address2: resDataJson[resData]['address2']
                , rfid_get_order_seq: resDataJson[resData]['rfid_get_order_seq']
                , publish_start_number: resDataJson[resData]['publish_start_number']
                , publish_end_number: resDataJson[resData]['publish_end_number']
                , status: resDataJson[resData]['status']
                , confirm_id: resDataJson[resData]['confirm_id']
                , publish_id: resDataJson[resData]['publish_id']
              });
            }
            // 전체 데이터 사이즈 
            this.totalSize = this.totalData.length;
            // 보여주는 부분만 slice 
            this.tableData = this.totalData.slice(0, this.pageSize);
            this.currentPage = 1;
            this.loading = false;
            this.status = reqJson.status;
            this.$message.success(this.$store.state.searchSuccess);
          }else{
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

       save(value){

        let url = "";

        if (this.multipleSelection.length <= 0)
        {
          this.$message.error("데이터를 선택하세요 ");
          return ;
        }

        if (value == 1) // 발행접수
        {
          if (this.status != '1') //status 가 1이어야만저장 가능 
          {
            this.$message.error("확정상테의 데이터만 발행접수가 가능합니다. ");
            return ;
          }
          url = this.$url + '/rfidOrder/rfidReceipt';
        }else if (value == 2){
          if (this.status != '2')
          {
            this.$message.error("발행접수 상태의 데이터만 발행 시작 가능합니다 . ");
            return ;
          }
          url = this.$url + '/rfidOrder/rfidPublishStart';
        } else if (value == 3){
           if (this.status != '3')
          {
            this.$message.error("발행시작 상태의 데이터만 완료 가능합니다 . ");
            return ;
          }
          url = this.$url + '/rfidOrder/rfidPublishComplete';
        }

         for(var resData in this.multipleSelection){
           this.multipleSelection[resData]['reg_user_id'] = this.$store.state.userId
           this.multipleSelection[resData]['upd_user_id'] = this.$store.state.userId
         }


        this.loading = true;
        Axios.post(url, this.multipleSelection , this.$store.state.config  ).then(res => { 
         
          console.log(res);
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

      excelDownFunc(){

        if(this.multipleSelection==null || this.multipleSelection.length == 0 ){
          this.$message.error(this.$store.state.noDataSelect);
          return ;
        }

        if (this.status != '2')
        {
          this.$message.error("발행접수 상태의 데이터만 발행 시작 가능합니다 . ");
          return ;
        }

        this.loading = true;
        var dataWS = XLSX.utils.json_to_sheet(this.multipleSelection);
        var wb = XLSX.utils.book_new();
        // workbook에 워크시트 추가
        // 시트명은 'nameData'
        XLSX.utils.book_append_sheet(wb, dataWS, '발행정보');
        // 엑셀 파일을 내보낸다.
        this.$store.commit("dayBefore",0); // 오늘날짜
        XLSX.writeFile(wb, this.$store.state.dateOption +'.xlsx');
        this.loading = false;
        this.save(2);

      },
      filesChange(e) {
        let fileName = e.target.files[0].name
        let lastIndex = fileName.lastIndexOf('.')
        let fileExtension = fileName.substring(lastIndex, fileName.length).toLowerCase()


        if (fileExtension !== '.txt') {
          this.$alert('텍스트 파일만 업로드 가능합니다.', '알림', { confirmButtonText: '확인' })
          return
        }

        let fileList = e.target.files
        if (!fileList.length) return

        const reader = new FileReader()
        const file = e.target.files[0];

        

        reader.onload =  evt => {
          
          this.text = evt.target.result;
          const textArray = this.text.split('\n');
          // console.log(textArray);

          console.log("======================" + this.text.length );
          if (this.text.length < 100)
          {
            this.$message.error(" 파일 포맷 오류 입니다. ");
            this.$refs.form.reset() ;
            return ;
          }


          const reqJson=[];

          this.loading = true;
          for(var i in textArray){
            if (i==0)
              continue;

              const dataArray = textArray[i].split(',');

              if (dataArray.length  < 10)
              {
                continue;
              }

              reqJson.push({ 
                  epc: dataArray[11]
                , rfid_tag_tid : dataArray[0]
                , reg_user_id : this.$store.state.userId
                // , createDate : dataArray[1]
                , rfid_order_seq : dataArray[2]
                , style : dataArray[6]
                , color : dataArray[7]
                , size : dataArray[8]
                , order_degree : dataArray[9]
                , publish_seq: dataArray[10]
                , status:'0'
              });
          }

          // console.log(reqJson);
          let url = "";
          url = this.$url + '/rfidInfo/insert';

          Axios.post(url, reqJson , this.$store.state.config  ).then(res => { 
          
            console.log(res);
            let saveCount = res.data;
            this.loading = false;
            this.$message.success(saveCount +'건 ' + this.$store.state.saveSuccess);
            this.search();
            // this.$refs.myFiles.files[0].name = null;
          }).catch(error => {
            console.log(error.response)
            this.$message.error(this.$store.state.serverErrorMessage);
            this.currentPage = 1;
            this.loading = false;
          }); // await Axios.post

        }
         reader.readAsText(file)
              
      },//filesChange

      Confirm(){
        // this.e.target.files[0].name = "";

        this.$refs.form.reset() ;
        this.dialogVisible = false
      },

      submitUpload() {
        this.$refs.upload.submit();
      }

    }, //methods
  }
</script>

