import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex)

const actions ={}
const mutations ={
    // 更新当前资产信息
    updateCurAsset(state,asset){
        state.curAsset =asset
    },
    // 更新花费资产信息
    updateCostAsset(state,asset){
        state.costAsset =asset
    }
}
const state ={
    // current asset
    curAsset:'',
    // cost asset 花费资产
    costAsset:'',
}

const store =new Vuex.Store({
    actions:actions,
    mutations:mutations,
    state:state
})


export default store