# Lecture 10

- [Lecture 10](#lecture-10)
  - [Video](#video)
  - [Performance](#performance)
  - [States vs Nodes](#states-vs-nodes)
  - [Informed Search](#informed-search)
    - [greedy BFS](#greedy-bfs)
    - [A* Search](#a-search)
  - [Admissible Heuristics](#admissible-heuristics)
  - [Consistent Heuristics](#consistent-heuristics)
  - [Optimality of A*](#optimality-of-a)
  - [Properties of A*](#properties-of-a)

## Video

[link](https://drive.google.com/file/d/1e3tkiEkJ5mZgQ_q8EBYApzGnVsdt3Lmo/view?usp=sharing)

## Performance

![perf](perf.png)

## States vs Nodes

![svn](svn.png)

- bfs me best case = 2nd level pe first node is my node
- dekhlio ki time me what r u considering, nodes generated or nodes expanded
  - generated me -b lunga, expanded me b

## Informed Search

### greedy BFS

![gbfs](gbfs.png)

- avoid repeating states

![pg](pgbfs.png)

- optimal nahi bcz all path dekhe hi nahi na

### A* Search

![as](as.png)

- cost already spent + estimated cost further
- basically total estimated cost ko 2 me tod dia
- Romania ka straight line distance wale pe A*
  - ![rom](romania.png)
  - Arad se start kia
  - initially fn = 366 = 0+366
  - ![as](as2.png)
  - I pick Sibiu
  - ![as](as3.png)
  - ![as](as4.png)
  - ![as](as5.png)

## Admissible Heuristics

![ah](aheu.png)

## Consistent Heuristics

![ch](che.png)

## Optimality of A*

![op](opt.png)

## Properties of A*

![props](prop.png)
