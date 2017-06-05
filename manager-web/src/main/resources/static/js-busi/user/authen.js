
var total = 0;
var pageNum = 0;
var pageSize = 0;
function queryData(index){
	var pageNo = index;
	if(pageNo==undefined){
		pageNo = 1;
	}
	var status = $("#status").val();
	$.ajax({
		url:WEB_ROOT+'/authencheck/getcheckdata',
		type:'POST',
		cache:false,
		async:true,
		dataType:'json',
		data : {
			status:status,
			pageNo : pageNo
		},
		success:function(obj){
			$('#authdatas').html('');
			$("#pagerId").html('');
			if(obj.success){
				var data = obj.data;
				for(var i=0;i<data.length;i++){
					var tr = $("<tr></tr>");
					tr.append($("<td></td>").append(data[i].taxId));
					tr.append($("<td></td>").append(data[i].invoiceTitle));
					tr.append($("<td></td>").append(data[i].contactInfo));
					tr.append($("<td></td>").append(data[i].taxpayerNo));
					tr.append($("<td></td>").append(data[i].taxNo));
					tr.append($("<td></td>").append(data[i].organCode));
					tr.append($("<td></td>").append(data[i].relName));
					if("10"==data[i].status){
						tr.append($("<td></td>").append("待审核"));
						tr.append($("<td></td>").append("<a href='"+WEB_ROOT+"/authencheck/getdetail/"+data[i].taxId+"'>审核</a>"));
					}else if("20"==data[i].status){
						tr.append($("<td></td>").append("审核通过"));
						tr.append($("<td></td>").append(""));
					}else {
						tr.append($("<td></td>").append("审核不通过"));
						tr.append($("<td></td>").append(""));
					}
					$('#authdatas').append(tr);
					
				}			
				/**
				 * 分页组件
				 */			
				var pageInfo = obj.pageInfo;
				total = pageInfo.count;
			    pageNum = pageInfo.pageNo;
			    pageSize = pageInfo.pageSize;
			    $("#pagerId").data({"count":total,
					"size":pageSize,
					"currentindex":pageNum});
				$('#pagerId').pager({callback: function(index){
					queryData(index);
				}});
			}else{
				var tr = $("<tr></tr>");
				tr.append($("<td colspan='9'></td>").append(obj.msg));
				$('#authdatas').append(tr);
			}
			
		}	
	});
}
$(function(){
	queryData(1);
});