<template>
  <el-container class="Container">
    <!-- header -->
    <el-header height="82px">
      <header-bar></header-bar>
    </el-header>
    <!-- 间隔线条 -->
    <div class="line"></div>
    <!-- 中间主体 -->
    <el-container class="mainContainer">
      <!-- 左侧边栏 -->
      <el-aside width="320px">
        <el-menu
        background-color="#0F337C"
        text-color="#fff"
        active-text-color="#fae173"
        :default-active="defaultActive"
         router>
          <el-menu-item index="/financialinfo">
            <i class="el-icon-s-home"></i>
            <span>Index</span>
          </el-menu-item>
          <el-menu-item index="/profit">
            <i class="el-icon-s-data"></i>
            <span>Profit</span>
          </el-menu-item>
          <el-menu-item index="/fundsdetail">
            <i class="el-icon-s-management"></i>
            <span>Funds</span>
          </el-menu-item>
          <el-menu-item index="/fundsmarket">
            <i class="el-icon-s-shop"></i>
            <span>Market</span>
          </el-menu-item>
          </el-menu>
      </el-aside>
      <!-- 右侧主体 -->
      <el-main>
        <router-view class="routeview"></router-view>
      </el-main>
    </el-container> 
  </el-container>
</template>

<script>
import headerBar from './headerBar/headerBar.vue'
export default {
  name:"Index",
  components: { headerBar },

  data(){
    return{
      defaultActive:""
    }
  },
  methods:{
    
  },
  created(){
    this.defaultActive =this.$route.path;
    // console.log(this.defaultActive);
  }

}
</script>

<style scoped>


.mainContainer{
  width: 100vw;
  height: calc(100vh - 84px);
}
.line{
  height: 2px;
  width: 100%;
  background-color: #ECE7FF;
}
.el-aside{
  background-color: #0F337C;
}
.el-menu-item{
  height: 80px;
  font-size: 16px;
  padding: 10px 0 10px 40px !important;
}
.el-menu-item i{
  font-size: 24px;
  color: rgb(240, 240, 240);
  padding-right: 5px;
}
.el-menu-item:hover{
  background-color: #ffffff5b !important;
  color: #0F337C !important;
}

.el-main{
  background-color: #F5F6FD;
}
.routeview{
  padding: 0 65px;
  height: 100%;
  overflow-y: scroll;
}
</style>