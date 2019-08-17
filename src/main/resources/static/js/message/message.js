$("#msg_submit").click(function () {
    $("#msg_input_content").val(editor.txt.html());
    var belong = $("#msg_belong").val();
    var form = $("#msg_form_csrf");
    $.ajax({
        url: form.attr("action"),
        type: "POST",
        data: form.serialize(),
        dataType: "json",
        beforeSend: function () {
        },
        error: function () {
            alert("评论发送失败，可能是您没有登录。");
        },
        complete: function () {
        },
        success: function (data) {
            $("#msg_ajax").load(
                "/message/" + belong + " #msg_history"
            );
        }
    });
    editor.txt.html("");
});

var E = window.wangEditor
var editor = new E('#msg_content')
editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
editor.customConfig.onchange = function (html) {
    ;
}
editor.create()