package com.example.lab.secondweblabnew.enums;

public enum Engine {

    GASOLINE( 0),
    DIESEL(1),
    ELECTRIC(2),
    HYBRID(3);

    private int index;

    Engine(int index){this.index = index; }
}
