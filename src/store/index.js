import Vue from 'vue'
import Vuex from 'vuex'

import createPersistedState from 'vuex-persistedstate';


Vue.use(Vuex)




export default new Vuex.Store({

  plugins: [ createPersistedState({ 
     accessToken:'',
     userId:'',
     deptSeq:'',
     deptName:'',
     address1:'',
     address2:''
      }),
  ],

  state: {
    menuName: '',
    gubun: '',
    dateOption: '',
    config:{
      headers: { 'content-type': 'application/json' },
    },
    serverErrorMessage:'SERVER 에서 오류가 발생하였습니다.',
    dataNotFound:'SERVER 에서 데이터를 조회하지 못했습니다. ',
    searchSuccess:'조회 완료',
    noDataSelect:'데이터를 선택하세요.',
    saveSuccess:'저장 완료',
    
  },
  mutations: {
    // TODAY(status){
    //   var date = new Date(); //Date 객체 생성
    //   var year = date.getFullYear(); // 현재의 년도 (2020)
    //   var month = date.getMonth() + 1; // 현재의 달 * 0부터 출력하여 +1
    //   var day = date.getDate() ;
    //   var today = year + "-" + (month<10 ? "0"+month : month) + 
    //                                             "-" +  (day<10 ? "0"+day : day);
    //   // console.log(today);
    //   status.requestEndDateOption = today;
    //   // return today;
    // },

    setAccessToken(status,value)
    {
      console.log("setAccessToken==================="+value+"========================");
      status.accessToken=value;
    },

    setUserId(status,value)
    {
      console.log("setUserId==================="+value+"========================");
      status.userId=value;
    },

    setDeptSeq(status,value)
    {
      console.log("setDeptSeq==================="+value+"========================");
      status.deptSeq=value;
    },

    setDeptName(status,value)
    {
      console.log("setDeptName==================="+value+"========================");
      status.deptName=value;
    },

    setAddress1(status,value)
    {
      console.log("setAddress1==================="+value+"========================");
      status.address1=value;
    },

    setAddress2(status,value)
    {
      console.log("setAddress2==================="+value+"========================");
      status.address2=value;
    },

    dayBefore(status,value){
      var date = new Date(); //Date 객체 생성
      date.setDate(date.getDate() - value);
      var year = date.getFullYear(); // 현재의 년도 (2020)
      var month = date.getMonth() + 1; // 현재의 달 * 0부터 출력하여 +1
      var day = date.getDate() ;
      var today = year + "-" + (month<10 ? "0"+month : month) + 
                                                "-" +  (day<10 ? "0"+day : day);


      console.log("===========================================");
      console.log(today);
      
      status.dateOption = today;

    },

  },
  actions: {

    
  },
  modules: {

  }
})
