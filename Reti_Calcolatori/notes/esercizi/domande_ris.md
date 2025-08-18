1) Quante connessioni TCP vengono aperte, con HTTP in versione non persistente, nel caso di
trasferimento di un contenuto web costituito da una pagina base HTML, 6 immagini gif e 5 immagini
jpeg ?
NB: Si supponga che nella pagina base sia presente un "link" ad un'altra pagina
HTML, memorizzata in un server differente...
a) 12
b) 11
c) 11 non persistenti (per le immagini) ed una persistente (per la pagina base)
d) 13, così suddivise: 12 per la pagina base più le immagini; un'ulteriore
connessione per il riferimento ipertesuale alla risorsa esterna
e) 10
f) Nessuna delle precedenti risposte è esatta

Risposta A: In http versione non persistente si apre e si chiude una connessione per ogni risorsa

2) Si consideri l'algoritmo per il controllo di congestione del protocollo TCP nella sua versione "Reno".
Indicare il Valore della finestra di congestione all'istante 16 nelle seguenti ipotesi:
il valore della finestra di congestione all'atto della prima trasmissione (istante 0) è pari a 1
MSS (Maximum Segment Size)
il valore iniziale della soglia è posto a 8 MSS
si ha un time-out in seguito alla trasmissione dell'istante 5
si hanno tre ACK duplicati in seguito alla trasmissione dell'istante 12
la dimensione della finestra di ricezione e' infinita
a)1 MSS
b)11 MSS
c)8 MSS
d)7 MSS
e)12 MSS
f)3 MSS
g)5 MSS
h)9 MSS
i)13 MSS

Risposta D: La congestion window determina il numero massimo di segmenti inviabili senza attendere un ACK di rispota, e la soglia anche detta slow start threshold è un valore che separa le due fasi fondamentali di slow start e congestion avoidance. Le fasi sono Inizio, avvio di una connession cwnd = 1MSS (maximum segment size), crescita esponenziale di cwnd raddoppiando ad ogni RTT (se 1 -> 2 se 2->4 se 4->8), quando la cwnd supera il ssthresh il meccanismo passa a crescita lineare di 1 MSS per RTT (8->9, 9->10) fino ad un evento di perdita. Perdita per timeout implica ssthresh = cwnd/2 e cwnd = 1, 3 ACK duplicati ssthresh = cwnd/2 e cwnd = ssthresh + 3. In questo caso cwnd0 = 1 ssthresh0=8. La cwnd incrementa in slow start fino all'istante 3 raddoppiando quindi cwnd4 = 8, poi in congestion avoidance cwnd4= 9 all'istante 5 si ha il timeout dopo l'incremento quidni cwnd5 = 10, evento di timeout ssthresh = cwnd/2 = 5 cwnd6 = 1 slow start fino all'istante 8 cwnd8=4 poi congestion avoidance fino all'istante 11 cwind11=7, all'istante 12 triplo ack duplicato quindi cwnd=8 ssthresh=cwnd/2=4 cwnd=ssthresh+3=7 all'istante 13 cwnd = 4 perchè 7 + grande della ssthresh, congestion avoidance fino all'istante 16 cwnd16 = 7

3) In TCP, come si garantisce che connessioni multiple su uno stesso canale condividano in maniera
equa la banda totale?
a)Non vi è alcuna garanzia. Come ottenere la condivisione equa della banda è ancora oggetto di
studio.
b)L'equità è garantita implicitamente dal meccanismo di controllo di congestione.
c)Si fa in modo che il mittente che per primo inizia la trasmissione si accorga della entrata di
un'altra connessione e riduca immediatamente il suo rate di trasmissione (meccanismo passivo).
d)Si fa in modo che un mittente che inizia a trasmettere i suoi dati chieda agli altri mittenti che
occupano il canale di ridurre il loro rate di trasmissione (meccanismo attivo).

Risposta C: i meccanismi di congestion avoidance gestiscono la banda totale

4) Con la tecnica CSMA/CA
a)Si introduce un messaggio di richiesta di trasmissione (Request To Send) per regolare la fase di
accesso al canale
b)Si introduce un messaggio di richiesta di trasmissione (Request To Send) per regolare la fase di
accesso al canale dopo una prima collisione
c)Non e' piu' necessario attendere un tempo pari allo Short Inter Frame Spacing (SIFS) prima di
rispondere ad un messaggio proveniente dalla controparte
d)Si introduce un messaggio di richiesta di trasmissione (Request To Send), eliminando la necessita'
di utilizzare il Network Allocation Vector

Risposta A:  In CSMA/CA, si introucono i messaggi Request to send per l'accesso al canale e il Clear to send per indicare agli altri nodi di astenersi dalla trasmissione per il periodo indicato nel campo duration

Con un codice a ridondanza ciclica che impieghi un "generatore" di 6 bit e' possibile
a)Correggere tutti gli errori che coinvolgono al massimo 5 bit
b)Rilevare tutti gli errori che coinvolgono al massimo 6 bit
c)Rilevare tutti gli errori che coinvolgono al massimo 5 bit
d)Rilevare e correggere tutti gli errori che coinvolgono al piu' 6 bit

Rispsta C: Il crc non corregge, si limita a rilevare tutti gli errori che coinvolgono al massimo r bit. Dato che il generatore è lungo r+1 bit dove r è il grado del polinomio, essendo G = 6 r = 5 dunque rileva errori fino a 5 bit

6) Un bridge che fa auto-apprendimento
a)Per ogni frame ricevuta, memorizza l'interfaccia di ingresso della frame, il MAC address sorgente
in essa contenuto ed il tempo attuale
b)Per ogni frame ricevuta, memorizza l'interfaccia di ingresso della frame ed il MAC address
destinazione in essa contenuto
c)Per ogni frame ricevuta, memorizza l'interfaccia di ingresso della frame ed il MAC address
sorgente in essa contenuto
d)Per ogni frame ricevuta, memorizza l'interfaccia di uscita selezionata per la frame ed il MAC
address destinazione in essa contenuto

