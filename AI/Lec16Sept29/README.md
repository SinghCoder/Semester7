# Lecture 16

- [Lecture 16](#lecture-16)
  - [video](#video)
  - [Uniform Cost search](#uniform-cost-search)
    - [Example](#example)
  - [Local Search Algos](#local-search-algos)
    - [Systematic vs Unsystematic Search](#systematic-vs-unsystematic-search)
    - [State-Space landscape](#state-space-landscape)

## video

[link](https://drive.google.com/file/d/1kn0Dg6QdHIzb8DIzaVkeJaxMq96env25/view)

## Uniform Cost search

![uc](uc.png)

### Example

![eg](eg.png)

- priority queuee rakhenge
- cost = distance form start node

![sol](soln.png)

- Optimal Path = Start -> A -> E -> F -> D -> Goal

![e](e2.png)

- we can have multiple start states as well
- here, we have to reach one of the goal state
- If I get a tie (same cost ke multiple nodes), then how to break the tie?
  - arbitrarily pick
  - alphabetical order
  - anyth u want

![s](1_soln.png)

- so ans is Start->d->C->G2 with cost = 13

![os](os.png)

## Local Search Algos

![ls](lsa.png)

- path to goal is irrelevant

### Systematic vs Unsystematic Search

![s](svus.png)

![us](us.png)

![ls](lsa2.png)

![ls](lseg.png)

- dual is easier to solve than primal

### State-Space landscape

![s](ls3.png)
