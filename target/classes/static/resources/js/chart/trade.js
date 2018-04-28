var nowMap = 'China';
var provinces = [];
var cities = [];
var zones = [];

var mapChart = echarts.init(document.getElementById('main'));
var mapOption = {
    padding: 0,
    geo: {
        show: true
    },
    tooltip: {
        show: true,
        trigger: 'item',
        formatter: function (params) {
            if (params.name && params.value) {
                var val = parseFloat(params.value / 100).toFixed(2);
                if (val > 10000) {
                    val = (val / 10000).toFixed(2) + '万';
                }
                return params.name + ': ' + val + '元';
            }
            return '暂无数据';
        },
        backgroundColor: 'rgba(255,255,255,.75)',
        textStyle: {
            color: '#333'
        },
        extraCssText: 'box-shadow: 0 0 1em #666'
    },
    series: {
        type: 'map',
        mapType: 'China',
        roam: true,
        data: [{name: '江西', value: 10000}],
        itemStyle: {
            normal: {
                label: {
                    show: true,
                    color: 'rgba(255,255,255,.5)',
                    fontSize: 14
                },
                borderColor: '#fff',
                areaColor: '#fff',
                opacity: 0.1
            },
            emphasis: {
                label: {
                    show: true,
                    color: '#fff'
                },
                areaColor: '#fff',
                opacity: 0.5,
                shadowColor: '#000',
                shadowBlur: 16
            }
        }
    }
};

function fetchMapData(callBack) {
    $.ajax({
        url: '/resources/map/china.json',
        success: function (geoJson) {
            echarts.registerMap('China', geoJson);
            $.ajax({
                url: '/resources/map/guangdong.json',
                success: function (geoJson) {
                    echarts.registerMap('GuangDong', geoJson);
                    $.ajax({
                        url: '/resources/map/guangzhou.json',
                        success: function (geoJson) {
                            echarts.registerMap('GuangZhou', geoJson);
                            callBack();
                        }
                    });
                }
            });
        }
    });
}

function switchMap(map) {
    if (!map) map = 'China';
    mapOption.series.mapType = map;
    nowMap = map;
    if (nowMap === 'China') {
        mapOption.series.data = provinces;
    } else if (nowMap === 'GuangDong') {
        mapOption.series.data = cities;
    } else {
        mapOption.series.data = zones;
    }
    mapChart.setOption(mapOption, true);
    adjust();
}

window.onload = function() {
    fetchMapData(switchMap);
};

var tendencyOption = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            lineStyle: { color: '#57617B' }
        },
        backgroundColor: 'rgba(255,255,255,.75)',
        textStyle: {
            color: '#333'
        },
        extraCssText: 'box-shadow: 0 0 1em #666'
    },
    grid: {
        left: 20,
        right: 20,
        bottom: 10,
        top: 20,
        containLabel: true,
        textStyle: { color: '#ddd' }
    },
    xAxis: {
        type: 'category',
        bottom: 10,
        boundaryGap: false,
        splitLine: {
            show: false,
            interval: 'auto',
            lineStyle: { color: ['#D4DFF5'] }
        },
        axisTick: { show: false },
        axisLine: {
            lineStyle: { show: false, color: '#ddd' }
        },
        axisLabel: {
            textStyle: { fontSize: 14 }
        },
        data: []
    },
    yAxis: {
        type: 'value',
        splitNumber: 5,
        splitLine: {
            lineStyle: { show: false }
        },
        axisTick: { show: false },
        axisLine: {
            lineStyle: { show: false, color: '#ddd' }
        },
        axisLabel: {
            textStyle: { fontSize: 14 }
        }
    },
    series: [{
        name: '今日',
        type: 'line',
        smooth: true,
        showSymbol: false,
        symbol: 'circle',
        symbolSize: 6,
        data: [],
        position: 'top',
        areaStyle: {
            normal: { color: 'rgba(199, 237, 250,0.5)' }
        },
        itemStyle: {
            normal: { color: '#f7b851' }
        },
        lineStyle: {
            normal: { width: 2 }
        }
    }]
};

