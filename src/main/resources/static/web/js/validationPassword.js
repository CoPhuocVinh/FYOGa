// validation password

const newPasswordInput = document.querySelector('.new-password');
const confirmPasswordInput = document.querySelector('.confirm-password');

//check password

// Add event listener to the new password input
newPasswordInput.addEventListener('input', () => {
    const newPassword = newPasswordInput.value.trim();
    const confirmPassword = confirmPasswordInput.value.trim();

    if (newPassword === confirmPassword) {
        // Passwords match
        newPasswordInput.style.borderColor = ''; // Reset border color
        confirmPasswordInput.style.borderColor = ''; // Reset border color
        document.querySelector('.error-message').style.display = 'none'; // Hide error message
    } else {
        // Passwords do not match
        newPasswordInput.style.borderColor = 'red'; // Set border color to red
        confirmPasswordInput.style.borderColor = 'red'; // Set border color to red
        document.querySelector('.error-message').style.display = 'inline'; // Show error message
    }
});


// Add event listener to the confirm password input
confirmPasswordInput.addEventListener('input', () => {
    const newPassword = newPasswordInput.value.trim();
    const confirmPassword = confirmPasswordInput.value.trim();

    if (newPassword === confirmPassword) {
        // Passwords match
        newPasswordInput.style.borderColor = ''; // Reset border color
        confirmPasswordInput.style.borderColor = ''; // Reset border color
        document.querySelector('.error-message').style.display = 'none'; // Hide error message
    } else {
        // Passwords do not match
        newPasswordInput.style.borderColor = 'red'; // Set border color to red
        confirmPasswordInput.style.borderColor = 'red'; // Set border color to red
        document.querySelector('.error-message').style.display = 'inline'; // Show error message
    }
});

