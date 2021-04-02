<template>
  <div id="app">
    <div class="menu-header">
      <h2 class="menu">Menu</h2>
    </div>
    <div class="center">
       <div class="box">
           <el-card>
            <el-row>
            <el-button type="success" class="menu-box" @click="goToShopIn" icon="el-icon-search">매장입고</el-button> 
            <el-button type="success" class="menu-box" @click="goToSale">매장판매</el-button>
            <br><br>
            <el-button type="success" class="menu-box" @click="goToInventoryCheck">재고조사</el-button>
            <el-button type="success" class="menu-box">반품  </el-button>
            </el-row>
            </el-card>
       </div>
      </div> 
  </div>


</template>

<script>
export default {
    data(){
    return {
      show: true
      }
    },
   methods:{

     // gubun 값이 1이면 조회 먼저 하고 reding 
     // 2 이면 reding 먼저 하고 조회 
      goToShopIn(){

        this.$store.test ='asdf';

        this.$store.menuName = 'shopIn';
        this.$store.gubun = '1';
        this.$router.push({ name: 'appRfid' })
      },
      goToInventoryCheck(){

        this.$store.menuName = 'Inventory Check';
        this.$store.gubun = '1';
        this.$router.push({ name: 'appRfid'})
      },

      goToSale(){
        this.$store.menuName = 'sale';
        this.$store.gubun = '2';
        this.$router.push({ name: 'appRfid'  })
      },


  }
}
</script>


<style>

  .menu-box {
    margin-bottom: 20px;
    width: 135px;
    height: 50px;
    border-radius: 4px;
    text-align: left;
    color: #fff;
    padding: 10px 20px;
    box-sizing: border-box;
    margin-right: 40px;
    justify-content: center;
    align-items: center;
  }
  .menu {
    font-family: "Open Sans";
    letter-spacing: 3px;
    padding-top: 0px;
    padding-bottom: 0px;
  }
  .menu-header {
  padding: 1px 20px;
  
  display: flex;
  flex-direction: column;
  align-items: center;

}
</style>

<style scoped>
.box {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
<style lang="scss">
$teal: rgb(0, 124, 137);

.box .el-card {
  width: 340px;
  height: 530px;
  display: flex;
  justify-content: center;
}
</style>
