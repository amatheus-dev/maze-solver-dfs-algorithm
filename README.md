# Maze Solver (Depth-First-Search)

A console-based maze solver written in Java, built as a study project to practice 2D arrays, recursion and backtracking.

## Overview

The maze is represented as a 2D `int` matrix, where each value has a specific meaning:

| Value | Meaning        |
|-------|----------------|
| `0`   | Free path      |
| `1`   | Wall           |
| `2`   | Start          |
| `3`   | Exit           |

The program searches for a valid path from the start to the exit, exploring the grid one step at a time and backtracking whenever it hits a dead end.

## Project Phases

The project is structured incrementally, so each phase builds on the previous one:

1. **Modeling** — Represent the maze as a 2D matrix with the values described above.
2. **DFS with backtracking** — Recursive depth-first search that explores the four possible neighbors (up, right, down, left) from each cell, marking visited cells and backtracking automatically through the call stack when it reaches a dead end. 
3. **Visualization** — Print the discovered path in the console using the `*` symbol to show which cells it went and which it did not went, plus, it always shows where does it start `head` and where does it end `exit`.


## How It Works (DFS)

The core recursive method, `cellValidation(row, column)`, follows this logic for each cell:

1. Mark the current cell's position in the path-tracking structure.
2. Check if the current cell is the exit (`3`). If so, record its coordinates and return `true`.
3. Otherwise, compute the four neighboring coordinates (up, right, down, left).
4. For each neighbor, validate it using `isValid(row, column)`, which checks — in this order:
   - The coordinates are within the matrix bounds
   - The cell is not a wall
   - The cell has not been visited yet
5. For every valid neighbor, mark it as visited and recursively call `cellValidation` on it.
   - If that recursive call returns `true`, propagate `true` immediately (success found, stop exploring other neighbors).
   - If it returns `false`, try the next neighbor.
6. If none of the four neighbors lead to the exit, return `false`, letting the previous call in the recursion stack try its remaining options (backtracking).

Because Java's `&&` operator short-circuits, `isValid` always checks array bounds **before** accessing the matrix, preventing `ArrayIndexOutOfBoundsException`.

Visited cells are tracked in a separate `boolean[][] isVisited` matrix rather than overwriting the original maze values — this keeps the original maze data intact and makes the visited/unvisited state easier to reason about.

## Project Structure

```
Main.java        // Entry point — builds the maze, runs the search, prints results
Labirinto.java   // Maze logic: validation, DFS traversal, path visualization
```


The program will print:
- The original maze matrix
- Whether a path to the exit was found
- A visualization of the explored path
- The coordinates of the exit, if found

## Example Maze

```java
private static final int[][] labirinto = {
        {0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
        {1, 0, 1, 0, 1, 0, 1, 1, 1, 0},
        {2, 0, 0, 0, 0, 0, 1, 0, 0, 0},
        {0, 1, 1, 1, 1, 0, 1, 0, 1, 1},
        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        {1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
        {0, 1, 1, 1, 1, 1, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
        {1, 1, 1, 1, 1, 3, 1, 1, 1, 1}
};
```

## Goals of This Project

This project is primarily a learning exercise, focused on:

- Understanding recursion and how the call stack naturally implements backtracking
- Working hands-on with 2D arrays and coordinate-based traversal
- Building an intuition for graph search algorithms (DFS vs. BFS) without needing heavy theory up front
- Comparing traversal strategies (stack/LIFO vs queue/FIFO) and their practical trade-offs (shortest path guarantees, memory usage, performance)

