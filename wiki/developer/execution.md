# Compilazione ed esecuzione del sistema
Il tool può essere avviato localmente oppure tramite container docker

## Docker
Ciascun microservizio è accompagnato da
- Un `Dockerfile`, che specifica le istruzioni per costruire l’immagine Docker (base image, dipendenze, file da copiare, comandi da eseguire, ...);
- Un `docker-compose`, che permette di definire e gestire più container Docker insieme, come parte di un’unica applicazione (ad esempio per i microservizi che comunicano con i database).

Ciascuna immagine è generabile e deployabile separatamente tramite riga di comando, ma è comunque consigliato l'utilizzo degli script forniti nella direcotry root del progetto, 
data la dipendenza del sistema da specifici volumi e reti docker. Nello specifico:
- Gli script `build.bat` / `build.sh` si occupano di generare l'immagine Docker di ciascun microservizio;
- Gli script `deploy.bat` / `deploy.sh` si occupando della generazione dei volumi e reti docker richieste dal sistema e del successivo deploy e avvio dei container Docker. 
Il deploy funziona in due modalità:
  1. Se sono presenti localmente le immagini, genererà i container a partire da quelle;
  2. Se non sono presenti, verrà scaricata l'ultima versione caricata di Dockerhub e utilizzata quella.
- Gli script `selective_build_and_deploy.bat` / `selective_build_and_deploy.sh` permettono di buildare, deployare e avviare microservizi specifi 

