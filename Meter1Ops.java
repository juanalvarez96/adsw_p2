package es.upm.dit.adsw.ej2;

import java.util.Random;

/**
 * @author jmanas
 * @version 07-Dec-15.
 * @author jpuente
 * \version 2016.02.23
 */
//@SuppressWarnings("Duplicates")
public class Meter1Ops {
    private static final Random random = new Random();

    public static void main(String[] args) {
//       Diccionario diccionario = new DiccionarioBinario(100000);
    	Diccionario diccionario = new BST();

        int[] nn = {
                1000,    // precalentamiento
                100, 200, 500,
                1000, 2000, 5000,
                10000, 20000, 50000,
        };
        for (int n : nn) {
            for (int i = 0; i < 5; i++) {
                long t = meter(diccionario, n);
                System.out.printf("%s %d%n", n, t);
            }
        }
    }

    private static long meter(Diccionario diccionario, int n) {
        long t = 0;
        for (int j = 0; j < 100; j++) {
            load(diccionario, n);
            long t0 = OpMeter.reset();
            for (int i = 0; i < 100; i++) {
                int r = random.nextInt(2 * n);
                String clave = mkKey(r);
                String valor = mkValue(r);
                diccionario.get(clave);
            }
            long t2 = OpMeter.getOps();
            t += t2 - t0;
        }
        return t;
    }

    private static String mkKey(int k) {
        return String.valueOf(k);
    }
    private static String mkValue(int v) {
        return String.format("[%d]", v);
    }

    private static void load(Diccionario diccionario, int n) {
        diccionario.clear();
        do {
            int r = random.nextInt(4 * n);
            String clave = mkKey(r);
            String valor = mkValue(r);
            diccionario.put(clave, valor);
        } while (diccionario.size() < n);
    }
}
