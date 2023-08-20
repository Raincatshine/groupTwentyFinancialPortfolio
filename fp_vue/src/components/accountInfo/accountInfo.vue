<template>
  <div class="accountInfo">
    <h3 class="infotitle">My Accounts</h3>
    <div class="infocontainer">
        <div class="info infoleft">
          <div class="avatarbox">
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            :size="120"
            ></el-avatar>
          </div>
          <div class="infobox">
            <h3>User : Room20</h3>
            <h3>Role : User</h3>
          </div>
        </div>
        <div class="info infocenter">
          <div class="assetbox">
            <h3>Current Assets - ￥{{ casset }}</h3>
            <h3>Expense Assets - ￥{{ easset }}</h3>  
          </div>
          <div class="btngroup">
            <el-button type="primary" @click="moneyFormVisible=true">Operator Money</el-button>
            <el-dialog title="Operator Money" :visible.sync="moneyFormVisible" >
              <el-form :model="moneyform">
                <el-form-item label="Money" >
                  <el-input v-model="moneyform.count" autocomplete="off" type="number"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="moneyFormVisible = false" >Cancel</el-button>
                <el-button type="primary" @click="addMoney" >Injection</el-button>
                <el-button type="warning" @click="withdrawMoney" >Widhdraw</el-button>
              </div>
            </el-dialog>
            <!-- <el-button type="danger" plain class="addBtn" @click="openWdForm">Withdraw Money </el-button> -->
          </div>

        </div>
        <div class="info inforight">
          <h3>Holding Products </h3>
          <h4>Funds : {{ fundscount }}</h4>
          <h4>Stocks : 0</h4>
          <h4>Debits : 0</h4>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name:"accountInfo",
  data(){
    return{
      casset:0,
      easset:0,
      fundscount:0,
      moneyFormVisible:false,
      moneyform:{
        count:''
      }
    }
  },
  mounted(){
    this.getAccount();
  },

  methods:{
    async getAccount(){
      let res =await this.$request.get('/api/financialinfo/account',{});
      let tempcur =res.data.currentAssets;
      tempcur =tempcur.toFixed(2);
      let tempcost =Math.abs(res.data.expenseAssets);
      tempcost =tempcost.toFixed(2);
      this.fundscount =res.data.fundsTypeCount;
      this.$store.commit('updateCurAsset',Number(tempcur));
      this.$store.commit('updateCostAsset',Number(tempcost));
      this.casset =this.$store.state.curAsset;
      this.easset =this.$store.state.costAsset;
    },

    updateAsset(){
      // 更新data中的数据
        this.casset =(this.$store.state.curAsset).toFixed(2);
        this.easset =(this.$store.state.costAsset).toFixed(2);

    },

    async addMoney(){
      let res =await this.$request.postWithParams('/api/financialinfo/injection',{
        injectionAmount:this.moneyform.count
      })
      if(res.status ===200){
        this.$message.success('Inject money successfully');
        this.moneyFormVisible =false;
        this.$store.commit('updateCurAsset',Number(res.data.currentAssets));
      }

      
    },
    async withdrawMoney(){
      let res =await this.$request.postWithParams('/api/financialinfo/withdraw',{
        withdrawAmount:this.moneyform.count
      })
      if(res.status ===200){
        this.$message.success('Withdraw money successfully');
        this.moneyFormVisible =false;
        this.$store.commit('updateCurAsset',Number(res.data.currentAssets));
      }
    }
  },

  watch:{
    moneyFormVisible(){
      this.moneyform.count ='';
    },
    '$store.state.curAsset'(){
      this.updateAsset();
    }
  }
}
</script>

<style scoped>
.infocontainer{
    width: 100%;
    display: flex;
    justify-content: space-between;
    margin: 30px 0;
}
.info{
    height: 160px;
    width: 443px;
    background-color: #fff;
    border-radius: 30px;
    border-color: F0F0F0;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}
.infoleft{
  display: flex;
  align-items: center;  
}

.avatarbox .el-avatar{
  margin-left: 40px;
}
.infobox{
  display: flex;
  flex-direction: column;
  margin-left: 40px;
}
.infocenter{
  display: flex;
  align-items: center;
}
.infocenter h3{
  margin-left: 40px;
}

.assetbox{
  width: 200px;
}
.infocenter .btngroup{
  width: 150px;
  height: 120px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  margin-left: 30px;
}
.btngroup .el-button+.el-button{
  margin: 0;
}


.inforight h3{
  text-align: center;
}
.inforight h4{
  margin: 10px 0 10px 40px;
}


</style>