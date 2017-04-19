$(function() {
    $("form").submit(function(event) {
        event.preventDefault();
        var id = $(this).attr("id").split('_')[1];
        console.log($(this).find('input') == $(this).find('input'));
        var field = $(this).parent('tr').find('input');
        if (!field.length)
            field = $(this).find('input');
        addToCart(id, field.val());
    });
});


function addToCart(id, field) {
    var reqBody = {'id': id, 'quantity' : field};
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : function() {
            if(location.pathname.search(/\d+$/) != -1)
                return "../addToCart";
            else
                return "addToCart";
        }(),
        data : JSON.stringify(reqBody),
        success : function(data) {
            console.log("SUCCESS: ", data);
            $("#itemsQuantity").html(data.itemsQuantity);
            $("#subtotal").html(data.subtotal);
        },
        error : function(data) {
            console.log("ERROR: ", data);
            var errorMessageField = $("input[form='form_" + id +"'] ~ .errorMessage");
            errorMessageField.html(data.responseJSON.message);
            errorMessageField.removeClass("hidden");
            setTimeout(function(){
                errorMessageField.addClass("hidden");
            }, 3000);
        }
    });
}