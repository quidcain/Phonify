$(function() {
    /*$("button").click(function(event) {
        var id = $(this).attr("id").split('_')[1];
        var field = $("#input_" + id);
        addToCart(id, field);
    });*/
    $("form").submit(function(event) {
        event.preventDefault();
        var id = $(this).attr("id").split('_')[1];;
        console.log(id);
        var field = $("input[form='form_" + id +"']");
        console.log(field.val());
        addToCart(id, field);
    });
});


function addToCart(id, field) {
        var reqBody = {};
        reqBody["id"] = id;
        reqBody["quantity"] = field.val();
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
            dataType : "json",
            timeout : 100000,
            success : function(data) {
                console.log("SUCCESS: ", data);
                $("#itemsQuantity").html(data.itemsQuantity);
                $("#subtotal").html(data.subtotal);
                field.val("");
            },
            error : function(data) {
                console.log("ERROR: ", data);
                var errorMessage = $("input[form='form_" + id +"'] ~ .errorMessage");
                errorMessage.html(data.responseJSON.message);
                errorMessage.removeClass("hidden");
                setTimeout(function(){
                    errorMessage.addClass("hidden");
                }, 3000);
            }
        });
}