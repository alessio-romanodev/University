### Sistemi Informatici

Processo aziendale -> Sequenza di attività aziendali volte alla realizzazione di un servizio o prodotto
Sistema informativo -> Insieme di informazioni gestite dai processi aziendali

Componenti sistema informativo
- Patrimonio di dati (db)
- Insieme di procedure (funzioni nel codice)
- Insieme di risorse umane (ruoli nel dl)
- Insieme di mezzi e strumenti (tool del db)

La produzione dell'informazione si basa su i seguenti step
- Acquisizione del dato -> Immagazzinamento del dato -> Elaborazione del dato -> Immagazzinamento dato elaborato -> Trasmissione verso l'utente finale

Si distinguono due tipi di informazioni
 **Informazioni Elementari** -> Si ricavano interpretando i dati di un'organizzazione (non hanno relazioni con altre informazioni)
 **Informazioni Complesse** -> Un insieme di informazioni correlate logicamente associando una opportuna semantica ai legami

### Base di dati
Una base di dati è l'insieme di informazioni associato a collezioni di dati:
- Dati correlati tra loro
- Dotati di un'opportuna descrizione

Una base di dati contiene un insieme di dati detti **metadati**. I metadati sono informazioni riguardanti la base di dati stessa, ad esempio il modo in cui vengono immagazzinati i dati.

I database moderni si basano sull'architettura ANSI-SPARC. Un'architettura a 3 livelli in cui ogni livello rappresenta un'astrazione del livello sottostante
- **Livello esterno (vista-logica)**: Il livello esterno, si occupa dell'interazione con  le applicazioni, personalizzando l'accesso alle informazioni per un singolo utente o per una classe di utenti
- **Livello concettuale (logico)**: Si occupa della descrizione di come sono organizzati i dati dal punto di vista logico e come quest'ultimi sono collegati tra loro
- **Livello interno (fisico)**: Si occupa di gestire la memorizzazione su disco fisico dei dati 
Mediante la definizione a più livelli si garantisce l'indipendenza sia logica che fisica dei dati.
- Indipendenza logica -> si applica attraverso il meccanismo delle viste
- Indipendenza fisica -> si applica attraverso lo schema logico

