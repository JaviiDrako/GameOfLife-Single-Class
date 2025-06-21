## Conways's Game of Life in Java

This is a simple Java program that implements Conway's Game of Life using a single class.  
It reads configuration parameters directly from command-line arguments (`String[] args`), without using `Scanner` or interactive input. The project is intended for 
beginners to practice core programming concepts such as arrays, loops (`while`, `for`), `switch` statements, and conditional logic.

---

### Description

Conway's Game of Life is a cellular automaton devised by mathematician John Horton Conway. 
It consists of a grid of cells that can be alive or dead, and their states evolve in discrete steps according to simple rules based on the neighbors of each cell.

This project implements the game with the following features:

- **Compact code:** The entire program is contained within a single Java class for ease of understanding and study.  
- **Imperative programming style:** Uses basic control structures to manage the game's evolution.  
- **Array manipulation:** The grid and cell states are represented and updated using arrays.

--- 

## 🚀 How to Run

The program is run from the **command line** and accepts arguments via the `String[] args` parameter in the `main` method. These arguments let you customize the initial configuration and behavior of the game.

There are **5 configurable parameters**:

---

### 🧱 Width
- **Description:** Number of columns in the game grid.  
- **Allowed values:** Any integer between `10` and `80`.

---

### 📏 Height
- **Description:** Number of rows in the game grid.  
- **Allowed values:** Any integer between `10` and `80`.

---

### 🔄 Generations
- **Description:** Number of generations to simulate. Each generation applies the Game of Life rules to every cell.
- **Allowed values:** Any positive integer.  
  - If set to `0`, the game will run infinitely.

---

### ⏱️ Speed
- **Description:** How fast generations are updated (in milliseconds).  
- **Allowed values:** Any integer between `250` and `1000`.

---

### 🌱 Population (Initial Seed)
- **Description:** The starting configuration of live and dead cells.  
- **Input format:** A string using:
  - `1` for alive cells, `0` for dead cells.
  - `#` to indicate a new row (you can use multiple to skip rows).
  - Use `"rnd"` to generate a random initial seed.

#### Example (6×6 grid):

101#111#0001#1##101
```
 ██ ░░ ██ ░░ ░░ ░░
 ██ ██ ██ ░░ ░░ ░░
 ░░ ░░ ░░ ██ ░░ ░░
 ██ ░░ ░░ ░░ ░░ ░░
 ░░ ░░ ░░ ░░ ░░ ░░
 ██ ░░ ██ ░░ ░░ ░░
```


---

### 🧭 Neighborhood Pattern

Each cell in the grid is evaluated according to the **Game of Life rules** to determine if it will live, die, or be born in the next generation. This evaluation depends on the number of **neighboring live cells**, based on a specified pattern.

#### 🔹 Game of Life Rules:
- A **live** cell with **2 or 3 neighbors** stays alive.
- A **dead** cell with **exactly 3 neighbors** becomes alive (birth).
- All other cells **die** or remain dead due to underpopulation or overpopulation.

There are **5 neighbor patterns**. The white squares (`██`) represent neighbors; the center (`░░`) is the current cell.

#### Pattern 1


 ```
 ░░ ██ ░░
 ██ ░░ ██
 ░░ ██ ░░
 ```

#### Pattern 2
 ```
 ██ ██ ░░
 ██ ░░ ██
 ░░ ██ ██
 ```

#### Pattern 3 (Default – Conway's Original)
 ```
 ██ ██ ██
 ██ ░░ ██
 ██ ██ ██
 ```

#### Pattern 4
 ```
 ██ ░░ ██
 ░░ ░░ ░░
 ██ ░░ ██
 ```

#### Pattern 5
 ```
 ██ ██ ██
 ░░ ░░ ░░
 ██ ██ ██
 ```

---

## 🕹️ Usage

To run the game, you first need to **compile** the `Gol.java` class using the following command:

```
javac Gol.java
```

Then, you can **execute the game** with:

```
java Gol arg1 arg2 arg3 arg4 ...
```


Each parameter is passed as a `key=value` pair. Here's what each one means:

| Argument      | Description                                           |
|---------------|-------------------------------------------------------|
| `w=value`     | Width of the grid (e.g., `w=60`)                      |
| `h=value`     | Height of the grid (e.g., `h=20`)                     |
| `g=value`     | Number of generations (e.g., `g=300`; use `0` for infinite) |
| `s=value`     | Speed in milliseconds between generations (e.g., `s=300`) |
| `p=seed`      | Initial population seed (`p=rnd` for random, or a custom seed string) |
| `n=value`     | Neighborhood pattern (1 to 5; see above for details)  |

---

### ✅ Example:



```
java Gol w=60 h=20 g=300 s=300 p=rnd n=3
```


This command starts the game with:
- A 60×20 grid
- 300 generations
- 300 ms per generation
- A random starting population
- Neighborhood pattern 3 (Conway’s default)

























