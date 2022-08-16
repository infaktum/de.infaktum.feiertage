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

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test für die Klasse Feiertag.
 */
class FeiertagsDatumTest {
    private final static Logger log = LoggerFactory.getLogger(FeiertagsDatumTest.class);
    private final Feiertag feiertag = Feiertag.maifeiertag;

    @Test
    @DisplayName("Konstruktor")
    public void getInstance() {
        log.debug("Test getInstance");
        FeiertagsDatum feiertagsDatum = new FeiertagsDatum(feiertag, 2022);
        log.debug(feiertagsDatum.toString());
        assertEquals(feiertag, feiertagsDatum.getFeiertag());
    }

}