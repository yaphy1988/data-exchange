var ckeditPackage,ckeditDataDetail,ckeditDataExample,ckeditCase,ckeditCompany;
/**
 * 弹出商品标签选择框
 * @returns
 */
function selectGdsLabelModal(){
	$("#myModal2").modal();
	queryGdsLabelQuikList();
}
/**
 * 快速选择标签
 * @returns
 */
function selectGdsLabel(obj){
	var labId=$(obj).attr("labId");
	var labName=$(obj).attr("labName");
	var labColor=$(obj).attr("labColor");

	$("selGdsLabId").val(labId);
	$("selGdsLabName").val(labName);
	$("selGdsLabColor").val(labColor);
}

/**
 * 配置标签保存
 * @returns
 */
function saveGdsLabelQuik(){
	
	var labId=$("selGdsLabId").val();
	var labName=$("selGdsLabName").val();
	var labColor=$("selGdsLabColor").val();
	var catFist = $("#catFist").val();
	var params={
			labName:labName,
			labColor:labColor,
			catFist:catFist
	}
	var url="/gdsEdit/saveGdsLabelQuik";
	$.ajax({
		url : url,
		type : "POST",
		dataType : "json",
		async : false,
		data : params,
        success : function(data) {
        	if (data.success){
        		var labAppend='<span class="label label-primary">API</span>';
        		$("td[name='labelList']").append();
        	}
         },
     });

}
/**
 * 查询分类属表
 */