Risposta A

7) Dato l'indirizzo IP di classe C 194.33.28.0, nel caso in cui si applichi unatecnica di subnetting con
netmask 255.255.255.240, quali dei seguenti sono indirizzi validi, rispettivamente, per indicare una
particolare sottorete ed il relativo indirizzo di broadcast?
a) 194.33.28.96 (sottorete) e 194.33.28.255 (broadcast)
b) 194.33.28.128 (sottorete) e 194.33.28.240 (broadcast)
c) 194.33.28.96 (sottorete) e 194.33.28.111 (broadcast)
d) 194.33.28.12 (sottorete) e 194.33.28.15 (broadcast)
e) 194.33.28.128 (sottorete) e 194.33.28.255 (broadcast)
f) 194.33.28.0 (sottorete) e 194.33.28.255 (broadcast)

Risposta C: La subnet mask 255.255.255.11110000 ha 4 bit per gli host, e gli incrementi della rete sono dunque di 2^4 = 16. Gli ip delle sottoreti sono 194.33.28.0 -> 194.33.28.15 ... 194.33.28.96 -> 194.33.28.111

Si supponga che un bridge ad 8 porte riceva su una delle sue porte una frame destinata ad un
indirizzo MAC. Indicare come tale frame verrà trattata dal bridge supponendo che MAC non sia
presente nella sua tabella.
a) il bridge inoltra copie della frame sulle sue restanti 7 porte
b) il bridge scarta la frame
c) il bridge invoca il protocollo ARP per individuare la localizzazione del destinatario
d) il bridge inoltra copie della frame sulle tutte le sue 8 porte
e) il bridge segnala un errore al mittente della frame
f) il bridge invia la frame esclusivamente sulla porta sulla quale è connessa la scheda di rete
avente indirizzo MAC

Risposta A: il bridge inoltra sulle restanti porte la trasmissione in broadcast

La commutazione di messaggio (commutazione di pacchetto senza frammentazione)
a) È meno efficiente della commutazione di pacchetto (con i messaggi frammentati), perché
sfrutta tecniche di trasmissione meno avanzate (tipo time division multiplexing)
b) È in generale più efficiente della commutazione di pacchetto (con i messaggi frammentati),
perché sfrutta l’effetto del pipelining per la trasmissione dei messaggi
c) È sempre più efficiente della commutazione di pacchetto (con i messaggi frammentati),
anche in caso di messaggi di grandi dimensioni, perché aggiunge meno overhead dovuto all’header
d) Nessuna delle precedenti risposte è esatta

Risposta D: La riduzione di efficienza di una commutazione di messaggio rispetto alla frammentazione non è dovuta a tecniche di multiplexing, ma al fatto che non si può iniziare a inoltrare il messaggio prima di averlo ricevuto completamente (assenza di pipelining). Il pipelining appartiene alle reti a commutazione di pacchetto con frammentazione e dunque la b è falsa. La commutazione di pacchetto senza frammentazione è vero che introduce meno overhead, ma soffre del ritardo introdotto dal sistema store and forward

Quante delle seguenti affermazioni sono false, con riferimento al cosiddetto "problema della stazione
nascosta"?
Affermazione 1: Il problema si risolve facendo in modo che una stazione che ascolta il messaggio
CTS (Clear To Send) non interferisca con l'imminente trasmissione
Affermazione 2: Il problema si risolve facendo in modo che una stazione che ascolta il messaggio
RTS (Request To Send) non interferisca con l'imminente trasmissione
Affermazione 3: Il problema si risolve facendo in modo che una stazione che ha atteso un tempo
pari al Distributed Inter Frame Space (DIFS) sia l'unica a trasmettere
Affermazione 4: Il problema si risolve introducendo il meccanismo di richiesta esplicita di
associazione di una stazione ad un Access Point
a) Una sola affermazione è falsa
b) Due affermazioni sono false
c) Nessuna affermazione è falsa
d) Tre affermazioni sono false
e) Tutte le affermazioni sono false

Dato l'indirizzo di rete 150.175.0.0 si vuole operare una suddivisione in sottoreti tale da garantire
1000 host su ciascuna sottorete e da massimizzare il numero di sottoreti a disposizione. Quale dei
seguenti indirizzi appartiene ad una delle sottoreti derivanti da un tale subnetting?
a) 150.175.32.0
b) 150.175.33.0
c) 150.175.32.128
d) 150.175.18.0
e) Nessuna delle precedenti risposte è esatta

Risposta A: per avere 1000 host, abbiamo bisogno di log_2(1002) bit, log_2(1024) bit in quanto si va in multipli di byte. Questo implica che la rete ha una subnet mask /22, ossia 255.255.11111100.00000000. Le sottoreti sono identificate dal terzo ottetto con incrementi di 2^2 = 4. Dunque 150.175.0.0 -> 150.175.3.255. Procedendo con il ragionamento si evince che 150.175.32.0 individua una sottorete

Quante delle seguenti affermazioni sono vere, in relazione al tipo di informazioni di controllo gestite
dal protocollo FTP?
Affermazione 1: Informazioni dette out-of-band, perché spedite su di un canale UDP, piuttosto che
sul canale TCP associato ai dati
Affermazione 2: Informazioni dette in-band, perché spedite sul medesimo canale utilizzato per la
trasmissione dei dati
Affermazione 3: Informazioni out-of-band nel caso di connessioni persistenti, in-band nel caso di
connessioni non persistenti
Affermazione 4: Informazioni crittografate, appositamente concepite per salvaguardare la privacy
degli utenti della rete
a) Tre affermazioni sono vere
b) Due affermazioni sono vere
c) Una affermazione è vera
d) Nessuna affermazione è vera
e) Tutte le affermazioni sono vere

Risposta D: nessuna delle seguenti, le informazioni out of band sono quelle spedite sul canale di controllo, tuttavia sia quelle out of band che in band operano su UDP

