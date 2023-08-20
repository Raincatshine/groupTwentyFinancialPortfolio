<template>
  <div>
    <span>dashBoard</span>
    <div id="main"></div>
  </div>
</template>

<script>
export default {
    name:"dataCharts",

    data(){
        return{

        }
    },

    methods:{
        drawCharts(){
            // 基于准备好的dom，初始化echarts实例
            let mycharts =this.$echarts.init(document.getElementById("main"));
            let options ={
                title:{
                    text:"test charts",
                    left:"center",
                    top:20,
                    textStyle:{
                        fontweight:"bold"

                    }
                },
                tooltip:{},
                grid:{
                    left: "5%",
                    top: "20%",
                    right: "5%",
                    bottom: "10%",
                    containLabel:true

                },
                legend:{
                    data:["销量"],
                    top:"center",
                    right:15
                },
                xAxis:{
                    data:["衬衫","羊毛"]
                },
                yAxis:{
                   
                },
                series:[
                    {
                        name:"销量",
                        type:"bar",
                        data:[10,20]
                    }
                ]
            };
            
            mycharts.setOption(options);

        },

    },

    mounted(){
        this.drawCharts();
    }


}
</script>

<style scoped>

#main{
    width: 100%;
    height: 400px;
    margin-top: 30px;
    background-color: #fff;
    border-radius: 30px;
    overflow: hidden;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)

}

</style>