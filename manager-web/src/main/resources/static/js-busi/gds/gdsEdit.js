var ckeditPackage,ckeditDataDetail,ckeditDataExample,ckeditCase,ckeditCompany;
var saveValid=true;
var basePath = WEB_ROOT;
$(function(){
	var isEdit = $("#isEdit").val();
	var catFirst = $("#catFirst").val();
	if(isEdit=="true"){
		var gdsId=$("#gdsId").val();
		var catText="/";
		catText += $("#catFirstName").val();
		catText +="/";
		if(catFirst=="1"){
			$('div[name="DivAPI"]').css("display","");
			catText +=$("#catName").val();
			gridAPIInfoList(1);
		}else if(catFirst=="3"){
			$("#otherInfo").css("display","");
			addSolutionText();
		}else{
			$("#otherInfo").css("display","");
		}
		$("#catText").val(catText);
		queryGdsInfo2Prop(gdsId);
	}
	
});
/**
 * 弹出商品标签选择框
 * @returns
 */
function selectGdsLabelModal(){
	var  catFirst = $("#catFirst").val();
	if(catFirst==""){
		WEB.msg.info("提示","请选择商品分类");
		$("#myModal2").modal('hide');
		return;
	}
	queryGdsLabelQuikList();
	$("#myModal2").modal();
}
/**
 * 快速选择标签
 * @returns
 */
function selectGdsLabel(obj){
	var labName=$(obj).attr("labName");
	var labColor=$(obj).attr("labColor");
	$("#selGdsLabName").val(labName);
	$("#selGdsLabColor").val(labColor);
	changeLabel();
}
function changeLabel(){
	var labName=$("#selGdsLabName").val();
	var labColor=$("#selGdsLabColor").val();
	var labAppend='<span class="label" style="border:solid 1px '+labColor+';color:'+labColor+'; " labId="" labName="'+labName+'" labColor="'+labColor+'">'+labName+'</span>';
	$("#addChangeLabel").empty();
	$("#addChangeLabel").append(labAppend);

}

/**
 * 配置标签保存
 * @returns
 */
function saveGdsLabelQuik(){
	
	var labId=$("#selGdsLabId").val();
	var labName=$("#selGdsLabName").val();
	var labColor=$("#selGdsLabColor").val();
	var catFirst = $("#catFirst").val();
	if(catFirst==""||catFirst==undefined){
		WEB.msg.info("提示","请选择商品分类");
		$("#myModal2").modal('hide');
		return;
	}
	if(labName==""){
		WEB.msg.info("提示","标签名称不能为空");
		return;
	}
	if(labName.length>20){
		WEB.msg.info("提示","标签名称不能超过20个字符");
		return;
	}
	var labAppend = '<p style="border:solid 1px ' + labColor+ ';color:' 
					+ labColor + '; " labId="" labName="' + labName+ '" labColor="' 
					+ labColor + '"><span><label>' 
					+ labName + '</label><a class=" pl5 colse"  onclick="deleteGdsLabel(this)">×</a></span></p>';
	$("#gdsLabelList").append(labAppend);
	$("#myModal2").modal('hide');
}
/**
 * 删除标签
 * @param obj
 */
function deleteGdsLabel(obj){
	WEB.msg.confirm("提示","确定要删除该标签吗？",function(){
		$(obj).parent().parent().remove();
	});
}
/**
 * 新增套餐
 */
function addPackAgeOne(){
	var html='';
	html +='<tr><td><input type="text" name="skuName"></td>'
		   +'<td><input type="text" name="packPrice"></td>'
		   +'<td><input type="text" name="packTimes"></td>'
		   +'<td><input type="text" name="packDay"></td>'
		   +'<td><a href="javascript:void(0);" onclick="deleteGdsSku(this)">删除</a></td></tr>';
	$("#APIPackage").find("tbody").append(html);
}
/**
 * 删除套餐
 */
function deleteGdsSku(obj){
	WEB.msg.confirm("提示","确定要删除该套餐吗？",function(){
		$(obj).parent().parent().remove();
	});
}
/**
 * 查询分类属表
 */
