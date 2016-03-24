package com.carolyncheung.calc.data;

/**
 * Created by Carolyn Cheung on 2/27/2016.
 */
public class PlateData {
    private int id;
    private String color;
    private int size;
    private int amount;
    private double weight;
    private int unit;

    public PlateData() {
        super();
    }

    public PlateData(int id, String color, int size, int amount, double weight, int unit) {
        super();
        this.id = id;
        this.color = color;
        this.size = size;
        this.amount = amount;
        this.weight = weight;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public int getAmount() {
        return amount;
    }

    public double getWeight() {
        return weight;
    }

    public int getUnit() {
        return unit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