1)Con il Binary Exponential Backoff
A-Dopo ogni collisione si incrementa di uno l'intervallo di slot tra cui scegliere per la ritrasmissione
B-Dopo la terza collisione consecutiva si aspetta, prima di ritrasmettere, un numero di slot scelto in maniera
casuale tra 0 e 7
C-Dopo ogni collisione si incrementa di due l'intervallo di slot tra cui scegliere per la ritrasmissione
D-Dopo l'undicesima collisione consecutiva si aspetta, prima di ritrasmettere, un numero di slot scleto in
maniera casuale tra 0 e 2047

Risposta B: nel meccanismo di Exponential Backoff dopo ogni collisione il nodo aumenta (in termini dell'esponente) il numero di slot tra cui scegliere un tempo di attesa casuale per la ritrasmissione. In pratica dopo la n-esima collisione il tempo di attesa viene scelto casualmente da un intervallo di slot compreso tra 0 e 2^n - 1 fino a un massimo di n=10. Facendo i calcoli dopo 3 collisioni si ha 2^3 - 1 = 7 

2)In TCP, come si garantisce che connessioni multiple su uno stesso canale condividano in maniera equa la
banda totale?
A-L'equità è garantita implicitamente dal meccanismo di controllo di congestione.
B-Si fa in modo che il mittente che per primo inizia la trasmissione si accorga della entrata di un'altra
connessione e riduca immediatamente il suo rate di trasmissione (meccanismo passivo).
C-Non vi è alcuna garanzia. Come ottenere la condivisione equa della banda è ancora oggetto di studio.
D-Si fa in modo che un mittente che inizia a trasmettere i suoi dati chieda agli altri mittenti che occupano il
canale di ridurre il loro rate di trasmissione (meccanismo attivo).
E-Nessuna delle precedenti

Risposta A: analoga ad una domanda precedente

4)Quante connessioni TCP vengono aperte, con HTTP in versione persistente, nel caso di trasferimento di un
contenuto web costituito da una pagina base HTML, 6 immagini gif e 5 immagini jpeg? NB: Si supponga che
nella pagina base sia presente un "link" ad un'altra pagina HTML, memorizzata in un server differente...
A-13, così suddivise: 12 per la pagina base più le immagini; un'ulteriore connessione per il riferimento
ipertesuale alla risorsa esterna
B-10
C-11
D-11 non persistenti (per le immagini) ed una persistente (per la pagina base)
E-12
F-Nessuna delle precedenti risposte è esatta

Risposta F: in versione persistente viene aperta un unica connessione TCP per gestire tutte le richieste

5)A cosa servono le estensioni MIME (Multipurpose Internet Mail Extensions)?
A-Ad utilizzare la posta elettronica anche per altri fini ("multipurpose"), quali l'Instant Messaging ed il Peer-
To-Peer
B-A definire nuovi campi dell'header di protocolli quali HTTP e SMTP, volti a descrivere in maniera
appropriata il contenuto (ad esempio, tipo e codifica impiegata) di un messaggio
C-Ad inviare tramite posta elettronica informazioni aggiuntive riguardanti l'agente della posta posseduto dal
client SMTP
D-A definire nuovi campi dell'header del protocollo SMTP, volti esclusivamente a gestire i cosiddetti messaggi
"multiparte" (contenenti, ad esempio, testo ed immagini)
E-Nessuna delle precedenti

Risposta D: le estensioni MIME definiscono nuovi campi nell'header per formati diversi dall ASCII, tuttavia si limitano al protocollo SMTP non sono incluse nel protocollo HTTP

Con la tecnica del "pipelining"
a) E' necessario "bufferizzare" alcuni dati nel mittente e/o nel destinatario
b) Non è necessario aumentare l'intervallo dei numeri di sequenza
c) Si puo' utilizzare solo l'approccio "Go Back N"
d) Tutte le precedenti affermazioni sono vere

Risposta A: Con il pipelining il mittente invia più frame senza attendere l'ACK per ciascuno, e per gestire correttamente i dati in volo è necessario avere dei buffer sia nel mittente (per memorizzare i frame non ancora riconosciuti) sia, in alcuni casi, nel destinatario (per gestire eventuali arrivi fuori ordine o per applicazioni che usano il selective repeat). Quindi questa affermazione è vera.

7. Il protocollo DVMRP (Distance Vector Multicast Routing Protocol)
a) Usa l'approccio "Truncated Reverse Path Forwarding" per realizzare l'instradamento di tipo
multicast
b) E' il più utilizzato protocollo per il routing multicast con approccio group-shared
c) In modalita' "densa" effettua allagamento totale della rete
d) E' un protocollo indipendente dal protocollo di instradamento unicast sottostante

Risposta C: In modalità “densa”, DVMRP inizia con un allagamento (flooding) totale del pacchetto in tutta la rete e successivamente, tramite messaggi di prune, elimina le ramificazioni dove non ci sono membri interessati al gruppo multicast. Quindi, l’affermazione (c) è corretta.

8. Il programma traceroute
a) E' tipicamente usato anche per analizzare le caratteristiche del collegamento tra due endpoint
della rete Internet
b) Utilizza il messaggio di errore “Time To Live Exceeded” del protocollo ICMP per scoprire
iterativamente i router presenti sul percorso tra sorgente e destinazione
c) Si arresta alla ricezione del messaggio ICMP "Echo Reply" da parte della destinazione
d) Tutte le precedenti risposte sono esatte

Risposta D: Il programma traceroute si utilizza per la diagnostica di rete e sfrutta il messaggio TTL e si arresta al messaggio ICMP "Echo reply" della destinazione

9. Il campo "Acknowledgement Number" dell'header del TCP
a) Ha senso solo se il flag ACK e' posto ad zero
b) Contiene il numero di sequenza del ricevitore di una porzione di dati trasmessa su di una
connessione TCP
c) Se si utilizza la tecnica del piggybacking, consente di inviare un riscontro insieme al "carico
utile" (dati) di un segmento
d) Tutte le precedenti affermazioni sono vere

