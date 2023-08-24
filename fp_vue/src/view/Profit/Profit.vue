<template>
  <div>
    <div class="profitbox">
      <div class="totalprofit">
        <h3>Total Profit : {{ totalprofit }}</h3>
        <el-button type="primary" @click="isShowDetail=!isShowDetail">Profit Detail</el-button>
      </div>
      <div class="profitdetail" v-show="isShowDetail" >
        <div class="profitdetail-content" >
        <ul class="detailbox">
          <li v-for="(item,index) in profitarray" :key="index"> {{ item.name }} profit is: {{ item.profit }}</li>
        </ul>
        </div>
      </div>
    </div>
    <h3>Profit Line</h3>
    <!-- input -->
    <el-input 
            v-model="searchid" 
            placeholder="Search ID"
            prefix-icon="el-icon-search"
      ></el-input>
      <el-button type="primary" @click="clickSearchBtn">Search</el-button>

    <chart-example className="profitLineClass" :option="profitLineOption" :width="100+'%'" :height="500+'px'" v-if="isReset"></chart-example>
  </div>
</template>

<script>
import chartExample from '@/components/dataCharts/chartExample.vue'
import {profitLine} from '../../util/chartOptions'
import { formatTime,changeSciNum} from '@/util/tool'
export default {
  name:"profit",
  components:{chartExample},

  data(){
    return{
      totalprofit:0,
      isShowDetail:false,
      profitLineOption:profitLine,
      profitarray:[
      ],
      searchid:'',
      isReset:false,
      xvalue:[],
      yvalue:[],
      isinit:true
    }
  },
  mounted(){
    this.getProfitbyId(1);
    this.getProfitDetail();
    this.isinit =false;
  },

  methods:{

    async getProfitDetail(){
      let res =await this.$request.get('/api/product/fundsprofit',{});

      for(let i in res.data){
        if(i!=res.data.length-1){
          let tempitem ={'name':res.data[i][1],'profit':changeSciNum(Number(res.data[i][2]))};
          this.profitarray.push(tempitem);
        }

      }
      this.totalprofit =res.data[res.data.length-1][2];
      this.totalprofit =this.totalprofit.toFixed(4);
    },

    clickSearchBtn(){
      this.getProfitbyId(this.searchid);
    },
    async getProfitbyId(id){
    //TODO 根据input的id值，去后端请求对应数据，并保存最新价格
      let res =await this.$request.get('/api/product/profitrate',{
        productId:id
      });
      let [tempx,tempy]=[[],[]];
      for(let i in res.data){
        let temp =formatTime(res.data[i][0],'YYYY-MM-DD');
        tempx.push(temp);
        tempy.push(res.data[i][1]);
      }
      this.xvalue =tempx.reverse();
      this.yvalue =tempy.reverse();

      if(!this.isinit){
        this.updateChartData();
      }
    },
    updateChartData(){
    // 先销毁组件
      this.isReset =false;
      // 更新option
      this.profitLineOption.title.text ='Profit Line';
      this.profitLineOption.xAxis.data =this.xvalue;
      this.profitLineOption.series[0].data =this.yvalue;
      // 重建组件
      this.$nextTick(() => {
        this.isReset = true; 
      });
    },
  }

}
</script>

<style scoped>
.profitbox{
  display: flex;

}
.totalprofit{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 30%;
  height: 150px;
  background-color: #fff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 30px;
}

.profitdetail{
  width: 65%;
  margin-left: 5%;
  background-color: #fff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 30px;
  overflow: hidden;
}
.profitdetail-content{
  height: 150px;
  overflow-y: auto;
}
.detailbox{
  margin: 20px 0 20px 20px;
  height: 150px;
}
.detailbox li{
  line-height: 20px;
}

.el-input{
  width: 400px;
  margin-right: 40px;
}

</style>