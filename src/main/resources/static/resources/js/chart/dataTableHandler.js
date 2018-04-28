var nowTemp = new Date();
var nowDay = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0,0,0,0).valueOf();
var nowMonth = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), 1,0,0,0,0).valueOf();
var nowYear = new Date(nowTemp.getFullYear() + 1, 0,1,0,0,0,0).valueOf();
(function () {
    $('#result').html(nowTemp.getFullYear() + '-'
        + ((nowTemp.getMonth() + 1) < 10 ? '0' + (nowTemp.getMonth() + 1) : nowTemp.getMonth() + 1) + '-'
        + ((nowTemp.getDate()) < 10 ? '0' + (nowTemp.getDate()) : nowTemp.getDate())
    );
    $('#startResult').html(nowTemp.getFullYear() + '-'
        + ((nowTemp.getMonth() + 1) < 10 ? '0' + (nowTemp.getMonth() + 1) : nowTemp.getMonth() + 1) + '-'
        + ((nowTemp.getDate()) < 10 ? '0' + (nowTemp.getDate() - 1) : nowTemp.getDate() - 1)
    );
    $('#endResult').html(nowTemp.getFullYear() + '-'
        + ((nowTemp.getMonth() + 1) < 10 ? '0' + (nowTemp.getMonth() + 1) : nowTemp.getMonth() + 1) + '-'
        + ((nowTemp.getDate()) < 10 ? '0' + (nowTemp.getDate()) : nowTemp.getDate())
    );
    $('#date').datepicker({onRender: render}).on('changeDate.datepicker.amui', function (event) {
        $('#result').text($('#date').data('date'));
    });
    $('#date').datepicker('setValue', $('#result').html());
    $('#startDate').datepicker({onRender: render}).on('changeDate.datepicker.amui', function (event) {
        $('#startResult').text($('#startDate').data('date'));

        $('#endDate').datepicker({onRender: function (date, viewMode) {
            var viewDate = nowDay;

            switch (viewMode) {
                case 1:
                    viewDate = nowMonth;
                    break;
                case 2:
                    viewDate = nowYear;
                    break;
            }

            return new Date($('#startDate').data('date')).valueOf() - 1000 * 60 * 60 * 24 > date.valueOf() || date.valueOf() > viewDate ? 'am-disabled' : '';
        }}).on('changeDate.datepicker.amui', function (event) {
            $('#endResult').text($('#endDate').data('date'));
        });

        if ($('#endDate').data('date') <= $('#startDate').data('date')) {
            $('#endDate').datepicker('setValue', $('#startDate').data('date'));
        } else {
            $('#endDate').datepicker('setValue', $('#endDate').data('date'));
        }
        $('#endResult').text($('#endDate').data('date'));
    });
    $('#startDate').datepicker('setValue', $('#startResult').html());

})();

function render (date, viewMode) {
    var viewDate = nowDay;

    switch (viewMode) {
        case 1:
            viewDate = nowMonth;
            break;
        case 2:
            viewDate = nowYear;
            break;
    }

    return date.valueOf() > viewDate ? 'am-disabled' : '';
}

function changeMode () {
    if ($('#single').is(':checked')) {
        $('#date-single').css('display', 'inline');
        $('#date-multiple').css('display', 'none');
    } else {
        $('#date-single').css('display', 'none');
        $('#date-multiple').css('display', 'inline');
    }
}

