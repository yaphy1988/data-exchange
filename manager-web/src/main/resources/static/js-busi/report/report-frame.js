/**
 * Created by yaphy on 2017/5/24.
 */
var $table ,
    $remove ,
    selections = [];
function initTable() {
        $table = $('#table'),
        $remove = $('#remove'),
    $table.bootstrapTable({
        height: getHeight(),
        queryParams : queryParams,
        pageList: [10, 25, 50, 100,200],
        ajaxOptions:{
            crossDomain: true,
           xhrFields: {
                withCredentials: true
            }
        },
        clickToSelect:true,
        showExport: true,                     //是否显示导出
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
    $table.on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
        $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
        // save your data, here just save the current page
        selections = getIdSelections();
        // push or splice the selections if you want to save all data selections
    });
/*    $table.on('expand-row.bs.table', function (e, index, row, $detail) {
        if (index % 2 == 1) {
            $detail.html('Loading from ajax request...');
            $.get('LICENSE', function (res) {
                $detail.html(res.replace(/\n/g, '<br>'));
            });
        }
    });*/
    $table.on('all.bs.table', function (e, name, args) {
        console.log(name, args);
    });
    $remove.click(function () {
        var ids = getIdSelections();
        $table.bootstrapTable('remove', {
            field: 'id',
            values: ids
        });
        $remove.prop('disabled', true);
    });
    $(window).resize(function () {
        $table.bootstrapTable('resetView', {
            height: getHeight()
        });
    });
}
function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

function detailFormatter(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    });
    return html.join('');
}
function operateFormatter(value, row, index) {
    return [
        '<a class="like" href="javascript:void(0)" title="Like">',
        '<i class="glyphicon glyphicon-heart"></i>',
        '</a>  ',
        '<a class="remove" href="javascript:void(0)" title="Remove">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
window.operateEvents = {
    'click .like': function (e, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .remove': function (e, value, row, index) {
        $table.bootstrapTable('remove', {
            field: 'id',
            values: [row.id]
        });
    }
};
function totalTextFormatter(data) {
    return 'Total';
}
function totalNameFormatter(data) {
    return data.length;
}
function totalPriceFormatter(data) {
    var total = 0;
    $.each(data, function (i, row) {
        total += +(row.price.substring(1));
    });
    return '$' + total;
}
function getHeight() {
    return $(window).height() - $('h1').outerHeight(true);
}

//加载服务器数据之前的处理程序，可以用来格式化数据。
//参数：res为从服务器请求到的数据。服务器返回PageInfo<T>的json格式，必须包含total
function responseHandler(res) {
    if (res) {
        var rows = res.list;
        $.each(rows, function (i, row) {
            row.state = $.inArray(row.id, selections) !== -1;
        });
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