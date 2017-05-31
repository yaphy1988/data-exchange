$(function () {
    queryComplaintList(1);
});
function hideRepeatModal() {
    $('#modal_repeat').modal('hide');
}
function initComplaintRepeat(complaintId,orderId,complaintContId,complaintItem) {
    var url = MANAGE_ROOT+'/ordgdsComplaint/initComplaintRepeat';
    $.appAjax({
        url:url,
        data:{complaintId:complaintId,orderId:orderId,complaintContId:complaintContId,complaintItem:complaintItem},
        dataType:'html',
        cache:false,
        async:true,
        success:function(data){
            $('#modal_repeat>div').html(data);
            $('#modal_repeat').modal('show');
        }
    });
}
function updateComplaintRepeat() {
    var compId = $('#repeat_compId').val();
    var compContId = $('#repeat_compContId').val();
    var compRepeat = $.trim($('#repeat_Cont').val());
    var params ={
        compId:compId,comStatus:'2',compContId:compContId,compRepeat:compRepeat
    };
    var callBack = function (data) {
        $.gridUnLoading();
        if (data.success){
            WEB.msg.info('提示','更新成功！');
            queryComplaintList(1);
        }else{
            WEB.msg.info('提示','更新失败！');
        }
    };
    updateComplaintCommon(params,callBack);
}
