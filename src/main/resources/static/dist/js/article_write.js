/**
 * 게시글 삽입 요청을 위한 Ajax 요청을 보낸다.
 *
 * @param data
 * @return
 */
function insert(data) {
    console.log("insert 요청 보내기");
    // if (validateForm()) return;

    var uploadInfo = setHiddenInputForFile();
    var uploadInfoObject = { attach : uploadInfo };
    var combined = Object.assign(data, uploadInfoObject);
    console.log(combined);

    $.ajax({
        url: "/board/write",
        type: "post",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(combined),
        success: function(resultData) {
            alert("insert 데이터 정상 처리 !\nidx: " + resultData.idx);
            console.log("데이터 정상 처리" + resultData);
            window.location.href = '/board/read?idx=' + resultData.idx;
        },
        error: function(request, error) {
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

/**
 * 게시글 작성을 위한 입력 폼에 대해 유효성 검증을 한다.
 *
 * @param
 * @return boolean
 */
function validateForm() {
    // 제목 또는 내용이 비어있다면
    if ($.trim($("#title").val()) == "" || CKEDITOR.instances.content.getData().length < 1) {
        alert("제목 또는 내용을 입력하세요 !");
        return false;
    }
    return true;
}

/**
 * 업로드된 파일 목록을 글 작성 form 태그 안에 <input type='hidden' ..> 으로 추가한다.
 * 글 작성하기 버튼을 누르면 첨부파일 정보도 같이 보내 DB에 저장한다.
 *
 * @param
 * @return Object
 */
function setHiddenInputForFile() {
    var formObj = $("form[role='form']");
    var str = "";

    $(".uploadResult ul li").each(function(i, obj) {
        var $obj = $(obj);
        console.dir($obj);

        str += "<input type='hidden' name='attach.fileName' value='" + $obj.data("filename") + "'>";
        str += "<input type='hidden' name='attach.uuid' value='" + $obj.data("uuid") + "'>";
        str += "<input type='hidden' name='attach.uploadPath' value='" + $obj.data("path") + "'>";
        str += "<input type='hidden' name='attach.fileType' value='" + $obj.data("type") + "'>";
    });

    formObj.append(str);

    return {
        "fileName" : $(".uploadResult ul li").data("filename")
        , "uuid"     : $(".uploadResult ul li").data("uuid")
        , "uploadPath"     : $(".uploadResult ul li").data("path")
        , "fileType"     : $(".uploadResult ul li").data("type")
    };
}