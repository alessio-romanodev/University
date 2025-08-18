import random


RESET = '\033[0m'
BLUE = '\033[34m'
RED = '\033[31m'
GREEN = '\033[32m'
YELLOW = '\033[33m'
GREY = '\033[90m'


def make_grid(width, height, obstacle_prob, seed=None):
    if seed is not None:
        random.seed(seed)
    return [[0 if random.random() > obstacle_prob else 1 for _ in range(width)] for _ in range(height)]


def reconstruct_path(came_from, current):
    path = [current]
    while current in came_from:
        current = came_from[current]
        path.append(current)
    return path[::-1]


def display_console(grid, path_a=None, path_g=None, start=None, goal=None):
    path_a_set = set(path_a) if path_a else set()
    path_g_set = set(path_g) if path_g else set()
    print(f"{BLUE}S{RESET}=start {RED}G{RESET}=goal {GREEN}·{RESET}=A* {YELLOW},{RESET}=Greedy {GREY}█{RESET}=ostacolo")
    for y, row in enumerate(grid):
        line = []
        for x, cell in enumerate(row):
            coord = (x, y)
            if coord == start:
                line.append(f"{BLUE}S{RESET}")
            elif coord == goal:
                line.append(f"{RED}G{RESET}")
            elif coord in path_a_set:
                line.append(f"{GREEN}·{RESET}")
            elif coord in path_g_set:
                line.append(f"{YELLOW},{RESET}")
            elif cell == 1:
                line.append(f"{GREY}█{RESET}")
            else:
                line.append("·")
        print("".join(line))
