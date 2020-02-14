package respectful.rapist.pclicker.listener;

import lc.kra.system.mouse.event.GlobalMouseEvent;
import lc.kra.system.mouse.event.GlobalMouseListener;
import respectful.rapist.pclicker.AutoClicker;

public class Mouse implements GlobalMouseListener {
    @Override
    public void mousePressed(GlobalMouseEvent globalMouseEvent) {
        if (!AutoClicker.robotMouseEvent && globalMouseEvent.getButton() == 1) {
            AutoClicker.active = true;
        }
    }

    @Override
    public void mouseReleased(GlobalMouseEvent globalMouseEvent) {
        if ((!AutoClicker.robotMouseEvent) && (globalMouseEvent.getButton() == 1)) {
            AutoClicker.active = false;
        } else {
            AutoClicker.robotMouseEvent = false;
        }
    }

    @Override
    public void mouseMoved(GlobalMouseEvent globalMouseEvent) {

    }

    @Override
    public void mouseWheel(GlobalMouseEvent globalMouseEvent) {

    }
}