function queryGdsProp(catId){
	//商品分类：API、数据定制、解决方案
	var url="/gdsEdit/queryGdsPropList";
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
//新增套餐
function addGdsSkuHtml(){
	
}
function addProp(layout) {
	var proType = layout['proType'];//1文本、2富文本
	var proId = layout['proId'];
	var proName = layout['proName'];
	var sort = layout['showOrder'];
	var modular=$("#addProp");
//	switch (proId) {
//		case 1:
			//套餐介绍
			addCkedit(proId,proName,proType);
//			break;
//		case 2:
//		default:
//			break;
//		}
}
/**
 * 查询获取商品标签快速选择数据
 */
function queryGdsLabelQuikList(){
	//商品分类：API、数据定制、解决方案
	var  labelName = $("#qryLabName").val();
	var  catFirst = $("#catFirst").val();
	var url="/gdsEdit/queryGdsLabelQuikList";
	var params={
			catFirst:catFirst,
			labelName:labelName
	};
	$.ajax({
		url : url,
		type : "POST",
		dataType : "json",
		async : false,
		data : params,
        success : function(data) {
        	 var html=$("#gdsLabelQuikTable").find("tbody");
        	 var append="";
 			if(data.object != null && data.object != ""){
				$.each(data, function(i, n) {
					var prop=n;
                	append +='<tr><td labId="'+prop.labId+'" labColor="'+prop.labColor+'" onclick="selectGdsLabel(this)">'+prop.labName+'</td></tr>';
				});
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
	$this.parent().siblings().find(".active").removeClass('active');
	$this.addClass("warning")
	$this.addClass("active");
	//商品分类：API、数据定制、解决方案
	var  catId = $this.attr("catId");
	//当前操作的节点层级
	var nodeLevel = Number($this.attr('level'));

	var url="/gdsEdit/querySubCatNodes";
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
	var url="/gdsEdit/querySubCatNodes";
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
         		alert('提示','请选择一个没有子级的商品分类');
         		return;
         	}else{

         		var catFirst = $('div[name="catDiv"]').eq(0).find('.active').attr('catId');
         		var catFirstName=$('div[name="catDiv"]').eq(0).find('.active').attr('catName');
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
	var oldCatFirst = $('#catFirst').val();
	if (oldCatFirst != catFirst) {
		queryGdsProp(catFirst);
	}
	//API分类
	if(catFirst=="1"){
		$('[name="DivAPI"]').css("display","")	;
		$("#TableAPI").css("display","");
		$("#APIPackage").css("display","");
	}else{
		$('[name="DivAPI"]').css("display","none")	;
		$("#TableAPI").css("display","none");
		$("#APIPackage").css("display","none");
		$("#gdsApiId").val("");
		$("#APIPackage").find("tbody").empty();
	}
	$('#catName').val(catName);
	$('#catId').val(catId);
	$('#catFirst').val(catFirst);
	var catText = "/" + catFirstName + "/" + catName;
	$("#catText").val(catText);
}

// 保存商品信息
function saveGds(){
	var catId = $("catId").val();
	var catFirst = $("#catFirst").val();
	var gdsId = $("#gdsId").val();
	var gdsInfo2CatVO={
			catId:catId,
			catFirst:catFirst,
			gdsId:gdsId,
	};
	var gdsName = $("#gdsName").val();
	var gdsSubTitle = $("#gdsSubTitle").val();
	var apiId = $("#apiId").val();
	var gdsPic = $("#gdsPic").val();
	var ifRecommend = $("#ifRecommend").val();
	var funIntroduction = $("#funIntroduction").val();
	var commpanyName = $("#commpanyName").val();
	var gdsInfoVO={
			gdsId:gdsId,
			gdsName:gdsName,
			gdsSubTitle:gdsSubTitle,
			catFirst:catFirst,
			apiId:apiId,
			gdsPic:gdsPic,
			ifRecommend:ifRecommend,
			funIntroduction:funIntroduction,
			commpanyName:commpanyName
	}
	var labId = $("#labId").val();
	var labName = $("#labName").val();
	var labColor = $("#labColor").val();
	var gdsLabelVO={
			labId:labId,
			labName:labName,
			labColor:labColor,
			showOrder:1
	};
	var params={
			gdsInfoVO:gdsInfoVO,
			gdsInfo2CatVO:gdsInfo2CatVO,
	};
	var url="/gdsEdit/addGds";
	$.ajax({
        url : url,
        data : params,
        async : true,
        dataType : 'json',
        success : function(data) {
            if (data.success&&data.object.length>0) {
               for(var i=0 ;i<data.object.length;i++){
               	var prop=data.object[i];
               	addmodular(prop);
               }
            }
        },
        error : function() {
        }
    });
}
/**
 * 上传图片
 * 
 * @param {}
 *            object
 * @param {}
 *            path
 */
function uploadImage(object, path) {
	var filepath = path;
	filepath = (filepath + '').toLowerCase();
	var regex = new RegExp(
			'\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$', 'gi');
	/** 上传图片文件格式验证 */
	if (!filepath || !filepath.match(regex)) {
		alert('请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp).');
		uploadfile.value = "";
		return;
	}
	var url = basePath + '/product/uploadImage';
	var callback = function(data, status) {

		/** 上传成功，隐藏上传组件，并显示该图片 */
		if (data.flag == true) {
			// $("#imageFileUpload").hide();
			var img = "img[name='" + data.id + "']";
			$(img).attr("src", data.imagePath);
			var hid_pic_str = "input[type='hidden'][name='" + data.id + "']";
			var hid_pic_str_name = "input[type='hidden'][name='" + data.id
					+ "Name']";
			$(hid_pic_str).val(data.imageId);
			$(hid_pic_str_name).val(data.imageName);
			$('.productImage_con').next().children("input[type='file']").each(
					function() {
						$(this).bind("change", function(o) {

									var path = $(this).val();
									uploadImage(this, path);
								});
					});
		} else {
			alert(data.error);
		}
	};
	ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback);
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
function ajaxFileUpload(url, secureuri, fileElementId, type, dataType, callback) {
	$.ajaxFileUpload({
				url : url, // 用于文件上传的服务器端请求地址
				secureuri : secureuri, // 一般设置为false
				fileElementId : fileElementId, // 文件上传空间的id属性 <input
				// type="file" id="imageFile"
				// name="imageFile" />
				type : type, // get 或 post
				dataType : dataType, // 返回值类型
				success : callback, // 服务器成功响应处理函数
				error : function(data, status, e) // 服务器响应失败处理函数
				{
					alert(e);
				}
			});
}
function addCkedit(proId,proName,proType){
	var modular=$("#addProp");
	var html="";
	html +='<div class="title">'+proName+'</div>';
	var ckeditId="";
	if(proId==1){//套餐介绍
		ckeditId="editorPackage";
	}else if(proId==3){//数据详情
		ckeditId="editorDataDetail";
	}else if(propId==4){//样例数据
		ckeditId="editorDataExample";
	}else if(propId==7){//应用场景或案例
		ckeditId="editorCase";
	}else if(pro==8){
		ckeditId="editorCompany";
	}
	if(proType=="2"){//富文本
		html +='<div id='+ckeditId+'></div>';
	}else{
		if(proId=="2"){
			html +='<table class="editTable"><tbody><tr>'
					+'<td class="Hint tips" width="120"><span class="col_red">*</span>'
					+proName+':</td><td width="520"><textarea class="form-control" rows="5"></textarea>'
					+'</td></tr></tbody></table>';
		}else{
			html +='<div><textarea class="form-control"  rows="5"></textarea></div>';	
		}
	}
	modular.append(html);
	createEditor(ckeditId);
}
//创建编辑器
function createEditor(id) {
	// Create a new editor inside the <div id="editor">, setting its value to
	// html

	var config = {
		filebrowserImageUploadUrl : "/ck/upload?type=Image",
		filebrowserUploadUrl :"/ck/upload?type=File",
		filebrowserFlashUploadUrl :"/ck/upload?type=Flash"
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