import heapq
from utils import reconstruct_path

def greedy_best_first(grid, start, goal, heuristic, verbose=False):
    """
    Greedy Best-First Search con strutture dati canoniche:
      - open set come heap priority queue (heapq)
      - closed set come hash set
    """
    # open set: heap di tuple (h_score, nodo)
    open_heap = []
    start_h = heuristic(start, goal)
    heapq.heappush(open_heap, (start_h, start))

    came_from = {}       # mappa nodo → predecessore
    closed_set = set()   # nodi già espansi
    expansion = 0

    while open_heap:
        h, current = heapq.heappop(open_heap)
        if verbose:
            print(f"[Greedy] Espandendo nodo {current} con h={h}, espansioni={expansion}")
        expansion += 1

        # se abbiamo raggiunto il goal, ricostruiamo il percorso
        if current == goal:
            if verbose:
                print("[Greedy] Obiettivo raggiunto, percorso trovato.")
            return reconstruct_path(came_from, current), closed_set

        # se il nodo è già stato chiuso, lo saltiamo
        if current in closed_set:
            continue
        closed_set.add(current)

        x, y = current
        # esplora i quattro vicini ortogonali
        for dx, dy in [(-1,0),(1,0),(0,-1),(0,1)]:
            neigh = (x + dx, y + dy)
            nx, ny = neigh
            # verifica che sia dentro la griglia, non un ostacolo e non già chiuso
            if (0 <= ny < len(grid) and 0 <= nx < len(grid[0])
                    and grid[ny][nx] == 0
                    and neigh not in closed_set):
                came_from[neigh] = current
                heapq.heappush(open_heap, (heuristic(neigh, goal), neigh))

    # se l'open set si esaurisce senza raggiungere il goal
    if verbose:
        print("[Greedy] Nessun percorso trovato")
    return None, closed_set
