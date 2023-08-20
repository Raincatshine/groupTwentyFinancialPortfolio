import axios from "axios"

const instance =axios.create({
        // 这里使用了proxy
        baseURL: '/api',
        timeout: 30000,
        withCredentials: true,
        // headers:{
        //     post:{
        //         "Content-Type":'application/x-www-form-urlencoded;charset=UTF-8'
        //     }
        // }
        
})

// 请求拦截
instance.interceptors.request.use(config=>{
     // config为AxiosRequestConfig的一个实例，它是包含请求配置参数的对象
    // 在这里可以在请求发送前做一些处理，如向config实例中添加属性，取消请求，设置loading等  
    return config
},err=>{
    // 这里是请求报错时的拦截方法，这里直接返回一个状态为reject的promise
    console.log(err)
    return Promise.reject(err);    
})

// 响应拦截
instance.interceptors.response.use(response=>{
    // 响应成功的场景
    // 在这里可以关闭loading或者对响应的返参对象response进行处理
    return response

},err=>{
    // 响应失败的场景
    // http状态码不为2xx时就会进入，根据项目要求处理接口401，404，500等情况
    // this.$message(err.response.data.msg);
    console.log(err)
    return Promise.reject(err)
})


/** 
 * get方法，对应get请求 
 * @param {String} url [请求的url地址] 
 * @param {Object} params [请求时携带的参数] 
 **/
export default{
    get(url,params){
        return instance({
            method:'get',
            url,
            params
        })
    },
    post(url,data){
        // console.log(data);
        return instance({
            method:'post',
            url,
            data
        })
    },
    postWithParams(url,params){
        // console.log(data);
        return instance({
            method:'post',
            url,
            params
        })
    }
}