# Refactoring sistema - Pre Esperimento

## Modulo T1
**Problematiche:**
1. I controller Spring erano monolitici, gestendo più entità differenti e rendendo difficile la manutenzione, l’estensione e la comprensione del codice. La stessa problematica era presente nei Service.
2. La sezione dedicata agli avversari (robot) e alle classi UT presentava le seguenti criticità:
   1. Permetteva di caricare una classe UT e relativi test generati esclusivamente da EvoSuite o Randoop, non accettando altri tipi di avversari (es: test scritti da studenti). 
      Il caricamento, inoltre, non effettuava alcun tipo di controllo sul contenuto degli zip dei robot, non verificando, ad esempio, la presenza o meno dei file di copertura;
   2. Permetteva la generazione di test EvoSuite o Randoop per una determinata classe UT, ma con le seguenti problematiche:
      - La generazione era lenta, non restituiva informazioni in caso di errore ed era bloccante, impedendo all’amministratore di continuare ad usare l’interfaccia durante l’esecuzione;
      - Le metriche associate ai test generati non erano confrontabili: Randoop produceva metriche tramite JaCoCo (EclEmma), mentre EvoSuite produceva metriche interne.
        Questa eterogeneità rendeva impossibile un confronto oggettivo tra i test generati dai due tool;
   3. I test venivano salvati in due volumi Docker distinti.

**Modifiche principali:**
1. È stata effettuata una prima ripartizione degli endpoint e della logica di business in controller e service dedicati per ogni entità gestita;
2. Il sistema di generazione dei test è stato scorporato dal modulo T1** ed è stato creato il nuovo modulo autonomo T0, dove le operazioni di generazione vengono eseguite tramite script CLI dedicati richiamabili da terminale;
3. Il sistema di upload è stato completamente rivisitato:
   - Per ogni classe UT è ora caricabile un solo zip, che al suo interno contiene i tutti gli avversari associati, divisi per tipo e difficoltà. La struttura di questo zip è stata ben documentata e definita;
   - In assenza dei file di copertura di JaCoCo e/o EvoSuite risultino assenti, questi verranno generati automaticamente tramite richieste API ai moduli T7 e T8 prima del completamento del caricamento;
   - I precedenti volumi `VolumeT8` (EvoSuite) e `VolumeT9` (Randoop) sono stati consolidati in un unico volume `T0`, che conterrà tutti gli avversari generati, indipendentemente dalla loro origine o tipologia, organizzati in una struttura ben definita e documentata.

## Modulo T4
**Problematiche:**
In seguito all'evoluzione e al refactoring del sistema, è stato necessario aggiornare il modulo T4 per adeguarlo ai cambiamenti effettuati.

**Modifiche principali:**
1. L'entità rappresentante gli avversari (precedentemente robot) è stata aggiornata per:
   - Accettare anche avversari differenti da EvoSuite e Randoop;
   - Mantenere le metriche di copertura sia di JaCoCo che di EvoSuite, dove prima era mantenuta solo la copertura delle istruzioni restituita da JaCoCo.
2. Sono state aggiunte una serie di entità necessarie a implementare una prima versione dello stato del giocatore. Questo stato descriveva:
   - I punti esperienza ottenuti dall'utente;
   - Gli achievement definiti "globali" sbloccati dall'utente. Un achievement è considerato globale se non è legato ad una modalità di gioco;
   - Lo stato contro ciascun avversario affrontato, ovvero se è già stato battuto (necessario per evitare il riassegnamento di punti esperienza già guadagnati) e i relativi achievement sbloccati.

**Criticità:**
Il modulo era scritto in GO (in un ecosistema Spring Boot), rendendo difficile la manutenzione e l'evoluzione.

## Modulo T7 
**Problematiche:**
Durante il primo refactoring del modulo, l'attenzione si era concentrata sulla ristrutturazione interna del codice e sulla gestione della concorrenza. Tuttavia, non si era dato troppo pesa alla gestione del carico computazionale derivante da richieste multiple e concorrenti. In scenari di utilizzo reale, con più utenti attivi contemporaneamente, questo portava a rallentamenti, timeout e inefficienze.

