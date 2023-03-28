/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TNA
 */
public class Product {

    private int productID;
    private String productName;
    private int categoryID;
    private String quantityPerUnit;
    private double unitPrice;
    private int unitInStock;
    private boolean discontinued;
    private int userID;
    private String description, type, images;
    private int sellQuantity;
    private int views;

    public Product() {
    }

    public Product(int productID, String productName, int categoryID, String quantityPerUnit, double unitPrice, int unitInStock, boolean discontinued, int userID, String description, String type, String images, int sellQuantity, int views) {
        this.productID = productID;
        this.productName = productName;
        this.categoryID = categoryID;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitInStock = unitInStock;
        this.discontinued = discontinued;
        this.userID = userID;
        this.description = description;
        this.type = type;
        this.images = images;
        this.sellQuantity = sellQuantity;
        this.views = views;
    }
    public Product(int productID, String productName, int categoryID, String quantityPerUnit, double unitPrice, int unitInStock, boolean discontinued, int userID, String description, String type, String images, int sellQuantity) {
        this.productID = productID;
        this.productName = productName;
        this.categoryID = categoryID;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitInStock = unitInStock;
        this.discontinued = discontinued;
        this.userID = userID;
        this.description = description;
        this.type = type;
        this.images = images;
        this.sellQuantity = sellQuantity;        
    }

    

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(int unitInStock) {
        this.unitInStock = unitInStock;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getType() {
        return type.split(";");
    }
    
    public String getTextType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getImages() {
        return images.split(";");
    }
    
    public String getTextImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(int sellQuantity) {
        this.sellQuantity = sellQuantity;
    }   

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }        
}
