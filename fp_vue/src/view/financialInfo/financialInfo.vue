<template>
  <div>
    <accountInfo class="accountInfo"></accountInfo>
    <fundsList class="fundsList"></fundsList>
    <!-- <dataCharts class="dataCharts"></dataCharts> -->
    <h3>DashBoard</h3>
    <div class="chartBox">
      <chartExample className="propertyPieClass" :option="propertyPieOption" v-if="isReset"></chartExample>
      <chartExample className="countLineClass" :option="countLineOption"></chartExample>
    </div>
    
  </div>

</template>

<script>
import accountInfo from '../../components/accountInfo/accountInfo.vue'
import fundsList from '../../components/fundsList/fundsList.vue'
import dataCharts from '../../components/dataCharts/dataCharts.vue'
import chartExample from '../../components/dataCharts/chartExample.vue'
import {countLine,propertyPie} from '../../util/chartOptions'
import {changeDecimalToPercentage} from '../../util/tool'

export default {
  name:'financialInfo',
  components:{accountInfo,fundsList,dataCharts,chartExample},

  data(){
    return {
      countLineOption:countLine,
      propertyPieOption:propertyPie,
      countLineXais:[],
      piedata:[],
      isReset:false
    }
  },
  
  created(){
    this.countLineXais =['test1','test2'];
    this.countLineOption.xAxis.data =this.countLineXais;
    this.updateProperyPieData();
  },

  methods:{
    updateCountLineData(){
      //TODO 向后台请求数据并更新options的数据
    },
    updateProperyPieData(){
      //TODO 从vuex中取出数据
      // 先销毁组件
      this.isReset =false;
      // 更新option
      let curpercent =this.$store.state.curAsset;
      let costpercent =this.$store.state.costAsset;
      this.piedata =[
        {value:curpercent,name:"Current Assets"},
        {value:costpercent,name:"Expense Assets"}
    ],
      this.propertyPieOption.series[0].data =this.piedata;
      // 重建组件
      this.$nextTick(() => {
        this.isReset = true; 
      });
     
    }
  },
  watch:{
    '$store.state.curAsset'(){
      this.updateProperyPieData();
    }
  }

}
</script>

<style scoped>

.accountInfo{
  margin: 30px 0;
}
.fundsList{
  margin: 30px 0;
}
.chartBox{
  display: flex;
  justify-content: space-between;
  width: 100%;
}


</style>