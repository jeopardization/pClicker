package respectful.rapist.pclicker;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import respectful.rapist.pclicker.util.Random;
import respectful.rapist.pclicker.util.Window;

import java.awt.*;

public class AutoClicker implements Runnable {
    public boolean active, robotMouseEvent, requireWindow, enabled;
    public int toggleKey = 77, hideShowKey = 34;
    String windowList = "Minecraft 1.7.10,Minecraft 1.8.9";
    float maxCPS = 14.0F, minCPS = 9.0F;
    private long holdDelay = (long) Random.nextFloat(1000.0F / maxCPS, 1000.0F / minCPS), releaseDelay = holdDelay / 3L;
    private Robot robot;

    public AutoClicker() {
        try {
            robot = new Robot();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK).show();
        }
    }

    public void toggle() {
        enabled = !enabled;
        if (enabled) {
            robotMouseEvent = false;
            new Thread(this).start();
            Controller.instance.toggle.setSelected(true);
        } else {
            Controller.instance.toggle.setSelected(false);
        }
    }

    @Override
    public void run() {
        try {
            while (enabled) {
                if (!Window.getWindow().equals("pClicker")) {
                    if (requireWindow && !Window.inWindow(windowList.split(","))) {
                        continue;
                    }
                    if (active) {
                        Thread.sleep(holdDelay - releaseDelay);
                        robotMouseEvent = true;
                        robot.mousePress(16);
                        Thread.sleep(releaseDelay);
                        robot.mouseRelease(16);
                        holdDelay = (long) Random.nextFloat(1000.0F / maxCPS, 1000.0F / minCPS);
                        releaseDelay = holdDelay / 3L;
                    }
                }
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK).show();
        }
    }
}
