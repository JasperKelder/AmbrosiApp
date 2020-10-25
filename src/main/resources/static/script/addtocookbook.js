// this Jquery - AJAX load() method replaces everything between the div #cookbooks with addtocookbook.html and passes
// the recipeId data to the controller.

function retrieveCookbooks() {
    let recipeId = $("#recipeId").val();
    $("#cookbooks").load('/addtocookbook?recipeid=' + recipeId);
}