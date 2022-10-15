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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Eine Standard-Implementierung für das deutsche Datumsformat.
 *
 * @author Heiko Sippel
 * @version 1.0
 */
@Component
public class FeiertageDe extends FeiertageGenericImpl {
    private static final Logger log = LoggerFactory.getLogger(FeiertageDe.class);

    @Autowired
    private FeiertagsDatumRepository repository;


    /**
     * Initialisiert das System. Bis zum gegebenen Jahr werden alle FeiertagsDatums-Objekte erzeugt und in der Datenbank
     * gespeichert.
     *
     * @param endJahr Das letzte Jahr.
     */
    @Override
    public void init(final int endJahr) {
        for (int jahr = 1970; jahr <= endJahr; jahr++) {
            for (Feiertag feiertag : Feiertag.values()) {
                repository.save(new FeiertagsDatum(feiertag, jahr));
            }
        }
    }


}
