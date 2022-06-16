package com.sti.lazshop;

public class ItemModel {
    String itemName;
    String itemPrice;
    int image;

    public ItemModel(String itemName, String itemPrice, int image) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public int getImage() {
        return image;
    }
}
