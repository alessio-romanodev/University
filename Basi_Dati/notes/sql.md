### Syntax

*SELECT* - Permette di selezionare istanze da una tabella
*SELECT * FROM Customers*
Ne esiste una variante che seleziona le istanze distinte presenti in una tabella

*WHERE* - Permette di specificare una condizione per la SELECT
*
SELECT Name, Surname FROM Customers
WHERE Name = 'Peppe'    
*

*ORDER BY* - Permette di ordinare le istanze dei dati in base ad una condizione in ordine ascendente o discendente (asc default)

*IS NULL / IS NOT NULL* - Permette di verificare se un campo contiene o no dati

MIN()/MAX() - Funzioni che ritornano il valore massimo o minimo di un'istanza 
*
SELECT MIN(Price)
FROM Products p
*

COUNT() - Conta le istanze che rispettano un certo criterio
*
SELECT COUNT(*)
FROM Products p 
WHERE p.OnSale = 'true'
*

SUM() - Somma le colonne numeriche selezionate
*
SELECT SUM(column_name)
FROM table_name
WHERE condition;
*

IN - Permette di specificare più valori in una clausola WHERE
*
SELECT * FROM Customers
WHERE City IN ('Germany', 'France', 'UK')
*


