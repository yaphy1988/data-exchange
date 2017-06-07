/**
 * Created by yx on 2017/6/6.
 */
// 表格参数设置
var tableInite = {
    cache: false,
    height: $(window).height() - 220
};
$('#table').bootstrapTable(tableInite);

// 获取查询参数
var getQueryParam = function (pageNo) {
    var options = {
        pageNo: pageNo,
        pageSize: 20,
        startTime : $.trim($("#startTime").val()),
        endTime : $.trim($("#endTime").val())
    };
    return options;
};

// 列表查询
var getList = function (pageNo) {
   $.ajax({
       url: REPORT_ROOT+"/reportdata/queryBillSummary",
       type: "POST",
       dataType: "html",
       cache: false,
       async: true,
       crossDomain: true,
       xhrFields: {
           withCredentials: true
       },
       success:function (data) {
           var result = data.list ;
           $('#table').bootstrapTable('load', {
               data: result
           })
           if (data.obj.pageCount > 0) {
               $("#pageUl").show();
               var optionsPaginator = {
                   bootstrapMajorVersion:3,
                   currentPage: pageNo,//当前页数
                   totalPages: data.total,//总页数 注意不是总条数
                   onPageClicked: function (e, originalEvent, type, page) {
                       getList(page);
                   }
               };
               $("#pageUl").bootstrapPaginator(optionsPaginator);
           } else {
               $("#pageUl").hide();
           }
       }
   })
};

$(function () {
    getList(1);
   
})