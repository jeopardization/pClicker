package com.xertxa.pclicker.listener;

import com.xertxa.pclicker.AutoClicker;
import com.xertxa.pclicker.Controller;
import javafx.application.Platform;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;

import static com.xertxa.pclicker.AutoClicker.toggle;

public class Keyboard implements GlobalKeyListener {
    @Override
    public void keyPressed(GlobalKeyEvent globalKeyEvent) {
        if (globalKeyEvent.getVirtualKeyCode() == AutoClicker.toggleKey) {
            toggle();
        }

        if (globalKeyEvent.getVirtualKeyCode() == AutoClicker.hideShowKey) {
            Platform.runLater(Controller::toggleVisibility);
        }
    }

    @Override
    public void keyReleased(GlobalKeyEvent globalKeyEvent) {

    }
}
