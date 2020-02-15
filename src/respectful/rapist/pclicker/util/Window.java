package respectful.rapist.pclicker.util;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

public class Window {
    public static String getWindow() {
        WinDef.HWND hWnd = User32.INSTANCE.GetForegroundWindow();
        char[] buffer = new char[2048];
        User32.INSTANCE.GetWindowText(hWnd, buffer, 1024);
        return Native.toString(buffer);
    }

    public static boolean inWindow(String[] windowList) {
        for (String window : windowList) {
            if (window.equals(getWindow())) {
                return true;
            }
        }
        return false;
    }
}
