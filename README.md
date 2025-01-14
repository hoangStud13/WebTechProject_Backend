# Projektbeschreibung 
***
ArmanAir ist eine Webanwendung, welches im Rahmen eines Universitätsprojekt entwickelt wurde und eine Microservice ähnliche Architektur nutzt. 
Die Anwendung bietet verschiedene Funktionalitäten für die Verwaltung von Flügen und Nutzerkonten. 
<br><br>
Das Backend besteht aus drei unabhängigen Microservices, die jeweils eine spezifische Aufgabe übernehmen und über REST-Schnittstellen miteinander
kommunizieren.
<br><br>
Ziel des Projekts ist es, Flugbuchwebseiten ähnlich wie Opodo oder Fluege.de nachzuprogrammieren, die eine benutzerfreundliche Interaktion mit Flug- und Ticketinformationen ermöglichen.

---

## Inhalt


- [Architektur](##Architektur)
- [Services im Detail](#link)
    - [FlightsService](#link)
    - [PopularFlightService](#link)
    - [UserManagementService](#link)
- [Sicherheit](#link)
  - [FilterChain](#link)
  - [JWT](#link)
- [API Dokumentation / Test](#link)
  - [Swagger-UI](#link)
  - [Tests](#link)


---

## Architektur
Wir folgen der klassischen 3-Tier Architecture. Das Frontend-Repository befindet sich [hier](#link). Für die Datenbank setzen wir auf MySQL.
<br>
Schematisch gesehen sieht unsere Anwendung wie folgt aus:
<br>
![Techstack picture](md-images/architecture.jpeg)
Das Backend besteht aus 3 Services, die jeweils für eine bestimmte Aufgabe tätig sind. 
<br>
Die Projektstruktur sieht so aus:
```
WebTechProject_Backend/
├── FlightsService/ 
├── PopularFlightService/ 
├── UserManagementService/ 
└── DockerCompose.yml
```
Mit der Docker Compose File lässt sich die ganze Anwendung lokal starten und testen.
Hier für einfach 
``` bash
         Docker Compose Up
```
in der Konsole ausführen.

<br>
Jeder Service ist in etwa wie folgt aufgebaut:

```
src/
├── controller/ Zuständig für die Verwaltung der eingehenden HTTP Anfragen
├── service/ Implementierung der Geschäftslogik
├── repository/ JPA um auf die Datenbank zuzugreifen
├── dto/ Objekt, welches die Entitäten repräsentiert
Dockerfile-Servicename
```
Zusätzlich hat jeder Service eine Dockerfile, welches wir für das deployment genutzt haben.

Die Datenbank umfasst insgesamt fünf Tabellen, deren Strukturen wie folgt dargestellt sind:

![alt text](md-images/erm_diagramm.png)
Vier der Tabellen sind durch einfache 1:n-Beziehungen miteinander verknüpft. Die fünfte Tabelle, PopularflightsService, steht jedoch isoliert und hat keine Verknüpfungen zu den anderen Tabellen.



---
## Services im Detail


### FlightsService
Das FlightsService (könnte man auch AutocompleteService nennen) bietet eine Autovervollständigungsfunktion, die Nutzern bei der Eingabe von Suchbegriffen relevante Vorschläge liefert. Gibt ein Nutzer beispielsweise "Ber" ein, wird eine Liste mit möglichen Treffern wie "Berlin" und "Bern" zurückgegeben.

Diese Vorschläge beschränken sich ausschließlich auf Flughäfen und Städte. Im Frontend wird die Liste der vorgeschlagenen Ergebnisse angezeigt, sodass der Nutzer daraus eine passende Stadt oder einen Flughafen auswählen kann.

![alt text](md-images/flightsservice_example.PNG)

Dafür exposed der FlightsService eine einzige GET REST API. 