var myinfomodal = document.querySelector("#myinfo-modal");
var myinfoButton = document.querySelector("#myinfo-button");
var myinfoCloseButton = document.querySelector("#myinfo-close-button");

myinfoButton.addEventListener("click", function() {
    myinfomodal.classList.toggle("opened");
});
myinfoCloseButton.addEventListener("click", function() {
    myinfomodal.classList.toggle("opened");
});