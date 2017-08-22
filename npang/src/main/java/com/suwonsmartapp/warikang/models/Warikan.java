package com.suwonsmartapp.warikang.models;

/**
 * Created by junsuk on 2017. 8. 21..
 */

public class Warikan implements Calculator {
    private int totalPrice;
    private int peopleCount;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    @Override
    public int calculate() {
        if (totalPrice == 0 && peopleCount == 0) {
            return 0;
        }

        return totalPrice / peopleCount;
    }
}
