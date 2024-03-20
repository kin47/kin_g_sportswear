package com.example.kingsportswear.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Product implements Parcelable {
    private String name;
    private String description;
    private List<String> image;
    private double price;
    private List<String> category_id;
    private String owner_id;

    public Product() {
    }

    public Product(String name, String description, List<String> image, double price, List<String> category_id, String owner_id) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.category_id = category_id;
        this.owner_id = owner_id;
    }

    protected Product(Parcel in) {
        name = in.readString();
        description = in.readString();
        price = in.readDouble();
        owner_id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeStringList(image);
        dest.writeDouble(price);
        dest.writeStringList(category_id);
        dest.writeString(owner_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getCategory_id() {
        return category_id;
    }

    public void setCategory_id(List<String> category_id) {
        this.category_id = category_id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }
}
