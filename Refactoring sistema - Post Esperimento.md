# Refactoring sistema - Post Esperimento - In corso
## Autenticazione
**Problematiche:**
La precedente implementazione e gestione dell'autenticazione presentava le seguenti problematiche:
1. La gestione dell'autenticazione di amministratore e giocatore (ex utente) era effettuata da due moduli separati: l'amministratore
   era registrato, mantenuto e autenticato da T1, mentre il giocatore da T23;
2. L'autenticazione, effettuata tramite controllo del JWT restituito al browser al momento del login, era eseguita
   solo da T1 (internamente) e T5 (tramite chiamata API a T23), dove ogni endpoint verificava il JWT ricevuto prima di
   procedere a soddisfare la richiesta;
3. I moduli T4, T7 e T8, accessibili solo tramite chiamate API interne ai moduli T1, T23 o T5, non presentavano alcuna forma di verifica nativa dell'autenticazione.
   Le richieste fatte a partire dai moduli non prevedevano tuttavia l'invio del JWT;
4. Una protezione parziale delle chiamate API interne era stata prevista nel nuovo API Gateway, ma non ancora attivata.

**Modifiche principali:**
1. L'autenticazione è stata demandata interamente a T23, che ora registra, mantiene e autentica sia amministratori che giocatori, servendo tutte le pagine web relative;
2. Al momento del login, oltre al token JWT di durata limitata, all'utente viene ora restituito anche un token di refresh, utilizzabile dai vari moduli
   per rinnovare automaticamente il JWT in caso di scadenza, senza richiedere nuovamente il login tramite credenziali;
3. Il modulo T23 ora implementa un sistema di redirect automatico alla main page dell'amministratore o del giocatore se questo è già loggato (ovvero dispone di un JWT valido).
   Ad esempio, se un amministratore tenta di accedere alla pagina di login e il JWT fornito è valido, verrà indirizzato direttamente alla pagina principale corrispondente;
4. I moduli T1 e T4 implementano ora un filtro sulle richieste HTTP che verifica automaticamente la presenza del JWT nella richiesta e la sua validità
   (tramite chiamata API a T23). Nel caso in cui il JWT fosse scaduto e fosse stato fornito anche il token di refresh, il filtro richiederà automaticamente a T23 un nuovo JWT
   e lo restituirà al browser nella risposta finale.
5. I moduli T1 e T4 ora memorizzano, per la sola durata della richiesta, il jwt ricevuto per poterlo inoltrare nelle richieste API interne ai moduli T4, T7, T8.
6. L'API Gateway implementa ora un filtro di autenticazione corretto per catturare le richiesta, estrarre il jwt e validarlo tramite richiesta API a T23. Il filtro è stato
   attivato per i moduli T4, T7 e T8, a cui mancava la verifica dell'autenticazione.

## Centralizzazione delle chiamate API - IN CORSO
**Problematiche:**
1. I moduli T1 e T23 effettuano numerose chiamate API, sia dal frontend che dal backend, rivolte al proprio backend o ad altri moduli; tuttavia, tali chiamate sono distribuite su più file, rendendo complessa l’identificazione completa e la manutenzione delle API utilizzate;
2. Anche il modulo T5 presenta lo stesso problema, sebbene limitato alle chiamate effettuate dal frontend;
3. Gli URL di navigazione (analogamente alle chiamate API) nei moduli T1, T5 e T23 sono sparsi in file HTML e script JavaScript, complicandone gestione e aggiornamenti.

**Modifiche principali:**
1. Per le chiamate effettuate dal backend è stato creato un package dedicato che fornisce un template per l'invio delle richieste, includendo automaticamente l’autenticazione tramite il JWT presente nella richiesta utente, e una classe centralizzata che raccoglie tutte le chiamate API utilizzate. 
   Questo approccio riprende quanto già adottato nel backend di T5, seppur in forma semplificata, in considerazione del numero limitato di chiamate nei moduli T1 e T23;
2Per le richieste effettuate dal frontend è stato introdotto un sistema analogo, con un template per le API e la centralizzazione degli URL e delle chiamate API in specifici file JavaScript organizzati.

**Attenzione:**
Questa fase di refactoring è ancora in corso, a causa della complessità nell'individuare e mappare tutte le chiamate API e gli URL di navigazione esistenti.

## Riscrittura del modulo T4 usando Spring Boot
**Problematiche:**
Il modulo T4, responsabile della persistenza delle partite in corso e concluse, era stato originariamente sviluppato in Go invece che in Spring Boot, come il resto dell’ecosistema. Poiché Go non è insegnato in ambito universitario, la sua manutenzione si è dimostrata complessa, portando in alcuni casi all’introduzione di funzionalità implementate in modo non ottimale.

**Modifiche principali:**
1. Il modulo è stato completamente riscritto utilizzando Spring Boot, per uniformarlo agli altri moduli del sistema;
2. Il passaggio a Spring ha consentito anche un miglioramento delle funzionalità precedentemente presenti, ottimizzandone l’efficienza e la chiarezza.


## Riorganizzazione dei database
**Problematiche:**
La precedente implementazione del database non distribuiva correttamente le entità tra i vari moduli:
1. T1 manteneva sia gli amministratori sia le classi UT inserite nel sistema;
2. T23 manteneva i giocatori e le informazioni relative al loro profilo utente (es. follower, email, nome, ...);
3. T4 manteneva le partite in corso e concluse, gli avversari disponibili e il progresso del giocatore (punti esperienza, achievement, ...).

**Modifiche principali:**
Anche grazie alla riscrittura del modulo T4, le entità sono state ridistribuite come segue:
1. T1 mantiene ora esclusivamente le classi UT e i relativi avversari disponibili;
2. T23 mantiene sia amministratori sia giocatori, includendo per questi ultimi il profilo utente e il relativo stato di avanzamento nel gioco;
3. T4 conserva solo ed esclusivamente lo stato delle partite in corso e concluse.

---------

# Parzialmente implementate - da valutare

## Centralizzazione di componenti/logica/entità comuni
**Problematiche:**
Molti moduli utilizzano gli stessi componenti, entità o utility. Diverse di queste risultano replicate o riadattate all'interno di ciascun modulo che ne fa uso, portando alle seguenti criticità:
- Una quantità significativa di codice risulta duplicata;
- Ogni modifica effettuata in una copia deve essere replicata manualmente anche nelle altre, se correlate.

**Idea:**
Una possibile soluzione attualmente in valutazione consiste nella creazione di un modulo condiviso contenente le classi comuni, da importare nei moduli che le richiedono.
Questo approccio ridurrebbe la duplicazione del codice e permetterebbe la propagazione automatica delle modifiche a tutti i moduli interessati. I principali componenti interessati a questo refactoring sarebbero le classi DTO.

**Implementazione:**
Il sistema include ora un modulo aggiuntivo, denominato T-shared, che attualmente contiene:
1. Alcuni DTO utilizzati nello scambio di dati tra i moduli;
2. Le enumerazioni che definiscono gli avversari (tipo, difficoltà, modalità di gioco);
3. Utility per l'estrazione delle metriche dai file generati da JaCoCo ed EvoSuite;
4. Mapper per la conversione tra DTO ed entità (e viceversa).

---------

# Da implementare

