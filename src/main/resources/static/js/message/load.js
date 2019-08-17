function showMessage(belong) {
    document.write("<div id='msg_loader'></div>");
    $("#msg_loader").load("/message/" + belong + " #msg_main", function () {
        $.ajax({
            url: "/js/wangEditor.min.js",
            dataType: "script",
            async: false
        });
        $.ajax({
            url: "/js/message/message.js",
            dataType: "script"
        });
    });
}