<template>


     <el-container style="height: 800px; border: 1px;" >


      <!-- left -->
     <el-row class="tac"  >
    
      <!-- left top -->
      <div  style="text-align: left; font-size: 11px; height: 20px; background-color:#013263; width:170px; color:#fff;" >
      <span>Alien M/W </span>
      </div>

     <!-- left menu background-color="#545c64" -->
     <el-menu
      default-active="1"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#013263"
      text-color="#fff"
      active-text-color="#ffd04b"
  
      >


      <el-submenu index="1"
      v-if="isMenuVIf[0]"
      >
        <template slot="title" text-color="#ffd04b" >
              RFID 주문 (생산처)
          </template>
      

      <router-link to="/rfidGetOrder/List">
          <el-menu-item index="1-1" >
              RFID 주문 (생산처)
          </el-menu-item>
      </router-link>

       <router-link to="/rfidOrder/RevCompleteList">
        <el-menu-item index="1-2" >
          RFID 주문 (확정)
        </el-menu-item>
      </router-link>

        <router-link to="/rfidOrder/PublishStatusList">
          <el-menu-item index="1-3" >
            발행 진행 조회
          </el-menu-item>
        </router-link>

       <router-link to="/rfidOrder/TakeList">
          <el-menu-item index="1-4" >
           
            RFID 인수 </el-menu-item>
       </router-link>

      </el-submenu>

      <router-link to="/rfidOrder/PublishList">
      <el-menu-item index="2"
      v-if="isMenuVIf[1]"
      >
        <span>
          RFID 발행 (발행처)
          </span>
      </el-menu-item>
       </router-link>



      <el-submenu  index="3"
      v-if="isMenuVIf[2]"
      >
        <template slot="title" text-color="#ffd04b" >
              물류 입출고 상세조회
          </template>

          <router-link to="/warehouse/InList">
          <el-menu-item index="3-1" >
              물류 입고 조회
          </el-menu-item>
          </router-link>

          <router-link to="/warehouse/OutList">
          <el-menu-item index="3-2" >
              물류 출고 조회
          </el-menu-item>
          </router-link>

          <router-link to="/warehouse/ReturnList">
          <el-menu-item index="3-3" >
              물류 반품 조회
            
          </el-menu-item>
          </router-link>

      </el-submenu >


      <el-submenu  index="4"
      v-if="isMenuVIf[3]"
      >
        <template slot="title" text-color="#ffd04b" >
              매장 정보 관리
          </template>
          <router-link to="/shop/WOutList">
          <el-menu-item index="4-1" >
              입고 예정 정보
          </el-menu-item>
          </router-link>

          <router-link to="/shop/InList">
          <el-menu-item index="4-2" >
              매장 입고
          </el-menu-item>
          </router-link>

          <router-link to="/shop/SaleList">
          <el-menu-item index="4-3" >
              매장 판매 
            
          </el-menu-item>
          </router-link>

          <router-link to="/shop/ReturnList">
          <el-menu-item index="4-4" >
              매장 반품 
          </el-menu-item>
          </router-link>
      </el-submenu >


      <router-link to="/rfidInfo/HistoryList">
      <el-menu-item index="5"
      v-if="isMenuVIf[4]"
      >
        <span>RFID Tag 이력 조회 </span>
      </el-menu-item>
      </router-link>


      <router-link to="/user/List">
      <el-menu-item index="6"
      v-if="isMenuVIf[5]"
      >
        <span> 회원 관리  </span>
      </el-menu-item>
      </router-link>

       <router-link to="/deptInfo/List">
         <el-menu-item index="7"
                       v-if="isMenuVIf[6]"
         >
           <span> 업체 관리 </span>
         </el-menu-item>
       </router-link>


      <el-menu-item index="8"
      @click="logOut"
      >
        <span>로그 아웃</span>
      </el-menu-item>


      <!-- <el-submenu index="3">
        <template slot="title"><i class="el-icon-setting"></i>Setting </template>
          <el-menu-item index="3-1">Option 1</el-menu-item>
          <el-menu-item index="3-2">Option 2</el-menu-item>
      </el-submenu>
       -->
    </el-menu>
    </el-row>
    <el-row class="tac"  style="width:1px;background-color: #B3C0D1;">
    </el-row>
    <!-- left end-->

        <!-- body -->
        <el-container>
            <el-main>
            

      
                <!-- router main -->
                <router-view/> 
            </el-main>
        </el-container>

    </el-container>

    
</template>


    // <div id="nav">
    //   <router-link to="/">Home</router-link> |
    //   <router-link to="/Login">Login</router-link> |
    //   <router-link to="/Rfid">Rfid</router-link> |
    //     <router-link to="/Menu">Menu</router-link> |
    //     <router-link to="/Temp">Blank</router-link> |
    //     <router-link to="/Table">Table</router-link> |
    // </div>


<script>




  export default {
    data() {
      return {
        isTitleIf:this.$store.state.isTitleVIf,
        isMenuVIf:[],
      }
    },

    created() {
      // this.isMenuVIf=false;
      //this.isTitleIf=this.$store.state.isTitleVIf;

      if(this.$store.state.accessToken == null || this.$store.state.accessToken == "")
      {
        this.$router.push({ name: 'Login' })
      }

      this.$router.push({ name: 'Main' })
      console.log("======created=============="+this.$store.state.accessToken+"=============================");

      for(var i=0; i< 7; i++){
        this.isMenuVIf[i] = false;
      }

      if (this.$store.state.accessToken == '100' ) {

          for( i in this.isMenuVIf){
            this.isMenuVIf[i] = true;
          }
        
      }else if (this.$store.state.accessToken == 1)
      {
          for( i in this.isMenuVIf){
            if (i == 0){
              this.isMenuVIf[i] = true;
            }else {
              this.isMenuVIf[i] = false;
            }
          }
      }else if (this.$store.state.accessToken == 2)
      {
          for( i in this.isMenuVIf){
            if (i == 1){
              this.isMenuVIf[i] = true;
            }else {
              this.isMenuVIf[i] = false;
            }
          }
      }

      
      
    },

    destroyed(){
      console.log("==========destroyed==============");
    },

    beforeDestroy() {
        console.log('beforeDestroy');
        this.$store.commit("setAccessToken",'' );
    },


    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },

      logOut(){
    
            this.$store.commit("setAccessToken", '');
            this.$store.commit("setDeptSeq", '');
            this.$store.commit("setUserId", '');
            this.$store.commit("setDeptName", '');
            this.$store.commit("setAddress1", '');
            this.$store.commit("setAddress2", '');
            this.$message.success("LOG OUT ");
            this.$router.push({ name: 'Login' })
      }

    }
  }
</script>

<style>
.el-aside {
    color:#013263;
  }
.el-submenu__title{
  font-size: 9px !important;
}
.el-menu-item, .el-submenu__title {
  height: 42px !important;
  display: flex !important;
  align-items: center !important;
}

.el-menu-item {
   font-size: 9px !important;
}

.el-submenu__icon-arrow {

    right: 30px !important;
}

/* .el-submenu .el-menu-item {
    min-width: 160px !important;
} */

a {
    text-decoration: none !important; 
}


element.style {
    padding-left: 40px;
    color: rgb(255, 208, 75);
    background-color: rgb(1, 50, 99);
    
}

.el-submenu__title  {
    width: 170px !important; 
}
</style>
