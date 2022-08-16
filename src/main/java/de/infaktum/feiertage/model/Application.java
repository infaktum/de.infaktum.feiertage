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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Die Anwendung startet die Web-Applikation.
 *
 * @author Heiko Sippel
 * @version 1.0
 */
@SpringBootApplication
public class Application {
    /**
     * Boots the web app.
     *
     * @param args Optional parameters
     */
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Der Controller des REST-Services.
     */
    @RestController
    public static class Controller {
        private final static Logger log = LoggerFactory.getLogger(Controller.class);
        @Autowired
        private Feiertage feiertage;

        /**
         * Initialisiert das Feiertags-Bestimmungssystem bis zum angegeben Jahr.
         */
        @PostConstruct
        public void init() {
            feiertage.init(2100);
        }

        @GetMapping("/feiertag")
        public FeiertagsDatum feiertag(
                @RequestParam final String datum, @RequestParam final String land) {
            log.info("Request-Parameter datum {}, Land {}", datum, Land.getlandByKuerzel(land));
            return feiertage.getFeiertag(datum, Land.getlandByKuerzel(land));
        }

        @GetMapping("/feiertage")
        public List<FeiertagsDatum> feiertage(
                @RequestParam final String von, @RequestParam final String bis, @RequestParam final String land) {
            log.info("Request-Parameter von {} bis {}, Land {}", von, bis, Land.getlandByKuerzel(land));
            return feiertage.getFeiertage(von, bis, Land.getlandByKuerzel(land));
        }
    }
}

