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

import java.time.LocalDate;
import java.util.List;

/**
 * Feiertage stellt alle Funktionen zur Überprüfung von Zeitangaben auf Feiertage bereit.
 *
 * @author Heiko Sippel
 * @version 1.0
 */

public interface Feiertage {
    /**
     * Initialisiert das System ab Jahr 1970 bis zu einem gegebenen Jahr.
     *
     * @param endJahr Das letzte Jahr.
     */
    void init(final int endJahr);

    /**
     * Überprüft, ob ein gegebener Tag ein Feiertag ist.
     *
     * @param datum Das Datum.
     * @param land  Das Land
     * @return true, falls es sich um einen Feiertag handelt.
     */
    boolean isFeiertag(final LocalDate datum, final Land land);

    /**
     * Findet einen Feiertag durch das Datum.
     *
     * @param datum Das Datum.
     * @param land  Das Land.
     * @return Das gefundene Objekt.
     */
    FeiertagsDatum getFeiertag(final String datum, final Land land);

    /**
     * Findet einen Feiertag durch das Datum.
     *
     * @param datum Das Datum.
     * @param land  Das Bundesland
     * @return Das gefundene Objekt.
     */
    FeiertagsDatum getFeiertag(final LocalDate datum, final Land land);

    /**
     * Findet alle Feiertage in einem Zeitraum.
     *
     * @param von  Das Anfangs-Datum.
     * @param bis  Das End-Datum.
     * @param land Das Land
     * @return Die Liste gefundener Objekte.
     */

    List<FeiertagsDatum> getFeiertage(final LocalDate von, final LocalDate bis, final Land land);

    /**
     * Findet alle Feiertage in einem Zeitraum.
     *
     * @param von  Das Anfangs-Datum.
     * @param bis  Das End-Datum.
     * @param land Das Bundesland
     * @return Die Liste gefundener Objekte.
     */

    List<FeiertagsDatum> getFeiertage(final String von, final String bis, final Land land);
}
