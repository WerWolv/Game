package com.werwolv.api.event;

public class KeyEvent{

    public static class KeyPressed extends Event {

        private int keyCode;

        public KeyPressed(int keyCode) {
            this.keyCode = keyCode;
        }

        public int getKeyCode() {
            return keyCode;
        }
    }

    public static class KeyReleased extends Event {

        private int keyCode;

        public KeyReleased(int keyCode) {
            this.keyCode = keyCode;
        }

        public int getKeyCode() {
            return keyCode;
        }
    }

}