Risposta C: ha senso solo se il flag ACK è posto ad 1, e contien il numero del prossimo byte che il ricevente (mittente dell'ACK) si aspetta di ricevere. La pratica di piggybacking implica che l'ACK sia inviato insieme al carico utile dunque la C è corretta

1. Con la trasmissione multicast in Internet
a) Chiunque può inviare dati ad un gruppo, a patto di rispettare il vincolo dell'impiego del
protocollo TCP
b) Chiunque può inviare dati ad un gruppo, a patto che utilizzi un indirizzo sorgente di classe D
c) Per inviare dati ad un gruppo, è necessario iscriversi ad esso tramite IGMP
d) Per inviare dati ad un gruppo è necessario supportare il routing multicast
e) Nessuna delle risposte precedenti è esatta

Risposta e: il protocollo multicast si basa su UDP, può ricevere dati da qualunque indirizzo sorgente e non implica l'iscrizione al gruppo per poter inviare dati ad un gruppo.

Con la commutazione di pacchetto
a) Ciascun flusso di dati è suddiviso in pacchetti gestiti dai nodi della rete con la tecnica nota
come "Store and Forward"
b) Ciascun flusso e' suddiviso in pacchetti recanti tutti il medesimo identificativo di circuito
virtuale
c) Ciascun flusso di dati e' suddiviso in pacchetti aventi tutti la medesima dimensione
d) Ciascun flusso di dati è sempre instradato lungo il medesimo percorso all'interno della rete
e) Nessuna delle precedenti risposte è esatta

Risposta A: i pacchetti sono gestiti in store e forward da parte dei nodi 

Quante delle seguenti affermazioni sono vere
Affermazione 1: Nelle reti a circuiti virtuali ogni pacchetto contiene il numero del circuito virtuale
Affermazione 2: Nelle reti a circuiti virtuali il circuito virtuale è sempre stabilito prima della
trasmissione dei dati
Affermazione 3: Nelle reti a circuiti virtuali i nodi devono conservare informazioni relative ai
circuiti che li attraversano
Affermazione 4: Nelle reti a circuiti virtuali pacchetti tra la stessa coppia sorgente-destinazione
possono seguire percorsi differenti

Risposta 3:
Affermazione 1:
Nelle reti a circuiti virtuali ogni pacchetto contiene un identificativo (VCID) che permette ai nodi di instradarlo lungo il circuito stabilito. (Vero)
Affermazione 2:
In una rete a circuiti virtuali il circuito viene stabilito prima della trasmissione dei dati (mediante un processo di signaling, come avviene nei circuiti virtuali commutati). (Vero)
Affermazione 3:
I nodi di una rete a circuiti virtuali devono mantenere informazioni (stato) relative ai circuiti che attraversano, per sapere come instradare i pacchetti in arrivo. (Vero)
Affermazione 4:
Per definizione, una volta stabilito il circuito virtuale, tutti i pacchetti appartenenti a quella connessione seguono lo stesso percorso; quindi, non possono seguire percorsi differenti. (Falso)

17. Quante delle seguenti affermazioni sono vere, in relazione al tipo di informazioni di
controllo gestite dal protocollo FTP?
Affermazione 1: Informazioni dette out-of-band, perché spedite su di un canale UDP, piuttosto che
sul canale TCP associato ai dati
Affermazione 2: Informazioni dette in-band, perché spedite sul medesimo canale utilizzato per la
trasmissione dei dati
Affermazione 3: Informazioni out-of-band nel caso di connessioni persistenti, in-band nel caso di
connessioni non persistenti
Affermazione 4: Informazioni crittografate, appositamente concepite per salvaguardare la privacy
degli utenti della rete
a) Tre affermazioni sono vere
b) Due affermazioni sono vere
c) Una affermazione è vera
d) Nessuna affermazione è vera
e) Tutte le affermazioni sono vere

Risposta E: analoga a domanda precedente

19. Come si divide la banda tra due grossi flussi, uno TCP ed uno UDP, che competono sullo
stesso canale?
a) Il flusso TCP acquisirà maggiori risorse grazie all'algoritmo di controllo di congestione che
provocherà delle perdite nei pacchetti del flusso UDP non affidabile.
b) Non è possibile fare alcuna previsione sulla ripartizione della banda tra i due flussi, a causa
dell'eterogeneità dei protocolli in gioco
c) Due flussi aventi protocolli di trasporto differenti non possono coesistere su uno stesso
canale.
d) La banda si equiripartisce (approssimativamente) tra i due flussi.
e) Nessuna delle precedenti affermazioni è vera

Risposta E: tecnicamente l'algoritmo di controllo di congestione di TCP riduce il throughput e dunque UDP continua a pieno regime, due flussi possono coesistere sullo stesso canale e la banda non viene equipartita

20.Come fa un server ad identificare le richieste provenienti da due diversi client in esecuzione sulla stessa
macchina?
a) si basa sul numero di porto sorgente, che sarà sicuramente diverso per i due client
b) si basa sul numero di porto sorgente, sia sul numero di porto destinazione, come richiesto dal
demultiplexing delle connessioni
c) si basa sull'inidirizzo IP, che sarà diverso per i due client( a differenza del numero di porto sorgente, che
potrebbe anche coincidere)
d) utilizza due porti di ricezione diversi, uno per il primo client ed uno per il secondo client
e) nessuna delle precedenti

Risposta A:

21.Nel protocollo DHCP
a) il pacchetto DHCP discover è inviato all'indirizzo IP del server DHCP, il quale fornirà all'host i dati per la
corretta configurazione dei parametri di rete, assegnandoglieli per un periodo di tempo denominato lease
time
b) il pacchetto DHCP discover è incapsulato direttamente in un datagramma IP, con il campo Protocol
opportuamente configurato pari al numero standard del protocollo DHCP
c) il pacchetto DHCP discover è inviato utilizzando, al livello trasporto, il protocollo UDP
d) il pacchetto DHCP discover è inviato utilizzando, al livello trasporto, il protocollo TCP, al fine di instaurare
una connessione permanente al server che garantisca una configurazione automatica degli host con i
parametri corretti
e) Nessuna delle precedenti

