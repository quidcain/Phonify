$(function() {
    $("button").click(function(event) {
        var id = $(this).attr("id").split('_')[1];
        var quantity = $("#input_" + id).val();
        createRequest(id, quantity);
    });
    $("form").submit(function(event) {
        event.preventDefault();
    });
});


function createRequest(id, quantity) {
    var reqBody = {}
    reqBody["id"] = id;
    reqBody["quantity"] = quantity;

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/addToCart",
        data : JSON.stringify(reqBody),
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
            console.log("SUCCESS: ", data);
            $("#itemsQuantity").html(data.itemsQuantity);
            $("#totalPrice").html(data.totalPrice);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}