**Modifiche principali:**
1. È stato introdotto un sistema basato su code di task e un thread pool executor per gestire in modo ordinato le richieste di compilazione in arrivo.
2. Ogni richiesta viene etichettata con un timestamp e inserita in una coda in attesa di essere elaborata.
3. Un controllo periodico verifica se una richiesta ha superato una soglia massima di attesa: in tal caso, viene rimossa dalla coda e il servizio chiamante (T5) viene notificato dell’impossibilità di elaborare la richiesta in tempi ragionevoli, suggerendo un nuovo tentativo.
4. Per ogni task accettato in esecuzione, viene attivato un timer interno di timeout: se la compilazione o l’analisi tramite JaCoCo non si concludono entro il tempo prestabilito, l’operazione viene interrotta in sicurezza, evitando blocchi o congestioni del sistema.
5. Terminata una compilazione, l’executor passa automaticamente al task successivo in coda, garantendo continuità nel servizio.

**Benefici:**
* Il modulo è ora in grado di gestire un numero maggiore di richieste concorrenti senza compromettere la stabilità del sistema;
* I tempi di risposta risultano più prevedibili, anche in condizioni di carico elevato;

---

## Modulo T8 
**Problematiche:**
La versione originaria del modulo presentava numerose criticità sia strutturali che architetturali:
1. Era realizzata in Node.js e JavaScript, in contrasto con l’ecosistema prevalente basato su Spring Boot;
2. Esponeva endpoint obsoleti o non più utilizzati, con URL e parametri query verbosi e poco intuitivi;
3. Eseguiva la compilazione direttamente nel volume Docker condiviso, introducendo rischi di conflitto e problemi di concorrenza;
4. L'immagine del container era particolarmente pesante, contenendo più copie di EvoSuite;
5. Nonostante la presenza dei file jar, i tempi di build erano comunque lunghi, poiché EvoSuite veniva riscaricato a ogni build;
6. Il calcolo delle metriche non interrompeva l’esecuzione in caso di errore, portando a spreco di risorse;
7. L’intera logica di calcolo era delegata a uno script `.sh` esterno, con gestione degli errori limitata.

**Modifiche apportate:**
Il modulo è stato completamente riscritto in Spring Boot, per uniformarlo all'architettura del sistema e migliorando diversi aspetti fondamentali:
1. Gli endpoint sono stati semplificati, limitandoli a due: uno per l'elaborazione del codice utente (chiamato da T5) e uno per quello dell’avversario (chiamato da T1);
2. La compilazione dei progetti Java avviene ora all'interno del container, in cartelle temporanee isolate e sicure, garantendo la corretta gestione concorrente;
3. L’immagine Docker è stata ottimizzata, riducendo la dimensione complessiva e migliorati sensibilmente i tempi di build;
4. Il calcolo delle metriche è stato modificato per interrompere l'esecuzione al primo errore critico, evitando computazioni inutili;
5. Le chiamate a EvoSuite sono ora eseguite tramite comandi bash hardcodate direttamente nel servizio Spring, eliminando la dipendenza da script esterni e semplificando il flusso di esecuzione.
6. Per la gestione del carico:
   1. È stato introdotto un sistema basato su code di task e un thread pool executor per gestire in modo ordinato le richieste di compilazione in arrivo.
   2. Ogni richiesta viene etichettata con un timestamp e inserita in una coda in attesa di essere elaborata.
   3. Un controllo periodico verifica se una richiesta ha superato una soglia massima di attesa: in tal caso, viene rimossa dalla coda e il servizio chiamante (T5) viene notificato dell’impossibilità di elaborare la richiesta in tempi ragionevoli, suggerendo un nuovo tentativo.
   4. Per ogni task accettato in esecuzione, viene attivato un timer interno di timeout: se la compilazione o l’analisi tramite JaCoCo non si concludono entro il tempo prestabilito, l’operazione viene interrotta in sicurezza, evitando blocchi o congestioni del sistema.
   5. Terminata una compilazione, l’executor passa automaticamente al task successivo in coda, garantendo continuità nel servizio.

**Risultati e benefici:**
1. Il modulo è ora più coerente, scalabile e manutenibile;
2. L’infrastruttura risulta più robusta in scenari concorrenti, anche sotto carico;
3. I tempi di risposta sono più prevedibili e stabili;

---

# Build and deploy
**Problematiche:**
- Build che del deploy del sistema erano effettuate da un unico script monolitico, che non forniva la possibilità di solo buildare o solo deployare il sistema;
- Solo la versione Windows dello script era aggiornata allo stato corrente del sistema, mentre quella Linux era rimasta indietro;

**Modifiche apportate:**
- Lo script monolitico è stato suddiviso in due script distinti, uno di build per la generazione e il tagging delle immagini dei container e uno di deploy per la creazione della rete Docker, dei volumi e il build dei container;
- I due script sono stati resi disponibili sia per Windows che per Linux.
