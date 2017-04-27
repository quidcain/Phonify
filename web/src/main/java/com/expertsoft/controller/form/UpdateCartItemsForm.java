package com.expertsoft.controller.form;

import javax.validation.Valid;
import java.util.Map;


public class UpdateCartItemsForm {
    @Valid
    private Map<Long, QuantityForm>  items;

    public Map<Long, QuantityForm> getItems() {
        return items;
    }

    public void setItems(Map<Long, QuantityForm> items) {
        this.items = items;
    }
}
