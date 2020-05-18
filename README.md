### optaPlannerExample
[![Build Status](https://travis-ci.org/viadee/optaPlannerExample.svg?branch=master)](https://travis-ci.org/github/viadee/optaPlannerExample) 
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=de.viadee%3Auniplaner&metric=alert_status)](https://sonarcloud.io/dashboard?id=de.viadee%3Auniplaner)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=de.viadee%3Auniplaner&metric=coverage)](https://sonarcloud.io/dashboard?id=de.viadee%3Auniplaner)
-----
Beispielprojekt für JBoss OptaPlanner: Raum- und Terminplanung an einer Universität.

-----

Weitere Informationen zu OptaPlanner finden sich hier:

http://jaxenter.de/artikel/jboss-optaplanner-optimierung-2-0-176855  
Kurzversion eines Artikels, der ein Vorgehensmodell zur Lösung von Optimierungsproblemen mit OptaPlanner
skizziert. (Artikel veröffentlicht in Java Magazin 2.15)

http://www.optaplanner.org  
offizielle OptaPlanner-Webseite

-----

**BEISPIELSZENARIO**

Das Projekt implementiert beispielhaft die (stark vereinfachte) Raum- und Terminplanung an einer Universität
auf Basis von JBoss OptaPlanner und JBoss Drools Expert. Ziel ist dabei, dass jeder Vorlesung ein Termin
(Wochentag und Zeitintervall) und ein Raum zugeordnet wird.

Folgende Bedingungen müssen bei der Planung erfüllt werden (Hard Constraints):
- Zwei Vorlesungen dürfen nicht zeitgleich im gleichen Raum stattfinden.
- Die Vorlesungen eines Studiengangs dürfen sich zeitlich nicht überschneiden.

Folgende Bedingungen sollten erfüllt werden (Soft Constraints):
- Die Vorlesungen eines Studiengangs sollten im gleichen Raum stattfinden.
- Die Vorlesungen eines Studiengangs sollten an möglichst wenigen Wochentagen stattfinden.
- Die Vorlesungen eines Studiengangs sollten pro Tag kompakt sein, sodass es möglichst keine Freistunden gibt.

Diese Bedingungen werden durch Drools-Regeln formuliert.

-----

**ENTWICKLUNGSUMGEBUNG**

Das Projekt wurde mit Eclipse in der Version 4.3 (Kepler) umgesetzt. Das folgende Eclipse-Plugin wird benötigt:

- JBoss Drools Core  
(Update Site: http://download.jboss.org/drools/release/6.1.0.Final/org.drools.updatesite)

Nach der Installation des Plugins muss eine Drools Runtime mit dem Namen "Drools 6.1.0.Final" eingerichtet 
werden. Dies erfolgt unter:  
Preferences > Drools > Installed Drools Runtimes > Create a new Drools 6 Runtime

Das JDK wird mindestens in der Version 7 benötigt.

-----

**TESTDATEN**

Zum Testen der Optimierung kann die Klasse `SolverTest` verwendet werden. Diese lädt einige Testdaten für Räume, Studiengänge
und Vorlesungen aus drei csv-Dateien (siehe `src/test/resources`) in eine H2-Datenbank und führt die Optimierung mit
dem OptaPlanner-Solver durch.

-----

**KONTAKT**
- Magdalena Lang (magdalena.lang@viadee.de)
- Constanze Klaar (constanze.klaar@viadee.de)
