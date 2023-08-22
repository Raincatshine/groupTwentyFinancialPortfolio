<template>
  <div>
    <accountInfo class="accountInfo"></accountInfo>
    <fundsList class="fundsList"></fundsList>
    <!-- <dataCharts class="dataCharts"></dataCharts> -->
    <h3>DashBoard</h3>
    <div class="countlinebox">
        <el-select v-model="listindex" placeholder="Holding products" @change="getFundCount($event)" >
        <el-option
          v-for="item in holdingfunds"
          :key="item.index"
          :label="item.label"
          :value="item.index">
        </el-option>
    </el-select>
      </div>
    <div class="chartBox">
      <chartExample className="propertyPieClass" :option="propertyPieOption" v-if="isReset"></chartExample>
      <chartExample className="countLineClass" :option="countLineOption" v-if="isResetLine"></chartExample>
    </div>
    
  </div>

</template>

<script>
import accountInfo from '../../components/accountInfo/accountInfo.vue'
import fundsList from '../../components/fundsList/fundsList.vue'
import dataCharts from '../../components/dataCharts/dataCharts.vue'
import chartExample from '../../components/dataCharts/chartExample.vue'
import {countLine,propertyPie} from '../../util/chartOptions'
import {formatTime} from '../../util/tool'

export default {
  name:'financialInfo',
  components:{accountInfo,fundsList,dataCharts,chartExample},

  data(){
    return {
      countLineOption:countLine,
      propertyPieOption:propertyPie,
      countLineXais:[],
      piedata:[],
      isReset:true,
      isResetLine:true,
      listindex:'',
      holdingfunds:[]

    }
  },
  
  created(){
    this.getHoldingFunds();
    this.updateProperyPieData();
    this.updateCountLineData([0],[0]);
  },

  methods:{
    async getHoldingFunds(){
      //获取目前持有的基金，并更新funds数组
      let res =await this.$request.get('/api/financialinfo/funds',{});
      let temarr =[];
      for(let i in res.data){
        let obj ={'index':res.data[i].id,'label':res.data[i].fundsName};
        temarr.push(obj);
      }

      this.holdingfunds =temarr;
    },

    async getFundCount(e){
      let res =await this.$request.get('/api/financialinfo/count',{
        operationProductID:this.listindex
      });
      // 过滤重复天数，取当天最后一次操作
      let tempMap =new Map();
      for(let i in res.data){
        res.data[i][0] =formatTime(res.data[i][0],'YYYY-MM-DD');
          tempMap.set(res.data[i][0],res.data[i][1]);
      }
      // 分别取出x、y的值
      let tempx =[],tempy=[]
      tempMap.forEach((value,key)=>{
        tempy.push(value);
        tempx.push(key);
      })
      this.updateCountLineData(tempx,tempy);
    },

    updateCountLineData(xAxis,seriesdata){
      //TODO 向后台请求数据并更新options的数据
      //TODO 从vuex中取出数据
      // 先销毁组件
      this.isResetLine =false;
      // 更新option
      this.countLineOption.xAxis.data =xAxis;
      this.countLineOption.series[0].data =seriesdata;
      // 重建组件
      this.$nextTick(() => {
        this.isResetLine = true; 
      });

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
  height: 450px;
}
.countlinebox{
  margin-left: 52%;
}


</style>