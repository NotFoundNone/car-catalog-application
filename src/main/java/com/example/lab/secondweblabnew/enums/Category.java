package com.example.lab.secondweblabnew.enums;

public enum Category {
    CAR ( 0),
    BUS (1),
    TRUCK(2),
    MOTORCYCLE(3);

    private int index;

    Category(int index) {this.index = index; }
}
