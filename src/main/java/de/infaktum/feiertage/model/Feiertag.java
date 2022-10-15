/*
Copyright 2022 Heiko Sippel
Permission is hereby granted, free of charge, to any person obtaining a
copy of this software and associated documentation files (the "Software"),
to deal in the Software without restriction, including without limitation the
rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the Software
is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included
in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package de.infaktum.feiertage.model;

import static de.infaktum.feiertage.model.Bundesland.baden_wuerttemberg;
import static de.infaktum.feiertage.model.Bundesland.bayern;
import static de.infaktum.feiertage.model.Bundesland.berlin;
import static de.infaktum.feiertage.model.Bundesland.brandenburg;
import static de.infaktum.feiertage.model.Bundesland.bremen;
import static de.infaktum.feiertage.model.Bundesland.hamburg;
import static de.infaktum.feiertage.model.Bundesland.hessen;
import static de.infaktum.feiertage.model.Bundesland.mecklenburg_vorpommern;
import static de.infaktum.feiertage.model.Bundesland.niedersachsen;
import static de.infaktum.feiertage.model.Bundesland.nrw;
import static de.infaktum.feiertage.model.Bundesland.rheinland_pfalz;
import static de.infaktum.feiertage.model.Bundesland.saarland;
import static de.infaktum.feiertage.model.Bundesland.sachsen;
import static de.infaktum.feiertage.model.Bundesland.sachsen_anhalt;
import static de.infaktum.feiertage.model.Bundesland.schleswig_holstein;
import static de.infaktum.feiertage.model.Bundesland.thueringen;
import static java.util.Calendar.AUGUST;
import static java.util.Calendar.DECEMBER;
import static java.util.Calendar.JANUARY;
import static java.util.Calendar.MARCH;
import static java.util.Calendar.MAY;
import static java.util.Calendar.NOVEMBER;
import static java.util.Calendar.OCTOBER;
import static java.util.Calendar.SEPTEMBER;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;


/**
 * Die gesetzlichen deutschen Feiertage.
 *
 * <p>Die Klasse Feiertag enthält neben dem Namen des Feiertags auch eine Liste der Bundesländer, in der er gültig ist.
 * Da viele Feiertage beweglich sind, wird das Datum nicht vorgehalten, sondern erst in der Klasse @FeiertagsDatum.
 * </p>
 *
 * <p>Jeder Feiertag kann auch sein Datum in einem gegebenen Jahr berechnen.
 * </p>
 *
 * @author Heiko Sippel
 * @version 1.0
 */


public enum Feiertag {

    neujahr("Neujahr", x -> LocalDate.of(x, JANUARY, 1), Arrays.asList(Bundesland.values())),
    allerheiligen("Allerheiligen", x -> LocalDate.of(x, NOVEMBER, 1),
        Arrays.asList(baden_wuerttemberg, bayern, nrw, rheinland_pfalz, saarland)),
    frauentag("Internationaler Frauentag", x -> LocalDate.of(x, MARCH, 8), List.of(berlin)),
    weltkindertag("Weltkindertag", x -> LocalDate.of(x, SEPTEMBER, 20), List.of(thueringen)),
    maifeiertag("Tag der Arbeit", x -> LocalDate.of(x, MAY, 1), Arrays.asList(Bundesland.values())),
    maria_himmelfahrt("Maria Himmelfahrt", x -> LocalDate.of(x, AUGUST, 15), List.of(saarland)),
    dreikoenigstag("Heilige drei Könige", x -> LocalDate.of(x, JANUARY, 6),
        Arrays.asList(bayern, baden_wuerttemberg)),
    nationalfeiertag("Tag der deutschen Einheit", x -> LocalDate.of(x, OCTOBER, 3),
        Arrays.asList(Bundesland.values())),
    reformationstag("Reformationstag", x -> LocalDate.of(x, OCTOBER, 31),
        Arrays.asList(brandenburg, bremen, hamburg, mecklenburg_vorpommern, niedersachsen, sachsen, sachsen_anhalt,
            schleswig_holstein, thueringen)),
    heiligabend("Heiligabend", x -> LocalDate.of(x, DECEMBER, 24),
        Arrays.asList(Bundesland.values())),
    weihnachtstag1("1. Weihnachtstag", x -> LocalDate.of(x, DECEMBER, 25),
        Arrays.asList(Bundesland.values())),
    weihnachtstag2("2. Weihnachtstag", x -> LocalDate.of(x, DECEMBER, 26),
        Arrays.asList(Bundesland.values())),
    silvester("2. Weihnachtstag", x -> LocalDate.of(x, DECEMBER, 31),
        Arrays.asList(Bundesland.values())),
    //    rosenmontag("Rosenmontag", x -> ostern(x, -29), Arrays.asList(Bundesland.nrw)),
    karfreitag("Karfreitag", x -> ostern(x, -2), Arrays.asList(Bundesland.values())),
    //    ostersonntag("Ostersonntag", x -> ostern(x, 0), Arrays.asList()),
    ostermontag("Ostermontag", x -> ostern(x, 1), Arrays.asList(Bundesland.values())),
    christi_himmelfahrt("Christi Himmelfahrt", x -> ostern(x, 40), Arrays.asList(Bundesland.values())),
    fronleichnam("Fronleichnam", x -> ostern(x, 60), Arrays.asList(baden_wuerttemberg, bayern,
        hessen, nrw, rheinland_pfalz, saarland)),
    //    pfingstsonntag("Pfingstsonntag", x -> ostern(x, 49), Arrays.asList(Bundesland.values())),
    pfingstmontag("Pfingstmontag", x -> ostern(x, 50), Arrays.asList(Bundesland.values())),
    buss_und_bettag("Buß- und Bettag", Feiertag::bussUndBettag, List.of(sachsen));

