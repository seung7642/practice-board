const regEx = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
const maxSize = 1024 * 1024 * 5; // 5MB

/**
 * 업로드하는 파일의 확장자와 사이즈를 체크하는 함수.
 *
 * @param fileName
 * @param fileSize
 */
function checkExtension(fileName, fileSize) {
    if (fileSize >= maxSize) {
        alert("파일 사이즈 초과");
        return false;
    }

    if (regEx.test(fileName)) {
        alert("해당 종류의 파일은 업로드할 수 없습니다.");
        return false;
    }

    return true;
}

/**
 * 파일 업로드용 ajax 요청을 담은 함수.
 *
 * @returns {boolean}
 */
function uploadFile() {
    const formData = new FormData(); // Javascript의 Built-in 객체인 FormData 객체
    const inputFile = $("input[name='uploadFile']");
    const files = inputFile[0].files;
    console.log(files);

    for (let i = 0; i < files.length; ++i) {
        if (!checkExtension(files[i].name, files[i].size)) return false;

        formData.append("uploadFile", files[i]);
    }

    $.ajax({
        url: '/board/uploadFile',
        type: "post",
        processData: false,
        contentType: false,
        data: formData,
        success: function(resultData) {
            alert("Uploaded Success !");
        },
        error: function() {
            alert("Upload Failed !");
        }
    })
}

function insert() {
    // TODO: 게시글 데이터와 첨부 파일 데이터를 하나의 ajax로 같이 보낼 수 있나 ?
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
            if (resultData !== 0) {
                console.log("insert 데이터 정상 처리 !");
                alert("insert 데이터 정상 처리 !");
            } else {
                alert("insert 실패 !");
            }
        },
        error: function() {
            alert("insert 에러 !");
        }
    });
}

$(document).ready(function() {

    $("#btnSave").on("click", insert);
})