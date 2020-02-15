package respectful.rapist.pclicker.util;

import java.security.SecureRandom;

public class Random {
    private static SecureRandom secureRandom = new SecureRandom();

    public static float nextFloat(float min, float max) {
        return min + (max - min) * secureRandom.nextFloat();
    }
}
