import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Main from '../views/Main.vue'
import LeftMenu from '../views/LeftMenu.vue'
import rfidGetOrder from '../rfidGetOrder/List.vue'
import RevCompleteList from '../rfidOrder/RevCompleteList.vue'
import rfidOrderPublishList from '../rfidOrder/PublishList.vue'
import rfidOrderPublishStatusList from '../rfidOrder/PublishStatusList.vue'
import rfidOrderTakeList from '../rfidOrder/TakeList.vue'
import warehouseInList from '../warehouse/InList.vue'
import warehouseOutList from '../warehouse/OutList.vue'
import warehouseReturnList from '../warehouse/ReturnList.vue'
import shopInList from '../shop/InList.vue'
import shopWOutList from '../shop/WOutList.vue'
import shopSaleList from '../shop/SaleList.vue'
import shopReturnList from '../shop/ReturnList.vue'
import rfidInfoHistoryList from '../rfidInfo/HistoryList.vue'
import store from '../store/index';
import userList from '../user/List.vue'
import deptInfoList from '../deptInfo/List.vue'


// app
import appMenu from '../views/app/Menu.vue'
import appLogin from '../views/app/Login.vue'
import appRfid from '../views/app/Rfid.vue'
import appBlank from '../views/app/Blank.vue'
import appTable from '../views/app/Table.vue'
 
Vue.use(VueRouter)


const requireAuth = (value) => (to, from, next) => {

  console.log("==========value ==="+value+"=============");
  console.log("=========accessToken===="+store.state.accessToken+"=============");

  if (store.state.accessToken == '100' ) {
    return next();
  }

  if (store.state.accessToken ==  value ) {
  
    return next();
  }
  
  this.$router.push({ name: 'Login' })
  
  return from
};


const routes = [
  {
    path: '/LeftMenu',
    name: 'LeftMenu',
    component: LeftMenu,
    children: [

      {
        path: '/Main',
        name: 'Main',
        component: Main,
        beforeEnter: requireAuth('1')
      },
      { // RFID 주문(생산처)
        path: '/rfidGetOrder/List',
        name: 'rfidGetOrder',
        component: rfidGetOrder,
        beforeEnter: requireAuth('1')
      },
      { // RFID 주문(확장)
        path: '/rfidOrder/RevCompleteList',
        name: 'RevCompleteList',
        component: RevCompleteList,
        beforeEnter: requireAuth('1')
      },

      { // 주문상태조회
        path: '/rfidOrder/PublishStatusList',
        name: 'rfidOrderPublishStatusList',
        component: rfidOrderPublishStatusList,
        beforeEnter: requireAuth('2')
      },
      { // RFID 인수
        path: '/rfidOrder/TakeList',
        name: 'rfidOrderTakeList',
        component: rfidOrderTakeList,
        beforeEnter: requireAuth('1')
      },
      { // 발행 
        path: '/rfidOrder/PublishList',
        name: 'rfidOrderPublishList',
        component: rfidOrderPublishList,
        beforeEnter: requireAuth('2')
      },

      {
        path: '/warehouse/InList',
        name: 'warehouseInList',
        component: warehouseInList,
        beforeEnter: requireAuth('3')
      },
      {
        path: '/warehouse/OutList',
        name: 'warehouseOutList',
        component: warehouseOutList,
        beforeEnter: requireAuth('3')
      },
      {
        path: '/warehouse/ReturnList',
        name: 'warehouseReturnList',
        component: warehouseReturnList,
        beforeEnter: requireAuth('3')
      },
      {
        path: '/shop/InList',
        name: 'shopInList',
        component: shopInList,
        beforeEnter: requireAuth('4')
      },
      {
        path: '/shop/WOutList',
        name: 'shopWOutList',
        component: shopWOutList,
        beforeEnter: requireAuth('4')
      },
      {
        path: '/shop/SaleList',
        name: 'shopSaleList',
        component: shopSaleList,
        beforeEnter: requireAuth('4')
      },

      {
        path: '/shop/ReturnList',
        name: 'shopReturnList',
        component: shopReturnList,
        beforeEnter: requireAuth('4')
      },
      {
        path: '/rfidInfo/HistoryList',
        name: 'rfidInfoHistoryList',
        component: rfidInfoHistoryList,
        beforeEnter: requireAuth('5')
      },
      {
        path: '/user/List',
        name: 'userList',
        component: userList,
        beforeEnter: requireAuth('6')
      },
      {
        path: '/deptInfo/List',
        name: 'deptInfoList',
        component: deptInfoList,
        beforeEnter: requireAuth('7')
      }
    ],
  },
  
  {
    path: '/',
    name: 'Login',
    component: Login
  },

  {
    path: '/appLogin',
    name: 'appLogin',
    component: appLogin
  },
  {
    path: '/appMenu',
    name: 'appMenu',
    component: appMenu
  },
  {
    path: '/appRfid/',
    name: 'appRfid',
    component: appRfid
  },
  {
    path: '/appBlank',
    name: 'appBlank',
    component: appBlank
  },
  {
    path: '/appTable',
    name: 'appTable',
    component: appTable
  },


  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
