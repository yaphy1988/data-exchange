/**
 * Created by yaphy on 2017/5/24.
 */
function initTable(tableId) {
    var $table = $('#'+tableId+'');
    $table.bootstrapTable({
        height: getHeight(),
        // queryParams : queryParams,
        pageList: [10, 25, 50, 100,200],
        ajaxOptions:{
            crossDomain: true,
           xhrFields: {
                withCredentials: true
            }
        },
        exportDataType: "basic",              //basic', 'all', 'selected'.
        responseHandler: responseHandler,

    });
    $('#toolbar').find('select').change(function () {
        $table.bootstrapTable('refreshOptions', {
            exportDataType: $(this).val()
        });
    });
    // sometimes footer render error.
    setTimeout(function () {
        $table.bootstrapTable('resetView');
    }, 200);
    $table.on('all.bs.table', function (e, name, args) {
        console.log(name, args);
    });
/*    $(window).resize(function () {
        $table.bootstrapTable('resetView', {
            height: getHeight()
        });
    });*/
}

function detailFormatter(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    });
    return html.join('');
}
function getHeight() {
    return $(window).height() - $('h1').outerHeight(true);
}

//加载服务器数据之前的处理程序，可以用来格式化数据。
//参数：res为从服务器请求到的数据。服务器返回PageInfo<T>的json格式，必须包含total
function responseHandler(res) {
    if (res) {
        return {
            "rows" : res.list,
            "total" : res.total
        };
    } else {
        return {
            "rows" : [],
            "total" : 0
        };
    }
}
//自定义查询触发事件
function doQuery(params){
    $('#table').bootstrapTable('refresh');    //刷新表格
}