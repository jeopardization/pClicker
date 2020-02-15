package respectful.rapist.pclicker.listener;

import lc.kra.system.mouse.event.GlobalMouseEvent;
import lc.kra.system.mouse.event.GlobalMouseListener;
import respectful.rapist.pclicker.Controller;

public class Mouse implements GlobalMouseListener {
    @Override
    public void mousePressed(GlobalMouseEvent globalMouseEvent) {
        if (!Controller.instance.autoClicker.robotMouseEvent && globalMouseEvent.getButton() == 1) {
            Controller.instance.autoClicker.active = true;
        }
    }

    @Override
    public void mouseReleased(GlobalMouseEvent globalMouseEvent) {
        if (!Controller.instance.autoClicker.robotMouseEvent && globalMouseEvent.getButton() == 1) {
            Controller.instance.autoClicker.active = false;
        } else {
            Controller.instance.autoClicker.robotMouseEvent = false;
        }
    }

    @Override
    public void mouseMoved(GlobalMouseEvent globalMouseEvent) {

    }

    @Override
    public void mouseWheel(GlobalMouseEvent globalMouseEvent) {

    }
}
