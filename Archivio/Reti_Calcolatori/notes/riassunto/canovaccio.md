1. Modello a Strati

    Definizione e motivazioni del modello a strati
    Indipendenza e interazione tra livelli
    Protocollo di comunicazione tra strati
    Modello OSI:
        7 livelli e le loro funzioni
        Differenze tra L1, L2, L3
        Vantaggi e svantaggi del modello

2. L3: Rete

    Packet Switching:
        Concetto di commutazione di pacchetto
        Store-and-forward nei router
        Code di pacchetti nei buffer di memoria
    Datagram:
        Pacchetti instradati in modo indipendente
        Percorsi differenti e ordini di arrivo diversi
    QoS (Quality of Service):
        Misurazione della qualità di rete (ritardi, throughput, perdita di pacchetti)
    Protocolli:
        IPv4 e la sua struttura (header, indirizzamento, frammentazione)

3. L4: Trasporto

    Multiplexing e Demultiplexing:
        Identificazione delle connessioni tramite numeri di porta
    UDP e TCP:
        Differenze tra protocolli orientati alla connessione (TCP) e senza connessione (UDP)
        Affidabilità e controllo del flusso
    Client-Server Model:
        Struttura di base della comunicazione tra client e server

4. HTTP

    Funzionamento e struttura del protocollo
    Metodi HTTP:
        GET, POST, PUT, HEAD: funzioni e differenze
    Risposte HTTP e codici di stato
    Header HTTP e la loro importanza
    Cookies e gestione della sessione
    Connessioni HTTP (persistenti e non)
    Web Caching e proxy
    Browser Web e il loro ruolo nell'interazione con HTTP

5. DNS

    Funzione del DNS e risoluzione dei nomi
    Struttura gerarchica del DNS
    Root Name Server, TLD, Authoritative Name Server
    Caching DNS e query iterative/ricorsive
    Tipologie di query DNS
    Ruolo del DNS nelle prestazioni di rete

6. FTP

    Struttura client-server di FTP
    Connessioni dati e controllo
    FTP attivo vs passivo
    Comandi principali (USER, PASS, LIST, GET, PUT)

7. SMTP e POP3

    SMTP:
        Funzionamento e interazione tra mail server e client
        MIME per la gestione degli allegati
    POP3:
        Funzionamento e limiti
        Differenze con IMAP

8. CDN

    Scopo e funzionamento delle Content Delivery Networks
    Benefici: riduzione della latenza, caching distribuito
    Gestione del carico su internet

9. P2P

    Modello peer-to-peer vs client-server
    Directory centralizzata vs decentralizzata
    Esempi di reti P2P

10. IPv4

    Struttura degli indirizzi IPv4
    Classi di indirizzi e subnetting
    Frammentazione dei pacchetti e ricomposizione
    Ruolo dei router e tabelle di routing

11. Protocollo ARP

    Scopo dell’ARP e risoluzione degli indirizzi MAC
    ARP Request e Reply
    Caching ARP e Gratuitous ARP
    Sicurezza e possibili attacchi ARP

12. ICMP

    Messaggi di errore e diagnostica (Echo Request, Destination Unreachable, Time Exceeded)
    Utilizzo di ICMP in strumenti come ping e traceroute
    Sicurezza e rischi legati a ICMP

13. NAT

    Scopo della Network Address Translation
    Funzionamento e traduzione degli indirizzi
    NAT statico, dinamico e PAT (Port Address Translation)

14. DHCP

    Funzione del DHCP e assegnazione dinamica degli IP
    Processo DORA (Discover, Offer, Request, Acknowledge)
    Vantaggi e svantaggi rispetto alla configurazione manuale

15. Routing

    Tipologie di routing: statico vs dinamico
    Fattori che influenzano la scelta del percorso
    Metriche di routing: banda, ritardo, hop count

16. Routing Distance Vector

    Concetto di Distance Vector Routing
        Ogni router mantiene una tabella con il costo per raggiungere ogni destinazione.
        Gli aggiornamenti delle tabelle vengono scambiati con i router adiacenti.
    Equazione di Bellman-Ford
        Algoritmo iterativo: ogni router aggiorna la tabella in base alle informazioni ricevute dai vicini.
    Velocità di Convergenza
        Il tempo necessario affinché tutti i router abbiano una visione coerente della rete.
        Problema principale: Count-to-Infinity (loop infiniti se un link cade).
    Poisoned Reverse
        Soluzione per evitare loop di routing:
            Se un router X usa il router Y per raggiungere una destinazione, X annuncia a Y un costo infinito per quella destinazione.

17. Link-State e Dijkstra

    Principio del Routing Link-State
        Ogni router costruisce una mappa completa della rete.
        I router comunicano tra loro tramite Link State Packets (LSP).
        LSP contiene:
            Identità del router mittente.
            Stato di ogni link (up/down).
            Costo dei collegamenti.
    LSP Flooding
        Gli LSP vengono distribuiti a tutti i router nella rete.
        Meccanismo di aggiornamento in caso di cambiamenti nella topologia.
    Gestione degli LSP
        Router scartano pacchetti duplicati e inoltrano solo nuove informazioni.
        Utilizzo di numeri di sequenza per identificare aggiornamenti più recenti.
    Algoritmo di Dijkstra
        Trova il percorso più breve da un nodo sorgente a tutti gli altri nodi.
        Funzionamento:
            Inizializzazione (distanza 0 a sé stesso, infinito agli altri).
            Espansione: si seleziona il nodo con il costo minore e si aggiornano i vicini.
            Ripetizione fino a coprire tutta la rete.

