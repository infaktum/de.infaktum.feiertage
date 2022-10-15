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

/**
 * Die deutschen Bundesländer als Enumeration. Neben dem Namen wird auch der offizielle Schlüssel und das
 * Kürzel gespeichert.
 *
 * <p>Es gibt zwei statisch Methoden, über die ein Bundesland ruch den Schlüssel und das Kürzel bestimmt werden kann.
 *
 * <p>Die Werte für Schlüssel und Kürzel findet man etwa bei DESTATIS.
 *
 *  <ul>
 *  <li>01 Schleswig-Holstein(NI)</li>
 *  <li>02 Hamburg (HB)</li>
 *  <li>03 Niedersachsen (NI)</li>
 * <li> 04 Bremen (HB)</li>
 * <li> 05 Nordrhein-Westfalen (NW)</li>
 * <li> 06 Hessen (HE)</li>
 * <li> 07 Rheinland-Pfalz (RP)</li>
 * <li> 08 Baden-Württemberg (BW)</li>
 * <li> 09 Bayern (BY)</li>
 * <li> 10 Saarland (SL)</li>
 * <li> 11 Berlin (BE)</li>
 * <li> 12 Brandenburg (BB)</li>
 * <li> 13 Mecklenburg-Vorpommern (MV)</li>
 * <li> 14 Sachsen (SN)</li>
 * <li> 15 Sachsen-Anhalt (ST)</li>
 * <li> 16 Thüringen (TH)</li>
 *  </ul>
 *
 * @author Heiko Sippel
 * @version 1.0
 */

public enum Bundesland implements Land {
    schleswig_holstein(1, "SW", "Schleswig Holstein"),
    hamburg(2, "HH", "Hamburg"),
    niedersachsen(3, "NI", "Niedersachsen"),
    bremen(4, "HB", "Bremen"),
    nrw(5, "NW", "Nordrhein-Westfalen"),
    hessen(6, "HE", "Hessen"),
    rheinland_pfalz(7, "RP", "Rheinland-Pfalz"),
    baden_wuerttemberg(8, "BW", "Baden-Württemberg"),
    bayern(9, "BY", "Bayern"),
    saarland(10, "SL", "Saarland"),
    berlin(11, "BE", "Berlin"),
    brandenburg(12, "BB", "Brandenburg"),
    mecklenburg_vorpommern(13, "MV", "Mecklenburg-Vorpommern"),
    sachsen(14, "SN", "Sachsen"),
    sachsen_anhalt(15, "ST", "Sachsen-Anhalt"),
    thueringen(16, "TH", "Thüringen");

    private final int schluessel;
    private final String kuerzel;
    private final String bezeichnung;


    /**
     * Erzeugt eine neue Instanz.
     *
     * @param schluessel  Der Schlüssel (gemäß DESTATIS)
     * @param kuerzel     Die Abkürzung für das Bundesland, z.B. NW.
     * @param bezeichnung Der Name des Bundeslandes, z.B. Nordrhein-Westfalen.
     */

    Bundesland(int schluessel, String kuerzel, String bezeichnung) {
        this.schluessel = schluessel;
        this.kuerzel = kuerzel;
        this.bezeichnung = bezeichnung;
        landSchluessel.put(schluessel, this);
        landKuerzel.put(kuerzel, this);
    }

    /**
     * Liefert den Schlüssel für das Bundesland.
     *
     * @return Der Schlüssel des Bundeslandes.
     */
    @Override
    public int schluessel() {
        return schluessel;
    }

    /**
     * Liefert das Kürzel für das Bundesland.
     *
     * @return Das Kürzel des Bundeslandes.
     */
    @Override
    public String kuerzel() {
        return kuerzel;
    }

    /**
     * Liefert den Namen für das Bundesland.
     *
     * @return Der Name des Bundeslandes.
     */
    @Override
    public String bezeichnung() {
        return bezeichnung;
    }

    @Override
    public String toString() {
        return "Bundesland{ schluessel= " + schluessel + ", kuerzel = " + kuerzel + ", bezeichnung='"
            + bezeichnung + '}';
    }
}
