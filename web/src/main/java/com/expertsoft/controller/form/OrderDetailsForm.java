package com.expertsoft.controller.form;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class OrderDetailsForm {
    @NotBlank
    @Size(min = 3, max = 40)
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 40)
    private String lastName;
    @NotBlank
    @Size(min = 3, max = 60)
    private String deliveryAddress;
    @NotBlank
    @Size(min = 3, max = 60)
    private String contactPhoneNo;
    private String additionalInfo;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactPhoneNo() {
        return contactPhoneNo;
    }

    public void setContactPhoneNo(String contactPhoneNo) {
        this.contactPhoneNo = contactPhoneNo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
