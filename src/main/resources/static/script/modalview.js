// Name selects
var nameModal = document.querySelector("#name-modal");
var nameButton = document.querySelector("#name-button");
var nameCloseButton = document.querySelector("#name-close-button");

// Email selectors
var emailModal = document.querySelector("#email-modal");
var emailButton = document.querySelector("#email-button");
var emailCloseButton = document.querySelector("#email-close-button");

// Password selectors
var passwordModal = document.querySelector("#password-modal");
var passwordButton = document.querySelector("#password-button");
var passwordCloseButton = document.querySelector("#password-close-button");

// Name modal open/close
nameButton.addEventListener("click", function() {
    nameModal.classList.toggle("opened");
    if (emailModal.classList.contains("opened")) {
        emailModal.classList.toggle("opened");
    }
    if (passwordModal.classList.contains("opened")) {
        passwordModal.classList.toggle("opened");
    }
});
nameCloseButton.addEventListener("click", function() {
    nameModal.classList.toggle("opened");
});

// Email modal open/close
emailButton.addEventListener("click", function() {
    emailModal.classList.toggle("opened");
    if (nameModal.classList.contains("opened")) {
        nameModal.classList.toggle("opened");
    }
    if (passwordModal.classList.contains("opened")) {
        passwordModal.classList.toggle("opened");
    }
});
emailCloseButton.addEventListener("click", function() {
    emailModal.classList.toggle("opened");
});

// Password modal open/close
passwordButton.addEventListener("click", function() {
    passwordModal.classList.toggle("opened");
    if (nameModal.classList.contains("opened")) {
        nameModal.classList.toggle("opened");
    }
    if (emailModal.classList.contains("opened")) {
        emailModal.classList.toggle("opened");
    }
});
passwordCloseButton.addEventListener("click", function() {
    passwordModal.classList.toggle("opened");
});