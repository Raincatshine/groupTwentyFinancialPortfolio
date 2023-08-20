<template>
    <div class="headerbar">
        <div class="left">
            <i class="el-icon-s-marketing"></i>
            <span>Financial Portfolio </span>
        </div>
        <div class="center">
            <el-input 
            v-model="searchInput" 
            placeholder="Search"
            prefix-icon="el-icon-search"
            ></el-input>
        </div>
        <div class="right">
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
            <span class="username">username</span>
            <i class="el-icon-bell"></i>
        </div>
    </div>
  
</template>

<script>
export default {
    name:"headerBar",
    data(){
        return{
            searchInput:""
        }
    }

}
</script>

<style scoped>
.headerbar{
    display: flex;
    width: 100%;
    height: 100%;
    background-color: #F5F6FD;
}
.headerbar .left{
    display: flex;
    align-items: center;
    width: 20%;
}
.left i{
    margin-left: 40px;
    font-size: 40px;
}
.left span{
    color: #888888;
    font-size: 20px;
}
.headerbar .center{
    /* margin-right: 300px; */
    width: 55%;
}
.el-input{
    width: 410px;
    margin: 20px 50px;

}
.el-input /deep/ .el-input__inner{
    border-radius: 30px;
}
.headerbar  .right{
    display: flex;
    align-items: center;
    width: 25%;
}
.el-avatar{
    margin-left: 120px;
}

.right .username{
    color: #888888;
    padding-left: 10px;
}
.right i{
    padding-left: 100px;
    font-size: 30px;
}
</style>