// this Jquery - AJAX load() method replaces everything between the div #cookbooks with addtocookbook.html and passes
// the recipeId data to the controller.

function retrieveCookbooks() {
    let recipeId = $("#recipeId").val();
    $("#cookbooks").load('/addtocookbook?recipeid=' + recipeId);
}


// this makes a modal view appear with the above mentioned loaded content in it.
var viewrecipeModal = document.querySelector("#viewrecipe-modal");
var viewrecipeButton = document.querySelector("#addToCookbook");
var viewrecipeCloseButton = document.querySelector("#viewrecipe-close-button");

viewrecipeButton.addEventListener("click", function() {
    viewrecipeModal.classList.toggle("opened");
});
viewrecipeCloseButton.addEventListener("click", function() {
    viewrecipeModal.classList.toggle("opened");
});