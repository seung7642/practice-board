/**
 * 파일 업로드를 위한 Ajax 요청을 보낸다.
 *
 * @param formData
 */
function upload(formData) {
    $.ajax({
        url: '/board/upload',
        type: "POST",
        processData: false,
        contentType: false,
        dataType: "json",
        data: formData,
        success: function(result) {
            alert("파일 업로드 성공 !");
            console.log(result);
            showUploadResult(result);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("업로드 에러\ncode : " + jqXHR.status + "\nerror message : " + jqXHR.responseText);
        }
    });
}

/**
 * 파일 업로드를 위한 Ajax 요청 성공 후에 업로드 결과를 보여준다.
 *
 * @param uploadResultArr
 */
function showUploadResult(uploadResult) {
    if (!uploadResult) return;

    var uploadUL = $(".uploadResult ul");
    var str = "";

    if (uploadResult.image) { // 이미지 파일인 경우
        var fileCallPath = encodeURIComponent(uploadResult.uploadPath + "/s_" + uploadResult.uuid + "_" + uploadResult.fileName);
        str += "<li><div>";
        str += "<span>" + uploadResult.fileName + "</span>";
        str += "<button type='button' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
        str += "<img src='/board/display?filename=" + fileCallPath + "'>";
        str += "</div></li>";
    } else { // 이미지 파일이 아닌 경우
        var fileCallPath = encodeURIComponent(uploadResult.uploadPath + "/" + uploadResult.uuid + "_" + uploadResult.fileName);
        var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
        str += "<li><div>";
        str += "<span>" + uploadResult.fileName + "</span>";
        str += "<button type='button' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
        str += "<img src='/images/attach.png'>";
        str += "</div></li>";
    }

    uploadUL.append(str);
}

/**
 * 업로드하는 파일의 확장자와 사이즈를 체크한다.
 *
 * @param fileName
 * @param fileSize
 */
function checkExtension(fileName, fileSize) {
    var regEx = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
    var maxSize = 1024 * 1024 * 5; // 5MB
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
 * formData 값을 가져온다.
 *
 * @returns {boolean|FormData}
 */
function getFormData() {
    var formData = new FormData();
    var inputFile = $("input[name='uploadFile']");
    var files = inputFile[0].files;

    // formData에 첨부파일 데이터를 추가한다.
    for (var i = 0; i < files.length; ++i) {
        if (!checkExtension(files[i].name, files[i].size)) return false;
        formData.append("uploadFile", files[i]);
    }
    return formData;
}
