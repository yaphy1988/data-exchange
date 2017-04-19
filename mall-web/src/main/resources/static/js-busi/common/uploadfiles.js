/**
 * 通用文件上传服务公共js
 */
$(document).ready(function() {
    var uploadUrl =  WEB_ROOT + "/file/upload";
    var deleteUrl = WEB_ROOT + "/file/delete";

    commonFileInput("common-files", uploadUrl, deleteUrl);
});

function commonFileInput(className, uploadUrl, deleteUrl) {
    var control = $('input.' + className); //选取class为common-files的input元素

    control.fileinput({
        theme: "fa",
        language: 'zh', //设置语言
        uploadUrl: uploadUrl, //上传的地址
        deleteUrl: deleteUrl,
        uploadAsync: true,//异步上传
        maxFileCount: 8,
        dropZoneEnabled: false,
        preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
        previewFileIconSettings: { // configure your icon file extensions
            'doc': '<i class="fa fa-file-word-o text-primary"></i>',
            'xls': '<i class="fa fa-file-excel-o text-success"></i>',
            'ppt': '<i class="fa fa-file-powerpoint-o text-danger"></i>',
            'pdf': '<i class="fa fa-file-pdf-o text-danger"></i>',
            'zip': '<i class="fa fa-file-archive-o text-muted"></i>',
            'htm': '<i class="fa fa-file-code-o text-info"></i>',
            'txt': '<i class="fa fa-file-text-o text-info"></i>',
            'mov': '<i class="fa fa-file-movie-o text-warning"></i>',
            'mp3': '<i class="fa fa-file-audio-o text-warning"></i>',
            // note for these file types below no extension determination logic
            // has been configured (the keys itself will be used as extensions)
            'jpg': '<i class="fa fa-file-photo-o text-danger"></i>',
            'gif': '<i class="fa fa-file-photo-o text-muted"></i>',
            'png': '<i class="fa fa-file-photo-o text-primary"></i>'
        },
        previewFileExtSettings: { // configure the logic for determining icon file extensions
            'doc': function (ext) {
                return ext.match(/(doc|docx)$/i);
            },
            'xls': function (ext) {
                return ext.match(/(xls|xlsx)$/i);
            },
            'ppt': function (ext) {
                return ext.match(/(ppt|pptx)$/i);
            },
            'zip': function (ext) {
                return ext.match(/(zip|rar|tar|gzip|gz|7z)$/i);
            },
            'htm': function (ext) {
                return ext.match(/(htm|html)$/i);
            },
            'txt': function (ext) {
                return ext.match(/(txt|ini|csv|java|php|js|css|conf)$/i);
            },
            'mov': function (ext) {
                return ext.match(/(avi|mpg|mkv|mov|mp4|3gp|webm|wmv)$/i);
            },
            'mp3': function (ext) {
                return ext.match(/(mp3|wav)$/i);
            },
        }
    });
    control.on('fileuploaded', function(event, data) {
        var vfsId = data.response.initialPreviewConfig[0].key;
        var fileName = data.response.initialPreviewConfig[0].caption;
        if (typeof upFilesCallback === 'function') {
            upFilesCallback(vfsId,fileName);
        } else {
            console.info("回调方法不可用");
        }
    });
    control.on("filepredelete", function(event, key) {
        var abort = true;
        if (typeof deleteFilesCallback === 'function') {
            deleteFilesCallback(key);
            abort = false;
        }
        return abort; // you can also send any data/object that you can receive on `filecustomerror` event
    });
}