    /**
     * Interner Cache für berechnete Ostersonntage, denn die Berechnung ist etwas aufwendig.
     */
    private static final HashMap<Integer, LocalDate> ostersonntage = new HashMap<>();
    private final String bezeichnung;
    private final List<Bundesland> laender;
    private final Function<Integer, LocalDate> datum;

    /**
     * Erzeugt eine neue Fnstanz.
     *
     * @param bezeichnung Der Name des Feiertags
     */
    Feiertag(final String bezeichnung, Function<Integer, LocalDate> datum, final List<Bundesland> laender) {
        this.bezeichnung = bezeichnung;
        this.datum = datum;
        this.laender = laender;
    }

    /**
     * Liefert das Datum des Ostersonntags eines Jahres.
     * Der Algorithmus stammt von C.F. Gauss und wurde aus der Wikipedia übernommen.
     *
     * @param jahr   Das Jahr.
     * @param offset Ein möglicher Offset in Tagen. Für Ostersonntag ist offset = 0.
     *
     * @return Das Datum.
     */

    private static LocalDate ostern(Integer jahr, int offset) {
        if (ostersonntage.containsKey(jahr)) {
            return ostersonntage.get(jahr);
        }
        int a = jahr % 19;
        int b = jahr % 4;
        int c = jahr % 7;
        int k = jahr / 100;
        int p = (8 * k + 13) / 25;
        int q = k / 4;
        int m = (15 + k - p - q) % 30;
        int d = (19 * a + m) % 30;
        int n = (4 + k - q) % 7;
        int e = (2 * b + 4 * c + 6 * d + n) % 7;
        int tag = 22 + d + e;

        // Zwei Ausnahmen
        if (d == 29 && e == 6) {
            tag = 50;
        }
        if (d == 28 && e == 6 && a > 10) {
            tag = 49;
        }
        LocalDate firstOfMarch = LocalDate.of(jahr, Month.MARCH, 1);
        LocalDate ostersonntag = firstOfMarch.plusDays(tag + offset);
        ostersonntage.put(jahr, ostersonntag);
        return ostersonntag;
    }

    /**
     * Liefert das Datum des Buß- und Bettags (Mittwoch vor dem 1. Advent) im gegebenen Jahr.
     *
     * @param jahr Das Jahr, für das die Feiertage berechnet werden sollen
     *
     * @return Das Datum des Buß- und Bettags.
     */

    private static LocalDate bussUndBettag(final Integer jahr) {
        LocalDate advent4 = LocalDate.of(jahr, DECEMBER, 25).with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        LocalDate advent1 = advent4.minusWeeks(4);
        return advent1.with(TemporalAdjusters.previous(DayOfWeek.WEDNESDAY));
    }

    /**
     * Liefert die Bezeichnung des Feiertags.
     *
     * @return Der Name des Feiertags.
     */
    public String bezeichnung() {
        return bezeichnung;
    }

    /**
     * Liefert das Datum des Feiertags im gegeben Jahr..
     *
     * @param jahr Das Jahr.
     *
     * @return Das Datum des Feiertags im Jahr.
     */
    public LocalDate datum(final int jahr) {
        return datum.apply(jahr);
    }

    /**
     * Liefert die Länder, in denen der Feiertag gültig ist.
     *
     * @return Der Name des Feiertags.
     */
    public List<Bundesland> laender() {
        return laender;
    }

    /**
     * Überprüft, ob der Feiertag in einem Bundesland gültig ist.
     *
     * @param land Das Land
     *
     * @return true, wenn der Feiertag im Land gültig ist.
     */
    public boolean isFeiertagIn(final Land land) {
        return laender.contains(land);
    }


}
