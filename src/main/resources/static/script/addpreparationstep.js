// add input field for the preparation steps in dynamic list
$(document).ready(function () {
    $("#addstep").click(function () {
        var lastField = $("#dynamicPrepRows div:last");
        var intId = (lastField && lastField.length && lastField.data("idx") + 1) || 1;
        var fieldWrapper = $('<div class="fieldwrapperPrep"/>');
        fieldWrapper.data("idx", intId);
        var preparationStep = $('<textarea id="myPreparationSteps' + intId + '" placeholder="Preparation" name="preparationlist[]" />');
        var removeButton = $('<input type="button" class="remove-prep" value=" X " />');
        removeButton.click(function () {
            $(this).parent().remove();
        });
        fieldWrapper.append(preparationStep);
        fieldWrapper.append(removeButton);
        $("#dynamicPrepRows").append(fieldWrapper);

    });
});


// get the prepsteps from an existing recipe.
var prepStepString = document.getElementById("prepstepsToJson").value;
if (prepStepString !== "") {
    var prepstepsToJson = JSON.parse(prepStepString);
    makePrepStepList(prepstepsToJson);
}

function makePrepStepList(array) {
    for (var i = 0; i < array.length; i++) {
        var prepStepWrapper = $('<div class="fieldwrapperPrep"/>');
        var preparationStep = $('<textarea name="preparationlist[]">'+ array[i].preparationStep +'</textarea>');
        var removeButton = $('<input type="button" class="remove-prep" value=" X " />');
        removeButton.click(function () {
            $(this).parent().remove();
        });
        prepStepWrapper.append(preparationStep);
        prepStepWrapper.append(removeButton);
        $("#dynamicPrepRows").append(prepStepWrapper);

    }
}

