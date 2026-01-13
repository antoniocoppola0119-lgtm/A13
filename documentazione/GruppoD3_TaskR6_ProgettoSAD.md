# Documentazione di Progetto: Software Architecture Design
## Task R6: Gestione Profilo Giocatore

### 0. Identificazione del Team e Obiettivi
| Campo | Dettaglio |
| :--- | :--- |
| **ID Team** | Gruppo D3 |
| **Componenti** | Andrea Pironti, Antonio Coppola, Mario Berrino |
| **Task Assegnato** | Task R6, Gestione profilo giocatore |
| **Approccio** | Sviluppo Incrementale |
| **Repository Originale** | https://github.com/Testing-Game-SAD-2023/A13 |
| **Repository Consegnato** | https://github.com/antoniocoppola0119-lgtm/A13/tree/main |

---

### 1. Contesto del Progetto
**TestingRobotChallenge** è una piattaforma di gamification per l'apprendimento del software testing.  
Il sistema permette di sfidare avversari automatizzati ("Robot") scrivendo test JUnit4.  
L'obiettivo del **Task R6** è l'evoluzione dell'architettura esistente tramite la trasformazione della pagina profilo utente, aggiornandone logica e UI.

---

### 2. Analisi dei Requisiti

#### Requisiti Funzionali (RF)
<table style="border-collapse: collapse; width: 100%; font-size: 12px;">
  <thead>
    <tr style="background-color: #e0e0e0;">
      <th style="border: 1px solid black; padding: 6px;">Codice</th>
      <th style="border: 1px solid black; padding: 6px;">Nome</th>
      <th style="border: 1px solid black; padding: 6px;">Descrizione</th>
    </tr>
  </thead>
  <tbody>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">RF1</td>
      <td style="border: 1px solid black; padding: 6px;">Visualizzazione Informazioni Fisse</td>
      <td style="border: 1px solid black; padding: 6px;">Il sistema deve mostrare nella pagina profilo le informazioni statiche del giocatore, includendo nome, foto/avatar e biografia.</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">RF2</td>
      <td style="border: 1px solid black; padding: 6px;">Selezione Contenuti Dinamici</td>
      <td style="border: 1px solid black; padding: 6px;">L’utente deve poter selezionare, tramite un selettore dedicato, la categoria di informazioni da visualizzare nella sezione dinamica del profilo.</td>
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">RF3</td>
      <td style="border: 1px solid black; padding: 6px;">Visualizzazione Achievement</td>
      <td style="border: 1px solid black; padding: 6px;">Il sistema deve integrare la visualizzazione degli achievement tra le opzioni selezionabili.</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">RF4</td>
      <td style="border: 1px solid black; padding: 6px;">Gestione Persistenza Profilo</td>
      <td style="border: 1px solid black; padding: 6px;">Il sistema deve consentire il salvataggio e il recupero di biografia, nickname e avatar tramite il microservizio T23.</td>
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">RF5</td>
      <td style="border: 1px solid black; padding: 6px;">Navigazione Modulare</td>
      <td style="border: 1px solid black; padding: 6px;">Il passaggio tra le sezioni del profilo deve avvenire senza ricaricare l’intera pagina.</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">RF6</td>
      <td style="border: 1px solid black; padding: 6px;">Visualizzazione Progressi Giocatore</td>
      <td style="border: 1px solid black; padding: 6px;">L’utente deve poter visualizzare livello, rango e barra XP.</td>
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">RF7</td>
      <td style="border: 1px solid black; padding: 6px;">Visualizzazione Partite Passate</td>
      <td style="border: 1px solid black; padding: 6px;">L’utente deve poter consultare lo storico delle partite con risultati, achievement e dati degli avversari.</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">RF8</td>
      <td style="border: 1px solid black; padding: 6px;">Gestione Social</td>
      <td style="border: 1px solid black; padding: 6px;">L’utente deve poter gestire follower/following, cercare giocatori e seguire o smettere di seguire utenti.</td>
    </tr>
  </tbody>
</table>


