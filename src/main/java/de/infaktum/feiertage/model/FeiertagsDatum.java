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

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Die Klasse beschreibt einen gesetzlichen Feiertag.
 * <br/>
 * Sie enthält den Feiertag und das Datum des Feiertags im entsprechenden Jahr.
 * <br/>
 * Um nach Feiertagen in einem bestimmten Zeitraum zu suchen, verwenden wir eine In-Memory-Datenbank,
 * in der alle Instanzen von FeiertagsDatum gespeichert werden. Über ein CrudRepository stehen dann
 * alle Suchfunktionen zur Verfügung.
 *
 * @author Heiko Sippel
 * @version 1.0
 */

@Entity
public class FeiertagsDatum implements Serializable {
    public static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(FeiertagsDatum.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate datum;
    @Enumerated(EnumType.STRING)
    private Feiertag feiertag;

    /**
     * Geschützter Standard-Konstruktor für JPA.
     */
    protected FeiertagsDatum() {
    }

    /**
     * Erzeugt eine neue Instanz.
     *
     * @param feiertag Der Feiertag.
     * @param jahr     Das Jahr des Feietags.
     */
    public FeiertagsDatum(final Feiertag feiertag, int jahr) {
        Objects.requireNonNull(feiertag);
        this.datum = feiertag.datum(jahr);
        this.feiertag = feiertag;
    }

    /**
     * Liefert das Datum des Feiertags.
     *
     * @return Das Datum des Feiertags.
     */
    public LocalDate getDatum() {
        return datum;
    }

    /**
     * Liefert den Feiertag des Datums.
     *
     * @return Der Feiertag.
     */
    public Feiertag getFeiertag() {
        return feiertag;
    }

    /**
     * Liefert den Namen des Feiertags.
     *
     * @return Der Name des Feiertags.
     */
    public String getName() {
        return feiertag.bezeichnung();
    }

    /**
     * Überprüft, ob der Feiertag ein Sonntag ist.
     *
     * @return true, wenn der Feiertag ein Sonntag ist.
     */
    public boolean isSonntag() {
        return datum.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    /**
     * Überprüft, ob der Feiertag auf ein Wochenende (Samstag/Sonntag) fällt.
     *
     * @return true, wenn der Feiertag auf ein Wochenende fällt.
     */
    public boolean isWochenende() {
        log.trace("Test auf Wochenendede für {}", this);
        return datum.getDayOfWeek() == DayOfWeek.SATURDAY
            || datum.getDayOfWeek() == DayOfWeek.SUNDAY;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FeiertagsDatum that)) {
            return false;
        }

        return getDatum().equals(that.getDatum());
    }

    @Override
    public int hashCode() {
        return getDatum() != null ? getDatum().hashCode() : 0;
    }


    /**
     * Formatierte Ausgabe.
     *
     * @return Eine formatierte Ausgabe des Datums.
     */
    public String toString() {
        return datum + " (" + getName() + ") in: " + feiertag.laender();
    }

}
