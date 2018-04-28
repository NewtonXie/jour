var days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
var today = new Date();
var t_year = today.getFullYear();
var t_month = today.getMonth() + 1;
var t_day = today.getDate();
var startYear = 2018;
var str1 = '';
for (var i = t_year; i >= startYear; i--) {
    str1 += '<option value="' + i + '">' + i+'年' + '</option>';
}
$('#charts-year').html(str1);
var str2 = '';
for (var i = 1; i <= 12; i++) {
    str2 += '<option value="' + i + '">' + i+'月' + '</option>';
}
$('#charts-month').html(str2);
$('#charts-month').eq(0).selected('select', t_month - 1);
$('#charts-year').eq(0).selected('select', 0);

var categories = [];

var statusText = {
    50: '<span style="color: #66ff66">成功</span><br>',
    60: '<span style="color: #ffff66;">终止</span><br>',
    70: '<span style="color: #ff6666;">失败</span><br>'
};
var option = {
    color: ['#33ff33', '#05668d', '#ffcc33', '#f0f3bd', '#336666', '#00e089',
    '#75878a', '#065279', '#4b5cc4', '#41555d'],
    tooltip: {
        textStyle: {
            color: '#333'
        },
        backgroundColor: 'rgba(233,233,233,.75)',
        extraCssText: 'box-shadow: 0 0 1em rgba(0, 0, 0, .5); border-radius: .5em;',
        formatter: function (params) {
            var timeUsed = params.value[2] - params.value[1];
            if (timeUsed < 1000) {
                var result = timeUsed + 'ms';
            } else {
                var result = (timeUsed / 1000).toFixed(2) + 's';
            }
            return params.marker + params.seriesName + '<br>'
                + '开始时间 : ' + new Date(params.value[1] + 28800000 + 86400000 * params.value[0]).toLocaleString() + '<br>'
                + '结束时间 : ' + new Date(params.value[2] + 28800000 + 86400000 * params.value[0]).toLocaleString() + '<br>'
                + '执行结果 : ' + statusText[params.value[3]]
                + '耗时 : ' + result;
        }
    },
    title: {
        text: 'Azkaban任务执行时间',
        left: 'center'
    },
    legend: {
        align: 'left',
        left: 75,
        right: 75,
        top: 30
    },
    dataZoom: [{
        type: 'slider',
        filterMode: 'weakFilter',
        showDataShadow: false,
        top: 850,
        height: 10,
        borderColor: 'transparent',
        backgroundColor: '#e2e2e2',
        handleIcon: 'M10.7,11.9H9.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4h1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7v-1.2h6.6z M13.3,22H6.7v-1.2h6.6z M13.3,19.6H6.7v-1.2h6.6z', // jshint ignore:line
        handleSize: 20,
        handleStyle: {
            shadowBlur: 6,
            shadowOffsetX: 1,
            shadowOffsetY: 2,
            shadowColor: '#aaa'
        },
        labelFormatter: ''
    }, {
        type: 'slider',
        filterMode: 'filter',
        showDataShadow: false,
        top: 100,
        right: 25,
        height: 750,
        width: 12.5,
        yAxisIndex: 0,
        borderColor: 'transparent',
        backgroundColor: '#e2e2e2',
        handleIcon: 'M10.7,11.9H9.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4h1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7v-1.2h6.6z M13.3,22H6.7v-1.2h6.6z M13.3,19.6H6.7v-1.2h6.6z', // jshint ignore:line
        handleSize: 20,
        handleStyle: {
            shadowBlur: 6,
            shadowOffsetX: 1,
            shadowOffsetY: 2,
            shadowColor: '#aaa'
        },
        labelFormatter: ''
    }, {
        type: 'inside',
        filterMode: 'filter'
    }, {
        type: 'inside',
        filterMode: 'filter',
        yAxisIndex: 0
    }
    ],
    grid: {
        left: 75,
        top: 100,
        height: 700
    },
    xAxis: {
        scale: true,
        data: categories
    },
    yAxis: {
        scale: true,
        axisLabel: {
            formatter: function (val) {
                var date = new Date(val);
                return date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
            }
        }
    }
};
(function() {
    $.ajax({
        url: '/chart/azkaban/project',
        type: 'POST',
        success: function(data) {
            var str = '';
            for (var i = 0; i < data.length; i++) {
                str += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
            }
            $('#charts-project').html(str);
            $('#charts-year').val(t_year.toString());
            $('#charts-month').val(t_month.toString());
            $('#charts-project').change(getJobExecutions);
            $('#charts-year').change(getJobExecutions);
            $('#charts-month').change(getJobExecutions);
            $('#charts-month').eq(0).selected('select', t_month - 1);
            $('#charts-project').eq(0).selected('select', 0);
            getJobExecutions();
        },
        error: function () {
            $('#alert').modal('open');
        }
    })
})();

