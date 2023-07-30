
//thay đổi avt khi mà load được hình từ máy .
var fileInput = document.getElementById("fileInputAvatar");
var preview = document.getElementById("preview");
fileInput.addEventListener("change", function () {
    const file = this.files[0];
    const reader = new FileReader();
    reader.onload = function (e) {
        const img = document.createElement("img");
        img.src = e.target.result;
        preview.innerHTML = "";
        preview.appendChild(img);
    };

    reader.readAsDataURL(file);
});

function updateAvatar(event) {
    const file = event.target.files[0];
    const avatarImg = document.querySelector('.avatar');

    if (file) {
        const reader = new FileReader();

        reader.onload = function() {
            const newAvatarDataUrl = reader.result;
            avatarImg.src = newAvatarDataUrl;
        }

        reader.readAsDataURL(file);
    }

    // Đặt lại giá trị của input file thành null để cho phép chọn lại hình ảnh khác
    event.target.value = null;
}