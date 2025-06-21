## Conways's Game of Life in Java

This is a simple Java class that runs Conway's Game of Life using only one Java class. 
It is a beginner project designed to practice programming logic, arrays, and control structures such as while, for, switch, and conditionals.

---

### Description

Conway's Game of Life is a cellular automaton devised by mathematician John Horton Conway. 
It consists of a grid of cells that can be alive or dead, and their states evolve in discrete steps according to simple rules based on the neighbors of each cell.

This project implements the game with the following features:

- **Compact code:** The entire program is contained within a single Java class for ease of understanding and study.  
- **Imperative programming style:** Uses basic control structures to manage the game's evolution.  
- **Array manipulation:** The grid and cell states are represented and updated using arrays.

--- 

### How to Run

The program is run from the command line and accepts arguments via the `String[] args` parameter in the `main` method. These arguments allow you to customize the initial configuration and behavior of the game.
There are 5 configurable parameters:

- Width: How many columns there will be on the game grid.
  
  You can set any integer valor between 10 and 80.
- Heigth: How many rows there will be on the game grid.

  You can set any valor between 10 and 80.
- Generations: How many times Game of life rules will be applied on every cell on the grid.

  You can set any integer valor and if you set 0, there will be infinite generations.
- Speed: How fast generations will change.

- You can set an integer valor between 250 and 1000, these are miliseconds.
- Population: First generation to start the game.

  You can type a string seed to set the fisrt generationon following these syntax:

  - 1 is an alive cell and 0 is a died cell.
  - Each # symbol is a row jump, you can use them together to jump as many rows as you want.
  - You can use rnd string for a random first generation seed.

#### Example (grid 6x6):

101#111#0001#1##101
```
 ██ ░░ ██ ░░ ░░ ░░
 ██ ██ ██ ░░ ░░ ░░
 ░░ ░░ ░░ ██ ░░ ░░
 ██ ░░ ░░ ░░ ░░ ░░
 ░░ ░░ ░░ ░░ ░░ ░░
 ██ ░░ ██ ░░ ░░ ░░
```

- Neighborhood: Pattern for applying Game of life rules on a cell.

  There are 5 patterns for cells to set:
    - 1:
 
      

### Usage















