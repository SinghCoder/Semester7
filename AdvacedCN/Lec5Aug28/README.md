# Lecture 5

- [Lecture 5](#lecture-5)
  - [Video](#video)
  - [Agenda](#agenda)
  - [Slides](#slides)
  - [What is done so far](#what-is-done-so-far)
  - [Problems with current internet](#problems-with-current-internet)
  - [NEw Internet arch](#new-internet-arch)
  - [Key Research Topics for Future Internet Design](#key-research-topics-for-future-internet-design)
  - [Research projects on this](#research-projects-on-this)
  - [Mobility First Architecture](#mobility-first-architecture)
  - [Nebula](#nebula)
  - [Architectural Principles of NDN](#architectural-principles-of-ndn)
  - [Next LEcture reading](#next-lecture-reading)

## Video

[link](https://web.microsoftstream.com/video/f49a0426-b383-40b5-ba76-f07ab1c8d0fb)

## Agenda

- Future Internet Architecture Design Projects, [Paper link](https://drive.google.com/file/d/1SY5de80LUxTK2sAs72RZCzB89LFYnOr5/view?usp=sharing)

## Slides

[link](https://drive.google.com/file/d/1YGB0tVZ9hiDZ6ro-oed3dX8L4nYnvoz8/view?usp=sharing)

## What is done so far

![pw](pw.png)

## Problems with current internet

- security
  - control, mgmt and data planes are intermixed
  - control msgs are piggybacked with data packets
- mobility
  - identity and location in one (IP address) makes mobility complex
- energy
  - assumes live and awake end systems
  - communication can happen only when both ends are awake
- no explicit support for client-server traffic and distributed services
  - eg connecting to google

## NEw Internet arch

![new](newinternet.png)

## Key Research Topics for Future Internet Design

![res](restopics.png)

![rt2](rt2.png)

## Research projects on this

![pro](resprojs.png)

## Mobility First Architecture

![mob1st](mob1st.png)

## Nebula

![ne](nebula.png)

## Architectural Principles of NDN

![hour](hourglass.png)

- differences between IP and NDN
  - security is inbuilt in NDN
- IP is host centric, everything is around host (IP), IP is in center
- to access a service, u use URL which is a user defined name for IP address
- in NDN, there is no IP, everything is centered to contents only
  - my requirement is I need some content
  - in place of IP, I will have name of content
  - there is no need to give address, identification to a device
  - rest things are same
    - multiple applications
    - multiple types of connectivity
- NDN Project
  - current client server model facing challenges in supporting **secure content oriented** functionality
    - n/w is transparent and just f/wing the data
  - moving from end to end packet delivery from Content Centric Model
  - focusing on WHAT instead of WHERE (content vs address)
  - allows **content caching** on n/w side to optimize the traffic
  - content distribution is inherent in architecture

## Next LEcture reading

- [Named Data Networking (NDN) Project](https://drive.google.com/file/d/1HZeaXdReOL0t3EvRBm57iVUg_hegco65/view?usp=sharing)