18. Protocollo OSPF

    OSPF (Open Shortest Path First)
        Protocollo Link-State usato in reti di grandi dimensioni.
        Divide la rete in aree per ridurre il traffico di aggiornamento.
    Tipi di router in OSPF
        Internal Router (IR): opera interamente dentro un’area.
        Area Border Router (ABR): collega più aree OSPF.
        Backbone Router (BR): appartiene all’area centrale (area 0).
        Autonomous System Boundary Router (ASBR): collega OSPF ad altri protocolli.
    Comunicazione tra router
        Uso di multicast su 224.0.0.5 e 224.0.0.6.
        Autenticazione dei pacchetti per sicurezza.

19. Routing gerarchico

    Evoluzione dell'architettura di Internet
        Da una singola backbone a una struttura gerarchica con più AS (Autonomous Systems).
    Tipologie di AS
        Stub AS: ha un solo collegamento con Internet.
        Multi-homed AS: ha più connessioni a diversi provider.
        Transit AS: trasporta traffico di altri AS.
    Protocolli di Routing
        Intra-domain routing (RIP, OSPF, IGRP): gestisce il traffico all’interno di un AS.
        Inter-domain routing (BGP): gestisce il traffico tra AS diversi.
    Relazioni tra AS
        Provider-customer: il provider offre connettività a un cliente.
        Peering: scambio di traffico gratuito tra due AS.

20. Multicast

    Concetto di Multicast
        Inviare un pacchetto a più destinatari contemporaneamente senza doverlo duplicare.
    Indirizzi Multicast IPv4
        Classe D (224.0.0.0 – 239.255.255.255).
        Mapping tra indirizzi IPv4 multicast e MAC address multicast.
    Ruolo dei Multicast Router
        Smistano i pacchetti multicast sulla rete.
    Protocolli Multicast
        IGMP (Internet Group Management Protocol): gestione dei membri di un gruppo multicast.
    Routing Multicast
        Group-Shared Tree: un solo albero per tutti i membri del gruppo.
        Source-Based Tree: un albero separato per ogni mittente.

21. MBone

    Multicast Backbone
        Rete virtuale basata su tunnel per trasmettere pacchetti multicast.

22. IPv6

    Necessità di IPv6
        Esaurimento degli indirizzi IPv4.
        Miglior supporto per QoS, sicurezza e autoconfigurazione.
    Struttura dell’Header IPv6
        Header principale di 40 byte + Extension Headers.
    Indirizzamento IPv6
        Global Unicast (2000::/3) → indirizzi pubblici.
        Link-Local (FE80::/10) → comunicazione locale.
        Multicast (FF00::/8) → indirizzi di gruppo.
    ICMPv6
        Router Advertisement e Neighbor Discovery.
    Tunneling IPv6 su IPv4
        Tecnica per garantire compatibilità tra i due protocolli.

23. Multimedia

    Tipologie di streaming
        Live Streaming: trasmissione in tempo reale.
        Pre-registrato: file scaricati o riprodotti progressivamente.
    Gestione del jitter e buffering
        Playout buffer per mantenere la sincronia.

24. RTP/RTCP

    RTP (Real-Time Transport Protocol)
        Protocollo per il trasporto di dati multimediali su UDP.
        Timestamp e Sequence Number per gestire l’ordine dei pacchetti.
    RTCP (Real-Time Control Protocol)
        Feedback sulla qualità del servizio.
        Controllo di congestione.

25. Trasmissione Affidabile

    Stop-and-Wait
        Il mittente aspetta un ACK prima di inviare il prossimo pacchetto.
    Pipelining
        Go-Back-N: ritrasmette tutti i pacchetti successivi a uno perso.
        Selective Repeat: ritrasmette solo i pacchetti effettivamente persi.

26. TCP

    Connessione affidabile e ordinata
        Three-way handshake (SYN, SYN-ACK, ACK).
        Chiusura della connessione (Four-way handshake).
    Congestion Control
        Slow Start: aumento esponenziale della velocità.
        Congestion Avoidance: crescita lineare.
        Fast Recovery: risposta rapida a perdite di pacchetti.

27. Ethernet

    Frame Ethernet e indirizzamento MAC
    CSMA/CD per la gestione delle collisioni
    Evoluzione degli standard Ethernet

28. Dispositivi LAN

    Hub vs Bridge vs Switch
    Switching e tabelle MAC

29. VLAN

    Separazione logica delle reti
    VLAN Trunking per collegare più switch

30. Crittografia

    Chiavi simmetriche vs asimmetriche (RSA)
    Firma digitale per garantire autenticità

31. Sicurezza

    Autenticazione e certificati digitali
    Protezione delle email (PGP, S/MIME)
