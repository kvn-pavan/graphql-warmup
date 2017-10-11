package com.eblock.interview.warmup.domain;

import javax.money.MonetaryAmount;
import java.util.Objects;

public abstract class InventoryItem {

    private String id;
    private String tagLine;
    private String description;
    private Integer year;
    private MonetaryAmount price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

    public void setPrice(MonetaryAmount price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItem that = (InventoryItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(tagLine, that.tagLine) &&
                Objects.equals(description, that.description) &&
                Objects.equals(year, that.year) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagLine, description, year, price);
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "id='" + id + '\'' +
                ", tagLine='" + tagLine + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
