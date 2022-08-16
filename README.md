# de.infaktum.feiertage

Das Projekt implementiert einen REST-Service für deutsche Feiertage in Java auf Basis von Spring Boot.

## Gesetzliche deutsche Feiertage

Neben den deutschlandweit geltenden Feiertagen wie Ostern, Weihnachten und dem Nationalfeiertag gibt es noch eine Anzahl Feiertage, die nur in bestimmten Bundesländern gelten, wie etwa Fronleichnam und der Dreikönigstag in Süddeutschland und der Reformationstag in den östlichen Bundesländern. Darüber hinaus sind nicht alle Feiertage 
"ewig" gültig; so wurde der Buß- und Bettag in den 90er Jahren in den meisten Bundesländern abgeschafft, und 2020 nur in Berlin der Internationale Frauentag zu einem gesetzlichen Feiertag ernannt. Dazu kommen noch regionale Besonderheiten, wenn bei kirchlichen Feiertagen nur in Regionen mit überwiegend katholischer oder evangelischer Bevölkerung der Feiertag gilt. 

Das System geht von den im Jahr 2022 bestehenden Feiertagen aus. Es ist zwar leicht, neue Feiertage einzuführen, aber alle Feiertage werden so behandelt, als hätte es sie schon immer gegeben. Abfragen auf frühere Jahre sind zwar möglich, liefern aber entsprechend *falsche* Ergebnisse. Dies ist der Einfachheit der Implementierung geschuldet.

## Build-System, IDEs und Dependencies

Als Build-System wird maven verwendet. Die Dependencies finden sich daher in der beschreibenden **pom.xml** wieder. Die Projektdateien für IntelliJ liegen ebenfalls bei.



## Start des Systems

Der Service läuft mit installierter JVM ab Version 16. Start durch

<code>java -jar Feiertage.jar</code>

Danach ist die REST-Schnittstelle über den Port 8080 erreichbar.




