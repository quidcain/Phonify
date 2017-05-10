package com.expertsoft.controller.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


public class QuantityForm {

    @Max(value = 99, message = "{quantity.minmax}")
    @Min(value = 1, message = "{quantity.minmax}")
    private Long quantity;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
