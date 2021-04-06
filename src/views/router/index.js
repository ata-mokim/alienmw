import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'
import Login from '../Login.vue'
import Main from '../Main.vue'
import leftMenu from '../LeftMenu.vue'
import rfidGetOrder from '../web/rfidGetOrder/List.vue'
import RevCompleteList from '../web/rfidOrder/RevCompleteList.vue'
import rfidOrderPublishList from '../web/rfidOrder/PublishList.vue'
import rfidOrderPublishStatusList from '../web/rfidOrder/PublishStatusList.vue'
import rfidOrderTakeList from '../web/rfidOrder/TakeList.vue'
import warehouseInList from '../web/warehouse/InList.vue'
import warehouseOutList from '../web/warehouse/OutList.vue'
import warehouseReturnList from '../web/warehouse/ReturnList.vue'
import shopInList from '../web/shop/InList.vue'
import shopWOutList from '../web/shop/WOutList.vue'
import shopSaleList from '../web/shop/SaleList.vue'
import shopReturnList from '../web/shop/ReturnList.vue'
import rfidInfoHistoryList from '../web/rfidInfo/HistoryList.vue'
import store from '../store';
import userList from '../web/user/List.vue'
import deptInfoList from '../web/deptInfo/List.vue'


// app
import appMenu from '../app/production/Menu.vue'
import appLogin from '../app/production/Login.vue'
import appRfid from '../app/production/Rfid.vue'
import appBlank from '../app/Blank.vue'
import appTable from '../app/production/Table.vue'
import appOutList from '../app/production/OutList.vue'
import appInList from '../app/production/InList.vue'
import appOutGroupList from '../app/production/OutGroupList.vue'

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
    path: '/leftMenu',
    name: 'leftMenu',
    component: leftMenu,
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
  {
    path: '/appOutList',
    name: 'appOutList',
    component: appOutList
  },
  {
    path: '/appInList/',
    name: 'appInList',
    component: appInList
  },
  {
    path: '/appOutGroupList/',
    name: 'appOutGroupList',
    component: appOutGroupList
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
