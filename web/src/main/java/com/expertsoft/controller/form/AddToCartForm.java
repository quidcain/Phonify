package com.expertsoft.controller.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class AddToCartForm {
    private long id;

    @Max(value = 99, message = "{quantity.minmax}")
    @Min(value = 1, message = "{quantity.minmax}")
    private String quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
