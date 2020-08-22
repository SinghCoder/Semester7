# Lecture 2

- [Lecture 2](#lecture-2)
  - [Video](#video)
  - [Summary](#summary)
  - [Topics of today](#topics-of-today)
  - [Why Distributed System](#why-distributed-system)
  - [Real time distributed system](#real-time-distributed-system)
  - [Process Models / Distribute computations](#process-models--distribute-computations)
  - [Network Topologies](#network-topologies)
  - [Synchronization](#synchronization)

## Video

[link](https://web.microsoftstream.com/video/ff0c657d-f008-42b2-99ae-646b03e08324)

## Summary

- criteria a distributed system specifies
  - multiple processes must be present
    - these should be sequential
    - each having independent thread of control
    - processes can be both - user or system processes
  - IPC - Inter Process Communication
    - processes should communicate with each other using msgs
    - msgs take some finite time depending upon channel (can be wired/ wireless)
  - Disjoint Address space
    - so shared memory multi-processor system ko we do not count as distributed system
  - Collective Goal
    - OLX - Auction eg

## Topics of today

- Introduction contd..

## Why Distributed System

- In real life, we mostly have **geographically distributed environments**
  - Banking me ATMs are geographically dispersed
- **Speedup**
  - Traditional uniprocessor models have reached already their speedup
  - parallel processing helps to improve it, but not much scalable
  - so shift to Distributed systems
  - can incrementally increase computing power by purchasing resources
- **Resource Sharing**
- **Fault Tolerance : TMR**
  - Graceful degradation

- Examples
  - Data intensive applications
  - Compute intensive applications
    - use client, server architecture
  - World wide web
  - social networks - fb, insta
  - inter-bank n/ws
  - peer-to-peer networks
    - internet telepony, content distribution, file distribution
    - **Napster** used it to distribute songs
      - still it is not a truly P2P bcz it used a *centralized directory*
    - **Gnutellr, Kazza** - avoided use of central directory
    - ***OCEAN STORE*** project
      - hosted at *University of California, Berkeley*
      - they built an online archiving mechanism on top of P2P, known as ***Tapestry***
    - Skype

## Real time distributed system

- Industrial plants use these extensively for their network of controllers which control their diff mechanical systems and machineries

## Process Models / Distribute computations

- model = an abstraction of real life thing
  - physical level details are exluded
- Common Knowledge of a process
  - no process has complete view of the DS
    - n/w topology
    - global state
  - processor will know
    - its own identity
    - its neighbors
    - size of the network
    - rest info it ill acquire over time by polling

## Network Topologies

- ring
- tree
- hypercube
- linear

## Synchronization

- clock issues
