function retrieveCookbooks() {
    let recipeId = $("#recipeId").val();
    $("#cookbooks").load('/addtocookbook?recipeid=' + recipeId);
}