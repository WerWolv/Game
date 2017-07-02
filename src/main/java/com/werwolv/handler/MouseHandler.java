package com.werwolv.handler;

import com.werwolv.api.API;
import com.werwolv.api.event.input.*;

import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        API.EVENT_BUS.postEvent(new MouseClickedEvent(EnumMouseButton.getButtonFromID(e.getButton()), e.getX(), e.getY(), e.getClickCount()));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        API.EVENT_BUS.postEvent(new MousePressedEvent(EnumMouseButton.getButtonFromID(e.getButton()), e.getX(), e.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        API.EVENT_BUS.postEvent(new MouseReleasedEvent(EnumMouseButton.getButtonFromID(e.getButton()), e.getX(), e.getY()));

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        /* NOT IN USE */
    }

    @Override
    public void mouseExited(MouseEvent e) {
        /* NOT IN USE */
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        API.EVENT_BUS.postEvent(new MouseDraggedEvent(EnumMouseButton.getButtonFromID(e.getButton()), e.getX(), e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        API.EVENT_BUS.postEvent(new MouseMovedEvent(e.getX(), e.getY()));
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        API.EVENT_BUS.postEvent(new MouseWheelScrollEvent(e.getScrollAmount(), e.getX(), e.getY()));
    }
}