#### Requisiti Non Funzionali (RNF)
<table style="border-collapse: collapse; width: 100%; font-size: 12px;">
  <thead>
    <tr style="background-color: #e0e0e0;">
      <th style="border: 1px solid black; padding: 6px;">Codice</th>
      <th style="border: 1px solid black; padding: 6px;">Nome</th>
      <th style="border: 1px solid black; padding: 6px;">Descrizione</th>
    </tr>
  </thead>
  <tbody>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">RNF1</td>
      <td style="border: 1px solid black; padding: 6px;">Estensibilità</td>
      <td style="border: 1px solid black; padding: 6px;">L’architettura deve permettere l’aggiunta futura di nuove sezioni senza modificare la struttura principale.</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">RNF2</td>
      <td style="border: 1px solid black; padding: 6px;">Usabilità</td>
      <td style="border: 1px solid black; padding: 6px;">L’interfaccia deve evitare sovraffollamento informativo e garantire una navigazione intuitiva.</td>
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">RNF3</td>
      <td style="border: 1px solid black; padding: 6px;">Coerenza</td>
      <td style="border: 1px solid black; padding: 6px;">Lo stile della pagina profilo deve essere coerente con la pagina Achievement.</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">RNF4</td>
      <td style="border: 1px solid black; padding: 6px;">Manutenibilità</td>
      <td style="border: 1px solid black; padding: 6px;">I modelli dati del microservizio T23 devono rispettare le convenzioni esistenti.</td>
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">RNF5</td>
      <td style="border: 1px solid black; padding: 6px;">Prestazioni</td>
      <td style="border: 1px solid black; padding: 6px;">Il caricamento delle sezioni deve avvenire con latenza minima ottimizzando le chiamate API.</td>
    </tr>
  </tbody>
</table>

#### Use Case / Scenari / User Stories

##### Use Case / Scenari

* **Gestione Identità**  
  Il giocatore può gestire il proprio profilo tramite il caso d'uso *Modifica Profilo*, che include specificamente la *Modifica immagine profilo* e la *Modifica bio*.

* **Visualizzazione Progressi (Rank)**  
  All'interno del *RankContainer*, il sistema permette di:
  * Visualizzare la barra esperienza (con dettaglio sui punti mancanti)
  * Visualizzare il grado attuale con relativa icona
  * Consultare i dettagli di tutti i ranghi tramite un *Modal* informativo

* **Interazione Social**  
  L'utente può visualizzare le liste *Follows/Followed* ed effettuare ricerche tra i contatti.

* **Cronologia Partite**  
  Tramite il selettore, l'utente accede alla *Visualizzazione ultime partite giocate*.

##### User Stories

* **Bio e Avatar**  
  In qualità di *Giocatore*, voglio poter inserire una breve biografia, nickname e un'immagine del profilo affinché gli altri utenti possano identificarmi.

* **Sistema di Ranghi**  
  In qualità di *Giocatore*, voglio visualizzare il mio grado attuale e la barra di avanzamento per monitorare i miei progressi nel gioco.

* **Navigazione Dinamica**  
  In qualità di *Giocatore*, voglio usare un selettore per passare dalla visualizzazione delle mie partite alle informazioni social senza cambiare pagina.

