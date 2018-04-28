var data;
var days = 15;
var today = new Date();
var todayString = today.getFullYear() + '-'
    + (today.getMonth() + 1 < 10 ? '0' + (today.getMonth() + 1).toString() : today.getMonth() + 1) + '-'
    + (today.getDate() < 10 ? '0' + today.getDate() : today.getDate());

(function () {
    $('#loading').modal({dimmer: false});
    $('#loading').modal('open');
    $.ajax({
        url: '/chart/quality/history',
        type: 'GET',
        success: handleData,
        error: function () {
            $('#loading').modal('close');
            $('#alert').modal('open');
        }
    });
})();

function handleData (data) {
    $('#loading').modal('close');
    if (data.length === 0) {
        data = [{ datetime: '' }];
    }
    if (data[0].datetime === todayString) {
        var number = data[0].dataloadSum ? data[0].dataloadSum : 0;
        var size = data[0].fileSizeSum ? data[0].fileSizeSum : 0;
        var time = data[0].timeSum ? data[0].timeSum : 0;

        if (number < 1000) {
            $('#number').html(number);
        } else {
            $('#number').html((number / 1000).toFixed(1) + 'K');
        }

        if (size < 2 << 9) {
            $('#size').html(size);
            $('#size-unit').html('B');
        } else if (size < 2 << 19) {
            $('#size').html((size / (2 << 9)).toFixed(1));
            $('#size-unit').html('KB');
        } else if (size < 2 << 29) {
            $('#size').html((size / (2 << 19)).toFixed(1));
            $('#size-unit').html('MB');
        } else {
            $('#size').html((size / (2 << 29)).toFixed(1));
            $('#size-unit').html('GB');
        }

        if (time < 1) {
            $('#time').html("<1");
            $('#time-unit').html('秒');
        } else if (time < 60) {
            $('#time').html(time);
            $('#time-unit').html('秒');
        } else if (time < 60 * 60) {
            $('#time').html((time / 60).toFixed(1));
            $('#time-unit').html('分钟');
        } else {
            $('#time').html((time / 60 / 60).toFixed(1));
            $('#time-unit').html('小时');
        }
    }

    generateChart(data);
}