Risposta C: Il DHCP discover non viene inviato all'IP di un server DHCP ma trasmesso in broadcast attraverso UDP non datagramma IP. 

22.Una Content Delivery Network (CDN) ha l'obiettivo di
a) minimizzare lo spazio per la memorizzazione dei contenuti
b) facilitare l'aggiornamento dei contenuti da parte del provider
c) minimizzare il tempo di accesso alle risorse per l'utente finale
d) minimizzare la probabilità di accedere ad un contenuto non aggiornato
e) Nessuna delle precedenti risposte è esatta

Risposta C

.Quante delle seguenti affermazioni sono vere, con riferimento alle funzioni svolte dal livello Data Link?
Affermazione 1: Ha come scopo la trasmissione affidabile di frame di dati
Affermazione 2: Accetta come input frame di dati e li trasmette sequenzialmente
Affermazione 3: Verifica la presenza di eventuali errori di trasmissione, aggiungendo delle informazioni
aggiuntive di controllo
Affermazione 4: Può gestire meccanismi di correzione di errori tramite ritrasmissione

Risposta 3 affermazioni: riceve in input pacchetti dal protocollo di rete, non frame di dati

Il protocollo POP3
a)Usato in combinazione con il protocollo IMAP, consente di gestire cartelle di posta elettronica
b)Permette il collegamento al server di posta del mittente di un’e-mail, per prelevare i messaggi
inviati da quest’ultimo
c)Permette il collegamento al proprio server di posta per accedere alla posta in arrivo
d)Serve per gestire il servizio delle news

Risposta C: permette solo il download delle email

SMTP
a)È un protocollo di tipo pull (il server di posta del mittente “preleva” il messaggio dal server di
posta del destinatario)
b)È un protocollo sia di tipo push che di tipo pull, a seconda dello scenario di funzionamento
c)È un protocollo di tipo push (il server di posta del mittente “spinge” il messaggio verso il server di
posta del destinatario)
d)Usa lo stesso paradigma di interazione di http

Risposta C: SMTP permette di inviare il messaggio verso il server di posta del destinatario, il pull è gestito da POP3

Quanti, tra i seguenti protocolli, non possono prescindere dall'invio in broadcast di pacchetti per poter
funzionare correttamente?
* DHCP
* DNS
* ARP
* IGMP
* HTTP
* SMTP
a) 0
b) 1
c) 2
d) 3
e) 4
f) 5
g) 6

Risposta D: I protocolli che sfruttano richieste in Broadcast sono DHCP ARP e IGMP

Come viene utilizzata la stima del Round Trip Time (RTT) effettuata dal protocollo TCP durante il suo
funzionamento?
a) Per il corretto dimensionamento del timeout legato alla ricezione dei riscontri.
b) Per incrementare o decrementare il rate di trasmissione secondo un legame di diretta proporzionalità.
c) Per incrementare o decrementare il rate di trasmissione secondo un legame basato su media esponenziale
pesata.
d) TCP non effettua alcuna stima del RTT durante il suo funzionamento.

Risposta A

Quale tra i seguenti protocolli a finestra scorrevole (sliding-window) è più conveniente utilizzare su un canale
di trasmissione avente una latenza molto bassa e capacità ed affidabilità molto elevate?
a) Stop and wait
b) Selective repeat
c) Go Back-N
d) Indifferentemente uno dei tre

Risposta C: In stop and wait il mittente invia un solo pacchetto e aspetta l'ACK, se la latenza è bassa il tempo di attesa è minore ma si introduce overhead e non sfrutta bene la capacità del canale in quanto deve attendere l'ACK. Go Back N consente di inviare piu pacchetti senza attendere l'ACK per ciascuno di essi dunque è superiore. Selective Repeat è generalmente più efficiente ma più difficile da implementare in quanto ritrasmette solo i pacchetti persi invece di ritrasmettere l'intera finestra. Tuttavia in un canale altamente affidabile gli errori sono rari e il vantaggio di Selective Repeat è trascurabile

Con un algoritmo di instradamento di tipo link state:
a) ogni router invia in broadcast le informazioni presenti nella propria tabella di instradamento
b) ogni router invia ai propri vicini le informazioni relative ai percorsi inter-dominio ad esso
noti
c) ogni router contatta il proprio area border gateway per ricevere informazioni relative
all’instradamento al di fuori del proprio sistema autonomo
d) ogni router invia ai propri vicini le informazioni relative a tutti gli altri elementi della rete
e) Nessuna delle precedenti risposte è esatta

Risposta E: in link-state ogni router invia LSA (link state advertisment) per aggiornare i costi dei percorsi, non l'intera tabella di routing

15.A cosa serve il campo Max Response Time dell'header del protocollo IGMP?
a) E' usato per configurare in maniera esplicita il valore del timeout nelle trasmissioni multicast
b) E' usato dagli host della rete locale per "diluire", in maniera random, le risposte ad una query
IGMP (proveniente dal router del primo hop) su di un opportuno intervallo temporale, così da
migliorare la scalabilità del protocollo
c) E' usato per indicare un limite superiore al ritardo di trasmissione relativo ad una sessione
multicast su Internet
d) Nessuna delle precedenti risposte è esatta

Risposta B: Il campo Max Response Time dell'header IGMP è utilizzato per evitare congestione e migliorare la scalabilità del protocollo.
IGMP (Internet Group Management Protocol) è usato dai dispositivi su una rete locale per segnalare l'interesse a partecipare a un gruppo multicast.
Quando un router invia una Query IGMP agli host della rete locale, il valore del campo Max Response Time indica il tempo massimo entro cui gli host devono rispondere con un Membership Report.
    Per evitare congestione, ogni host attende un tempo casuale (random) prima di inviare la propria risposta.

