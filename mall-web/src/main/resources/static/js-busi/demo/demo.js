$(document).ready(function(){
    $("#demoSeachBtn").click(function(){
        loadDemoTable();
    });

    $("#demoTab li").click(function(){
        $("#demoTab li.active").removeClass("active");
        $(this).addClass("active");

        var tab = $(this).attr("tab");

        loadDemoTab(tab);
    });

    loadDemoTab($("#demoTab li.active").attr("tab"));
});

function loadDemoTable(){
    ajaxLoadPage({userName:'11'},WEB_ROOT+"/demo/loadDemoTable",function(data){
         $("#demoTable").html($(data).html());
         $('#pagerId').pager({
            callback:function(index){
                loadDemoTable();
            }
        });
    });
}

function loadDemoTab(id){
    ajaxLoadPage({id:id},WEB_ROOT+"/demo/loadDemoTab",function(data){
         $("#demoTabContext").html($(data).html());
     });
}

function ajaxLoadPage(params,url,callBack){
	$.ajax({
		url: url,
        data:params,
        type:'post',
        dataType:"html",
        async:true,
        cache:false,
        success: function (data){
            if(callBack != undefined){
        		callBack(data);
        	}
        }
	});
}