var distributeOption = {
    title: {
        text: '卡片分类使用情况',
        textStyle: { color: '#fff' },
        x: 'center',
        padding: 20
    },
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)',
        backgroundColor: 'rgba(255,255,255,.75)',
        textStyle: {
            color: '#333'
        },
        extraCssText: 'box-shadow: 0 0 1em #666'
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: [],
        padding: 20,
        textStyle: { color: '#fff' }
    },
    series: [
        {
            name: '占用比例',
            type: 'pie',
            radius: '55%',
            center: ['60%', '50%'],
            data: [],
            itemStyle: {
                normal: {
                    label: {
                        color: '#fff'
                    }
                },
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};

var GEO_LEFT = '14%';
var GEO_PROVICE_LEFT = '26%';

const consumeOption = {
    tooltip: {
        trigger: 'axis',
        axisPointer: { type: 'shadow' },
        backgroundColor: 'rgba(255,255,255,.75)',
        textStyle: {
            color: '#333'
        },
        extraCssText: 'box-shadow: 0 0 1em #666'
    },
    grid: {
        left: '3%',
        right: '10%',
        bottom: '3%',
        top: '3%',
        containLabel: true,
        textStyle: { color: '#fff' }
    },
    xAxis: {
        type: 'value',
        scale: true,
        position: 'top',
        boundaryGap: false,
        splitLine: { show: false },
        axisLine: { show: false },
        axisTick: { show: false },
        axisLabel: {
            margin: 10,
            textStyle: { color: '#ddd' }
        }
    },
    yAxis: {
        type: 'category',
        axisLine: {
            show: true,
            lineStyle: { color: '#ddd' }
        },
        axisTick: {
            show: false,
            lineStyle: { color: '#ddd' }
        },
        axisLabel: {
            interval: 0,
            textStyle: { color: '#ddd' }
        },
        data: []
    },
    series: [
        {
            name: '24小时内消费总比数',
            type: 'bar',
            data: [],
            top: '1%',
            right: '5px',
            left: '5px',
            label: {
                normal: {
                    show: true,
                    position: 'right',
                    color: '#dddddd'
                }
            },
            itemStyle: {
                normal: {
                    color: 'rgba(255,253,233, .5)'
                },
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
var tendencyChart = echarts.init(document.getElementById('tendencyChart'));
var distributeChart = echarts.init(document.getElementById('distributeChart'));
var consumeChart = echarts.init(document.getElementById('consumeChart'));
tendencyChart.setOption(tendencyOption);
distributeChart.setOption(distributeOption);
consumeChart.setOption(consumeOption);

websocket.onmessage = function (event) {
    var data = JSON.parse(event.data);
    update(data);
};

function update(json) {
   if (json.data.summary) {
       updateSummary(json.data.summary);
   }
   if (json.data.timeins) {
       updateTimeIns(json.data.timeins);
   }
   if (json.data.cardbin) {
       updateCardbin(json.data.cardbin);
   }
   if (json.data.payTypesStat) {
       updatePayTypesStat(json.data.payTypesStat);
   }
   if (json.data.consumeInterval) {
       updateConsumeInterval(json.data.consumeInterval);
   }
   if (json.data.area) {
       updateArea(json.data.area);
   }
   if (json.data.shops) {
       updateShops(json.data.shops);
   }
}

function updateSummary(summary) {
    $('#summary24Hamount').html(summary.summary24Hamount);
    $('#summary24Horder').html(summary.summary24Horder);
    $('#summary24Hconsumer').html(summary.summary24Horder);
}

function updateTimeIns(timeIns) {
    tendencyOption.xAxis.data = timeIns.timeline;
    tendencyOption.series.data = timeIns.ins;
    tendencyChart.setOption(tendencyOption);
}

function updateCardbin(cardData) {
    if (!cardData || cardData.length <= 0) return;

    var cardType = [];
    cardData.map(function(item) {
        cardType.push(item.name)
    });

    distributeOption.legend.data = cardType;
    distributeOption.series[0].data = cardData;
    distributeChart.setOption(distributeOption);
}

function updatePayTypesStat(data) {
    $('#unionpay').html(data.unionpay);
    $('#wxpay').html(data.wxpay);
    $('#alipay').html(data.wxpay);
}

function updateConsumeInterval(data) {
    var consumeInterval = formatConsumeInterval(data);

    if (consumeInterval) {
        consumeOption.yAxis.data = consumeInterval.name;
        consumeOption.series[0].data = consumeInterval.value;
    }
    consumeChart.setOption(consumeOption);
}

function formatConsumeInterval (comsumeInterval) {
    if (!comsumeInterval || comsumeInterval.length <= 0) return null;

    var consumeIntervalName = [];
    var consumeIntervalValue = [];

    comsumeInterval.map(function(item) {
        consumeIntervalName.push(item.name);
        consumeIntervalValue.push(item.value);
    });

    return { name: consumeIntervalName, value: consumeIntervalValue };
}

function updateShops(shops) {
    var str = '';
    for (var i = 0; i < shops.length; i++) {
        str += '<tr>';
        str += '<td class="top-table-col">' + shops[i].name + '</td>';
        str += '<td class="top-table-col">' + parseFloat(shops[i].value).toFixed(2) + '</td>';
        str += '</tr>';
    }
    $('#top10').html(str);
}

function updateArea(area) {
    provinces = area.provinces;
    cities = area.cities;
    zones = area.zones;
    if (nowMap === 'China') {
        mapOption.series.data = provinces;
    } else if (nowMap === 'GuangDong') {
        mapOption.series.data = cities;
    } else {
        mapOption.series.data = zones;
    }
    mapChart.setOption(mapOption);
}

function adjust() {

    $('#realtime-trade').css('width', document.body.clientWidth - 80 + 'px');
    $('#realtime-trade').css('height', document.body.clientHeight - 100 + 'px');
    mapChart.resize();
    $('#target').css('left', $('#' + nowMap).offset().left - 40 + 'px');
}

window.onresize = adjust;