Quante delle seguenti affermazioni sono vere, con riferimento ad una rete a
circuiti virtuali?
Affermazione 1: comporta un overhead per la creazione del circuito virtuale,
ma, rispetto ad una rete a datagrammi, risulta meno sensibile agli effetti dovuti
a guasti ai nodi
Affermazione 2: comporta un overhead per la creazione del circuito virtuale e,
rispetto ad una rete a datagrammi, aumenta la quantità di informazioni di stato
da mantenere
Affermazione 3: comporta un overhead per la creazione del circuito virtuale,
ma, rispetto ad una rete a datagrammi, semplifica le operazioni di
instradamento dei dati
Affermazione 4: è necessariamente realizzata in tecnologia ATM
a) Nessuna affermazione è vera
b) Due affermazioni sono vere
c) Tre affermazioni sono vere
d) Una sola affermazione è vera

Risposta B: Comporta overhead ed è piu sensibile ai guasti in quanto se un nodo non risponde bisogna ristabilire un circuito. tuttavia semplifica le operazioni di instradamento ed aumenta la quantita di informazioni da mantenere

l protocollo HTTP
a) è un protocollo stateful, in quanto sia il server che il client mantengono
informazioni relative ai messaggi precedentemente scambiati
b) nella versione persistente è un protocollo stateful, in quanto memorizza, dal
lato del server, informazioni relative ai client
c) è un protocollo stateless, in quanto né il server né il client mantengono
informazioni relative ai messaggi precedentemente scambiati
d) è un protocollo stateless, a patto che non si adoperino tecniche di caching
delle risorse

Risposta C:

Quante delle seguenti affermazioni sono vere, con riferimento al metodo HTTP HEAD?
Affermazione 1: E' simile al metodo GET, ma prevede, da parte del server, solo l'invio degli header relativi
alla risposta, senza alcun payload
Affermazione 2: Può essere usato per verificare l'accessibilità di una risorsa web
Affermazione 3: Serve per inviare al server dati contenuti all'interno di una form HTML
Affermazione 4: Può essere utilizzato per verificare se l'header (HEAD) della richiesta è privo di errori
a) Una affermazione e' vera
b) Nessuna affermazione e' vera
c) Due affermazioni sono vere
d) Tre affermazioni sono vere
e) Tutte le affermazioni sono vere

Risposta C: Non prevede l'invio di payload e serve unicamente a verificare l'accessibilità di una risorsa web

14. Quale delle seguenti affermazioni è falsa?
a) Una cache del web svolge funzionalità tipiche di un server proxy
b) Una cache del web serve per ridurre sostanzialmente il traffico su un link di accesso ad
Internet
c) Una cache del web è contemporaneamente un server ed un client
d) Una cache del web può ridurre il tempo di risposta ad una richiesta del client
e) Nessuna affermazione è falsa

Risposta E: Sono tutte vere

26. Dove si inseriscono le informazioni relative ai cookie scambiati tra client e
server HTTP?
a) Nell’header della richiesta
b) Nell’header della risposta
c) Sia nell’header della risposta (inserito dal server), che in quello della richiesta (inserito dal
client)
d) In un file nascosto, automaticamente generato dal browser del client

Risposta C

Una richiesta ARP
a) Viene spedita da un host mittente ad un host destinatario per ottenere la corrispondenza
“indirizzo IP del destinatatio --> indirizzo MAC del destinatario”
b) Viaggia sempre all’interno di una frame Ethernet
c) Viaggia sempre in broadcast su di una rete locale e serve per risolvere l’indirizzo IP del
router di default
d) Si usa per risolvere l’indirizzo IP del router di default nel caso in cui il destinatario
appartenga alla medesima rete logica del mittente
e) Nessuna delle risposte precedenti è esatta

Risposta E: viene spedita in broadcast e si utilizza per ricavare l'indirizzo MAC di qualunque interfaccia sulla rete

Si consideri la trasmissione di un file di F = M\*L bit su di un percorso di Q link. Ciascun link trasmette ad una
velocità di R bit/s. Gli M\*L bit che compongono il file sono suddivisi in M pacchetti di L bit ciascuno. Ritardo
di
accodamento e ritardo di elaborazione nei nodi e ritardo di propagazione della rete sono trascurabili.
Quanto tempo richiede la spedizione del file dalla sorgente alla destinazione, nel caso si utilizzi una rete a
commutazione di pacchetto (servizio senza connessione), con header di livello rete di h bit?
a) [M*(L+h)]\R
b) [(Q+M-1)*(L+h)]\R
c) [Q*M*(L+h)]\R
d) Q*M*L\R + h

Risposta : Per Q si intende il numero di link tra sorgente e destinazione, per M il numero totale di pacchetti, per L la dimensione dei pacchetti, per R la velocità di trasmissione, per h la dimensione dell'header. Il tempo sarà dato dal numero di pacchetti trasmessi - 1 in quanto la trasmissione avviene in pipeline + il numero di nodi in cui vengono trasmessi moltiplicato per la dimensione dei pacchetti + l'header apposto tutto diviso per la velocità di trasmissioen

Quante delle seguenti affermazioni sono false, con riferimento alle reti "a datagrammi"?
Affermazione 1: Ogni pacchetto e' sempre costituito da un'intestazione (header) e dal payload V
Affermazione 2: La sorgente decide quale strada i pacchetti dovranno percorrere F
Affermazione 3: I nodi intermedi si occupano dell'instradamento di ogni singolo pacchetto V
Affermazione 4: Ogni nodo memorizza i pacchetti in ingresso, per poi instradarli verso il nodo successivo
(store & forward) v

Risposta 3

Quante delle seguenti affermazioni sono vere?
Affermazione 1: Il programma traceroute utilizza, tra l'altro, il messaggio di
errore “Time-To-Live Exceeded” del protocollo ICMP
Affermazione 2: Il programma traceroute è una versione avanzata del
programma ping, in cui il messaggio “echo request” è inviato tre volte di
seguito
Affermazione 3: Il programma traceroute manda un messaggio “echo request”
indirizzato ad ogni router lungo il percorso tra sorgente e destinazione
Affermazione 4: Il programma traceroute sfrutta opportunamente il campo TTL
dell’header del protocollo ICMP per scoprire iterativamente i router presenti sul
percorso tra sorgente e destinazione
a) Due affermazioni sono vere
b) Una sola affermazione è vera
c) Tre affermazioni sono vere
d) Nessuna affermazione è vera
e) Tutte le affermazioni sono vere

