<template>
  <div id="app" >
    <div class="menu-header">
      
      <h2 class="menu">{{menuName}} {{gubun}} </h2>

    </div>
    <div class="center">
      <div>
        <el-select v-model="value" clearable placeholder="brand" class="brand-box"> 
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
        </el-select>
        <el-input placeholder="Store Code"  class="brand-box"  :disabled="true" value="CC뉴 강남"> </el-input>
        

      </div>


      <div>
      
      <el-date-picker
        v-model="value1"
        type="date"
        placeholder="Pick a day" style="padding: 1px 2px;width: 38%; height: 45px;  text-align: center; color: #fff;" >
      </el-date-picker>
            <el-date-picker
        v-model="value2"
        type="date"
        placeholder="Pick a day" style="padding: 1px 2px;width: 38%; height: 45px;  text-align: center; color: #fff;" >
      </el-date-picker>

      <el-button
        :loading="loading"
       type="primary"  @click="search()"  plain icon="el-icon-search" 
       style="padding: 1px 2px;width: 14%; height: 40px;  text-align: center;"
       >검색</el-button>


             <el-button
        :loading="loading"
       type="primary"  @click="reading('12345678901234567890','12345678901234567890' )"  plain icon="el-icon-search" 
       style="padding: 1px 2px;width: 14%; height: 40px;  text-align: center;"
       >추가</el-button>

      </div>

        <div>
          <RfidRead v-for="RfidRead in RfidReads"  :key="RfidRead.id" 
          :RfidReads="RfidRead"/>
        </div>


        <div>
          <ShopSale v-for="ShopSale in ShopSales"  :key="ShopSale.id" 
          :ShopSales="ShopSale"/>
        </div>

      </div>
        

    <div class="footer">
      <div>
        <el-button 
        :loading="loading"
        type="primary"  @click="save()" plain 
        style="padding: 1px 2px;width: 102%; height: 40px;  text-align: center;"
        >SAVE</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import RfidRead from '@/components/RfidRead.vue';
import ShopSale from '@/components/ShopSale.vue';
import Axios from 'axios';





export default {
 
  created() {
    window.getTag = this.addTodo
    window.reading = this.reading

  },
  components: {
    RfidRead ,
    ShopSale
  },
  data(){
    return {  
      menuName : this.$store.menuName,
      gubun : this.$store.gubun,

      loading: false,
      RfidReads :[
      ],
      ShopSales :[
      ],
      epcs :[
      ],
      show: true,
      options: [{
          value: 'KS',
          label: 'KS'
        }, {
          value: 'LC',
          label: 'LC'
        }, {
          value: 'WA',
          label: 'WA'
        }, {
          value: 'BW',
          label: 'BW'
        }
        ],
        value: '',
        storeCode: '',
      
        
        pickerOptions: {
          disabledDate(time) {
            return time.getTime() > Date.now();
          }
        },
        value1: '',
        value2: '',
    } // return 
  },  // data
  
  methods:{

    reading(tag, readEpc){

      if (this.gubun == 1)
      {
        for (var i=0; i < this.RfidReads.length ; i++)
        {
          if ( this.RfidReads[i].style.trim() == tag.substring(2,12).trim() &&
              this.RfidReads[i].color.trim() == tag.substring(12,15).trim()  &&
              this.RfidReads[i].size.trim() == tag.substring(15,18).trim()  &&
              this.RfidReads[i].orderDegree.trim() == tag.substring(18,20).trim()
          ){

            // 중복확인
            flag = true;

            for(var epc in this.epcs){
                if (this.epcs[epc] == readEpc)
                  flag = false;
            }

            console.log(flag);

            // 중복된 데이터가 없을경우 만 
            if (flag == true)
            {
                this.RfidReads[i].readCount = this.RfidReads[i].readCount + 1;
                this.epcs.push( 
                  readEpc
                ),
                window.KolonWebApp.playSound('success')
                //console.log(this.epcs);
            }
          }
        }
        // sale
      }else if  (this.gubun == 2) {
          // 중복확인
          var flag = true;
          for( epc in this.epcs){
              if (this.epcs[epc] == readEpc)
                flag = false;
          }

          

          // 중복된 데이터가 없을경우 만 
          if (flag == true)
          {
              this.addShopSale('sssss', 
                      'color',
                      'size',
                      '200,000'
                      );
              this.epcs.push( 
                  readEpc
                )
          }
      }
    },
    async search(){
      this.loading = true;

      let config = {
            headers: { 'content-type': 'application/json' },
      }
      const json = { 'status': null , 'storeCode': 2453 , 'brand': 'CW', "startDate" : "2020-05-15" , "endDate" : "2020-05-15"};
      let url = "";

      if (this.menuName == 'shopIn')
      {
        console.log(this.menuName);

        url = this.$url + '/rfidInfo/storeInGroupList';
      }else if (this.menuName == 'Inventory Check')
      {
        url = this.$url + '/rfidInfo/storeInventoryGroupList';
      }else {

        this.loading = false;
        return ;
      }


      
      await Axios.post(url, json, config).then(res => { 
        console.log(res.data.storeInGroupListDTO);
        var resDataJson = res.data.storeInGroupListDTO;
        if (res.data.resultCode)
        {
          this.RfidReads = [];
          this.epcs = [];
          
                
          for(var resData in resDataJson){
                console.log(resDataJson[resData]['style']);

          this.addTodo(resDataJson[resData]['style'], 
                      resDataJson[resData]['color'],
                      resDataJson[resData]['size'],
                      resDataJson[resData]['order_degree'],
                      resDataJson[resData]['count']
                      );
          }
        }else{
          this.$message.error("no data found");
          }
      }) ;
        this.loading = false;
    },
    async save(){
      this.loading = true;
      let config = {
            headers: { 'content-type': 'application/json' },
      }

      var req = new Object();
      req.status = "10";
      req.epc = this.epcs;
      // var stringJson = JSON.stringify(req);
      // console.log(stringJson);

      await Axios.post( this.$url + '/rfidInfo/statusChange', req , config).then(res => {
          console.log(res.data);

          if(res.data.resultCode)
          {
            this.$message.success("Saved");
          }else {
            this.$message.error("fail");
          }
          this.epcs = [];
      }) ;
      
      this.loading = false;
    },


    addTodo(style, color, size, orderDegree,  count){
        this.RfidReads.push( {
          id: Math.random(),
          style: style  ,
          color: color,
          size:  size ,
          orderDegree: orderDegree ,
          count: count,
          readCount : 0,
          checked: false
        });

    },

    addShopSale(style, color, size, price){
        this.ShopSales.push( {
          id: Math.random(),
          style: style  ,
          color: color,
          size:  size ,
          price: price ,
          checked: false
        });
    },
  } // methods 

}
</script>
<style scoped>
.rfidRead {
  justify-content: center;
  align-items: center;
}
</style>


<style lang="scss" scoped>
  .brand-box {
    padding: 1px 2px;
    margin-bottom: 1px;
    width: 45%;
    height: 45px;
    text-align: left;
    color: #fff;
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
