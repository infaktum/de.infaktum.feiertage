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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests für die Klasse Feiertage.
 */
@SpringBootTest
class FeiertageDeTest {
    protected final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final static Logger log = LoggerFactory.getLogger(FeiertageDeTest.class);
    Feiertag feiertag = Feiertag.neujahr;

    String datumString = "01.01.2020";
    @Autowired
    private FeiertageDe feiertage;


    @Test
    @DisplayName("Test getFeiertage")
    public void getFeiertag() {
        LocalDate datum = LocalDate.parse(datumString, formatter);
        FeiertagsDatum gefunden = feiertage.getFeiertag(datum, Bundesland.nrw);
        assertEquals(gefunden.getName(), feiertag.bezeichnung());
    }


    @Test
    @DisplayName("Test getFeiertage")
    public void getFeiertage() {
        LocalDate von = LocalDate.parse("01.01.2022", formatter);
        LocalDate bis = LocalDate.parse("31.12.2022", formatter);
        List<FeiertagsDatum> gefunden = feiertage.getFeiertage(von, bis, Bundesland.nrw);
        log.info(gefunden.toString());
        assertEquals(13, gefunden.size());
    }

    @Test
    @DisplayName("Weihnachten ist Feiertag")
    public void isFeiertag() {
        assertTrue(feiertage.isFeiertag(LocalDate.of(2022, Month.DECEMBER, 25), Bundesland.nrw));
    }
    @Test
    @DisplayName("1. Mai 2022 ist Sonntag")
    public void sonntag() {
        FeiertagsDatum gefunden = feiertage.getFeiertag("01.05.2022", Bundesland.nrw);
        assertTrue(gefunden.isSonntag());
    }

    @Test
    @DisplayName("1. Mai 2022 ist Wochenende")
    public void wochenende() {
        FeiertagsDatum gefunden = feiertage.getFeiertag("01.05.2022", Bundesland.nrw);
        assertTrue( gefunden.isWochenende());
    }

}