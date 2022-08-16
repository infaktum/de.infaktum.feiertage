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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generische Implementierung, die alle Feiertage aus einem Repository holt.
 * Dies muss zuvor in einer konkreten Implementierung gefüllt werden.
 *
 * @author Heiko Sippel
 * @version 1.0
 */

public abstract class FeiertageGenericImpl implements Feiertage {
    protected final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final static Logger log = LoggerFactory.getLogger(FeiertageGenericImpl.class);
    @Autowired
    private FeiertagsDatumRepository repository;

    /**
     * Überprüft, ob ein gegebener Tag ein Feiertag ist.
     *
     * @param datum Das Datum.
     * @param land  Das Land.
     * @return true, falls es sich um einen Feiertag handelt.
     */
    @Override
    public boolean isFeiertag(final LocalDate datum, final Land land) {
        log.trace("Ist {} ein Feiertag in {}", datum, land);
        return getFeiertag(datum, land) != null;
    }

    /**
     * Findet einen Feiertag durch das Datum.
     *
     * @param datum Das Datum als String.
     * @param land  Das Land.
     * @return Das gefundene Objekt.
     */
    @Override
    public FeiertagsDatum getFeiertag(final String datum, final Land land) {
        return getFeiertag(LocalDate.parse(datum, formatter), land);
    }

    /**
     * Findet einen Feiertag durch das Datum.
     *
     * @param datum Das Datum.
     * @param land  Das Land.
     * @return Das gefundene Objekt.
     */
    @Override
    public FeiertagsDatum getFeiertag(final LocalDate datum, final Land land) {
        FeiertagsDatum feiertagsDatum = repository.findByDatum(datum);
        log.debug("Gefundener Feiertag: {}",feiertagsDatum);
        if(feiertagsDatum == null)
            return null;
        List<FeiertagsDatum> feiertagsData = filter(List.of(feiertagsDatum), land);
        return feiertagsData.isEmpty() ? null : feiertagsData.get(0);
    }

    /**
     * Findet alle Feiertage in einem Zeitraum.
     *
     * @param von  Das Anfangs-Datum.
     * @param bis  Das End-Datum.
     * @param land Das Land
     * @return Die Liste gefundener Objekte.
     */

    @Override
    public List<FeiertagsDatum> getFeiertage(final LocalDate von, final LocalDate bis, final Land land) {
        return filter(repository.findByDatumBetween(von, bis), land);
    }

    /**
     * Findet alle Feiertage in einem Zeitraum.
     *
     * @param von  Das Anfangs-Datum.
     * @param bis  Das End-Datum.
     * @param land Das Land
     * @return Die Liste gefundener Objekte.
     */
    @Override
    public List<FeiertagsDatum> getFeiertage(final String von, final String bis, final Land land) {
        return getFeiertage(LocalDate.parse(von, formatter), LocalDate.parse(bis, formatter), land);
    }

    /**
     * Filtert aus einer Liste von FeiertagsDatums-Objekten diejenigen heraus, die im Bundesland gültig sind.
     *
     * @param feiertagsData Die Liste der FeiertagsDatums-Objekte
     * @param land          Das Land.
     * @return Alle Feiertage, die im Bundesland gültig sind.
     */
    private List<FeiertagsDatum> filter(final List<FeiertagsDatum> feiertagsData, final Land land) {
        return feiertagsData.stream().filter(f -> f.getFeiertag().isFeiertagIn(land)).collect(Collectors.toList());
    }
}
