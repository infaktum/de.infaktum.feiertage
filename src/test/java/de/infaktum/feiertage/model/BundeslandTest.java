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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.infaktum.feiertage.model.Bundesland.bayern;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test für die Enum Bundesland.
 */
class BundeslandTest {

    private final static Logger log = LoggerFactory.getLogger(BundeslandTest.class);

    @Test
    @DisplayName("Suche nach Land über Länderkürzel")
    public void getBundeslandByKuerzel() {
        Land gefunden = Land.getlandByKuerzel("BY");
        assertEquals(bayern, gefunden);
    }

    @Test
    @DisplayName("Suche nach Land über Länderschlüssel")
    public void getBundeslandBySchluessel() {
        Land gefunden = Land.getLandBySchluessel(9);
        assertEquals(bayern, gefunden);
    }

    @Test
    @DisplayName("Überprüfung der Daten")
    public void schluesselKuerzelBezeichnung() {
        log.debug("Überprüfe {}", bayern);
        assertEquals(bayern.schluessel(), 9);
        assertEquals(bayern.kuerzel(), "BY");
        assertEquals(bayern.bezeichnung(), "Bayern");

    }

    @Test
    @DisplayName("Bundesländer zählen: 16")
    public void zaehleLaender() {
        assertEquals(16, Bundesland.values().length);
    }

}