function generateChart(data) {
    var dates = [];
    for (var i = days - 1; i >= 0; i--) {
        var temp = today.valueOf() - i * 1000 * 60 * 60 * 24;
        var tempDate = new Date(temp);
        var tempString = tempDate.getFullYear() + '-'
            + (tempDate.getMonth() + 1 < 10 ? '0' + (tempDate.getMonth() + 1).toString() : tempDate.getMonth() + 1) + '-'
            + (tempDate.getDate() < 10 ? '0' + tempDate.getDate() : tempDate.getDate());
        dates.push(tempString);
    }

    var loadData = [];
    var fileData = [];
    var timeData = [];

    for (var i = 0; i < days; i++) {
        var found = false;
        for (var j = 0; j < data.length; j++) {
            if (dates[i] === data[j].datetime) {
                loadData.push(data[j].dataloadSum ? data[j].dataloadSum : 0);
                fileData.push(data[j].fileSizeSum ? data[j].fileSizeSum : 0);
                timeData.push(data[j].timeSum ? data[j].timeSum : 0);
                found = true;
                break;
            }
        }
        if (!found) {
            loadData.push(0);
            fileData.push(0);
            timeData.push(0);
        }
    }

    var loadHistory = echarts.init(document.getElementById('load-history'));
    var fileHistory = echarts.init(document.getElementById('file-history'));
    var timeHistory = echarts.init(document.getElementById('time-history'));

    var loadOption = {
        color: ['#6699cc'],
        title: {
            text: '文件装载行数',
            textStyle: {
                fontWeight: 'normal'
            }
        },
        grid: {
            width: '85%'
        },
        tooltip: {
            trigger: 'axis',
            backgroundColor: 'rgba(233,233,233,.75)',
            textStyle: {
                color: '#333'
            },
            formatter: function (params) {
                var val = params[0].data;
                var text = '日期 : ' + params[0].name + '<br>装载行数 : ';
                if (val > 1000) {
                    text += (val / 1000).toFixed(1) + 'K';
                } else {
                    text += val;
                }
                return text;
            },
            extraCssText: 'box-shadow: 0 0 1em rgba(0, 0, 0, .5); border-radius: .5em;'
        },
        xAxis: {
            type: 'category',
            data: dates,
            axisLabel: {
                interval: 0,
                rotate: -30,
                formatter: function (val) {
                    return val.substring(5);
                }
            }
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: function (val) {
                    if (val >= 1000) {
                        return (val / 1000).toFixed(1) + 'K';
                    } else {
                        return val;
                    }
                }
            }
        },
        series: [{
            type: 'line',
            data: loadData
        }]
    };
    loadHistory.setOption(loadOption);

    var fileOption = {
        color: ['#ddd053'],
        title: {
            text: '文件总大小',
            textStyle: {
                fontWeight: 'normal'
            }
        },
        grid: {
            width: '85%'
        },
        tooltip: {
            trigger: 'axis',
            backgroundColor: 'rgba(233,233,233,.75)',
            textStyle: {
                color: '#333'
            },
            formatter: function (params) {
                var val = params[0].data;
                var text = '日期 : ' + params[0].name + '<br>文件大小 : ';
                if (val < 2 << 9) {
                    text += val + 'B';
                } else if (val < 2 << 19) {
                    text += (val / (2 << 9)).toFixed(1) + 'KB';
                } else if (val < 2 << 29) {
                    text += (val / (2 << 19)).toFixed(1) + 'MB';
                } else {
                    text += (val / (2 << 29)).toFixed(1) + 'GB';
                }
                return text;
            },
            extraCssText: 'box-shadow: 0 0 1em rgba(0, 0, 0, .5); border-radius: .5em;'
        },
        xAxis: {
            type: 'category',
            data: dates,
            axisLabel: {
                interval: 0,
                rotate: -30,
                formatter: function (val) {
                    return val.substring(5);
                }
            }
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: function (val) {
                    if (val < 2 << 9) {
                        return val + 'B';
                    } else if (val < 2 << 19) {
                        return (val / (2 << 9)).toFixed(1) + 'KB';
                    } else if (val < 2 << 29) {
                        return (val / (2 << 19)).toFixed(1) + 'MB';
                    } else {
                        return (val / (2 << 29)).toFixed(1) + 'GB';
                    }
                }
            }
        },
        series: [{
            type: 'line',
            data: fileData
        }]
    };
    fileHistory.setOption(fileOption);

    var timeOption = {
        color: ['#ff8c42'],
        title: {
            text: '总耗时',
            textStyle: {
                fontWeight: 'normal'
            }
        },
        grid: {
            width: '85%'
        },
        tooltip: {
            trigger: 'axis',
            backgroundColor: 'rgba(233,233,233,.75)',
            textStyle: {
                color: '#333'
            },
            formatter: function (params) {
                var val = params[0].data;
                var text = '日期 : ' + params[0].name + '<br>耗时 : ';
                if (val < 60) {
                    text += val + 's';
                } else if (val < 60 * 60) {
                    text += (val / 60).toFixed(1) + 'm'
                } else {
                    text += (val / 60 / 60).toFixed(1) + 'h';
                }
                return text;
            },
            extraCssText: 'box-shadow: 0 0 1em rgba(0, 0, 0, .5); border-radius: .5em;'
        },
        xAxis: {
            type: 'category',
            data: dates,
            axisLabel: {
                interval: 0,
                rotate: -30,
                formatter: function (val) {
                    return val.substring(5);
                }
            }
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: function (time) {
                    if (time < 60) {
                        return time + 's';
                    } else if (time < 60 * 60) {
                        return (time / 60).toFixed(1) + 'm'
                    } else {
                        return (time / 60 / 60).toFixed(1) + 'h';
                    }
                }
            }
        },
        series: [{
            type: 'line',
            data: timeData
        }]
    };
    timeHistory.setOption(timeOption);

    window.onresize = function () {
        loadHistory.resize();
        fileHistory.resize();
        timeHistory.resize();
    }
}