function queryGdsProp(catId){
	//商品分类：API、数据定制、解决方案
	var url=WEB_ROOT+"/gdsEdit/queryGdsPropList";
	var params={
			catId:catId
	};
	 $.ajax({
         url : url,
         data : params,
         async : true,
         dataType : 'json',
         success : function(data) {
             if (data.success&&data.object.length>0) {
                for(var i=0 ;i<data.object.length;i++){
                	var prop=data.object[i];
                	addProp(prop);
                }
             }
         },
         error : function() {
         }
     });
}
/**
 * 查询商品分类属表
 */
function queryGdsInfo2Prop(gdsId){
	//商品分类：API、数据定制、解决方案
	var url=WEB_ROOT+"/gdsEdit/queryGdsInfo2PropList";
	var params={
			gdsId:gdsId
	};
	 $.ajax({
         url : url,
         data : params,
         async : true,
         dataType : 'json',
         success : function(data) {
             if (data.success&&data.object.length>0) {
                for(var i=0 ;i<data.object.length;i++){
                	var prop=data.object[i];
                	addProp(prop);
                }
             }
         },
     });
}
function addProp(layout) {
	var proType = layout['proType'];//1文本、2富文本
	if(proType=="2"){
		addCkedit(layout);
	}else{
		addEditor(layout);
	}
}
/**
 * 查询获取商品标签快速选择数据
 */
function queryGdsLabelQuikList(){
	//商品分类：API、数据定制、解决方案
	var  labName = $("#qryLabName").val();
	var  catFirst = $("#catFirst").val();
	var url=WEB_ROOT+"/gdsEdit/queryGdsLabelQuikList";
	var params={
			catFirst:catFirst,
			labName:labName
	};
	$.ajax({
		url : url,
		type : "POST",
		dataType : "json",
		async : false,
		data : params,
        success : function(data) {
        	$("#gdsLabelQuikTable").find("tbody").empty();
        	 var html=$("#gdsLabelQuikTable").find("tbody");
        	 var append="";
 			if(data.object != null && data.object != ""){
				for(var i=0;i<data.object.length;i++){
					var prop=data.object[i];
                	append +='<tr><td><span class="label" labId="'+prop.labId+'" labColor="'+prop.labColor+'"  labName="'+prop.labName+'" onclick="selectGdsLabel(this)"'
                			+'style="border:solid 1px '+prop.labColor+';color:'+prop.labColor+'; ">'+prop.labName+'</span></td></tr>';
				}
				html.append(append);
             }
         },
         error : function() {
         }
     });
}
/**
 * 查询获取商品分类快速选择数据
 */
function querySubCatNodes(obj){
	var $this = $(obj);
	$this.parent().siblings().find(".active").removeClass('warning');
	$this.parent().siblings().find(".active").removeClass('active');
	$this.addClass("warning")
	$this.addClass("active");
	//商品分类：API、数据定制、解决方案
	var  catId = $this.attr("catId");
	//当前操作的节点层级
	var nodeLevel = Number($this.attr('level'));

	var url=WEB_ROOT+"/gdsEdit/querySubCatNodes";
	var params={
			catId:catId
	};
	 $.ajax({
         url : url,
         data : params,
         async : true,
         dataType : 'json',
         success : function(data) {
        	 $this.parents().find('div[level="'+(nodeLevel+1)+'"] table tbody').empty();
        	 if(nodeLevel==1){
            	 $this.parents().find('div[level="'+(nodeLevel+2)+'"] table tbody').empty();
        	 }
             if (data.success&&data.object.length>0) {
            	 var appendCat=""
                for(var i=0 ;i<data.object.length;i++){
                	var prop=data.object[i];
                	appendCat +='<tr><td level="'+(nodeLevel+1)+'" catId="'+prop.catId+'" catName="'+prop.catName+'" onclick="querySubCatNodes(this)" >'+prop.catName+'</td></tr>';
                }
            	 $this.parents().find('div[level="'+(nodeLevel+1)+'"] table tbody').append(appendCat);
             }
         }
     });
}
/**
 * 校验当前结点是不是子结点，如果是则调用回调函数，否则提示不是叶子结点
 */
