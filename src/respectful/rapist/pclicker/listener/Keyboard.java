package respectful.rapist.pclicker.listener;

import javafx.application.Platform;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;
import respectful.rapist.pclicker.Controller;

public class Keyboard implements GlobalKeyListener {
    @Override
    public void keyPressed(GlobalKeyEvent globalKeyEvent) {
        if (globalKeyEvent.getVirtualKeyCode() == Controller.instance.autoClicker.toggleKey) {
            Controller.instance.toggle();
        } else if (globalKeyEvent.getVirtualKeyCode() == Controller.instance.autoClicker.hideShowKey) {
            Platform.runLater(Controller.instance::toggleVisibility);
        }
    }

    @Override
    public void keyReleased(GlobalKeyEvent globalKeyEvent) {

    }
}
