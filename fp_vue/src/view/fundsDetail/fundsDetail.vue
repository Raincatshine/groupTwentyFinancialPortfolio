<template>
  <div class="maincontainer">
    <div class="container">
      <el-select v-model="listindex" placeholder="Holding products" @change="getFundPrice($event)" >
        <el-option
          v-for="item in funds"
          :key="item.index"
          :label="item.label"
          :value="item.index">
        </el-option>
    </el-select>

    <!-- input -->
      <el-input 
            v-model="searchid" 
            placeholder="Search"
            prefix-icon="el-icon-search"
      ></el-input>
      <el-button type="primary" @click="clickSearchBtn">Search</el-button>

      <el-button type="primary"  @click="clickBuyBtn()">Buy</el-button>
        <el-dialog title="Buy Funds" :visible.sync="buyFormVisible" :modal="false">
              <el-form :model="buyform">
                <el-form-item label="buy count" >
                  <el-input v-model="buyform.count" autocomplete="off" type="number"></el-input>
                </el-form-item>
                <el-form-item label="cost money" >
                  {{cost}}
                </el-form-item>
              </el-form>
              
              <div slot="footer" class="dialog-footer">
                <el-button @click="buyFormVisible = false" >Cancel</el-button>
                <el-button type="primary" @click="buyFund()" >Buy</el-button>
              </div>
            </el-dialog>
    </div>
    
  <chart-example className="priceLineClass" :option="priceLineOption" :width="100+'%'" v-if="isReset"></chart-example>
  </div>
</template>

<script>
import chartExample from '@/components/dataCharts/chartExample.vue';
import { priceLine } from '@/util/chartOptions';
import { formatTime } from '@/util/tool';

export default {
  name:"fundsdetail",
  components:{chartExample},
  data(){
    return {
      priceLineOption:priceLine,
      xvalue:[],
      yvalue:[],
      isReset:true,

      searchid:'',
      choosetype:0, //判断此时选择是list里的id还是搜索得到的id,0是list，1是搜索
      // 目前持有的基金
      funds:[
        {
        index:1,
        label:'基金1'
      },
      {
        index:2,
        label:'基金2'
      }],
      listindex:'',
      buyFormVisible:false,
      buyform:{
        count:0
      },
      // 对应id基金的最新价格，也是购买价格
      currentPrice:0,
      buyid:0,
      coust:0,
      isinit:true
    }
  },

  created(){
    // 默认呈现id为1的基金价格曲线
    this.getFundbyId(1);
    this.getHoldingFunds();
    this.isinit =false;
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

      this.funds =temarr;
    },

    clickSearchBtn(){
      this.choosetype =1;
      this.getFundbyId(this.searchid);
    },

    async getFundbyId(id){
    //TODO 根据input的id值，去后端请求对应数据，并保存最新价格
    let res =await this.$request.get('/api/product/fundsprice',{
      productId:id
    });
    let [tempx,tempy] =[[],[]];
    for(let i in res.data){
      let temp =formatTime(res.data[i][0],'YYYY-MM-DD');
      tempx.push(temp);
      tempy.push(res.data[i][1]);
    }
      tempx=tempx.reverse();
      tempy =tempy.reverse();
      this.xvalue =tempx;
      this.yvalue =tempy;

    if(!this.isinit){
      this.updateChartData();
    }

    this.currentPrice =this.yvalue[this.yvalue.length-1];
    },

  // 通过list获取价格折线图
    getFundPrice(e){
       //基于listindex，get请求获得返回的数据
      this.choosetype =0;
      this.getFundbyId(this.listindex);
    },

    updateChartData(){
    // 先销毁组件
    this.isReset =false;
    // 更新option
    this.priceLineOption.xAxis.data =this.xvalue;
    this.priceLineOption.series[0].data =this.yvalue;
    // 重建组件
    this.$nextTick(() => {
        this.isReset = true; 
      });
    },

  // 点击购买btn
    clickBuyBtn(){
    this.buyFormVisible =true;
    this.buyid =this.choosetype?this.searchid:this.listindex; //根据choosetype填充buyid

    },
    async buyFund(){
    //根据buyid和count，更新后台
     //传id、count给后台，增加
     let res =await this.$request.postWithParams('api/financialinfo/buy',{
                id:this.buyid,
                fundsCount:this.buyform.count
            });

            if(res.data){
                //返回true
                this.$message.success('Buy Fund Successfully');
                this.buyFormVisible =false;
                this.$store.commit('updateCurAsset',this.$store.state.curAsset- Number(this.cost));
                this.$store.commit('updateCostAsset',this.$store.state.costAsset + Number(this.cost));
            }
            
    } 
  },

  computed:{
    cost(){
        let cost =this.currentPrice * this.buyform.count;
        if(cost>=0){
            return cost;
        }else{
            this.$message("Error,count can't be nagetive");
            return 0;
        }
    }
    },
}

</script>

<style scoped>

.maincontainer{
  margin-top: 40px;
}
.container{
  display: flex;
  width: 70%;
  justify-content: space-around;
}

.el-input{
    width: 410px;

}
/* .el-input /deep/ .el-input__inner{
    border-radius: 30px;
} */

.el-select{
  width: 300px;

}

</style>