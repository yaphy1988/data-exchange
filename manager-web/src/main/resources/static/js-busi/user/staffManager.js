/**
 * Created by yx on 2017/5/31.
 */
$(function () {
    queryAuthStaffPage(1);
})

function queryAuthStaffPage(pageNo){
    var params = {};
    params.pageNo = pageNo;
    $.ajax({
        url:WEB_ROOT + "/authStaff/queryAuthStaffPage",
        data:params,
        dataType:'html',
        type:'post',
        async:true,
        success:function (data) {
            $("#staffListDiv").html(data);
        }
    })
}

function pagerClick(pageNo){
    queryAuthStaffPage(pageNo);
}

function doStaffFlag(obj) {
    var params = {};
    var targetStaffFlag = $(obj).attr("targetStaffFlag");
    var staffFlag = $(obj).attr("staffFlag");
    var staffId = $(obj).attr("staffId");

    params.staffId = staffId;
    params.staffFlag = staffFlag;
    params.targetStaffFlag = targetStaffFlag;

    $.ajax({
        url:WEB_ROOT + "/authStaff/doStaffFlag",
        data:params,
        dataType:'json',
        type:'post',
        async:true,
        success:function (jsonObj) {
            if (jsonObj.success){
                var msg = "";
                if (targetStaffFlag == "0"){
                    msg = "失效成功！";
                }else if (targetStaffFlag == "1"){
                    msg = "生效成功！";
                }else if (targetStaffFlag == "2"){
                    msg = "加锁成功！";
                }
                WEB.msg.info("提示",msg,function () {
                    queryAuthStaffPage(1);
                });
            }else{
                var msg = "";
                if (targetStaffFlag == "0"){
                    msg = "失效失败！";
                }else if (targetStaffFlag == "1"){
                    msg = "生效失败！";
                }else if (targetStaffFlag == "2"){
                    msg = "加锁失败！";
                }
                WEB.msg.info("提示",msg);
            }
        }
    })
}

function staffInfoInit(isEdit) {
    var params = {};
    if (isEdit == "1"){
        var staffId = $("input[name='staffId']:checked").attr("staffId");
        params.staffId = staffId;
    }
    $.ajax({
        url:WEB_ROOT + "/authStaff/initStaffInfo",
        data:params,
        dataType:'html',
        type:'post',
        async:true,
        success:function (data) {
            $("#staffInfoModalDiv").html(data);
            $("#staffInfoModal").modal("show");
        }
    })
}