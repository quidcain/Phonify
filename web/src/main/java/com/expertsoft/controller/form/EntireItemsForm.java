package com.expertsoft.controller.form;

import javax.validation.Valid;
import java.util.Map;


public class EntireItemsForm {
    @Valid
    private Map<Long, GenericQuantityForm>  items;

    public Map<Long, GenericQuantityForm> getItems() {
        return items;
    }

    public void setItems(Map<Long, GenericQuantityForm> items) {
        this.items = items;
    }
}
