<template>
  <div id="app">
    <div class="login-header">
      <h2 class="logo">ALIEN M/W</h2>
    </div>
    <div class="center">
      
    <SignIn/> 
    </div>
    <div class="footer">
      <div class="links">
        <!-- <span>Feature 1 | </span>
        <span>Feature 2 | </span>
        <span>Feature 3 </span> -->
      </div>
      <div class="version">Version 1.02.05</div>
    </div>
  </div>
</template>

<script>
import SignIn from "@/components/SignIn.vue";


export default {
  components: {
    SignIn
  },
 data() {
      return {
      }
    },
};
</script>W

<style lang="scss">
.login-header {
    padding: 35px 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    font-size: 15px !important;
}

.footer {
    padding: 20px 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
}
</style>