Risposta A: Il programma traceroute non invia echo request ma si basa sul TTL per identificare gli hop

Nelle reti di calcolatori, un'interfaccia
a) definisce i servizi offerti dal livello (n-1) al livello n
b) regola la comunicazione tra entità di pari livello esistenti in due dispositivi
della rete tra loro comunicanti
c) definisce i servizi offerti dal livello n al livello (n-1)
d) definisce i servizi offerti dallo strato rete allo strato applicazione

Risposta A

Il protocollo PIM
a) In modalita' "sparsa" utilizza la tecnica del tunneling
b) E' indipendente da qualsiasi altro protocollo multicast utilizzato nella rete
c) In modalita' "densa" utilizza un approccio simile al Truncated Reverse Path
Forwarding
d) E' basato sul protocollo unicast Distance Vector
e) Nessuna delle precedenti affermazioni e' vera

Risposta C: Il PIM non utilizza tunneling, ne è indipendente da qualsiasi altro protocollo multicast in quanto si appoggia ad uno qualsiasi tra OSPF RIP BGP per determinare le migliori rotte unicast, inoltre non si basa su Distance Vector ma può funzionare con qualsiasi protocollo di routing unicast. In modalità densa utilizza una strategia flood-and-prune simile a quella del truncated reverse path forwarding

8. Con il controllo di parita' a tre bit e' possibile
a) Rilevare errori su tre bit e correggere errori su due bit
b) Rilevare e correggere errori su tre bit
c) Rilevare errori su tre bit, e correggere errori su un solo bit
d) Nessuna delle precedenti risposte è esatta

Risposta C: uando si usa un controllo di parità a tre bit, si sta adottando un sistema che permette di: Rilevare fino a tre errori (cioè, se tre bit sono corrotti, l’errore viene individuato).
Correggere un solo errore (se un solo bit è errato, può essere identificato e corretto)

quale delle seguenti aff su udp è falsa
a) il suo header è inferiore a quello di tcp
b) non fa controllo di congestione
c) non ha nessun tipo di controllo di errore
d) tutte le precedenti sono vere

Risposta C: Il protocollo UDP fa la checksum

Si consideri l'interfaccia di programmazione (API) delle socket di Berkeley. Qual è la primitiva che un server
non orientato alla connessione certamente non invocherà?
a) socket()
b) bind()
c) writeto()
d) accept()
e) readfrom()
f) read()
g) Tutte le precedenti chiamate posso essere potenzialmente utilizzate

Risposta D: accept si utilizza per accettare una connesione

Cosa NON puo dipendere da un server proxy.
a)migliori prestazioni di una rete
b)osolescenza dei contenuti inviati al client
c)Minor dimesione di dati inviati al client
d)maggior velocita percepita dal client a ricevere i dati

Risposta B: perché l’obsolescenza dei contenuti non dipende direttamente dal server proxy, ma piuttosto dalla configurazione della cache e dalle politiche di aggiornamento dei contenuti.

3)Quanti sono tra questi i campi che non sono cambiati da un NAPT
-Porta sorgente
-Porta destinazione
-IP sorgente
-Ip destinazione
-Checksum
-Payload
-Protocollo

Risposta (Porta destinazione IP destinazione Payload Protocollo): NAPT (Network Address and Port Translation) è una variante della tradizionale NAT (Network Address Translation), in cui non solo l'indirizzo IP sorgente, ma anche la porta sorgente vengono modificate.

A cosa serve il three way handshacking in TCP.
a)A concordare la RcvWindow e la ...(non mi ricordo)
b)ad essere completamente sicuri che entrambi siano disponibili alla conessione
c)perche per inziare una connessione sono necessari 3 ACK duplicati
d)perche per chiudere una connessione sono necessari 3 ACK duplicati

Risposta B: La three way handshake serve ad instaurare una connessione

6)Un host H vuole contattare l'indirizzo unina.it.
Quante richieste verreano inviate sapendo che H è completamente indipendente dai server autoritative .it e
unina.it e che la cache sia vuota.
Nel caso di richiesta iterativa e ricorsiva.
a)6-8
b)7-8
c)6-6
d)8-8
e)8-7
f)8-6

Risposta b: 

9)Come fa un Client a conoscere la fine di una frame HTTP
a)Col messaggio <\HTML>
b)perche la connessione TCP viene interrotta
c)con una riga vuota
d)dal campo contenent lenght

Risposta D: Il campo content length indidua la fine del messaggio

Il livello sessione
a) è il livello 6 dello stack ISO/OSI
b) si occupa di sincronizzare lo scambio di dati tra due programmi applicativi
che utilizzano il protocollo di trasporto TCP
c) regola gli aspetti sintattici delle informazioni da trasferire
d) nessuna delle precedenti risposte

Risposta E: gestisce il controllo del dialogo tra le applicazioni sia in TCP che UDP

Il protocollo BGP
a) Consente di far funzionare il protocollo distance vector anche tra sistemi autonomi differenti
b) Consente lo scambio di informazioni di tipo burocratico-amministrativo tra sistemi autonomi differenti
c) Può essere utilizzato per realizzare un instradamento multicast del tipo source-based tree
d) Consente di estendere l’approccio link state al caso inter-dominio
e) Nessuna delle precedenti ripsoste è esatta

Risposta E: il MBGP si occupa del routing multicast e il BGP si basa su path vector dunque nessuna delle precedenti

