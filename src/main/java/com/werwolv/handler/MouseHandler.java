package com.werwolv.handler;

import com.werwolv.api.API;
import com.werwolv.api.event.input.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        API.EVENT_BUS.postEvent(new MouseClickedEvent(e.getButton(), e.getX(), e.getY(), e.getClickCount()));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        API.EVENT_BUS.postEvent(new MousePressedEvent(e.getButton(), e.getX(), e.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        API.EVENT_BUS.postEvent(new MouseReleasedEvent(e.getButton(), e.getX(), e.getY()));

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
        API.EVENT_BUS.postEvent(new MouseDraggedEvent(e.getButton(), e.getX(), e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        API.EVENT_BUS.postEvent(new MouseMovedEvent(e.getX(), e.getY()));
    }
}