![Figura 1: Use Case diagram](https://i.imgur.com/v7z6WqT.jpeg)  

*Figura 1: Use Case diagram che descrive le interazioni del Giocatore con i moduli Statistiche, Social, Partite e Profilo per la gestione completa della propria identità e dei progressi, mostrando come si siano affrontati i vari requisiti.*  



---

<table style="border-collapse: collapse; width: 100%; font-size: 12px;">
  <thead>
    <tr style="background-color: #e0e0e0;">
      <th style="border: 1px solid black; padding: 6px;">Categoria</th>
      <th style="border: 1px solid black; padding: 6px;">Modulo</th>
      <th style="border: 1px solid black; padding: 6px;">Modifica</th>
    </tr>
  </thead>
  <tbody>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">Aggiunti (Frontend)</td>
      <td style="border: 1px solid black; padding: 6px;">profileMain.js</td>
      <td style="border: 1px solid black; padding: 6px;">Punto di ingresso principale: gestisce l'inizializzazione dei listener e lo switch dinamico delle view (Social, Matches, Stats) senza ricaricare la pagina.</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">Aggiunti (Frontend)</td>
      <td style="border: 1px solid black; padding: 6px;">profileFixed.js</td>
      <td style="border: 1px solid black; padding: 6px;">implementa la logica di editing per Bio e Nickname con limiti di caratteri (20/200) e la selezione dell'avatar.</td>
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">Aggiunti (Frontend)</td>
      <td style="border: 1px solid black; padding: 6px;">stats.js</td>
      <td style="border: 1px solid black; padding: 6px;">gestisce la mappatura degli XP nei dieci Ranghi, l'aggiornamento della barra di progresso e il rendering degli achievement.</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">Aggiunti (Frontend)</td>
      <td style="border: 1px solid black; padding: 6px;">social.js</td>
      <td style="border: 1px solid black; padding: 6px;">gestisce le chiamate per follower/following, la logica di ricerca utenti e l'aggiornamento dello stato del follow.</td>
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">Aggiunti (Frontend)</td>
      <td style="border: 1px solid black; padding: 6px;">matches.js</td>
      <td style="border: 1px solid black; padding: 6px;">recupera la storia dei match, gestendo la paginazione lato client e la visualizzazione dei badge vittoria/sconfitta.</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">Aggiunti (Backend)</td>
      <td style="border: 1px solid black; padding: 6px;">GameProgressService (T23)</td>
      <td style="border: 1px solid black; padding: 6px;">implementa la logica per il recupero dello storico completo delle partite di un giocatore tramite getGameHistoryByPlayer. Utilizza il MapperFacade per restituire una lista di GameProgressDTO al microservizio T5.</td>
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">Modificati (Backend)</td>
      <td style="border: 1px solid black; padding: 6px;">UserProfileController (T5)</td>
      <td style="border: 1px solid black; padding: 6px;">aggiunte rotte utili per la logica della pagina profilo</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">Modificati (Backend)</td>
      <td style="border: 1px solid black; padding: 6px;">T23Service (T5)</td>
      <td style="border: 1px solid black; padding: 6px;">aggiunte servizi utili per la logica della pagina profilo</td>
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">Modificati (Backend)</td>
      <td style="border: 1px solid black; padding: 6px;">UserGameProgress (T5)</td>
      <td style="border: 1px solid black; padding: 6px;">aggiunto attributo "Achievements"</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">Modificati (Backend)</td>
      <td style="border: 1px solid black; padding: 6px;">UserSocialController (T23)</td>
      <td style="border: 1px solid black; padding: 6px;">aggiunte rotte per editare profilo e cercare profili utenti tramite termini di ricerca</td>
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">Modificati (Backend)</td>
      <td style="border: 1px solid black; padding: 6px;">UserSocialService (T23)</td>
      <td style="border: 1px solid black; padding: 6px;">aggiunti servici per editare profilo e cercare profili utenti tramite termini di ricerca</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">Rimossi dalla logica ma non dal progetto (Frontend)</td>
      <td style="border: 1px solid black; padding: 6px;">Achievements.html (T5)</td>
      <td style="border: 1px solid black; padding: 6px;">Vecchia pagina degli obiettivi; i contenuti sono ora integrati dinamicamente nel nuovo profilo.</td>
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">Rimossi dalla logica ma non dal progetto (Frontend)</td>
      <td style="border: 1px solid black; padding: 6px;">Profile-Edit.html (T5)</td>
      <td style="border: 1px solid black; padding: 6px;">Vecchia pagina di modifica; sostituita dalla modalità di editing "in-place" gestita da profileFixed.js</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">Rimossi dalla logica ma non dal progetto (Frontend)</td>
      <td style="border: 1px solid black; padding: 6px;">GameHistory.html (T5)</td>
      <td style="border: 1px solid black; padding: 6px;">Vecchia pagina dello storico partite; i contenuti sono ora integrati dinamicamente nel nuovo profilo.
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">Rimossi dalla logica ma non dal progetto (Backend)</td>
      <td style="border: 1px solid black; padding: 6px;">UserProfileController (T5)</td>
      <td style="border: 1px solid black; padding: 6px;">rotta per /Achievements deprecata.</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">Rimossi dalla logica ma non dal progetto (Backend)</td>
      <td style="border: 1px solid black; padding: 6px;">UserProfileController (T5)</td>
      <td style="border: 1px solid black; padding: 6px;">rotta per /Edit-Profile deprecata.</td>
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">Rimossi dalla logica ma non dal progetto (Backend)</td>
      <td style="border: 1px solid black; padding: 6px;">UserProfileController (T5)</td>
      <td style="border: 1px solid black; padding: 6px;">rotta per /GameHistory deprecata.</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">Da implementare (sviluppi futuri)</td>
      <td style="border: 1px solid black; padding: 6px;">Notifiche Profilo</td>
      <td style="border: 1px solid black; padding: 6px;">Da implementare le notifiche sulla pagine /profile.</td>
    </tr>
    <tr style="background-color: #ffffff;">
      <td style="border: 1px solid black; padding: 6px;">Da implementare (sviluppi futuri)</td>
      <td style="border: 1px solid black; padding: 6px;">Immagini Personalizzate</td>
      <td style="border: 1px solid black; padding: 6px;">La scelta degli avatar è limitata a una lista fissa; l'upload di file personalizzati è rimandato.</td>
    </tr>
    <tr style="background-color: #f5f5f5;">
      <td style="border: 1px solid black; padding: 6px;">Da implementare (sviluppi futuri)</td>
      <td style="border: 1px solid black; padding: 6px;">Chat tra utenti</td>
      <td style="border: 1px solid black; padding: 6px;">Chat tra utenti che si seguono ancora da implementare.</td>
    </tr>
  </tbody>
</table>

### 3. Progettazione della Soluzione

#### Decisioni architetturali adottate

* **Separazione delle Responsabilità (Separation of Concerns)**  
  È stata mantenuta una netta distinzione tra la logica di presentazione (gestita in T5) e la logica di persistenza dei dati. I dati anagrafici di base e le varie informazioni specialistiche (social e statistiche) sono stati centralizzati in T23, garantendo un’architettura disaccoppiata.

* **Design Modulare e Dinamico della UI**  
  Seguendo le linee guida del task, si è deciso di implementare un layout ibrido: una sezione fissa per l'identità (Avatar/Bio) e una sezione variabile basata su un selettore. Questa scelta riduce il sovraffollamento informativo e permette di aggiungere nuovi moduli in futuro senza modificare la struttura portante della pagina.

* **Integrazione tramite API Gateway**  
  Tutte le comunicazioni tra il front-end (T5) e i servizi di backend (T23) sono state intermediate dall'API Gateway. Questa scelta architetturale permette di astrarre gli endpoint reali dei microservizi, fornendo un punto unico di accesso e facilitando la gestione delle politiche di sicurezza e routing.

#### Microservizi coinvolti e modificati

* **T5**
* **T23**

#### Code Smells e Anti-Patterns

* (T23) Tutte le chiamate che includevano la gestione della classe GameProgress andavano al PlayerProgressService, risolto aggiungendo la classe GameProgressService
* (T5-T23) Alcuni nomi delle rotte in T5 non combaciavano con quelli in T23, risolto modificando appropriatamente i nomi in T5 (specificatamente in T23Service)
* (T5-T23) Alcuni tipi di ritorno non combaciavano tra T5 e T23, risolto aggiungendo in T5 le conversioni appropriate.


### 4. Implementazione e Architettura
Il sistema utilizza una **Layered Architecture** distribuita tra il microservizio **T5** (UI) e il microservizio **T23** (Persistenza), comunicanti tramite REST.

![Figura 2: Sequence diagram](https://i.imgur.com/SykbObJ.jpeg)  

*Figura 2: Sequence diagram che descrive le interazioni del Giocatore tramite l'UI con il backEnd, ogni iterazione segue un preciso protocollo*


* **T5**: Sostituiti i vecchi file `Profile.js/html/css` per supportare il nuovo design, seguendo il modulo standard ES6.
![Figura 3: Component diagram](https://i.imgur.com/Cq1SMZN.jpeg)  

*Figura 3: Component diagram che illustra l'architettura modulare del frontend in cui profileMain.js coordina i moduli subordinati per le chiamate asincrone verso il controller.*

* **T23**: Potenziato con `GameProgressService` per la gestione dello storico.

![Figura 4: Class diagram](https://i.imgur.com/8qilzxA.jpeg)  

*Figura 4: Class diagram che descrive la relazione tra cosa viene visto per ogni match nell'UI e le classi nel MySQL, a partire dall'id del giocatore si trovano i progressi, poi i game_progress e da li si ottengono le informazioni utili dall'opponent e dagli achievements*

* **Editing**: Implementata una macchina a stati per passare da "Neutral" a "EditState" tramite interazione dell'utente.

![Figura 5: State Machine diagram](https://i.imgur.com/npHuXKF.jpeg)  


---

## 5. Testing

### Tipologie di test effettuati

- User Interface Testing
- Static Analysis e Code Reading

### Risultati ottenuti

- Pagina Web funzionante per modifica profilo, visualizzazione progressi utente e rank, visualizzazione partite utente, achievements, visualizzazione Followers/Following e ricerca generica giocatori con toggle follow.

### Problemi rilevati e relative soluzioni

- Problemi grafici di interfaccia utente risolti modificando il file CSS.
- Problemi con il toggle follow (con il pulsante *segui* per utenti già seguiti, il codice rimuoveva l’utente dai seguiti). Risolto salvando una cache di follower dell’utente, facendo sì che il pulsante *segui* non uscisse per utenti già seguiti.
- Impossibilità ad accedere alla pagina profilo. Risolto collegando la chiamata `/profile` al nome e cognome del giocatore.
- Assenza di vincoli sulla lunghezza degli input utente (Nickname e Bio), con potenziale rischio di overflow nella UI. Risolta implementando vincoli lato client tramite attributo `maxlength` e validazione JavaScript.

## 6. Sviluppi Futuri e Raccomandazioni

### Possibili estensioni future del progetto e raccomandazioni per il mantenimento

- Aggiungere le notifiche alla pagina profilo.
- Implementare una chat tra utenti nella pagina.
- Permettere all’utente di scegliere immagini personalizzate come avatar.
- Permettere all’utente di visualizzare i profili degli altri giocatori.

## Appendice – Riferimenti

- **Link GitHub**: https://github.com/antoniocoppola0119-lgtm/A13/tree/main




