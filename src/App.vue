<template>



                <!-- router main -->
                <router-view/> 

    
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
        isMenuVIf:true,
      }
    },

    created() {
      // this.isMenuVIf=false;
      //this.isTitleIf=this.$store.state.isTitleVIf;

    },


    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
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


.el-submenu .el-menu-item {
    min-width: 162px !important;
}
</style>
