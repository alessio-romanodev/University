import time
from utils import make_grid, display_console
from heuristics import manhattan, euclidean
from astar import astar
from greedy_best_first import greedy_best_first


def choose_heuristic():
    while True:
        print("Scegli l'euristica:")
        print("  1) Manhattan")
        print("  2) Euclidea")
        choice = input("Inserisci 1 o 2: ").strip()
        if choice == "1":
            return manhattan, "Manhattan"
        elif choice == "2":
            return euclidean, "Euclidea"
        else:
            print("Scelta non valida, riprova.\n")


def choose_dimensions():
    try:
        w = int(input("Larghezza griglia (es. 50): ").strip())
        h = int(input("Altezza griglia (es. 15): ").strip())
        if w > 0 and h > 0:
            return w, h
        else:
            print("Dimensioni devono essere positive.\n")
            return choose_dimensions()
    except ValueError:
        print("Per favore inserisci numeri interi.\n")
        return choose_dimensions()


def main():
    width, height = choose_dimensions()
    heuristic_fn, heuristic_name = choose_heuristic()

    grid = make_grid(width, height, obstacle_prob=0.1)
    start = (0, 0)
    goal = (width - 1, height - 1)

    print("\nGriglia iniziale:")
    display_console(grid)

    print(f"\nEsecuzione A* Search (euristica {heuristic_name})...")
    t0 = time.time()
    path_a, visited_a = astar(grid, start, goal, heuristic_fn, verbose=True)
    time_a = time.time() - t0
    print("\nPercorso A*:", path_a)
    print("Lunghezza A*:", len(path_a) if path_a else None)

    print(
        f"\nEsecuzione Greedy Best-First Search (euristica {heuristic_name})...")
    t0 = time.time()
    path_g, visited_g = greedy_best_first(
        grid, start, goal, heuristic_fn, verbose=True)
    time_g = time.time() - t0
    print("\nPercorso Greedy:", path_g)
    print("Lunghezza Greedy:", len(path_g) if path_g else None)

    print("\n--- Confronto ---")
    print(f"Euristica usata:       {heuristic_name}")
    print(f"Dimensioni griglia:    {width}×{height}\n")

    length_a = len(path_a) if path_a else 0
    length_g = len(path_g) if path_g else 0
    nodes_a = len(visited_a)
    nodes_g = len(visited_g)
    print(
        f"Lunghezza percorso     A*: {length_a},  Greedy: {length_g},  Δ = {length_a - length_g}")
    print(
        f"Nodi visitati          A*: {nodes_a},  Greedy: {nodes_g},  Δ = {nodes_a - nodes_g}")
    print(
        f"Tempo di esecuzione     A*: {time_a:.4f}s,  Greedy: {time_g:.4f}s,  Δ = {time_a - time_g:+.4f}s")

    print("\nPercorsi su griglia:")
    display_console(grid, path_a, path_g, start, goal)


if __name__ == "__main__":
    main()
