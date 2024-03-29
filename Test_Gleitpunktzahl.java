import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Test_Gleitpunktzahl {
    /**
     * Testklasse, ob alles funktioniert!
     */


    public static void main(String[] argv) {
        test_Gleitpunktzahl();
    }

    public static void test_Gleitpunktzahl() {

        /**********************************/
        /* Test der Klasse Gleitpunktzahl */
        /**********************************/

        System.out.println("-----------------------------------------");
        System.out.println("Test der Klasse Gleitpunktzahl");

        /*
         * Verglichen werden die BitFelder fuer Mantisse und Exponent und das
         * Vorzeichen
         */
        Gleitpunktzahl.setSizeMantisse(4); //
        Gleitpunktzahl.setSizeExponent(2);    // [0.5, 3.625]

        Gleitpunktzahl x;
        Gleitpunktzahl y;

        Gleitpunktzahl gleitref = new Gleitpunktzahl();
        Gleitpunktzahl gleiterg;

        Gleitpunktzahl test;
        Gleitpunktzahl ref;
        List<Gleitpunktzahl> testList = new ArrayList<>();
        List<Gleitpunktzahl> refList = new ArrayList<>();

        // test for numbers in the range [0.25,0.5)
        test = new Gleitpunktzahl(0.25);
        ref = new Gleitpunktzahl();
        ref.exponent = 0;
        ref.mantisse = 0b1000;
        ref.vorzeichen = false;
        testList.add(test);
        refList.add(ref);

        // test for numbers out of range => upper bound (exponent is bigger than maxExp)
        test = new Gleitpunktzahl(3.9);
        ref = new Gleitpunktzahl();
        ref.setInfinite(false);
        testList.add(test);
        refList.add(ref);

        // test for numbers in between the ranges
        test = new Gleitpunktzahl(0.01);
        ref = new Gleitpunktzahl();
        ref.setNaN();
        testList.add(test);
        refList.add(ref);

        // test for numbers out of range => lower bound
        test = new Gleitpunktzahl(-3.9);
        ref = new Gleitpunktzahl();
        ref.setInfinite(true);
        testList.add(test);
        refList.add(ref);

        // test for number in range
        test = new Gleitpunktzahl(1.9032);  // is 1,875 the right answer here ?
        ref = new Gleitpunktzahl();
        ref.exponent = 1;
        ref.mantisse = 15;
        ref.vorzeichen = false;
        testList.add(test);
        refList.add(ref);

        /* Test von setDouble */
        System.out.println("Test von setDouble");
        try {
            x = new Gleitpunktzahl(0.5);
            // Referenzwerte setzen
            gleitref = new Gleitpunktzahl(0.5);
            // Test, ob Ergebnis korrekt
            if (x.compareAbsTo(gleitref) != 0
                    || x.vorzeichen != gleitref.vorzeichen) {
                printErg("" + x.toDouble(), "" + gleitref.toDouble());
            } else {
                System.out.println("    Richtiges Ergebnis\n");
            }

            System.out.println("===================Our Tests for SetDouble=========================");

            for (int i = 0; i < testList.size(); i++) {
                if (testList.get(i).vorzeichen == refList.get(i).vorzeichen
                        && testList.get(i).mantisse == refList.get(i).mantisse
                        && testList.get(i).exponent == refList.get(i).exponent) {
                    System.out.println("    Richtiges Ergebnis:\n");
                    printErg("" + testList.get(i).toDouble(), "" + refList.get(i).toDouble());
                } else {
                    System.out.println("    FALSCHES ERGEBNIS!!!!:\n");
                    printErg("" + testList.get(i).toDouble(), "" + refList.get(i).toDouble());
                }
            }
/*            Gleitpunktzahl.setSizeMantisse(8); //
            Gleitpunktzahl.setSizeExponent(4);    //
            Gleitpunktzahl finalTest = new Gleitpunktzahl();
            finalTest.mantisse = 56;
            finalTest.exponent = 0;
            finalTest.vorzeichen = true;
            finalTest.normalisiere();

            System.out.println(finalTest+ " in double  "+ finalTest.toDouble());*/

            System.out.println("\n\nEIGENE TESTS EINFÜGEN!!!!!!!\n\n");

        } catch (Exception e) {
            System.out.print("Exception bei der Auswertung des Ergebnis!!\n");
        }

        /* Addition */
        System.out.println("Test der Addition mit Gleitpunktzahl");
        try {
            // Test: Addition
            System.out.println("Test: Addition  x + y");
            x = new Gleitpunktzahl(3.25);
            y = new Gleitpunktzahl(0.5);

            // Referenzwerte setzen
            gleitref = new Gleitpunktzahl(3.25 + 0.5);

            // Berechnung
            gleiterg = x.add(y);

            // Test, ob Ergebnis korrekt
            if (gleiterg.compareAbsTo(gleitref) != 0
                    || gleiterg.vorzeichen != gleitref.vorzeichen) {
                printAdd(x.toString(), y.toString());
                printErg(gleiterg.toString(), gleitref.toString());
            } else {
                System.out.println("    Richtiges Ergebnis\n");
            }

            /*************
             * Eigene Tests einfuegen
             */
            // size Mantisse = 4
            // start test  number range: [0.5, 3.75]
            ArrayList<Gleitpunktzahl> xList = new ArrayList<>();
            ArrayList<Gleitpunktzahl> yList = new ArrayList<>();
            ArrayList<Gleitpunktzahl> eList = new ArrayList<>();
            ArrayList<Gleitpunktzahl> rList = new ArrayList<>();
            Gleitpunktzahl e, r;

            // result out of upper bound
            x = new Gleitpunktzahl(4);  // no
            y = new Gleitpunktzahl(1.72);
            e = x.add(y);
            r = new Gleitpunktzahl(2.75 + 1.72);
            xList.add(x);
            yList.add(y);
            eList.add(e);
            rList.add(r);
            // result out of lower bound
            x = new Gleitpunktzahl(1.7);
            y = new Gleitpunktzahl(-1.5);
            r = new Gleitpunktzahl(0.2);   //result should be -Inf or NaN ==> discussion !
            e = x.add(y);

            xList.add(x);
            yList.add(y);
            eList.add(e);
            rList.add(r);
            // result is 0
            x = new Gleitpunktzahl(1.7);
            y = new Gleitpunktzahl(-1.7);
            e = x.add(y);
            r = new Gleitpunktzahl(0);
            xList.add(x);
            yList.add(y);
            eList.add(e);
            rList.add(r);
            // normal case 0.5+0.5   1.5-0.5

            System.out.println("Now it's our own test.\n -------------------------------------------------------------");
            for (int i = 0; i < xList.size(); i++) {
                if (eList.get(i).compareAbsTo(rList.get(i)) != 0
                        || eList.get(i).vorzeichen != rList.get(i).vorzeichen) {
                    printAdd(xList.get(i).toString(), yList.get(i).toString());
                    printErg(eList.get(i).toString(), rList.get(i).toString());
                } else {
                    System.out.println("    Richtiges Ergebnis\n");
                }
            }
            System.out.println("End of our own test.\n ---------------------------------------------------------------");

            // end test

            System.out.println("\n\nEIGENE TESTS EINFÜGEN!!!!!!!\n\n");

        } catch (Exception e) {
            System.out.print("Exception bei der Auswertung des Ergebnis!!\n");
        }

        /* Subtraktion */
        try {
            System.out.println("Test der Subtraktion mit Gleitpunktzahl");

            // Test: Addition
            System.out.println("Test: Subtraktion  x - y");
            x = new Gleitpunktzahl(3.25);
            y = new Gleitpunktzahl(2.75);

            // Referenzwerte setzen
            gleitref = new Gleitpunktzahl((3.25 - 2.75));

            // Berechnung
            gleiterg = x.sub(y);

            // Test, ob Ergebnis korrekt
            if (gleiterg.compareAbsTo(gleitref) != 0
                    || gleiterg.vorzeichen != gleitref.vorzeichen) {
                printSub(x.toString(), y.toString());
                printErg(gleiterg.toString(), gleitref.toString());
            } else {
                System.out.println("    Richtiges Ergebnis\n");
            }

            /*************
             * Eigene Tests einfuegen
             */

            System.out.println("\n\nEIGENE TESTS EINFÜGEN!!!!!!!\n\n");

        } catch (Exception e) {
            System.out.print("Exception bei der Auswertung des Ergebnis!!\n");
        }

        /* Sonderfaelle */
        System.out.println("Test der Sonderfaelle mit Gleitpunktzahl");

        try {
            // Test: Sonderfaelle
            // 0 - inf
            System.out.println("Test: Sonderfaelle");
            x = new Gleitpunktzahl(0.0);
            y = new Gleitpunktzahl(1.0 / 0.0);

            // Referenzwerte setzen
            gleitref.setInfinite(true);

            // Berechnung mit der Methode des Studenten durchfuehren
            gleiterg = x.sub(y);

            // Test, ob Ergebnis korrekt
            if (gleiterg.compareAbsTo(gleitref) != 0
                    || gleiterg.vorzeichen != gleitref.vorzeichen) {
                printSub(x.toString(), y.toString());
                printErg(gleiterg.toString(), gleitref.toString());
            } else {
                System.out.println("    Richtiges Ergebnis\n");
            }

            /*************
             * Eigene Tests einfuegen
             */

            System.out.println("\n\nEIGENE TESTS EINFÜGEN!!!!!!!\n\n");


        } catch (Exception e) {
            System.out
                    .print("Exception bei der Auswertung des Ergebnis in der Klasse Gleitpunktzahl!!\n");
        }

    }


    private static void printAdd(String x, String y) {
        System.out.println("    Fehler!\n      Es wurde gerechnet:            "
                + x + " + " + y);
    }

    private static void printSub(String x, String y) {
        System.out.println("    Fehler!\n      Es wurde gerechnet:            "
                + x + " - " + y + " = \n");
    }

    private static void printErg(String erg, String checkref) {
        System.out.println("      Ihr Ergebnis lautet:           " + erg
                + "\n      Das Korrekte Ergebnis lautet:  " + checkref + "\n");
    }
}