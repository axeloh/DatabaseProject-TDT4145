# Treningsdagbok-App dokumentasjon

i) En oversikt over hvilke klasser som finnes i programmet deres og en
tilhørende beskrivelse av hvilken oppgave hver klasse løser.
-
Apparat.java
- For å opprette apparat-objekter. Setter nødvendige variabler og har gettere og
settere for disse.

DBConnection.java
- Klasse for å sette opp og koble til databasen.

Main.java
- Har ingen praktisk funksjon. Kun en hjelpeklasse vi har brukt for å teste core
underveis.

Notat.java
- For å opprette notat-objekter. Består av en ID, et treningsformål og en beskrivelse av
hvordan treningsøkten opplevdes.  Har gettere og settere for disse.

Ovelse.java
- For å opprette øvelse-objekter. Dette er en abstrakt klasse, fordi en øvelse enten må
være med et apparat, eller uten et apparat. Inneholder alle felles felter, gettere og
settere.

OvelseGruppe.java
- For å opprette øvelsesgruppe-objekter. Setter nødvendige variabler og har gettere og
settere for disse.

OvelseMedApparat.java
- For å opprette øvelse med apparat-objekter. Her vil ekstra felter bli lagt til. Har
gettere og settere for disse.

OvelseUtenApparat.java
- For å opprette øvelse uten apparat-objekter. Her vil ekstra felter bli lagt til. Har
gettere og settere for disse.

QueryFactory.java
- Klassen som inneholder alle spørringene til databasen. Forespørsler fra brukeren
oversettes til sql-spørringer som databasen forstår. Her har vi en rekke gettere og
settere for de forskjellige objektene det er mulig å lage og hente informasjon om
(apparat, øvelse, treningsøkter osv osv). Det er også en del hjelpemetoder som blir
benyttet i gettere og i mainControlleren. Settere tar inn java-objekter og lagrer den
tilhørende dataen i tabellene i databasen, gettere henter ut allerede eksisterende
data fra databasen og returnerer java-objekter.

Treningsokt.java
- For å opprette treningsøkt-objekter.  Setter nødvendige variabler og har gettere og
settere for disse.

App.java
- Starter applikasjonen.

mainController.java
- Forbinder brukergrensesnittet med core-klassene. Oppretter og lagrer/henter og viser
data alt ettersom hva brukeren velger. All lagring og henting skjer via QueryFactory.
En rekke sjekker er lagt inn for å sikre gyldig tilstand i databasen. F. eks. om man vil
legge til et apparat kan ikke apparatet finnes fra før, om man vil legge til en øvelse
kan ikke øvelsen finnes fra før av. Brukeren får tilbakemelding i det grafiske
grensesnittet om forespørslene er gyldige eller ikke. Dersom forespørselen er gyldig
sendes den videre til QueryFactory som enten lagrer eller henter data i databasen.

welcome.fxml
- Brukergrensensnittet er definert her. Laget i SceneBuilder.

ii) En oversikt over hvilke use cases som er løst og hvordan de er realisert i
programmet.
-
1. Registrere apparater, øvelser og treningsøkter med tilhørende data.
	- Det er laget spørringer til databasen i QueryFactory som legger til nye
objekter i databasen. Metodene setApprarat, setOvelseMedApparat osv, er
løsningen på dette use caset. Bruker insert-sql-spørringer for å løse
oppgaven. MainController henter data skrevet inn av bruker, oppretter
objekter av disse (dersom dataene er gyldig) og sender objektene til
QueryFactory som utfører spørringen.

2. Få opp informasjon om et antall n sist gjennomførte treningsøkter med notater, der n
spesifiseres av brukeren.
	- Metoden “getSisteTreningsokter(Integer n)” løser dette use caset. Først
henter vi ut de n siste treningsøktene i databasen, lager Treningsøkt-objekter
av informasjonen og legger dem inn i en liste. Deretter må vi hente
informasjonen som er lagret i hver treningsøkt. For hver økt lager vi nye
spørringer som henter ut øvelser som er gjort i den økten. Hvert
Treningsøkt-objekt har attributtet “øvelser” som er en liste med øvelser gjort i
den økta. Først finner vi informasjon om alle øvelser uten apparat, deretter
alle med apparat. Dette fordi de har ulik informasjon knyttet til seg. F. eks vil
øvelse med apparat inneholde informasjon om apparatet, mens øvelser uten
apparat inneholder en beskrivelse av øvelsen. Vi lager så objekter ut fra
informasjonen og disse objektene legges inn i øvelser-listen til treningsøkten
det gjelder.  Denne lista kan dermed bestå av både
OvelseUtenApparat-objekter og OvelseMedApparat-objekter, og dette løser vi
altså med superklassen Ovelse (som da er typen til elementene i lista) og lar
OvelseUtenApparat-klassen og OvelseMedApparat-klassen arve fra denne.
Til slutt returneres Treningsøkt-objekt-listen hvor hvert Treningsøkt-objekt
inneholder all tilhørende informasjon om økta.

3. For hver enkelt øvelse skal det være mulig å se en resultatlogg i et gitt tidsintervall
spesifisert av brukeren.
	- Metoden “getResultatLogg(String ovelse, String start, String slutt)” løser dette
use caset. Tar inn øvelse du vil se resultatlogg for, og perioden du vil se
resultatene fra. Henter først ut alle treningsøkter fra databasen (ved å kalle
getSisteTreningsøkter(null)). Deretter itererer vi gjennom listen av

treningsøkten og  filtrerer ut de som ikke faller innenfor intervallet. Deretter
itererer vi gjennom de resterende treningsøktene og  dersom øvelsen vi er ute
etter finnes i øktene, legges de til i en liste, som tilslutt returneres.

4. Lage øvelsegrupper og finne øvelser som er i samme gruppe.
	- Metoden “getOvelserInOvelsegruppe(String ovelsegruppe)” løser dette use
caset. Henter ut alle øvelser i databasen (vha getAlleOvelser-metoden)
Deretter sjekker vi om attributet øvelsesgruppe i øvelsene mathcer
øvelsesgruppen som ble sendt inn i metoden. Returnerer en liste med alle
øvelsene som matchet.

5. Et valgfritt use case som dere selv bestemmer: Se antall ganger en øvelse er blitt
utført.
	- Metoden “getAntallGangerOvelseUtfort(String ovelse)” løser dette use caset.
Bruker getResultatLogg (ovelse, "0000-00-00 00:00:00", "9999-12-30
23:59:59") som returnerer en liste over alle øvelser med samme navn som er
blitt utført gjennom “alle” tider. Returnerer så størrelsen på lista som vil være
antall ganger øvelsen er blitt utført.
