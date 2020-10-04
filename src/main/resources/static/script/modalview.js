var myinfomodal = document.querySelector("#myinfo-modal");
var myinfoButton = document.querySelector("#myinfo-button");
var myinfoCloseButton = document.querySelector("#myinfo-close-button");

myinfoButton.addEventListener("click", function() {
    myinfomodal.classList.toggle("opened");
});
myinfoCloseButton.addEventListener("click", function() {
    myinfomodal.classList.toggle("opened");
});

var mycookbookmodal = document.querySelector("#mycookbook-modal");
var mycookbookButton = document.querySelector("#mycookbook-button");
var mycookbookCloseButton = document.querySelector("#mycookbook-close-button");

mycookbookButton.addEventListener("click", function() {
    mycookbookmodal.classList.toggle("opened");
});
mycookbookCloseButton.addEventListener("click", function() {
    mycookbookmodal.classList.toggle("opened");
});