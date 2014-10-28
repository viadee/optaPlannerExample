optaPlannerExample
==================

Beispielprojekt für JBoss OptaPlanner: Raum- und Terminplanung an einer Universität.

-----

Weitere Informationen zu OptaPlanner finden sich hier:

http://jaxenter.de/artikel/jboss-optaplanner-optimierung-2-0-176855<br/>
Kurzversion eines Artikels, der ein Vorgehensmodell zur Lösung von Optimierungsproblemen mit OptaPlanner
skizziert. Die vollständige Version erscheint in einer der nächsten Print-Ausgaben des Java Magazins.

http://www.optaplanner.org<br/>
offizielle OptaPlanner-Webseite

-----

<b>BEISPIELSZENARIO</b>

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

<b>ENTWICKLUNGSUMGEBUNG</b>

Das Projekt wurde mit Eclipse in der Version 4.3 (Kepler) umgesetzt. Das folgende Eclipse-Plugin wird benötigt:

- JBoss Drools Core<br/>(Update Site: http://download.jboss.org/drools/release/6.1.0.Final/org.drools.updatesite)

Nach der Installation des Plugins muss eine Drools Runtime mit dem Namen "Drools 6.1.0.Final" eingerichtet 
werden. Dies erfolgt unter:<br/>
Preferences > Drools > Installed Drools Runtimes > Create a new Drools 6 Runtime

-----

<b>KONTAKT</b>
- Jan-Philipp Friedenstab (jan-philipp.friedenstab@viadee.de)
- David Jorch (david.jorch@viadee.de)
- Christian Treptau (christian.treptau@viadee.de)