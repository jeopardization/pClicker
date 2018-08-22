package com.xertxa.pclicker.listener;

import lc.kra.system.mouse.event.GlobalMouseEvent;
import lc.kra.system.mouse.event.GlobalMouseListener;

import static com.xertxa.pclicker.AutoClicker.active;
import static com.xertxa.pclicker.AutoClicker.robotMouseEvent;

public class Mouse implements GlobalMouseListener {
    @Override
    public void mousePressed(GlobalMouseEvent globalMouseEvent) {
        if ((!robotMouseEvent) && (globalMouseEvent.getButton() == 1)) {
            active = true;
        }
    }

    @Override
    public void mouseReleased(GlobalMouseEvent globalMouseEvent) {
        if ((!robotMouseEvent) && (globalMouseEvent.getButton() == 1)) {
            active = false;
        } else {
            robotMouseEvent = false;
        }
    }

    @Override
    public void mouseMoved(GlobalMouseEvent globalMouseEvent) {

    }

    @Override
    public void mouseWheel(GlobalMouseEvent globalMouseEvent) {

    }
}