var limit = 15; // 每页显示数据条数
function query(offset) {
    var startDate, endDate;

    if ($('#single').is(':checked')) {
        startDate = endDate = $('#result').html();
    } else {
        startDate = $('#startResult').html();
        endDate = $('#endResult').html();
    }
    $('#loading').modal({dimmer: false});
    $('#loading').modal('open');
    $.ajax({
        url: '/chart/quality/rowscheck',
        type: 'POST',
        data: {
            startDate: startDate,
            endDate: endDate,
            limit: limit,
            offset: offset
        },
        success: function(data) {
            $('#loading').modal('close');
            $('#initial').css('display', 'none');
            var result = data.data;
            var total = data.total;

            if (total > 0) {
                $('#table').css('display', 'table');
                $('#no-data').css('display', 'none');
                $('#download').removeClass('am-disabled');
                $('#download').attr('disabled', false);
            } else {
                $('#table').css('display', 'none');
                $('#no-data').css('display', 'block');
                $('#download').addClass('am-disabled');
                $('#download').attr('disabled', true);
            }

            var str = ''
            for (var i = 0; i < result.length; i++) {
                var success = true;
                if (result[i].practicalRownum !== result[i].originalRownum ||
                    result[i].rownumAfterDataload !== result[i].originalRownum) {
                    success = false;
                }
                str += '<tr>';
                str += '<td>' + result[i].tablename + '</td>';
                str += '<td>' + result[i].originalRownum + '</td>';

                if (result[i].practicalRownum !== result[i].originalRownum) {
                    str += '<td style="color: red;">' + result[i].practicalRownum + '</td>';
                } else {
                    str += '<td>' + result[i].practicalRownum + '</td>';
                }

                if (result[i].rownumAfterDataload !== result[i].originalRownum) {
                    str += '<td style="color: red;">' + result[i].rownumAfterDataload + '</td>';
                } else {
                    str += '<td>' + result[i].rownumAfterDataload + '</td>';
                }

                if (result[i].fileSize < 2 << 9) {
                    str += '<td>' + result[i].fileSize + 'B' + '</td>';
                } else if (result[i].fileSize < 2 << 19) {
                    str += '<td>' + (result[i].fileSize / (2 << 9)).toFixed(2) + 'KB' + '</td>';
                } else if (result[i].fileSize < 2 << 29) {
                    str += '<td>' + (result[i].fileSize / (2 << 19)).toFixed(2) + 'MB' + '</td>';
                } else {
                    str += '<td>' + (result[i].fileSize / (2 << 29)).toFixed(2) + 'GB' + '</td>';
                }

                str += '<td>' + result[i].beginTimestamp + '</td>'
                str += '<td>' + result[i].endTimestamp + '</td>';

                var timeUsed = Number(new Date(result[i].endTimestamp)) - Number(new Date(result[i].beginTimestamp));
                var finalTime = '';

                if (timeUsed < 1000) {
                    finalTime = timeUsed + '毫秒';
                } else if (timeUsed < 1000 * 60) {
                    finalTime = (timeUsed / 1000).toFixed(2) + '秒';
                } else if (timeUsed < 1000 * 60 * 60) {
                    finalTime = Math.floor(timeUsed / 1000 / 60) + '分' + Math.floor(timeUsed / 1000) % 60 + '秒';
                } else {
                    finalTime = Math.floor(timeUsed / 1000 / 60 / 60) + '时' + Math.floor(timeUsed / 1000 / 60) % 60 + '分';
                }

                str += '<td>' + finalTime + '</td>';
                if (success) {
                    str += '<td style="color: green">' + '正常' + '</td>';
                } else {
                    str += '<td style="color: red">' + '异常' + '</td>';
                }
                str += '</tr>';
            }
            $('#data').html(str);

            if (total <= limit) {
                $('#pages').html('');
                return;
            }

            var pages = Math.ceil(total / limit);
            var current = offset / limit + 1;
            if (current > pages) current = pages;
            var left = current - 3;
            var right = current + 3 ;
            if (left < 1) {
                right += 1 - left;
                left = 1;
            }
            if (right > pages) {
                left += pages - right;
                if (left < 1) left = 1;
                right = pages;
            }

            var str = '';
            str += '<a class="page-button" onclick="query(0)">首页</a>';
            if (current === 1) {
                str += '<a class="page-button disable">上一页</a>';
            } else {
                str += '<a class="page-button" onclick="query(' + (offset - limit) + ')">上一页</a>';
            }
            for (var i = left; i <= right; i++) {
                if (i === current) {
                    str += '<a class="page-button disable">' + i + '</a>';
                } else {
                    str += '<a class="page-button" onclick="query(' + ((i-1) * limit) + ')">' + i + '</a>';
                }
            }
            if (current === pages) {
                str += '<a class="page-button disable">下一页</a>';
            } else {
                str += '<a class="page-button" onclick="query(' + (offset + limit) + ')">下一页</a>';
            }
            str += '<a class="page-button" onclick="query(' + ((pages-1) * limit) + ')">末页</a>';

            $('#pages').html(str);
        },
        error: function () {
            $('#loading').modal('close');
            $('#alert').modal('open');
        }
    });
}

function download () {
    var startDate, endDate;

    if ($('#single').is(':checked')) {
        startDate = endDate = $('#result').html();
    } else {
        startDate = $('#startResult').html();
        endDate = $('#endResult').html();
    }

    var a = document.createElement('a');
    a.download = 'ODS数据质量.xls';
    a.href ='/chart/quality/download?startDate=' + startDate + '&endDate=' + endDate;
    $('body').append(a);
    a.click();
    $(a).remove();
}

