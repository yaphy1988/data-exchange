var basePath = WEB_ROOT;
$(function(){
	queryNewsPageInfo(1);

})
function editOrAddNewsInfo(infoId){
	var url =basePath+ '/pageManage/addNewsPageInfo';
	$.ajax({
		url:url,
		dataType:'html',
		cacha:false,
		async:true,
		data:{infoId:infoId},
		success:function(data){
			$('#tab02').html(data).attr('class','tab-pane active');
			$('#tab01').attr('class','tab-pane');
			$('ul[role=tablist]').children().removeClass('active').eq(1).attr('class',"active");
			createEditor();
		}
	});
}
function updateNewsPageInfo(infoId,status){
	var url = basePath+'/pageManage/updateNewsPageInfo';
	$.ajax({
		url:url,
		dataType:'json',
		cacha:false,
		async:true,
		data:{infoId:infoId,status:status},
		success:function(data){
			if(data.infoByKey ==0){
				alert("删除失败");
			}else{
				queryNewsPageInfo(1);
			}
		}
	});
}
function createEditor() {
	// Create a new editor inside the <div id="editor">, setting its value to
	// html

	var config = {
		filebrowserImageUploadUrl : "/ck/upload?type=Image",
		filebrowserUploadUrl :"/ck/upload?type=File",
		filebrowserFlashUploadUrl :"/ck/upload?type=Flash"
	};
	ckeditor=CKEDITOR.replace("ckeditor", config);
	//ckeditPackage.setData(data.result);
	//ckeditPackage.getData()
}
function queryNewsPageInfo(pageNo){
	var infoTitle=$.trim($('#news_title').val());
	var status=$('#news_status').val();
	var infotype=$('#news_infotype').val();
	var url=basePath+'/pageManage/queryNewsPageInfo'
	var params={pageNo:pageNo,infoTitle:infoTitle,status:status,infotype:infotype};
	var callBack = function(data){
		var html='';
		if(data.success){
			$('#news_title').val(data.infoTitle);
			$('#news_status').val(data.status);
			$('#news_infotype').val(data.infotype);
			var newsList = data.pageInfo.result;
			$(newsList).each(function(i,d){
				html +='<tr>'+
                    '<td>'+parseInt(i+1)+'</td>'+
                    '<td> <div style="max-width:200px;" class="text_overflow">'+d.infoTitle+'</div></td>'+
                    '<td>'+d.infoType+'</td>';
					var pubTime = new Date(d.pubTime);
					html +='<td>'+initDate(pubTime)+'</td>';
					var lostTime = new Date(d.lostTime);
					html +='<td>'+initDate(lostTime)+'</td>';
					if(d.status =='1'){
						html +='<td>生效</td>';
					}else{
						html +='<td>失效</td>';
					}
                    html +='<td>'+
                        '<p class="pop-link">'+
                            '<a href="javascript:editOrAddNewsInfo('+d.infoId+')">编辑</a><i>|</i>'+
                            '<a href="javascript:updateNewsPageInfo('+d.infoId+',0)">删除</a>'+
                        '</p>'+
                    '</td>'+
                '</tr>';
			});
			$('#tab01 tbody').eq(1).html(html);
			$('#tab01').attr('class','tab-pane active');
			$('#tab02').attr('class','tab-pane');
			$('ul[role=tablist]').children().removeClass('active').eq(0).attr('class',"active");
			$("#pagerId").data({"count":data.pageInfo.count,
				"size":data.pageInfo.pageSize,
				"currentindex":data.pageInfo.pageNo});
			$('#pagerId').empty().pager({
				callback:function(index){
				queryNewsPageInfo(index);
        	}});
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

function initDate(pubTime){
	if(pubTime != null && pubTime != undefined){
		var d=new Date(pubTime);
		var year=d.getFullYear();
		var month=+d.getMonth()+1;
		var day=d.getDate();
		return year+"-"+month+"-"+day;
	}else{
		return "";
	}
}