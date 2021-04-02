<template>

        <div v-show="show" class="transition-box" >
            style:  {{ ShopSales.style }}   ||  size: {{ ShopSales.size }}     <br>
            color:  {{ ShopSales.color }}   ||  price:  {{ ShopSales.price }} <br>
           
             <!--input Type="checkbox" :checked="todoData.checked"-->
         <img src="/img/sale.jpg" width="100">
        </div>

        

</template>

<script>
export default {
    props:{
        ShopSales :{
            type: Object,
            required :true
        }

    },
    data(){
    return {
      show: true
      }
    },
}
</script>


<style>
  .transition-box {
  flex: 1;
  margin-top: 3px;
  display: flex;
  height: 80px;
  width: 330px;
  border-radius: 6px;
  background-color: #E6A23C;
  text-align: left;
  color: #fff;
  padding: 5px 5px;
  box-sizing: border-box;
  /* justify-content: center; */
  align-items: left;
  }
</style>