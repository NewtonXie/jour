/**
 * 刷卡类别
 */
var chart_etlsummary = echarts.init(document.getElementById('chart_etlsummary'));

var chart_etlsummary_option = {
    backgroundColor: '#2c343c',
    title: {
        text: 'ETL作业情况',
        left: 'left',
        top: 2,
        textStyle: {
            color: '#ccc'
        }
    },

    color:['#9BCD9B', 'red'],
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}:{c}({d}%)"
    },

    series: [
        {
            name: 'ETL作业情况',
            type: 'pie',
            radius: ['55%', '70%'],
            avoidLabelOverlap: false,
            data: [].sort(function (a, b) {
                return a.value - b.value;
            })
        }
    ]
}
chart_etlsummary.setOption(chart_etlsummary_option)

getETLSummary()

function getETLSummary() {
    $.ajax({
        url: "/metadata/etlsummary",
        async: true,
        timeout: 5000,
        dataType: 'json',
        success: function (data) {
            if (data.code == 0) {
                chart_etlsummary_option.series[0].data = data.data
                chart_etlsummary.setOption(chart_etlsummary_option)
            } else {

            }
        }
    });
}