function getJobExecutions() {
    var project = $('#charts-project').val();
    $('#charts-year').unbind();
    $('#charts-month').unbind();
    var year = $('#charts-year').val();

    $('#charts-year').change(getJobExecutions);
    $('#charts-month').change(getJobExecutions);
    var month = $('#charts-month').val();

    var start_time = Number(new Date(year + '-' + month + '-' + '1 00:00:00'));
    var monthDays = days[month - 1];
    if (year % 4 === 0 && year % 100 !== 0 && year % 400 !== 0 && monthDays === 28) {
        monthDays = 29;
    }
    var end_time = start_time + 60 * 60 * 24 * 1000 * monthDays;

    categories = [];
    if (year >= t_year && month >= t_month) {
        for (var i = 1; i <= (t_day > 7 ? t_day : 7); i++) {
            categories.push(i + '日');
        }
    }
    else {
        var tempDays = days[month - 1];
        if (year % 4 === 0 && year % 100 !== 0 && year % 400 !== 0 && tempDays === 28) {
            tempDays = 29;
        }
        for (var i = 1; i <= tempDays; i++) {
            categories.push(i + '日');
        }
    }

    $('#charts-project').on('change');
    $('#charts-year').on('change');
    $('#charts-month').on('change');

    $('#loading').modal({dimmer: false});
    $('#loading').modal('open');
    $.ajax({
        url: '/chart/azkaban/job',
        type: 'POST',
        data: {
            projectId: project,
            startTime: start_time,
            endTime: end_time
        },
        success: function(data) {
            $('#loading').modal('close');
            if (data.length === 0) {
                $('#azkaban').css('display', 'none');
                $('#hint').css('display', 'block');
                return;
            } else {
                $('#azkaban').css('display', 'block');
                $('#hint').css('display', 'none');
            }
            var rowData = [];
            var startDay = new Date(year + '-' + month + '-' + '1 00:00:00');
            var minTime = parseInt(data[0].startTime) % 86400000 + startDay.getTime();
            var maxTime = minTime;
            for (var j = 0; j < data.length; j++) {
                var startTime2 = parseInt(data[j].startTime) % 86400000 + startDay.getTime();
                var endTime2 = parseInt(data[j].endTime) % 86400000 + startDay.getTime();

                if (startTime2 < minTime) minTime = startTime2 - 100;
                if (endTime2 > maxTime) maxTime = endTime2 + 100;

                rowData.push({
                    name: data[j].jobId,
                    value: [
                        parseInt((data[j].startTime - startDay.getTime()) / 86400000),
                        startTime2,
                        endTime2,
                        data[j].status
                    ],
                    itemStyle: {
                        normal: {
                            color: data[j].status === 50 ? null : '#ff0000',
                            opacity: .8
                        }
                    }
                })
            }
            console.log(minTime, maxTime);
            var sorted = {};
            for (var i = 0; i < rowData.length; i++) {
                if (!sorted[rowData[i].name]) {
                    sorted[rowData[i].name] = [];
                }
                sorted[rowData[i].name].push(rowData[i]);
            }
            console.log(sorted);

            var legend = [];
            var series = [];
            for (var key in sorted) {
                legend.push(key);
                series.push({
                    name: key,
                    type: 'custom',
                    renderItem: renderItem,
                    legendHoverLink: true,
                    itemStyle: {
                        emphasis: {
                            opacity: 1,
                            barBorderWidth: 1,
                            shadowBlur: 15,
                            shadowOffsetX: 0,
                            shadowOffsetY: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.75)'
                        }
                    },
                    encode: {
                        x: 0,
                        y: [1, 2]
                    },
                    data: sorted[key]
                })
            }
            option.legend.data = legend;
            option.series = series;
            option.yAxis = {
                scale: true,
                min: minTime,
                max: maxTime,
                axisLabel: {
                    formatter: function (val) {
                        val += 8 * 60 * 60 * 1000;
                        var date = new Date(val);
                        return date.getHours() + ':' + (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':' + (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
                    }
                }
            };
            option.xAxis = {
                scale: true,
                data: categories
            };
            myChart.setOption(option, true);
        },
        error: function () {
            $('#loading').modal('close');
            $('#alert').modal('open');
        }
    })
}

var myChart = echarts.init(document.getElementById('azkaban'));

myChart.setOption(option);

function renderItem(params, api) {
    var categoryIndex = api.value(0);
    var start = api.coord([categoryIndex, api.value(1)]);
    var end = api.coord([categoryIndex, api.value(2)]);
    var width = api.size([0, 1])[0] * 0.75;

    return {
        type: 'rect',
        shape: {
            x: start[0] - width / 2,
            y: end[1],
            height: -end[1] + start[1],
            width: width
        },
        style: api.style(),
        styleEmphasis: api.styleEmphasis()
    };
}