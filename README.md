# DatabaseProject-TDT4145
Gruppeprosjekt i TDT4145 - Datamodellering og databasesystemer

Læringsmål for prosjektet er å:
-
  - Få praktisk trening i å programmere med SQL og JDBC opp mot en database. 
  - Få forståelse av hvordan databasen kan designes som en del av systemet. 
 
 
Prosjektet går ut på å lage en elektronisk treningsdagbok som skal brukes til å holde oversikt over treninger, resultater og øvelser. En slik treningsdagbok skal kunne brukes for et treningssenter, for eksempel kjelleren på SiT Gløshaugen treningssenter.  
 
Hver treningsøkt skal lagres med dato, tidspunkt, varighet og informasjon om hvilke øvelser som ble utført. Personlig form og prestasjon kan være relevant, begge disse lagres som heltall mellom 1 og 10. For hver trening skal man ha mulighet til å føre et notat som beskriver treningsformål, opplevelsen av treningen eller lignende.  
 
Hver øvelse skal kunne lagres med et navn. Vi skiller mellom to type øvelser: De som gjennomføres på et fastmontert apparat på treningssenteret og de som ikke gjør det. Øvelser på et apparat krever navn på øvelsen, antall kilo, antall sett og med en referanse til apparatet som brukes. Et apparat trenger et navn og et felt som beskriver hvordan apparatet fungerer. Det er ikke uvanlig at et apparat kan tilby mange forskjellige øvelser. Øvelser som ikke foregår på et apparat trenger en tekstlig beskrivelse i tillegg til et navn.  
 
Hver øvelse kan inngå i en eller flere øvelsegrupper. For eksempel kan en gruppe med øvelser være beinstyrkeøvelser, og inneholde elementer som knebøy, utfall, froskehopp og så videre. Dette ønsker vi å utnytte til å finne øvelser som ligner på hverandre. 
 
Treningsdagboken må minimum ha følgende funksjonalitet:
- 
	 1. Registrere apparater, øvelser og treningsøkter med tilhørende data. 
	 2. Få opp informasjon om et antall n sist gjennomførte treningsøkter med notater, der n spesifiseres av brukeren. 
	 3. For hver enkelt øvelse skal det være mulig å se en resultatlogg i et gitt tidsintervall spesifisert av brukeren.  
	 4. Lage øvelsegrupper og finne øvelser som er i samme gruppe. 5. Et valgfritt use case som dere selv bestemmer. 

Treningsdagboken skal implementeres i Java med JDBC (Java Database Connectivity), i henhold til deres datamodell. De 5 kravene til funksjonalitet presentert over må være oppfylt. 
