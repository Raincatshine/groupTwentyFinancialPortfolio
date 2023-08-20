import moment from "moment";

// 数字转换百分数
export function changeDecimalToPercentage(data) {
    let temp = (data*100).toFixed(4);
    return temp;
  }

// 格式化时间
export function formatTime(origintime,type){
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