import moment from "moment";

// 数字转换百分数
export function changeDecimalToPercentage(data) {
    let temp = (data*100).toFixed(4);
    return temp;
  }

// 格式化时间
export function formatTime(origintime,type){
    //后端返回的是T16:00:00.000,
    //北京时间：2020-01-14 00:00:00对应的国际标准时间格式为：2020-01-13T16:00:00.000Z
    return moment(origintime).format(type);


}

// 科学计数法转换数字
export function changeSciNum(num){
    //处理非数字
    if(isNaN(num)){return num};
    //处理不需要转换的数字
    var str = ''+num;
    if(!/e/i.test(str)){return num;};
    
    return (num).toFixed(18).replace(/\.?0+$/, "");

    
}