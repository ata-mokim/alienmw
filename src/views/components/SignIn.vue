<template>
  <div class="login">

    <el-card>
      <div  class="login-title">
      <h2>Login</h2>
      </div>
      <el-form
        :model="model"
        :rules="rules"
        class="login-form"
        ref="form"
        @submit.native.prevent="login"
      >
       <!-- @submit.native.prevent="login" -->
        <el-form-item prop="username">
          <el-input v-model="model.username" placeholder="Username" value="admin" >admin</el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input v-model="model.password" placeholder="Password" type="password" value="1234"  ></el-input>
        </el-form-item>


        <el-form-item>
          <el-button
            :loading="loading"
            class="login-button"
            type="primary"
            native-type="submit"
            block
            icon="el-icon-lock"
          >Login</el-button>
        </el-form-item>

 
           <a class="forgot-password" href="">Forgot password ?</a>
      </el-form>
    </el-card>
     
    
  </div>

  
</template>

<script>
import Axios from 'axios'

export default {
  name: "login",
  data() {
    return {
      validCredentials: {
        username: "admin",
        password: "1234"
      },
      loading: false,
      model: {
        username: "admin",
        password: "1234",
      },
      rules: {
        username: [
          {
            required: true,
            message: "Username is required",
            trigger: "blur"
          },
          {
            min: 4,
            message: "Username length should be at least 5 characters",
            trigger: "blur"
          }
        ],
        password: [
          { 
            required: true, 
            message: "Password is required", 
            trigger: "blur" 
          },
          {
            min: 4,
            message: "Password length should be at least 4 characters",
            trigger: "blur"
          }
        ]
      }
    };
  },
  
  methods: {
    simulateLogin() {
      return new Promise(resolve => {
        setTimeout(resolve, 800);
      });
  },

  async login() {

     // this.$store.commit("displayChange");
      let valid = await this.$refs.form.validate();
      if (!valid) {
        return;
      }
      this.loading = true;
      const reqJson = { 'user_id': this.model.username.toLowerCase() , 'password': this.model.password.toLowerCase()};
      const config ={
        headers: { 'content-type': 'application/json' },
      };

      await Axios.post( this.$url + '/UserInfo/logIn', reqJson, config  ).then(res => {
          // console.log(res.data) 
          if (res.data.resultCode)
          { 
            this.$message.success("Login successfull");
            this.$store.commit("setAccessToken",res.data.accessToken );
            this.$store.commit("setDeptSeq",res.data.dept_seq );
            this.$store.commit("setUserId",res.data.user_id );
            this.$store.commit("setDeptName",res.data.dept_name );
            this.$store.commit("setAddress1", res.data.address1);
            this.$store.commit("setAddress2", res.data.address2);

            console.log("address1==================="+res.data.address1+"========================");
            console.log("address2==================="+res.data.address2+"========================");


            this.$router.push({ name: 'leftMenu' })

          }else{
            this.$message.error("Username or password is invalid");
          }

          this.loading = false;
      })
      .catch(error => {
        console.log(error.response)
        this.$message.error(this.$store.state.serverErrorMessage);
        this.loading = false;
      }); // await Axios.post
    },//async login()

  },//methods
   
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.login-title {
    padding: 15px 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.login {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
.login-icon {
  width: 10%;
  margin-top: 0px;
}

.login-username {
  width: 100%;
  margin-top: 10px;
}
.login-password {
  width: 100%;
  margin-top: 10px;
}



.login-form {
  width: 290px;
}
.forgot-password {
  margin-top: 10px;
}
</style>
<style lang="scss">
$teal: rgb(0, 124, 137);
.el-button--primary {
  background: $teal;
  border-color: $teal;

  &:hover,
  &.active,
  &:focus {
    background: lighten($teal, 7);
    border-color: lighten($teal, 7);
  }
}
.login .el-input__inner:hover {
  border-color: $teal;
}
.login .el-input__prefix {
  background: rgb(238, 237, 234);
  left: 0;
  height: calc(100% - 2px);
  left: 1px;
  top: 1px;
  border-radius: 3px;
  .el-input__icon {
    width: 30px;
  }
}
.login .el-input input {
  padding-left: 35px;
}
.login .el-card {
  padding-top: 0;
  padding-bottom: 30px;
}
h2 {
  font-family: "Open Sans";
  letter-spacing: 1px;
  font-family: Roboto, sans-serif;
  padding-bottom: 20px;
}
a {
  color: $teal;
  text-decoration: none;
  &:hover,
  &:active,
  &:focus {
    color: lighten($teal, 7);
  }
}
.login .el-card {
  width: 340px;
  height: 380px;
  display: flex;
  justify-content: center;
}

.login .el-input input {
  padding-left: 15px;
  width: 100% !important;
  height: 30px !important;
   margin-top: 20px  !important;

}
.login-button {
  width: 100%;
  height: 50px !important;
  margin-top: 30px  !important;
}

</style>