11. Per poter effettuare una trasmissione dati, come fa un ipotetico host situato in
Italia a conoscere l'indirizzo MAC della sua entità paritaria situata a Cuba?
a) attraverso invocazioni iterate del protocollo ARP tra i vari hop che separano i due host
b) attraverso un'unica invocazione del protocollo ARP
c) ai fini di una trasmissione dati, non c'è alcun motivo per cui l'host in Italia debba conoscere
l'indirizzo MAC dell'host a Cuba
d) lo chiede direttamente all'host situato a Cuba attraverso un pacchetto di livello applicazione

Risposta C: il protocollo ARP viene utilizzato per trovare indirizzi MAC solo all'interno di una LAN

La cache ARP viene popolata
a) Con le informazioni relative al destinatario di una richiesta ARP, all’interno di tutti gli
elementi della rete che ricevono la richiesta stessa
b) Con le informazioni relative al mittente di una richiesta ARP, all’interno di tutti gli elementi
della rete che ricevono la richiesta stessa
c) Con le informazioni relative al destinatario di una richiesta ARP, all’interno di tutti gli
elementi della rete che ricevono la richiesta stessa, all’atto della ricezione del messaggio di risposta
inviato in broadcast sulla rete locale
d) Con le informazioni relative al router di default della rete, quando quest’ultimo risponde ad
una richiesta ARP inoltrata verso l’esterno

Risposta B: Ogni dispositivo che riceve una richiesta ARP in broadcast può salvare nella propria cache l'indirizzo IP e MAC del mittente della richiesta.
Questo perché se un dispositivo sta inviando una richiesta ARP, significa che è attivo sulla rete e il suo MAC-IP può essere utile in futuro.

Quale delle seguenti affermazioni è falsa?
a) I ripetitori operano soltanto al livello fisico
b) I bridge e gli switch di rete locale operano fino al livello Data Link
c) I router operano fino al livello rete
d) Nessuna affermazione è falsa
e) I bridge e gli switch di rete locale possono effettuare instradamento tra reti distribuite eterogenee
f) Tutte le affermazioni sono false

Risposta E: Bridge e switch NON fanno routing tra reti diverse.
Operano solo a livello 2 (Data Link) e gestiscono le comunicazioni all'interno della stessa LAN.
Per effettuare instradamento tra reti diverse e potenzialmente eterogenee, serve un router (livello 3).

5. Quante delle seguenti affermazioni sono false, in relazione all’algoritmo distance vector?
Affermazione 1: Non è possibile che si creino cicli di instradamento
Affermazione 2: E' un algoritmo di tipo iterativo, asincrono e distribuito
Affermazione 3: Non esiste il concetto di convergenza: lo scambio dei messaggi tra i router della
rete continua ad avvenire con scadenza periodica
Affermazione 4: È sempre possibile incorrere in situazioni di routing ciclico, anche se si adottano
tecniche quali il poisoned riverse
a) tutte le affermazioni sono false
b) una sola affermazione è falsa
c) tre affermazioni sono false
d) due affermazioni sono false

Risposta D

In una rete di calcolatori, i programmi applicativi
a) sono eseguiti nei router della rete e rispettano il paradigma client-server
b) sono eseguiti negli end-system della rete e rispettano sempre il paradigma
client-server
c) sono eseguiti negli end-system della rete ed usano sempre il protocollo di
trasporto TCP
d) sono eseguiti sia negli end-system della rete che nei router e rispettano il
paradigma client-server
e) Nessuna delle precedenti affermazioni è vera

Risposta E

Cosa si intende per “approccio dual stack” in IPv6?
a) Si fa riferimento alla scelta, all’interno dei router della rete, di supportare o il protocollo IPv4 o il
protocollo IPv6
b) Si fa riferimento alla possibilità di configurare un’interfaccia di un router con un indirizzo IPv6 ed un’altra
interfaccia con un indirizzo IPv4
c) Si fa riferimento alla possibilità di far accedere un host ad un’isola IPv6, tramite un tunnel IPv4
d) Si fa riferimento alla possibilità di far accedere un host ad un’isola IPv4, tramite un tunnel IPv6
e) Nessuna delle precedenti risposte è esatta

Risposta E: Dual stack vuol dire supportare entrambi i protocolli

68. Cosa si intende per “alias” del nome di un host di Internet?
a) Si usa per assegnare lo stesso nome a più host di Internet, così da bilanciare, tramite DNS, il carico
nell’accesso ad una particolare risorsa(che risulterà replicata su tutti gli host in oggetto)
b) Ad un’interfaccia di rete di un host si possono assegnare più indirizzi IP virtuali
c) Ad una macchina con un nome complicato può essere associato un “soprannome” più piccolo e semplice
da ricordare. Tale servizio è a carico del DNS
d) Ad un server diposta si può associare il nome di un dominio, per facilitare la memorizzazione dell’indirizzo
di posta elettronica degli utenti di quel dominio. Di ciò se ne occupa il DNS
e) Nessuna delle precedenti

Risposta C

La commutazione di pacchetto:
a)Adotta tecniche di multiplexing statistico per rendere efficiente la trasmissione
b)è una tecnica appropriata per trasmissioni di tipo real-time
c)adotta circuiti fisici o virtuali
d)nessuna delle precedenti affermazioni è vera

Risposta A

8. Nell'implementazione del TCP, in quale caso si raddoppia l'intervallo di
timeout?
a) In seguito ad un timeout, in occasione della ritrasmissione del segmento non ancora riscontrato ed avente
il più piccolo numero di sequenza
b) Quando il valore di EstimatedRTT diventa il doppio di DevRTT
c) Quando la finestra di congestione raggiunge il valore corrente della soglia
d) Quando si perdono tre segmenti consecutivi

Risposta A

Un protocollo di routing link state
a) Soffre del problema del conteggio all’infinito
b) Può subire oscillazioni nel caso in cui la funzione di costo adottata dipenda dal carico dei
link
c) Non è mai soggetto ad oscillazioni
d) Può subire oscillazioni nel caso in cui la funzione di costo adottata dipenda dal numero di
passi

Risposta B: Il protcollo utilizza Dijkstra dunque subisce oscillazioni nel caso in cui la funzione di costo dipenda dal carico dei link














