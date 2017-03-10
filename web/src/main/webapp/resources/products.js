$(function() {
    $("button").click(function(event) {
        var id = $(this).attr("id").split('_')[1];
        var field = $("#input_" + id);
        createRequest(id, field);
    });
    $("form").submit(function(event) {
        event.preventDefault();
    });
});


function createRequest(id, field) {
        var reqBody = {};
        reqBody["id"] = id;
        reqBody["quantity"] = field.val();
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "addToCart",
            data : JSON.stringify(reqBody),
            dataType : 'json',
            timeout : 100000,
            success : function(data) {
                console.log("SUCCESS: ", data);
                $("#itemsQuantity").html(data.itemsQuantity);
                $("#subtotal").html(data.subtotal);
                field.val("");
            },
            error : function(e) {
                console.log("ERROR: ", e);
                field.val("value must be from 0 to 100!");
                field.addClass("incorrectInput");
                setTimeout(function(){
                    field.removeClass("incorrectInput");
                    field.val("");
                }, 3000);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
}