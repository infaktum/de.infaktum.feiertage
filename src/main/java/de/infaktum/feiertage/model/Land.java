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

import java.util.HashMap;
import java.util.Map;

/**
 * Feiertage sind meist nur in bestimmten Ländern gültig. Dies können auch Bundesländer,
 * Provinzen etc. sein. Dieses interface beschreibt ein generisches Land mit einer
 * Schlüsselnummer, einem Kürzel und eine Bezeichnung (= Name)
 */
public interface Land {

    Map<Integer, Land> landSchluessel = new HashMap<>();
    Map<String, Land> landKuerzel = new HashMap<>();

    /**
     * Liefert das Land über den Schlüssel.<br>
     * Konkrete Implementierungen müssen dazu die entsprechende
     * Hashmap <code>landSchluessel</code> füllen.
     *
     * @param schluessel Der numerische Schlüssel des Landes.
     * @return Das gefundene Land.
     */
    static Land getLandBySchluessel(int schluessel) {
        return landSchluessel.get(schluessel);
    }


    /**
     * Liefert das Land über das Kürzel.<br/>
     * Konkrete Implementierungen müssen dazu die entsprechende Hashmap <code>landKuerzel</code>
     * füllen.
     *
     * @param kuerzel Das Kürzel des Landes.
     * @return Das gefundene Land.
     */
    static Land getlandByKuerzel(String kuerzel) {
        return landKuerzel.get(kuerzel.toUpperCase());
    }

    /**
     * Liefert alle Länder.
     *
     * @return Alle Länder.
     */
    static Iterable<Land> getAll() {
        return landKuerzel.values();
    }

    /**
     * Liefert den numerischen Schlüssel des Landes.
     *
     * @return Der Schlüssel des Landes.
     */
    int schluessel();

    /**
     * Liefert das Kürzel des Landes.
     *
     * @return Das Kürzel des Landes.
     */
    String kuerzel();

    /**
     * Liefert die Bezeichnung des Landes.
     *
     * @return Die Bezeichnung des Landes.
     */
    String bezeichnung();
}
