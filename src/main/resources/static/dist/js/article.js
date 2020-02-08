/**
 * 게시글 삽입 요청을 위한 Ajax 요청을 보낸다.
 *
 * @param
 */
function insert() {
    if (validateForm()) return;

    $.ajax({
        url: "/board/write",
        type: "post",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            title: $("#title").val()
            , content: $("#content").val()
            , writer: $("#writer").val()
        }),
        success: function(resultData) {
            alert("insert 데이터 정상 처리 !\nidx: " + resultData.idx);
            window.location.href = '/board/read?idx=' + resultData.idx;
        },
        error: function(request, error) {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

/**
 * 게시글 작성을 위한 입력 폼에 대해 유효성 검증을 한다.
 */
function validateForm() {

}