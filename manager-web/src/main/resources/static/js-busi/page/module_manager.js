var basePath = WEB_ROOT;
$(function(){
	queryModuleList(1);

})
function queryModuleList(pageNo){
	var moduleName=$.trim($('#moduleName').val());
	var url=basePath+'/pageManage/queryModuleList'
	var params={pageNo:pageNo,moduleName:moduleName};
	var callBack = function(data){
		var html='';
		if(data.success){
			$('#moduleName').val(data.moduleName);
			var moduleList = data.moduleList;
			$(moduleList).each(function(i,d){
				html +='<tr>'+
                    '<td>'+d.moduleId+'</td>'+
                    '<td> '+d.moduleName+'</td>';
                    if(d.modulePid == '-1'){
                    	html += '<td>'+d.modulePid+'</td>';
                    }else{
                    	html += '<td>'+d.remark+'</td>';
                    }
					html +='<td>'+d.moduleCount+'</td>'+
					'<td>'+d.orderNo+'</td>';
					if(d.moduleType == '01'){
						html +='<td>商品</td>';
					}else if(d.moduleType == '02'){
						html +='<td>广告</td>';
					}else if(d.moduleType == '03'){
						html +='<td>新闻资讯</td>';
					}else{
						html +='<td>-</td>';
					}
					
					html +='<td>'+
                        '<p class="pop-link">'+
                            '<a href="'+basePath+'/pageManage/editModule?moduleId='+d.moduleId+'">编辑</a>'+
                        '</p>'+
                    '</td>'+
                '</tr>';
			});
			$('#tab01 tbody').html(html);
			/*$("#pagerId").data({"count":data.pageInfo.count,
				"size":data.pageInfo.pageSize,
				"currentindex":data.pageInfo.pageNo});
			$('#pagerId').empty().pager({
				callback:function(index){
					queryModuleList(index);
        	}});*/
		}
	}
	$.ajax({
		url:url,
		dataType:'json',
		cacha:false,
		async:true,
		data:params,
		success:callBack
	});
}
