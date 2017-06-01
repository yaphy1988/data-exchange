function updateComplaintCommon(params,callBack) {
    $.gridLoading({message:'正在更新...'})
    var url = MANAGE_ROOT+'/ordgdsComplaint/updateComplaint';
    $.appAjax({
        url:url,
        cache:false,
        data:params,
        async:true,
        dataType:'json',
        success:callBack
    });
}
function queryComplaintList(index){
    var url = MANAGE_ROOT+'/ordgdsComplaint/queryComplaint';
    var startTime = $('#comp_startTime').val();
    var endTime = $('#comp_endTime').val();
    var compStatus = $.trim($('#comp_status').val());
    var compItem = $.trim($('#comp_item').val());
    var params={pageNo:index,startTime:startTime,endTime:endTime,compStatus:compStatus,compItem:compItem};
    $.gridLoading({ message:"正在加载中.." });
    $.appAjax({
        url:url,
        data:params,
        dataType:'html',
        cache:false,
        async:true,
        success:function(data){
            $.gridUnLoading();
            $('#complant_content').html(data);
            $('#tab02').attr('class','tab-pane active');
            dateTimepickerinit('comp_startTime','comp_endTime');
        }
    });
}
/**
 * 初始化时间控件
 * @param startTime
 * @param endTime
 */
function dateTimepickerinit(startTime,endTime) {
    $("#"+startTime+"").datetimepicker({
        format: 'yyyy-mm-dd 00:00:00',
        minView:'month',
        language: 'zh-CN',
        clearBtn:true,// 自定义属性,true 显示 清空按钮 false 隐藏 默认:true
        autoclose:true
        // startDate:new Date()
    }).on("click",function(){
        $("#"+startTime+"").datetimepicker("setEndDate",$("#"+endTime+"").val());
    });
    $("#"+endTime+"").datetimepicker({
        format: 'yyyy-mm-dd 23:59:59',
        minView:'month',
        language: 'zh-CN',
        clearBtn:true,// 自定义属性,true 显示 清空按钮 false 隐藏 默认:true
        autoclose:true
        // startDate:new Date()
    }).on("click",function(){
        $("#"+endTime+"").datetimepicker("setStartDate",$("#"+startTime+"").val());
    });
}
function modalDatahiden() {
    $('#myModal').modal('hide');
}
//我要投诉
function iwantComplaint(orderId,compId){
    $.appAjax({
        url:MANAGE_ROOT+'/ordgdsComplaint/iwantComplaint',
        data:{orderId:orderId,compId:compId},
        dataType:'html',
        cache:false,
        async:true,
        success:function (data) {
            $('#myModal>div').html(data);
            $('#myModal').modal('show');
        }
    })

}
function pagerClick(index){
    queryComplaintList(index);
}