function catIsSubNode(){
	//最后一个选择的商品分类
	var lastCatDiv = $('div[name="catDiv"] .active:last');
	var catId=lastCatDiv.attr("catId");
	var catName=lastCatDiv.attr("catName");
	var level =  Number(lastCatDiv.attr("level"));
	var url=WEB_ROOT+"/gdsEdit/querySubCatNodes";
	var params={
			catId:catId
	};
	var isSubNode=true;
	 $.ajax({
         url : url,
         data : params,
         async : true,
         dataType : 'json',
         success : function(data) {
             if (data.success&&data.object.length>0) {
            	 isSubNode=false;
             }
             if(!isSubNode){
            	 WEB.msg.info("提示",'请选择一个没有子级的商品分类');
         		return;
         	}else{

         		var catFirst = $('div[name="catDiv"]').eq(0).find('.active').attr('catId');
         		var catFirstName=$('div[name="catDiv"]').eq(0).find('.active').attr('catName');
         		$('td[name="catFirstError"] span').html("");
         		if(typeof(selectCatCallback)!='undefined'){
         			selectCatCallback(catId,catName,catFirst,catFirstName);
         		}
         		$('#myModal').modal('hide');
         	}
         }
     });
}
/**
 *  选择商品大类回调函数
 * @param catId
 * @param catName
 * @param catFirst
 * @param catFirstName
 */
function selectCatCallback(catId, catName, catFirst, catFirstName) {
	//API分类
	if(catFirst=="1"){
		$('div[name="DivAPI"]').css("display","");
		$("#otherInfo").css("display","none");
		addPackAgeOne();
		gridAPIInfoList(1);
	}else if(catFirst=="3"){//解决方案
		$('div[name="DivAPI"]').css("display","none");
		$("#otherInfo").css("display","");
		$("#gdsApiId").val("");
		$("#APIPackage").find("tbody").empty();
		$("#otherInfo").find("tbody").empty();
		addSolutionText();
	}else{
		$('div[name="DivAPI"]').css("display","none");
		$("#otherInfo").css("display","");
		$("#gdsApiId").val("");
		$("#APIPackage").find("tbody").empty();
		$("#otherInfo").find("tbody").empty();
	}	
	$("#addProp").empty();
	var oldCatFirst = $('#catFirst').val();
	if (oldCatFirst != catFirst) {
		$("#addProp").empty();
		queryGdsProp(catFirst);
	}
	$('#catName').val(catName);
	$('#catId').val(catId);
	$('#catFirst').val(catFirst);
	var catText = "/" + catFirstName + "/" ;
	if(catFirst==1||catFirst==3){
		catText +=catName;
	}
	$("#catText").val(catText);
}

