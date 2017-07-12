package com.werwolv.api.event.input;

public enum EnumMouseButton {
    LEFT(1),
    MIDDLE(2),
    RIGHT(3),
    BTN_4(4),
    BTN_5(5),
    OTHER(0);

    private int id;

    EnumMouseButton(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public static EnumMouseButton getButtonFromID(int id) {
        switch(id) {
            case 1: return LEFT;
            case 2: return MIDDLE;
            case 3: return RIGHT;
            case 4: return BTN_4;
            case 5: return BTN_5;
            default: return OTHER;
        }
    }
}