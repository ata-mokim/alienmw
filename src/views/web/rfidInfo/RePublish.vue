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



      <!-- 조회 조건 영역 -->
      <div class="searchBody">
        
        <div class="searchBody_brand">
          <el-button
            :loading="loading"
            type="primary"
            @click="search"
          >임시저장</el-button>
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
       width="100">
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
    >확정</el-button>

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

      this.deptSeqOption = this.$store.state.deptSeq;
      this.deptNameOption = this.$store.state.deptName;
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

        styleOption :'',
        colorOption :'',
        sizeOption :'',
        orderDegreeOption :'',
        publishSeqOption : '',
        deptNameOption:'',
        deptSeqOption:0,
        requestStartDateOption:'',
        requestEndDateOption:'',
        statusOption:'발행완료',
        epcOption:'',
        seq:0,

      }
    },

    methods: {


      async search(){

        if (this.styleOption.length <= 0)
        {
          this.$message.error("스타일은 필수 입니다.");
          return ;
        }
        if (this.colorOption.length <= 0)
        {
          this.$message.error("컬러는 필수 입니다.");
          return ;
        }
        if (this.sizeOption.length <= 0)
        {
          this.$message.error("사이즈는 필수 입니다.");
          return ;
        }
        if (this.sizeOption.length <= 0)
        {
          this.$message.error("사이즈는 필수 입니다.");
          return ;
        }
        if (this.orderDegreeOption.length <= 0)
        {
          this.$message.error("생산차수는 필수 입니다.");
          return ;
        }
        if (this.publishSeqOption.length <= 0)
        {
          this.$message.error("일련번호는 필수 입니다.");
          return ;
        }

        this.tableData.push({
              seq : this.seq++
            , epc: ''
            , style: this.styleOption
            , color: this.colorOption
            , size : this.sizeOption
            , order_degree : this.orderDegreeOption
            , publish_seq : this.publishSeqOption
            , dept_seq : this.deptSeqOption
            , dept_name : this.deptNameOption

          });
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
        let url = this.$url + '/RePublishInfo/save';

        Axios.post(url, this.multipleSelection , this.$store.state.config  ).then(res => {
          let saveCount = res.data;
          this.loading = false;
          this.$message.success(saveCount +'건 ' + this.$store.state.saveSuccess);
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

        for(var i in this.multipleSelection) {

            for (var j in this.tableData) {
              if (this.multipleSelection[i]['seq'] == this.tableData[j]['seq'])
                this.tableData.splice(j, 1)
            }
        }

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