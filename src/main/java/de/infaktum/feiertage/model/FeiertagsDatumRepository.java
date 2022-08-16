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

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository für die FeiertagsDatums-Objekte. Die Implementierung erfolgt durch Spring Boot.<br/>
 * Durch das Repository werden alle Suchfunktionen automatisch implementiert, die wir sonst selbst programmieren
 * müssten. Sollten weiter Suchfunktionen gewünscht werden, so können sie hier definiert werden.
 * <br/>
 * Die In-Memory-Datenbank muss bei Start des Services initial gefüllt werden und wird danach nur
 * noch lesend verwendet.<br/>
 *
 * @author Heiko Sippel
 * @version 1.0
 */
public interface FeiertagsDatumRepository extends CrudRepository<FeiertagsDatum, Long> {
    /**
     * Findet einen Feiertag durch das Datum.
     *
     * @param datum Das Datum.
     * @return Das gefundene Objekt.
     */
    FeiertagsDatum findByDatum(final LocalDate datum);

    /**
     * Findet alle Feiertage in einem Zeitraum.
     *
     * @param von Das Anfangs-Datum.
     * @param bis Das End-Datum.
     * @return Die Liste gefundener Objekte.
     */

    List<FeiertagsDatum> findByDatumBetween(final LocalDate von, final LocalDate bis);

}
