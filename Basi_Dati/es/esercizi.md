### Esercizio 1

SELECT s.Nome 
FROM SALE s 
WHERE s.Città = 'Pisa'

SELECT f.Titolo
FROM FILM f
WHERE f.Regista = 'Fellini' AND f.AnnoProduzione > 1960

SELECT f.Titolo, f.Durata
FROM FILM f
WHERE f.Genere = 'Fantascienza' AND (f.Nazionalità = 'Francese' OR f.Nazionalità = 'Giapponese') AND f.AnnoProduzione = 1990

SELECT f.Titolo
FROM FILM f
WHERE f.Genere = 'Fantascienza' and ((f.Nazionalità = 'Giapponese' AND f.Anno > 1990) OR f.Nazionalità = 'Francese')

SELECT f.Titolo
FROM FILM f
WHERE f.Regista = (
    SELECT f.Regista
    FROM FILM f
    WHERE f.Titolo = 'Casablanca'
)

SELECT DISTINCT f.Titolo, f.Genere
FROM FILM f, PROIEZIONI p
WHERE p.DataProiezione = 25/12/04 AND f.CodFilm = p.CodFilm

SELECT DISTINCT f.Titolo, f.Genere
FROM FILM f, PROIEZIONI p, SALE s
WHERE (p.DataProiezione = 25/12/04 AND s.Città = 'Napoli') 
AND p.CodFilm = f.CodFilm
AND p.CodSala = s.CodSala

SELECT DISTINCT s.Nome
FROM SALE s, PROIEZIONI p, FILM f, ATTORI a, RECITA r
WHERE (p.DataProiezione = 25/12/04 AND s.Città = 'Napoli'
AND a.Nome = 'R.Williams')
AND a.CodAttore = r.CodAttore AND r.CodFilm = p.CodFilm
AND d.CodFilm = p.CodFilm AND p.CodSala = f.CodSala

SELECT DISTINCT f.Titolo
FROM ATTORI a
JOIN RECITA r ON a.CodAttore = r.CodAttore
JOIN FILM f ON r.CodFilm = f.CodFilm
WHERE a.Nome = "M.Mastroianni" OR a.Nome = "S.Loren"

SELECT DISTINCT f.TITOLO
FROM ATTORI a
JOIN RECITA r ON a.CodAttore = r.CodAttore
JOIN FILM f ON r.CodFIlm = f.CodFilm
WHERE (a.Nome = 'M.Mastroianni' AND a.Nome = 'S.Loren')

SELECT f.Titolo, a.Nome
FROM ATTORI a, RECITA r, FILM f, 
WHERE (a.Nazionalità = 'Francese')
AND a.CodAttore = r.CodAttore
AND r.CodFilm = f.CodFilm

SELECT DISTINCT f.Titolo, s.Nome
FROM FILM f, SALE s, PROIEZIONI p
WHERE (s.Città = 'Pisa' AND p.DataProiezione 
BETWEEN 01/01/05 AND 31/01/05)
AND f.CodFilm = p.CodFilm
AND p.CodSala = s.CodSala

SELECT COUNT(*)
FROM SALE s
WHERE s.Città = 'Pisa' AND s.POSTI > 60

SELECT SUM(s.Posti)
FROM s.SALE
WHERE s.Città = 'Pisa'

SELECT s.Città, COUNT(*)
FROM SALE s
GROUP BY s.Città

SELECT s.Città, COUNT(*)
FROM SALE s
WHERE s.Posti > 60
GROUP BY s.Città

SELECT f.Regista, COUNT(*)
FROM FILM f
WHERE f.AnnoProduzione > 1990
GROUP BY f.Regista

SELECT f.Regista, SUM(p.Incasso)
FROM FILM f, PROIEZIONE p
WHERE f.CodFilm = p.CodFIlm
GROUP BY f.Regista

SELECT f.Titolo, COUNT(*) as NumeroProiezioni, SUM(p.Incasso) AS IncassoTotale
FROM FILM f, PROIEZIONI p, SALE s
WHERE f.Regista = 'S.Spielberg' AND s.Città = 'Pisa'
AND f.CodFilm = p.CodFilm
AND p.CodSala = s.CodSala
GROUP BY f.CodFilm, f.Titolo

SELECT COUNT(*) as FilmRegistaAttore
FROM FILM f, ATTORE a, RECITA r
WHERE a.CodAttore = r.CodAttore
AND r.CodFilm = f.CodFilm
GROUP BY f.Regista, a.Nome

SELECT f.Regista, f.Titolo
FROM FILM f, ATTORI a, RECITA r,
WHERE a.CodAttore = r.CodAttore
AND f.CodFilm = r.CodFilm
GROUP BY CodFilm, CodAttore
HAVING COUNT(CodAttore) < 6
