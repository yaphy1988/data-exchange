/**
 * @license Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	//默认basic模式
    config.toolbar = 'Full';
    //定义full模式的按钮
    config.toolbar_Full = [
       ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print','SpellChecker', 'Scayt'],
       ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
       ['Source','Maximize'],
       '/',
       ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
       ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
       ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
       ['Link','Unlink','Anchor'],
       ['Image','Table','HorizontalRule','SpecialChar','PageBreak'],
       '/',
       ['Styles','Format','Font','FontSize','lineheight'],
       ['TextColor','BGColor'],
       ['Preview']
    ];
    
    //定义Basic的按钮
    config.toolbar_Basic = [
       ['Source','-','Save','NewPage','Preview','-','Templates'],
       ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
       ['Bold','Italic','Underline','Strike','-'],
       ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
       ['Image','Table','HorizontalRule','SpecialChar','PageBreak'],
       '/',
       ['Styles','Format','Font','FontSize','lineheight'],
       ['TextColor','BGColor']
       ['Source','Maximize'],
    ];
    
    
     //设置快捷键
    config.keystrokes = [
        [CKEDITOR.ALT + 121 /*F10*/, 'toolbarFocus' ],  //获取焦点
        [CKEDITOR.ALT + 122 /*F11*/, 'elementsPathFocus' ],  //元素焦点
        [ CKEDITOR.SHIFT + 121 /*F10*/,'contextMenu' ],  //文本菜单
       	[ CKEDITOR.CTRL + 90 /*Z*/, 'undo' ], //撤销
        [ CKEDITOR.CTRL + 89 /*Y*/, 'redo' ],  //重做
        [CKEDITOR.CTRL + CKEDITOR.SHIFT + 90 /*Z*/, 'redo' ],  //ctrl+shift+z
        [ CKEDITOR.CTRL + 76 /*L*/, 'link' ], //链接
        [ CKEDITOR.CTRL + 66 /*B*/, 'bold' ], //粗体
        [ CKEDITOR.CTRL + 73 /*I*/, 'italic' ],  //斜体
        [ CKEDITOR.CTRL + 85 /*U*/, 'underline' ],  //下划线
        [ CKEDITOR.ALT + 109 /*-*/,'toolbarCollapse' ]//收缩工具栏
    ];
    
    config.language = 'zh-cn'; 
	// Remove some buttons, provided by the standard plugins, which we don't
	// need to have in the Standard(s) toolbar.
	config.extraPlugins = 'font';
    config.extraPlugins += (config.extraPlugins ? ',lineheight' : 'lineheight');//
	config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;';
	config.removeButtons = 'Subscript,Superscript';
	config.image_previewText=' '; //预览区域显示内容
};
