<template>
  <div>
    <h3>Funds</h3>
    <div class="fundsarea">
        <el-table 
    :data="fundsdata.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
    stripeas
    border
    class="fundstb"
    @selection-change="handleSelectChange"
    :header-cell-style="{'text-align':'center','color':'#3a63b4'}"
    :cell-style="{'text-align':'center'}"
    >
    <el-table-column
      type="selection"
      align="center"
      width="100">
    </el-table-column>
      <el-table-column label="ID" prop="id" width="120"></el-table-column>
      <el-table-column label="Name" prop="fundsName" width="400"></el-table-column>
      <el-table-column label="Count" prop="fundsCount" width="140"></el-table-column>
      <el-table-column label="updateTime" prop="updateTime" width="250"></el-table-column>
      <el-table-column label="Price" prop="currentPrice" width="160"></el-table-column>
      <el-table-column
      fixed="right"
      label="Operation"
      width="180">
      <!-- buy btm -->
      <template slot-scope="scope">
        <el-button type="primary" plain class="addBtn" @click="clickBuyBtn(scope.$index)">Buy</el-button>
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
                <el-button type="primary" @click="buyFund" >Buy</el-button>
              </div>
            </el-dialog>
        <!-- sell btn -->
        <el-button type="danger" plain class="addBtn" @click="clickSellBtn(scope.$index)">Sell</el-button>
        <el-dialog title="Sell Funds" :visible.sync="sellFormVisible" :modal="false">
              <el-form :model="sellform">
                <el-form-item label="sell count" >
                  <el-input v-model="sellform.count" autocomplete="off" type="number"></el-input>
                </el-form-item>
                <el-form-item label="earn money" >
                  {{earn}}
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="sellFormVisible = false" >Cancel</el-button>
                <el-button type="primary" @click="sellFund" >Sell</el-button>
              </div>
            </el-dialog>
      </template>
    </el-table-column>
    </el-table>
         <!-- 分页器 -->
    <div class="block" style="margin-top: 15px">
        <el-pagination
            align="center"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="pageSize"
            :page-sizes="[5, 10]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="fundsdata.length"
        >
        </el-pagination>
    </div>

  </div>
</div>
</template>

<script>
import { formatTime } from '@/util/tool';
export default {
    name:"fundsList",

    data(){
        return{
            fundsdata:[],
            selectedItems:[],
            buyFormVisible:false,
            sellFormVisible:false,
            selectIndex:0,
            buyform:{
                count:0
            },
            sellform:{
                count:0
            },
            // 分页

            currentPage: 1, // 当前页码
            pageSize: 5, // 每页的数据条数
        }
    },

    mounted(){
        this.getFundsInfo();

    },
    methods:{
        // 获取持有基金
        async getFundsInfo(){
            let res =await this.$request.get('/api/financialinfo/funds',{});
            res.data.forEach(item => {
                item.updateTime =formatTime(item.updateTime,'YYYY-MM-DD');
            });

            this.fundsdata =res.data;
        },

        handleSelectChange(val){
        },

        clickBuyBtn(index){
            this.buyFormVisible= true;
            this.selectIndex =index+(this.currentPage-1)*this.pageSize;
            // console.log(this.fundsdata[this.selectIndex].id);
        },
        clickSellBtn(index){
            this.selectIndex =index+(this.currentPage-1)*this.pageSize;
            this.sellFormVisible =true;
            // console.log(this.fundsdata[this.selectIndex].id);
        },
        async buyFund(id){
            //TODO post index和count给接口,更新总资产和余额
            //传id、count给后台，增加
            let res =await this.$request.postWithParams('api/financialinfo/buy',{
                id:this.fundsdata[this.selectIndex].id,
                fundsCount:this.buyform.count
            });

            if(res.data){
                //返回true
                this.$message.success('Buy Fund Successfully');
                this.buyFormVisible =false;
                this.$store.commit('updateCurAsset',this.$store.state.curAsset- Number(this.cost));
                this.$store.commit('updateCostAsset',this.$store.state.costAsset + Number(this.cost));
                // 更新table
                this.getFundsInfo();
            }

        },
        async sellFund(id){
            //TODO post index和count给接口,更新总资产和余额
            let res =await this.$request.postWithParams('api/financialinfo/sell',{
                id:this.fundsdata[this.selectIndex].id,
                fundsCount:this.sellform.count
            });

            if(res.data){
                //返回true
                this.$message.success('Sell Fund Successfully');
                this.sellFormVisible =false;
            }
            
            this.$store.commit('updateCurAsset',this.$store.state.curAsset + Number(this.earn));
            this.getFundsInfo();
        },
         // 分页
        //每页条数改变时触发 选择一页显示多少行
        handleSizeChange(val) {
            // console.log(`每页 ${val} 条`);
            this.currentPage = 1;
            this.pageSize = val;
        },
        //当前页改变时触发 跳转其他页
        handleCurrentChange(val) {
            // console.log(`当前页: ${val}`);
            this.currentPage = val;
        }
    },

    computed:{
        cost(){
            let cost =this.fundsdata[this.selectIndex].currentPrice * this.buyform.count;
            if(cost>=0){
                return cost.toFixed(2);
            }else{
                this.$message("Error,count can't be nagetive");
                return 0;
            }
        },
        earn(){
            let earn =this.fundsdata[this.selectIndex].currentPrice * this.sellform.count;
            if(earn>=0){
                return earn.toFixed(2);
            }else{
                this.$message("Error,count can't be nagetive");
                return 0;
            }
        }
    },
    watch:{
        'sellform.count':{
            handler(newval,oldval){
                if(newval>this.fundsdata[this.selectIndex].count){
                    this.$message('Error,sell count can not greater than holding count');
                    this.sellform.count =this.fundsdata[this.selectIndex].count
                }
            }
        },
        buyFormVisible(){
            this.buyform.count =0
        },
        sellFormVisible(){
            this.buyform.count=0
        }
    }

}
</script>

<style scoped>

.fundsarea{
    background-color: #fff;
    border-radius: 30px;
    margin-top: 30px;
    overflow: hidden;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);

}
.addBtn{
    font-size: 16px;
}

.fundstb{
    width: 96%;
    margin: 20px auto;
}


</style>