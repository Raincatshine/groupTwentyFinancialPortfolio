// 后面可以改一下，封装各种类型的图的配置，而不是现在这种针对每个需求的配置
// 数量折线图
export const countLine ={
    title:{
        text:"Hoding Counts",
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
    // legend:{
    //     data:["Count"],
    //     top:20,
    //     right:200
    // },
    xAxis:{
        data:[0]
    },
    yAxis:{
       
    },
    series:[
        {
            name:"销量",
            type:"line",
            data:[0]
        }
    ]
}

// 资产饼图
export const propertyPie = {
    title: {
        text: 'Assets',
        top:20,
        left: 'center'
      },
      tooltip: {
        // trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 40,
        top:20
      },
      
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: '50%',
          data: [
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
  };

  // 收益率折线图
  export const profitLine = {
    title:{
      text:"Price Line",
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
          data:["ProfitRate"],
          top:20,
          right:200
      },
      xAxis:{
          data:[]
      },
      yAxis:{
        
      },
      series:[
          {
              name:"ProfitRate",
              type:"line",
              data:[]
          }
      ]
  };

  // 价格曲线
  export const priceLine ={
    title:{
        text:"Price Line",
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
        data:["Price"],
        top:20,
        right:200
    },
    xAxis:{
        data:[]
    },
    yAxis:{
       
    },
    series:[
        {
            name:"Price",
            type:"line",
            data:[]
        }
    ]
}