// 保存商品信息
function saveGds(){
	saveValid=true;
	var gdsInfo2CatVO = createGdsInfo2CatVO();
	var gdsInfoVO=createGdsInfoVO();
	if(!saveValid){
		return;
	}
	var gdsLabelVOList=createGdsLabelList();
	var gdsSkuVOList = createGdsSkuList();
	if(!saveValid){
		return;
	}
	var gdsInfo2PropVOList = createGdsInfo2PropList();
	if(!saveValid){
		return;
	}
	//整个活动信息的结构
	var gdsInfoObj={
			gdsInfoVO:{},//商品基本信息
			gdsInfo2CatVO:{},//商品分类关联
			gdsLabelVOList:[],// 商品标签
			gdsSkuVOList:[],//单品信息
			gdsInfo2PropVOList:[],// 属性信息
		
	}
	//逐一赋值
	gdsInfoObj.gdsInfoVO=JSON.stringify(gdsInfoVO);
	gdsInfoObj.gdsInfo2CatVO=JSON.stringify(gdsInfo2CatVO);
	if(gdsLabelVOList!=undefined){
		gdsInfoObj.gdsLabelVOList=JSON.stringify(gdsLabelVOList);	
	}
	if(gdsSkuVOList!=undefined){
		gdsInfoObj.gdsSkuVOList=JSON.stringify(gdsSkuVOList);	
	}
	if(gdsInfo2PropVOList!=undefined){
		gdsInfoObj.gdsInfo2PropVOList=JSON.stringify(gdsInfo2PropVOList);	
	}
	var gdsId=$("#gdsId").val();
	if(gdsId==""){
		var url=WEB_ROOT+"/gdsEdit/addGds";
	}else{
		var url=WEB_ROOT+"/gdsEdit/editGds";

	}
	$.ajax({
		url : url,
		type : "POST",
		dataType : "json",
		async : false,
		data : gdsInfoObj,
        success : function(data) {
            if (data.success=="true") {
            	WEB.msg.info("提示","保存成功",function(){
                	window.location.href = WEB_ROOT+"/gdsManage/index";
            	});
            }
        }
    });
}
function createGdsInfo2CatVO(){
	var gdsId = $("#gdsId").val();
	var catId = $("#catId").val();
	var catFirst = $("#catFirst").val();
	var gdsInfo2CatVO={
			catId:catId,
			catFirst:catFirst,
			gdsId:gdsId,
	};
	if(catFirst==""){
		$('td[name="catFirstError"] span').html("请选择商品分类");
		saveValid=false;
		return;
	}
	return gdsInfo2CatVO;
}
function createGdsInfoVO(){
	var gdsId = $("#gdsId").val();
	var catId = $("#catId").val();
	var catFirst = $("#catFirst").val();
	var gdsName = $("#gdsName").val();
	var gdsSubTitle = $("#gdsSubTitle").val();
	var apiId = $("#apiId").val();
	var gdsPic = $("#gdsPic").val();
	var ifRecommend = $("#ifRecommend").val();
	var funIntroduction = $("#funIntroduction").val();
	var commpanyName = $("#commpanyName").val();
	if(ifRecommend==undefined){
		ifRecommend="0";
	}
	if(funIntroduction==undefined){
		funIntroduction="";
	}else{
		if(funIntroduction!=""&&funIntroduction.length>1000){
			$('td[name="funIntroductionError"] span').html("功能介绍不能超过1000个字符");
			saveValid=false;
			return;
		}
	}
	if(commpanyName==undefined){
		commpanyName="";
	}else{
		if(commpanyName!=""&&commpanyName.length>1000){
			$('td[name="commpanyNameError"] span').html("公司名称不能超过50个字符");
			saveValid=false;
			return;
		}
	}
	//API分类
	if(catFirst=="1"){
		if(apiId==""){
			$('td[name="apiIdError"] span').html("请选择API接口");
			saveValid=false;
			return;
		}
	}
	if(gdsName==""){
		$('td[name="gdsNameError"] span').html("请录入商品名称");
		saveValid=false;
		return;
	}
	if(gdsName.length>20){
		$('td[name="gdsNameError"] span').html("商品名称不能超过20个字符");
		saveValid=false;
		return;
	}
	if(gdsSubTitle==""){
		$('td[name="gdsSubTitleError"] span').html("请录入商品副标题");
		saveValid=false;
		return;
	}
	if(gdsSubTitle.length>20){
		$('td[name="gdsSubTitleError"] span').html("商品副标题不能超过30个字符");
		saveValid=false;
		return;
	}
	if(gdsPic==""){
		WEB.msg.error("提示","请上传图片！");
		saveValid=false;
		return;
	}
	var gdsInfoVO={
			gdsId:gdsId,
			gdsName:gdsName,
			gdsSubTitle:gdsSubTitle,
			catFirst:catFirst,
			catId:catId,
			apiId:apiId,
			gdsPic:gdsPic,
			ifRecommend:ifRecommend,
			funIntroduction:funIntroduction,
			commpanyName:commpanyName
	}
	return gdsInfoVO;
}
function createGdsLabelList(){
	var gdsLabelVOList=new Array();
	$("#gdsLabelList").find("p").each(function(){
		var labName =$(this).attr("labName");
		var labColor =$(this).attr("labColor");
		var gdsLabelVO={
				labName:labName,
				labColor:labColor
		}
		gdsLabelVOList.push(gdsLabelVO);
	});
	return gdsLabelVOList;
}
function createGdsSkuList(){
	var gdsSkuVOList=new Array();
	var catFirst = $("#catFirst").val();
	var length=$("#APIPackage").find("tbody tr").length;
	if(length<1&&catFirst=="1"){
		WEB.msg.error("提示","请添加套餐！");
		saveValid=false;
		return;
	}
	$("#APIPackage").find("tbody tr").each(function(){
		var skuName=$(this).find('[name="skuName"]').val();
		var packPrice =$(this).find('[name="packPrice"]').val();
		var packTimes =$(this).find('[name="packTimes"]').val();
		var packDay =$(this).find('[name="packDay"]').val();
		if(skuName==""){
			WEB.msg.error("提示","套餐名称不能为空！");
			saveValid=false;
			return;
		}else if(skuName.length>20){
			WEB.msg.error("提示","套餐名称不能超过20个字符！");
			saveValid=false;
			return;
		}
		if(packPrice==""){
			WEB.msg.error("提示","套餐价格不能为空！");
			saveValid=false;
			return;
		}else if(!WEB.checkNum.isPirce(packPrice)){
			WEB.msg.error("提示","套餐价格输入错误");
			saveValid=false;
			return;
		}
		if(packTimes==""){
			WEB.msg.error("提示","调用次数不能为空！");
			saveValid=false;
			return;
		}else if(!isInteger(packTimes)){
			WEB.msg.error("提示","调用次数应为整数！");
			saveValid=false;
			return;
		}
		if(packTimes!="" ){
			if(!isInteger(packPrice)){
				WEB.msg.error("提示","有效期应为整数！");
				skuValid=false;
				return;
			}

		}
		var gdsSkuVO={
				skuName:skuName,
				packPrice:packPrice,
				packTimes:packTimes,
				packDay:packDay
		}
		gdsSkuVOList.push(gdsSkuVO);
	});
	return gdsSkuVOList;
}
function createGdsInfo2PropList(){
	var gdsInfo2PropVOList=new Array();
	$('tr[name="dataProps"]').each(function(){
		var gpId=$(this).find("td").attr("gpId");
		var proId=$(this).find("td").attr("proId");
		var proName=$(this).find("td").attr("proName");
		var proType=$(this).find("td").attr("proType");
		var proValue=$(this).find("textarea").val();
		var showOrder=$(this).find("td").attr("showOrder");
		if(proId=="2"){//数据描述
			if(proValue.length>300){
				WEB.msg.error("提示","数据描述不能超过300个字符！");
				saveValid=false;
				return;
			}
		}
		if(proId=="5"){//数据描述
			if(proValue.length>300){
				WEB.msg.error("提示","适用范围不能超过200个字符！");
				saveValid=false;
				return;
			}
		}
		if(proId=="6"){//特点
			if(proValue.length>300){
				WEB.msg.error("提示","特点不能超过200个字符！");
				saveValid=false;
				return;
			}
		}
		var gdsInfo2PropVO = {
			gpId : gpId,
			proId : proId,
			proName : proName,
			proValue : proValue,
			showOrder : showOrder,
			proType : proType
		}
		gdsInfo2PropVOList.push(gdsInfo2PropVO);
	});
	if(!saveValid){
		return;
	}
	$("#addProp").find(".ckeditName").each(function(){
		var gpId=$(this).attr("gpId");
		var proId=$(this).attr("proId");
		var proName=$(this).attr("proName");
		var proType=$(this).attr("proType");
		var showOrder=$(this).attr("showOrder");
		var proValue=""
		var ckeditName=$(this).attr("id");
		if(ckeditName=="editorPackage"){
			proValue=ckeditPackage.getData()
		}else if(ckeditName=="editorDataDetail"){
			proValue=ckeditDataDetail.getData()
		}else if(ckeditName=="editorDataExample"){
			proValue=ckeditDataExample.getData()
		}else if(ckeditName=="editorCase"){
			proValue=ckeditCase.getData()
		}else if(ckeditName=="editorCompany"){
			proValue=ckeditCompany.getData()
		}
			var gdsInfo2PropVO ={
					gpId:gpId,
					proId:proId,
					proName:proName,
					proValue:proValue,
					showOrder:showOrder,
					proType:proType	
			}
			gdsInfo2PropVOList.push(gdsInfo2PropVO);
	});
	return gdsInfo2PropVOList;
}
/**
 * 上传图片
 * 
 * @param {}
 *            object
 * @param {}
 *            path
 */
