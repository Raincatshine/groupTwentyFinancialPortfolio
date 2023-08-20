<!-- 尝试整体封装echarts图表 -->
<template>
  <div :class="className"  :style="{width:width,height:height}"></div>
</template>

<script>

export default {
    name:"chartExample",
    props:{
        className: {
        type: String,
        default: 'main'
        },
        width: {
            type: String,
            default: '48%'
        },
        height: {
            type: String,
            default: '400px'
        },
        option: {
        type: Object,
        default:()=>{return null}
        },
        
    },

    data(){
        return{
            mychart:null
        }
    },

    methods:{
        initCharts(){
            this.mychart =this.$echarts.init(this.$el);
            this.setOption()
        },
        setOption(){
            this.mychart && this.option && this.mychart.setOption(this.option)
        }
    },
    mounted(){
        this.$nextTick(()=>{
            this.initCharts()
        })
    },
    
    beforeDestroy(){
        this.mychart && this.mychart.dispose()
        this.mychart =null
    },


    watch:{
        option(){
            console.log('option change')
            this.mychart && this.setOption()
        }
    }
}
</script>

<style scoped>
div{
    margin-top: 30px;
    background-color: #fff;
    border-radius: 30px;
    overflow: hidden;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}

</style>