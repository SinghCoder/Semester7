# Lecture 9

- [Lecture 9](#lecture-9)
  - [Video](#video)
  - [PCA Maths](#pca-maths)
  - [PCA: Steps](#pca-steps)

## Video

[link](https://drive.google.com/file/d/1nISvDA82CZmMk7A9SYDLH0Xs7MZgNGJN/view)

- We use PCA to avoid Curse of Dimensionality
- entropy = measure of information, depends upon variability
- more the variance, btr, else no info
- say ht, wt data tha
- I rotate coordinate axes, how to assign some semantic now
- new x,y = x', y' is a linear combination of x, y
- we might not be able to attach some semantics to x', y'

## PCA Maths

![pca](pca1.png)

![pca](pca2.png)

- pca is interested only in direction, and not actual origin' value, so we shift and make vector pass through 0

![pca3](pca3.png)

![pca4](pca4.png)

![pca](pca5.png)

![math](math1.png)

![m](m2.png)

- covariance matrix is symmetrix

![m3](m3.png)

- column vector (3,2) is an eigen vector and 4 is an eigen value

![m4](m4.png)

## PCA: Steps

![s](pcasteps.png)

- eigen vector give me the best direction

![pc](pca_s1.png)

![pc](ps2.png)

![ps](ps3.png)

- mean adjust data
- get covariance matrix
- find eigen values and vectors
- arrange values from highest to lowest
- corresp vectors gice principal components
