package com.expertsoft.model;


public class CartItem {
    private Long id;
    private Phone phone;
    private Cart cart;

    private long quantity;

    public CartItem() {
    }

    public CartItem(Phone phone, Cart cart, long quantity) {
        this.phone = phone;
        this.cart = cart;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o.getClass() != CartItem.class)
            return false;
        return this.id == ((CartItem)o).id;
    }
}
