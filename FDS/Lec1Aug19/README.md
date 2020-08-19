# Lecture 1

- [Lecture 1](#lecture-1)
  - [Video](#video)
  - [Topics to  be covered in course](#topics-to-be-covered-in-course)
  - [Data Science](#data-science)
    - [What it is](#what-it-is)
  - [High Dimensional Space and data](#high-dimensional-space-and-data)
  - [To Read topics](#to-read-topics)

## Video

[link](https://drive.google.com/file/d/15SWfmVgrjGsq0AZcN3W2nw4akCy-dAMF/view?usp=sharing)

## Topics to  be covered in course

- Optimization topics
- Probability
- Tensors
- Function estimation
- linear algebra

## Data Science

### What it is

- ds is all about what u can do with data u have
- data comes in many diff shapes and sizes, forms
- Concept of dimensionality should be understood
- Dimensionality
  - we percieve everyth in 3d
  - say student data hai, but only IDs hai, data is **1-dimensional**
  - age bhi daal di, **2-dimensional**
  - city bhi, **3-dimensional**
  - CGPA b, **4-dimensional**
  - ***Dimensionality = number of attributes/features that u have in the data***
- Q; plot data points which are 50-dimensional and all dimensions are integral values
  - take 2/3 dimensions and plot it
  - numbr of such combinations = 50C3
  - which 2/3/ which combinations to pick
  - ***So, given 50 dimensions can we order dimensions/ attributes in such a way that first is most important attribute and so on, and then we pick as many as reqd to plot, leaving rest***
    - say 1 attribute has same value for all data points - it is *useless*
    - so, **variability** is to be looked
    - say 2 attributes are *highly positively correlated*, I can remove one

## High Dimensional Space and data

- 50 attributes is high-dimensional
- we can plot high-dim data in *high-dim space*
- represent n-dim points as n-dimensional vector - **Mathematical understanding**
- **Physical Underatanding**
  - 1d = point on number line
  - 2d = scatter plot with 2 dimensions
  - 2+d = scatter plot again
- Say I had 1 dimension = points on number line = x axis
  - I add another dimension = y
    - I lose linearity property
  - I add z axis
    - I lose planar prop
  - *Space in which I had points is increasing with incr in dimensions, tho # of points remain same*
  - ***So, On increasing dimensions = sparseness of data increases***
- An image with 20x20, I see it as 2d
  - but comp sees it as 400 dimensional image

## To Read topics

- 1-gram, 2-gram, ... , n-gram models