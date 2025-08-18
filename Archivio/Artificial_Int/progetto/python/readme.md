# Pathfinding: A\* vs Greedy Best-First

Confronto di due algoritmi per la ricerca informata su griglia 2D con ostacoli generati randomicamente, con possibilità di scegliere la funzione euristica e le dimensioni della griglia.

## Dipendenze

- Python 3.7+
- Nessuna libreria esterna: utilizza solo moduli della Standard Library
- File interni:
  - `utils.py` (funzioni ausiliarie)
  - `heuristics.py` (definizione di manhattan e euclidean)
  - `astar.py` (implementazione di A\*)
  - `greedy_best_first.py` (implementazione di Greedy Best-First)
  - `main.py` (entrypoint e TUI)

## Avvio

Dalla cartella "python"

```bash
python main.py
```
