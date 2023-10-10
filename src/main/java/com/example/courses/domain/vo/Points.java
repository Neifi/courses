package com.example.courses.domain.vo;

public class Points {
    private int quantity;

    public Points(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("points must be greater tan 0");
        }
        this.quantity = quantity;
    }

    public int quantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Points points)) return false;

        return quantity == points.quantity;
    }

    @Override
    public int hashCode() {
        return quantity;
    }
}
