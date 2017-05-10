package com.expertsoft.controller.form;


import com.expertsoft.model.Order;

import java.util.Map;

public class ChangeOrderStatusForm {
    private Map<Long, Order.Status> statuses;

    public Map<Long, Order.Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(Map<Long, Order.Status> statuses) {
        this.statuses = statuses;
    }
}
