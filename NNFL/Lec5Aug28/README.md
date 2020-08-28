# Lecture 5

- [Lecture 5](#lecture-5)
  - [Physical Meaning of Lagrange Multipliers](#physical-meaning-of-lagrange-multipliers)
  - [Inequality constraints](#inequality-constraints)
  - [Question](#question)
  - [Numerical Method : Unconstrained prob](#numerical-method--unconstrained-prob)
  - [Steepest Descent](#steepest-descent)
    - [Stopping Criteria](#stopping-criteria)
    - [Limitations](#limitations)
  - [Momentum method](#momentum-method)
  - [Variants](#variants)

- we saw in last class Lagrange thing
  - converted equality constrained case to inconstrained thing
  - from where that was coming(formulae?)

- assume f is a function of 2 variables x1, and x2

![dfx2x2](dfx1x2.png)

- now moving from x1,x2 to x1+dx1, x2+dx2

![gx1x2](gx1x2.png)

then we equate dx2/dx1 from both the equations

## Physical Meaning of Lagrange Multipliers

- a measure of how rigid constraints are
- say 2 constraints g1 and g2
- λ1, and λ2 are corresponding lagrange's
- then larger λ => rigid constraint

![phys](phys.png)

## Inequality constraints

![ineq](ineqconst.png)

- we add some slack variables
- Kuhn-tucker ne ineq constrained ko eq constrained me convert kardia

## Question

![ques](ques.png)

- 1 eqns, 3 unknowns
- underdetermined system
- prob is ![prob](prob.png)
- find lagrangian, and partial derivatives
- answer is ![ans](ans.png)
- on matlab ![matlab](onmatlab.png)

## Numerical Method : Unconstrained prob

![num](nummethod.png)

![dervn](derivation.png)

![drvn2](drvn2.png)

![dfds](dfds.png)

![stepasc](stepasc.png)

## Steepest Descent

![steps](steps.png)

### Stopping Criteria

![stop](stop.png)

![qans](qans.png)

- so, 0.23 is optimum step size

- ans = ![x2f](x2f.png)

![anex](anex.png)

![grph](grph.png)

- if I start from black point, I end up at another minimum, which is not optimal ![ansSec](ansSec.png)
- I gpt stuck at local minimum

### Limitations

![limits](limitations.png)

## Momentum method

![mom](momentum.png)

## Variants

![vars](variants.png)