Un **Database Managment System (DBMS)** è un insieme di programmi che permette di:
- Definire (specificare tipi, strutture, e vincoli sui dati)
- Manipolare (inserire, cancellare, aggiornare recuperare i dati)
- Controllare (Controllare l'accesso ai dati garantendo protezione da guasti e accessi indesiderati)
I DBMS eseguono particolari programmi detti **Transazioni**
- Una transazione è l'èsecuzione di un programma utente in ambiente DBMS che costituisce sintatticamente e semanticamente un'unità atomica di modifiche "persistenti" fatte alla base di dati
- Una transazione termina in uno stato di "commit" o di "abort"
Nelle transazioni ci possono essere diversi tipi di operazioni
- **SELECT** -> operazione di interrogazione
- **INSERT** -> operazione di inserimento nel DB
- **UPDATE** -> operazione di modifica di un oggetto pre-esistente
- **DELETE** -> operazione di rimozione di un oggetto all'interno del DB

Un DBMS ha una serie di caratteristiche fondamentali:
- Controllo della ridondanza e della consistenza
- Condivisione tra utenti del DBMS
- Integrità dei dati
- Gestione efficiente delle operazioni
- Gestione della concorrenza
- Affidabilità
- Sicurezza

### Modello Relazionale
Dati n domini non necessariamente distini, una relazione *r* sui domini D_i è un sottoinsieme del prodotto cartesiano
- r \subseteq D_1 x D_2...x D_n
La relazione è vista come un insieme di ennuple ordinate
t = (v_1, v_2, ..., v_n)
Ciascuna n-upla nella terminologia relazionale è detta tupla. Il numero di elementi *n* componenti la tupla è detto **grado** della relazione.

La rappresentazione delle relazioni può avvenire tramite tabella, dove ogni riga rappresenta una tupla, ed ogni colonna l'elemento v_i appartenente al dominio D_i.
Nella costruzione di una tabella si definisce relazione *r* su uno schema di relazione *R(X)* una istanza di R(X)
-- Intestazione --
--   Istanza    --
--   Istanza    --
Per gestire l'assenza di informazione di un dato attributo si utilizza il valore speciale detto NULL

Un vincolo di integrità è una regola che ogni istanza di uno schema di relazione deve rispettare e costituisce uno dei 3 fondamenti per lo schema di una base di dati. 
- Il nome del DB
- Gli schemi relazionali R1(X1), R2(X2)
- un insieme IC di regole di integrità
Un istanza che soddisfa tutti i vincoli di integrità di una base di dati si definisce istanza legale della base di dati. La verifica delle istanze è compito del DBMS
Ci sono diversi tipi di vincoli
- Vincoli Intrarelazionali
    - Vincoli di dominio -> sui valori di un attributo
    - Vincoli di tupla -> su più attributi della tupla
    - Vincoli di chiave -> per l'identificazione univoca di una tupla
- Vincoli Inter-relazionali
    - consentono di verificare la validità dei valori degli attributi inseriti in una relazione per correlarla ad un'altra

Sia dato uno schema di relazione R(X) e sia SK un sottoinsieme di attributi di X, diciamo che SK è una superchiave di una relazione r sullo schema R(X) se per ogni istanza è univoca, ossia vale
\forall t_i,t_J \in r, i != j -> t_i(SK) != t_j(SK)
Una sottoinsieme K  di attributi di X è chiave per r se è una superchiave minimale. 
Una chiave primaria è una chiave minimale univoca della tupla formata da un unico attributo.
Uno dei vincoli di integrità più importanti riguarda l'integrità dell'entità -> nessun valore di una chiave primaria può essere nullo

In un DB, è importante per evitare ridondanza dei dati ed eventuali inconsistenze, definire relazioni tra diverse tabelle. Abbiamo bisogno di un meccanismo relazionale che permetta di associare dati presenti in una tabella con quelli di un'altra tabella
Ciò avviene attraverso le **Chiavi Esterne**, si tratta di riferimenti a chiavi primarie di altre tabelle all'interno di una tabella

### SQL
Acronimo di Structured Query Language, comprende sia istruzioni per la definizone di dati DDL che per la loro manipolazione (DML)

-- CREATE TABLE
È usato per creare una nuova relazione (tabella), in esso si specificano
- Il nome della relazione
- Il nome e il tipo dei suoi attributi
- I vincoli intra e inter-relazionali
Il tipo degli attributi può essere
- Numeric 
- String
- Date:Time -> 10 posizioni aventi per componenti YEAR/MONTH/DAY mentre time ha 8 posizioni aventi come componenti HOUR/MINUTE/SECOND
I vincoli accettati da create table sono invece
- NOT NULL 
- UNIQUE
- primary_key
- foreign_key
La sintassi di una Create è la seguente
CREATE TABLE nomeTabella
(
    nomeAttributo Dominio [Default][Vincoli]
    {,nomeAttributo Dominio [Default][Vincoli]}
    [,altriVincoli]
)

-- CREATE USER
Crea un utente identificato da una password, serve per gestire la sicurezza del DBMS
CREATE USER username IDENTIFIED BY password
È possibile in oltre stabile privilegi per i vari utenti nel seguente modo, si ha una n-upla
P = <R,U1,U2,A,T>
In cui 
- R rappresenta la risorsa su cui si concede il privilegio
- U1 rappresetna l'utente che concede il privilegio
- U2 rappresenta l'utente che riceve il privilegio
- A rappresenta l'insieme delle azioni permesse
- T rappresenta l'autorizzazione concessa all'utente che riceve il privilegio di trasmettere lo stesso privilegio ad altri utenti
GRANT e REVOKE peremettono di garantire e revoare privilegi da un utente 
- GRANT privilegio1, ..., privilegioN ON NomeRisorsa TO username 