function uploadImage(object) {
    var filepath = $(object).val();
    if(filepath==""){
    	return false;
    }
	var width=$(object).attr("width");
	var height=$(object).attr("height");
	if(width==undefined||height==undefined){
		width="";
		height="";
	}
	filepath = (filepath + '').toLowerCase();
	var regex = new RegExp(
			'\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$', 'gi');
	/** 上传图片文件格式验证 */
	if (!filepath || !filepath.match(regex)) {
		alert('请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp).');
		return;
	}
	var url =WEB_ROOT+'/gdsEdit/uploadImage';
	var callback = function(data, status) {

		/** 上传成功，隐藏上传组件，并显示该图片 */
		if (data.flag == true) {
			$("#gdsPic").val(data.imageId);
			$("#gdsPicUrl").attr("src",data.imagePath)
		} else {
			alert(data.error);
		}
	};
	var params={
			width:width,
			height:height
	}
	ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json",params, callback);
}
/**
 * 上传文件
 * @param {} url
 * @param {} secureuri
 * @param {} fileElementId
 * @param {} type
 * @param {} dataType
 * @param {} callback
 */
function ajaxFileUpload(url, secureuri, fileElementId, type, dataType,params, callback) {
	$.ajaxFileUpload({
				url : url, // 用于文件上传的服务器端请求地址
				secureuri : secureuri, // 一般设置为false
				fileElementId : fileElementId, // 文件上传空间的id属性 <input
				type : type, // get 或 post
				data:params,
				dataType : dataType, // 返回值类型
				success : callback, // 服务器成功响应处理函数
				error : function(data, status, e) // 服务器响应失败处理函数
				{
					alert(e);
				}
			});
}
function addCkedit(layout){
	var modular=$("#addProp");
	var proType = layout['proType'];
	var proId = layout['proId'];
	var proName = layout['proName'];
	var showOrder = layout['showOrder'];
	var gdsId=$("#gdsId").val();
	var gpId="";
	var proValue="";
	if(gdsId!=""){
		var gpId=layout['gpId'];
		var proValue=layout['proValue'];
	}
	var html="";
	html +='<div class="title">'+proName+'</div>';
	var ckeditId="";
	if(proId==1){//套餐介绍
		ckeditId="editorPackage";
	}else if(proId==3){//数据详情
		ckeditId="editorDataDetail";
	}else if(proId==4){//样例数据
		ckeditId="editorDataExample";
	}else if(proId==7){//应用场景或案例
		ckeditId="editorCase";
	}else if(proId==8){
		ckeditId="editorCompany";
	}
	html +='<div id="'+ckeditId+'" class="ckeditName" gpId="'+gpId+'" proName="'+proName+'" proType="'+proType+'" proId="'+proId+'" showOrder="'+showOrder+'"></div>';
	modular.append(html);
	createEditor(ckeditId);
	if(proValue!=""&&proValue!=null){
		setCkeditValue(ckeditId,proValue);
	}
}
function addEditor(layout){
	var proType = layout['proType'];
	var proId = layout['proId'];
	var proName = layout['proName'];
	var showOrder = layout['showOrder'];
	var gdsId=$("#gdsId").val();
	var gpId="";
	var proValue="";
	if(gdsId!=""){
		var gpId=layout['gpId'];
		var proValue=layout['proValue'];
	}
	var name="";
	var html='<tr name="dataProps">'
				+'<td class="Hint tips" width="120"gpId="'+gpId+'" proName="'+proName+'" proType="'+proType+'" proId="'+proId+'" showOrder="'+showOrder+'"><span class="col_red">*</span>'+proName
				+'</td><td width="520" class="pb20"><textarea class="form-control" rows="5" name="dataProp'+proId+'"></textarea>'
				+'</td></tr>';
	$("#otherInfo").find("tbody").append(html);
	if(proValue!=""){
		$("#otherInfo").find('[name="dataProp'+proId+'"]').html(proValue);
	}
}
/**
 * 增加解决方案的功能介绍和公司名称
 * @param proId
 * @param proName
 */
