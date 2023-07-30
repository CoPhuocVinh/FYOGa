
//check số điện thoại
// Get all elements with the class 'checked-phone'
const phoneInputs = document.querySelectorAll('.checked-phone');

// Add event listener to each phone input element
phoneInputs.forEach((input) => {
    input.addEventListener('input', () => {
        const phoneNumber = input.value.trim();
        const regex = /^0[0-9]{9}$/; // Regular expression to match 10 digits starting with 0

        if (regex.test(phoneNumber)) {
            // Valid phone number
            input.style.borderColor = ''; // Reset border color
            document.querySelector('.error-message').style.display = 'none'; // Hide error message
        } else {
            // Invalid phone number
            input.style.borderColor = 'red'; // Set border color to red
            document.querySelector('.error-message').style.display = 'inline'; // Show error message
        }
    });
});

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
