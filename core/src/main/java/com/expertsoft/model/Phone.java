package com.expertsoft.model;

import java.math.BigDecimal;


public class Phone {
    private long id;
    private String model;
    private String color;
    private int displaySize;
    private BigDecimal price;

    public Phone() {
    }

    public Phone(String model, String color, int displaySize, BigDecimal price) {
        this.model = model;
        this.color = color;
        this.displaySize = displaySize;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(int displaySize) {
        this.displaySize = displaySize;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o.getClass() != Phone.class)
            return false;
        return this.id == ((Phone)o).id;
    }
}
