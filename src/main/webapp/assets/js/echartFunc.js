/**
 * Created by Administrator on 2018/9/5.
 */

var EChartCl =  function(){
    return {
        initBarChart:function(chartDivId,jsondata){
            //console.log(chartDivId);
            var node= document.getElementById(chartDivId);
            var title = jsondata.title.text;
            $("#"+chartDivId+"_title").text(title+" ("+ jsondata.title.subtext+")");
            //console.log(title);

            jsondata.title.text = "";
            jsondata.title.subtext = "";
            var myChart = echarts.init(node , 'light');
            //myChart.setOption({})
            myChart.setOption(jsondata,true)
        },
        initMap:function(chartDivId,mapdata,areaJson){
            var node= document.getElementById(chartDivId);
            var myChart = echarts.init(node , 'light');
            myChart.showLoading();
            $.get(areaJson, function (geoJson) {
                myChart.hideLoading();
                echarts.registerMap('FJ', geoJson);
                myChart.setOption(option = {
                    title: {
                        text: '设备部署'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{b}'
                    },
                    series: [
                        {
                            name: '设备部署 ',
                            type: 'map',
                            mapType: 'FJ', // 自定义扩展图表类型
                            left: 50,
                            top: 50,
                            //roam: true,
                            itemStyle:{
                                normal:{label:{show:true}},
                                emphasis:{label:{show:true}}
                            },
                            markPoint: {//动态标记
                                large: true,//这个选项，悬浮自动失效
                                //symbol:'image://../assets/images/device.png',
                                symbolSize: 20,
                                itemStyle: {
                                    normal: {
                                        shadowBlur: 2,
                                        shadowColor: 'rgba(37, 140, 249, 0.8)'
                                    }
                                },
                                tooltip: {
                                    trigger: 'item',
                                    formatter: '{b}'
                                },
                                data: mapdata
                                //     [
                                //     {name: '监测站1',coord: [117.51812,26.771538],symbol:'image://http://localhost:8080/assets/images/device.png'},
                                //     {name: '监测站2',coord: [117.56049,26.298622],symbol:'image://http://localhost:8080/assets/images/monitor.png'}
                                // ]
                            }
                        }
                    ]

                });
            });
        }
    }
}();


