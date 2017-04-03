package es.upm.dit.adsw.ej2;

/**
 * @author jmanas
 * @version09-Dec-15
 */
public class OpMeter {
    private static long ops;

    public static long reset() {
        ops = 0;
        return ops;
    }

    public static long getOps() {
        return ops;
    }

    public static int compareTo(String s1, String s2) {
        ops++;
        return s1.compareTo(s2);
    }
}