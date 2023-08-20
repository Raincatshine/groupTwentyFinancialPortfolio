import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const Index =()=>import("../components/Index.vue")
const FinancialInfo =()=>import("../view/financialInfo/financialInfo.vue")
const Profit =()=>import("../view/Profit/Profit.vue")
const fundsDetail =()=>import("../view/fundsDetail/fundsDetail.vue")
const fundsMarket =()=>import("../view/fundsMarket/fundsMarket.vue")

const routes =[
    // /--->index
    {
        path:'/',
        redirect:'/index'
    },
    // index ---->all views
    {
        path:'/index',
        component:Index,
        name:'index',
        redirect:'/financialinfo',
        children:[
            {
                path:'/financialinfo',
                component:FinancialInfo,
                name:'financialInfo'
            },
            {
                path:'/profit',
                component:Profit,
                name:'Profit'
            },
            {
                path:'/fundsdetail',
                component:fundsDetail,
                name:'fundsDetail'
            },
            {
                path:'/fundsmarket',
                component:fundsMarket,
                name:'fundsMarket'
            }
        ]
       
    }
]

const router =new VueRouter({
    routes,
    mode:'history'
})

export default router