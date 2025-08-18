import heapq
from utils import reconstruct_path

def astar(grid, start, goal, heuristic, verbose=False):
    """
    A* search con strutture dati canoniche:
      - open set come heap priority queue (heapq)
      - closed set come hash set
    """
    # open set: heap di tuple (f_score, g_score, nodo)
    open_heap = []
    start_h = heuristic(start, goal)
    heapq.heappush(open_heap, (start_h, 0, start))

    came_from = {}            # mappa nodo → predecessore
    g_score = {start: 0}      # costo dal start a ogni nodo
    closed_set = set()        # nodi già espansi
    expansion = 0

    while open_heap:
        f, g, current = heapq.heappop(open_heap)
        if verbose:
            print(f"[A*] Espandendo nodo {current} con g={g}, f={f}, espansioni={expansion}")
        expansion += 1

        # se abbiamo raggiunto il goal, ricostruiamo il percorso
        if current == goal:
            if verbose:
                print("[A*] Obiettivo raggiunto, percorso trovato.")
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
            # verifica che sia dentro la griglia e non un ostacolo
            if 0 <= ny < len(grid) and 0 <= nx < len(grid[0]) and grid[ny][nx] == 0:
                tentative_g = g + 1
                # se troviamo un cammino migliore verso neigh
                if tentative_g < g_score.get(neigh, float('inf')):
                    came_from[neigh] = current
                    g_score[neigh] = tentative_g
                    f_score = tentative_g + heuristic(neigh, goal)
                    heapq.heappush(open_heap, (f_score, tentative_g, neigh))

    # se l'open set si esaurisce senza raggiungere il goal
    if verbose:
        print("[A*] Nessun percorso trovato")
    return None, closed_set