function addSolutionText(proId,proName){
	var html='<tr>'
				+'<td class="Hint tips" width="120"><span class="col_red">*</span>功能介绍'
				+'</td><td width="520" class="pb20"><textarea class="form-control" rows="5" id="funIntroduction"></textarea>'
				+'</td><td  th:name="funIntroductionError"><span class="tip"></span></td></tr>';
	html +='<tr>'
		+'<td class="Hint tips" width="120"><span class="col_red">*</span>公司名称'
		+'</td><td width="520" class="pb20"><textarea class="form-control" rows="5" id="commpanyName"></textarea>'
		+'</td><td  th:name="commpanyNameError"><span class="tip"></span></td></tr>';
	$("#otherInfo").find("tbody").append(html);
	$("#funIntroduction").val($("#gdsFunIntroduction").val());
	$("#commpanyName").val($("#gdscommpanyName").val());
}
//创建编辑器
function createEditor(id) {
	// Create a new editor inside the <div id="editor">, setting its value to
	// html

	var config = {
		filebrowserImageUploadUrl :basePath+ "/ck/upload?type=Image",
		filebrowserUploadUrl :basePath+"/ck/upload?type=File",
		filebrowserFlashUploadUrl :basePath+"/ck/upload?type=Flash"
	};
	if(id=="editorPackage"){
		ckeditPackage=CKEDITOR.replace(id, config);
	}else if(id=="editorDataDetail"){
		ckeditDataDetail=CKEDITOR.replace(id, config);
	}else if(id=="editorDataExample"){
		ckeditDataExample=CKEDITOR.replace(id, config);
	}else if(id=="editorCase"){
		ckeditCase=CKEDITOR.replace(id, config);
	}else if(id=="editorCompany"){
		ckeditCompany=CKEDITOR.replace(id, config);
	}
}
function setCkeditValue(id, url) {
	 var obj=url+".html";
	$.appAjax({
			url:obj,
			async : true,
			dataType : 'jsonp',
		    jsonp :'jsonpCallback',//注意此处写死jsonCallback
			success: function (data) {
				if(id=="editorPackage"){
					ckeditPackage.setData(data.result);
				}else if(id=="editorDataDetail"){
					ckeditDataDetail.setData(data.result);
				}else if(id=="editorDataExample"){
					ckeditDataExample.setData(data.result);
				}else if(id=="editorCase"){
					ckeditCase.setData(data.result);
				}else if(id=="editorCompany"){
					ckeditCompany.setData(data.result);
				}
		    }
		});
}
//输入校验
function checkLen(obj){
	var messageValue=$(obj).val();
	var length=$(obj).attr("maxlength");
	var name=$(obj).attr("id");
	if(messageValue.length<length){
		$('td[name="'+name+'Error"] span').html("");
	}
}

function gridAPIInfoList(index){
	var param={pageNo:index};
	$.ajax({
		url:WEB_ROOT+'/gdsEdit/gridAPIInfoList',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#gdsAPIList').empty();
			$('#gdsAPIList').html(data);
		}
	});
}
function selectGdsAPIInfo(obj){
	var apiId=$(obj).parent().siblings().eq(0).text();
	var apiName=$(obj).parent().siblings().eq(1).text();
	$("#apiId").val(apiId);
	$("#apiText").val(apiName);
	$("#myModal3").modal('hide');
	$('td[name="apiIdError"] span').html("");
}
//校验输入是否为正整数
function isInteger(str){
	 var reg = /^(([1-9][0-9]*))$/;
	 return reg.test(str);
}