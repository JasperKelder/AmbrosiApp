//autocomplete function,source: https://www.w3schools.com/howto/howto_js_autocomplete.asp
function autocomplete(inp, arr) {
    /*the autocomplete function takes two arguments,
    the text field element and an array of possible autocompleted values:*/
    var currentFocus;
    /*execute a function when someone writes in the text field:*/
    inp.addEventListener("input", function(e) {
        var a, b, i, val = this.value;
        /*close any already open lists of autocompleted values*/
        closeAllLists();
        if (!val) { return false;}
        currentFocus = -1;
        /*create a DIV element that will contain the items (values):*/
        a = document.createElement("DIV");
        a.setAttribute("id", this.id + "autocomplete-list");
        a.setAttribute("class", "autocomplete-items");
        /*append the DIV element as a child of the autocomplete container:*/
        this.parentNode.appendChild(a);
        /*for each item in the array...*/
        for (i = 0; i < arr.length; i++) {
            /*check if the item starts with the same letters as the text field value:*/
            if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                /*create a DIV element for each matching element:*/
                b = document.createElement("DIV");
                /*make the matching letters bold:*/
                b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                b.innerHTML += arr[i].substr(val.length);
                /*insert a input field that will hold the current array item's value:*/
                b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                /*execute a function when someone clicks on the item value (DIV element):*/
                b.addEventListener("click", function(e) {
                    /*insert the value for the autocomplete text field:*/
                    inp.value = this.getElementsByTagName("input")[0].value;
                    /*close the list of autocompleted values,
                    (or any other open lists of autocompleted values:*/
                    closeAllLists();
                });
                a.appendChild(b);
            }
        }
    });
    /*execute a function presses a key on the keyboard:*/
    inp.addEventListener("keydown", function(e) {
        var x = document.getElementById(this.id + "autocomplete-list");
        if (x) x = x.getElementsByTagName("div");
        if (e.keyCode == 40) {
            /*If the arrow DOWN key is pressed,
            increase the currentFocus variable:*/
            currentFocus++;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 38) { //up
            /*If the arrow UP key is pressed,
            decrease the currentFocus variable:*/
            currentFocus--;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 13) {
            /*If the ENTER key is pressed, prevent the form from being submitted,*/
            e.preventDefault();
            if (currentFocus > -1) {
                /*and simulate a click on the "active" item:*/
                if (x) x[currentFocus].click();
            }
        }
    });
    function addActive(x) {
        /*a function to classify an item as "active":*/
        if (!x) return false;
        /*start by removing the "active" class on all items:*/
        removeActive(x);
        if (currentFocus >= x.length) currentFocus = 0;
        if (currentFocus < 0) currentFocus = (x.length - 1);
        /*add class "autocomplete-active":*/
        x[currentFocus].classList.add("autocomplete-active");
    }
    function removeActive(x) {
        /*a function to remove the "active" class from all autocomplete items:*/
        for (var i = 0; i < x.length; i++) {
            x[i].classList.remove("autocomplete-active");
        }
    }
    function closeAllLists(elmnt) {
        /*close all autocomplete lists in the document,
        except the one passed as an argument:*/
        var x = document.getElementsByClassName("autocomplete-items");
        for (var i = 0; i < x.length; i++) {
            if (elmnt != x[i] && elmnt != inp) {
                x[i].parentNode.removeChild(x[i]);
            }
        }
    }
    /*execute a function when someone clicks in the document:*/
    document.addEventListener("click", function (e) {
        closeAllLists(e.target);
    });
}

// turn the allIngredientsJson from the recipeController into an array for the autocomplete.
var ingredientsString = document.getElementById("myIngredients").value;
var ingredientsArray = JSON.parse(ingredientsString);
autocomplete(document.getElementById("myInput1"), ingredientsArray);

// get the ingredients, measuring units and quantities from an existing recipe.
var recipeString = document.getElementById("myRecipeToJson").value;
if (recipeString !== "") {
    var recipeToJson = JSON.parse(recipeString);
    makeIngredientList(recipeToJson);
}

