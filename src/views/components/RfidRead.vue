<template>

        <div v-show="show" class="transition-box" >
            style:  {{ RfidReads.style }}   ||  size: {{ RfidReads.size }}     <br>
            color:  {{ RfidReads.color }}   ||  주문차수:  {{ RfidReads.orderDegree }} <br>
            count:  {{ RfidReads.count }}   ||  Read Count:  {{ RfidReads.readCount }} 
             <!--input Type="checkbox" :checked="todoData.checked"-->

        </div>

        

</template>

<script>
export default {
    props:{
        RfidReads:{
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