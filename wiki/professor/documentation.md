# Professor's Guide
Nella sezione seguente, verranno descritte le funzionalità messe a disposizione e di interesse agli insegnati, seguite da una 
spiegazione passo passo sul loro utilizzo.
La versione attuale del sistema permette a un insegnante di insegnante:
1. Generare avversari per una data classe Java sotto test;
2. Caricare nel sistema classi sotto test e relativi avversari generati;
3. Accedere al volume Docker condiviso per visualizzare tutte le compilazioni (e relative metriche) effettuate dai giocatori, per ogni partita giocata.

# System Installation and Startup
Il tool può essere deployato sia direttamente sulla macchina host che tramite container Docker. Il deploy diretto è consigliato per 
lo sviluppo, mentre per tutte le altre casistiche è consigliato il deploy tramite docker. Di seguito, quindi, ci concentreremo su quest'ultimo.
TestingRobotChallenge mette a disposizione due gruppi di script, sia per macchine Linux che per macchine Windows:
- `build.bat` / `build.sh`: scripts to build and create Docker images for all microservices and components;
- `deploy.bat` / `deploy.sh`: scripts to deploy all containers, create necessary Docker volumes and networks, and start services. Local images will be used if available; otherwise, images from DockerHub will be pulled;

## Opponents generation
La generazione degli avversari è offerta dal servizio T0 ed è completamente indipendende dal resto del sistema. La generazione è effettuabile lanciando dalla macchina host gli script `generate.sh` for Linux systems (using `bash`)
o `generate.bat` per sistemi Windows. 



Attualmente il servizio permette di creare avversari tramite Randoop ed EvoSuite