// get a list of all the measuring units an add them to the dropdown box
var allMeasuringUnits = document.getElementById("myIngredientMeasuringUnits").value;
var allMeasuringUnitsArray = JSON.parse(allMeasuringUnits);

var $select = $("#measuringUnit").empty();
$.each(allMeasuringUnitsArray, function (index, item) {
    $('<option />', {
        value: item.measuringUnitId,
        text: item.measuringUnitName,
    }).appendTo($select)
})

// fill the dynamic ingredient fields with ingredients of the existing recipe.
function makeIngredientList(array) {
    for (var i = 0; i < array.length; i++) {
        var ingredientWrapper = $('<div class="ingredientwrapper"/>')
        var ingredientField = $('<input id="myInputUpdate" type="text" value="' + array[i].ingredient.ingredientName +
            '" name="ingredientName[]">');
        var ingredientUnitField = $('<select id="measuringUnitExistingRecipe' + i +
            '" name="ingredientUnit[]" class="margin">')
        var ingredientQuantityField = $('<input type="number" value="' + array[i].quantity +
            '" name="ingredientQuantity[]" class="margin quantity">')
        var removeButton = $('<input type="button" class="remove margin" value=" X " />');
        removeButton.click(function () {
            $(this).parent().remove();
        });
        ingredientWrapper.append(ingredientField);
        ingredientWrapper.append(ingredientUnitField);
        ingredientWrapper.append(ingredientQuantityField);
        ingredientWrapper.append(removeButton);
        $("#dynamicList").append(ingredientWrapper);
        var allMeasuringUnits = document.getElementById("myIngredientMeasuringUnits").value;
        var allMeasuringUnitsArray = JSON.parse(allMeasuringUnits);
        var $select = $("#measuringUnitExistingRecipe" + i).empty();
        $.each(allMeasuringUnitsArray, function (index, item) {
            $('<option />', {
                value: item.measuringUnitId,
                text: item.measuringUnitName,
            }).appendTo($select)
        })
        // set the dropdown menu to the wright value:
        $select.val(array[i].ingredient.measuringUnit.measuringUnitId);
    }
}

// add input fields for the ingredients dynamically:
$(document).ready(function () {
    $("#add").click(function () {
        var lastField = $("#dynamicRows div:last");
        var intId = (lastField && lastField.length && lastField.data("idx") + 1) || 2;
        var fieldWrapper = $('<div class="fieldwrapper" id="field' + intId + '"/>');
        fieldWrapper.data("idx", intId);
        var ingredientInput = $('<input id="myInput' + intId + '" type="text" class="ingredientInput" ' +
            'placeholder="Ingredient" name="ingredientName[]" />');
        var ingredientUnitField = $('<select id="measuringUnit' + intId + '" name="ingredientUnit[]" class="margin">')
        var ingredientQuantityField = $('<input type="number" placeholder="Quantity" ' +
            'name="ingredientQuantity[]" class="margin quantity">')
        var removeButton = $('<input type="button" class="remove margin" value=" X " />');
        removeButton.click(function () {
            $(this).parent().remove();
        });
        fieldWrapper.append(ingredientInput);
        fieldWrapper.append(ingredientUnitField);
        fieldWrapper.append(ingredientQuantityField);
        fieldWrapper.append(removeButton);
        $("#dynamicRows").append(fieldWrapper);
        // add the autocomplete for ingredient name and dropdown box for measuring units:
        autocomplete(document.getElementById("myInput" + intId), ingredientsArray);
        var $select = $("#measuringUnit" + intId).empty();
        $.each(allMeasuringUnitsArray, function (index, item) {
            $('<option />', {
                value: item.measuringUnitId,
                text: item.measuringUnitName,
            }).appendTo($select)
        })
    });
});

// Changes the display to none for the empty ingredient fields while updating
if (window.location.href.indexOf("update")) {
    function hideFields() {
        document.getElementById("myInput1").style.display = "none";
        document.getElementById("measuringUnit").style.display = "none";
        document.getElementById("quantity").style.display = "none";
    }
    window.onload = hideFields();
}