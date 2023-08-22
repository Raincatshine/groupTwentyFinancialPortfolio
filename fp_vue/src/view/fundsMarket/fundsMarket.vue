<template>
  <div>
    <h3>Total Assets : {{ tasset }}</h3>
    <div class="searchbox">
    <!-- input -->
    <el-input 
            v-model="searchid" 
            placeholder="Search ID"
            prefix-icon="el-icon-search"
            type="number"
      ></el-input>
      <el-button type="primary" @click="clickSearchBtn">Search</el-button>
      <el-button type="primary" @click="clickResetBtn">Reset</el-button>
    </div>

    <h3>FundsMarket</h3>
    <div class="fundsarea">
        <el-table 
    :data="marketfunds.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
    stripeas
    border
    class="fundstb"
    :header-cell-style="{'text-align':'center','color':'#3a63b4'}"
    :cell-style="{'text-align':'center'}"
    >
      <el-table-column label="ID" prop="id" width="120"></el-table-column>
      <el-table-column label="Name" prop="fundsName" width="450"></el-table-column>
      <el-table-column label="updateTime" prop="updateTime" width="260"></el-table-column>
      <el-table-column label="Price" prop="currentPrice" width="170"></el-table-column>
      <el-table-column label="GrowthRate" prop="growthRate" width="170"></el-table-column>
      <el-table-column
      fixed="right"
      label="Operation"
      width="180">
      <template slot-scope="scope">
        <el-button type="primary" plain class="buyBtn" @click="clickBuyBtn(scope.$index)">Buy</el-button>
        <!-- 这一组是直接写table里的，所以比包在div里的dialog显得外观有点问题...再继续深入研究 -->
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
        :page-sizes="[10, 20]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="marketfunds.length"
    >
    </el-pagination>
    </div>
    </div>

  </div>
</template>

<script>
import { formatTime } from '@/util/tool';
export default {
    name:"fundsMarket",

    data(){
        return{
            marketfunds:[ ],
            selectIndex:0,
            buyFormVisible:false, 
            buyform:{
                count:0
            },
            tasset:0,
            searchid:'',
            // 分页
            currentPage: 1, // 当前页码
            pageSize: 10, // 每页的数据条数
        }
    },

    created(){
        this.getAllFunds();
        this.tasset =this.$store.state.tasset;

    },

    methods:{
        async getAllFunds(){
            //请求所有市场基金
            //更新更新marketfunds
            let res =await this.$request.get('api/product/allproducts',{});
            res.data.forEach((item)=>{
                item.updateTime =formatTime(item.updateTime,'YYYY-MM-DD');
                // console.log(item.updateTime);
            })
            
            
            this.marketfunds =res.data;
            // console.log(this.marketfunds.length);
            
        },

        clickBuyBtn(index){
            this.selectIndex=index+(this.currentPage-1)*this.pageSize;
            this.buyFormVisible =true;
            // console.log(this.marketfunds[this.selectIndex].id);
        },

        async buyFund(){
            //传id、count给后台，增加
            
            let res =await this.$request.postWithParams('api/financialinfo/buy',{
                id:this.marketfunds[this.selectIndex].id,
                fundsCount:this.buyform.count
            });
            

            if(res.data){
                //返回true
                this.$message.success('Buy Fund Successfully');
                this.buyFormVisible =false;
                
            }
        },
        clickSearchBtn(){
            if(this.searchid){
                this.getFundById(this.searchid);
            }
            
        },
        async getFundById(id){
            //传id向后台请求数据
            //更新marketfunds
            //用模板字符串在url里进行拼接
            let res =await this.$request.post(`api/product/allproducts/${id}`,id);
            res.data.updateTime =formatTime(res.data.updateTime,'YYYY-MM-DD');
            this.marketfunds =[res.data];

        },
        clickResetBtn(){
            this.getAllFunds();
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
            let cost =this.marketfunds[this.selectIndex].currentPrice * this.buyform.count;
            if(cost>=0){
                return cost.toFixed(2);
            }else{
                this.$message("Error,count can't be nagetive");
                return 0;
            }
        }
    },
    watch:{
        buyFormVisible(){
            // 弹窗只要变化，数量就清零
            this.buyform.count =0
        },
    }

}
</script>

<style scoped>

.fundsarea{
    background-color: #fff;
    border-radius: 30px;
    margin-top: 30px;
    overflow: hidden;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)

}
.buyBtn{
    font-size: 16px;
}

.fundstb{
    width: 96%;
    margin: 20px auto;
}
.searchbox{
    width: 50%;
    display: flex;
    justify-content: space-around;
}
.el-input{
    width: 410px;

}
</style>