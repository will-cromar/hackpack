"""Some functions related to binomial distribution"""
from __future__ import division
from math import lgamma, exp, log
from operator import mul

# More stable way to compute binomial coefs
# aka (N choose R)
def ncr(n, k):
    # Compute complement if it's easier
    if k > n - k:
        return binom(n, n - k)

    # Generator for inside of "pi"" expression
    vals = ((n - i) / (k - i) for i in range(0, k))

    # Multiply out values and return
    return reduce(mul, vals, 1)

# Quickly compute log of binomial coefficient
def lncr(n, r):
    return lgamma(n + 1) - lgamma(r + 1) - lgamma(n - r + 1)

# PDF for binomial distribution
def binompdf(n, k, p):
    return exp(lncr(n, k) + (k * log(p)) + ((n - k) * log(1 - p)))

# PDF for hypergeometric distribution
# Essentially binomial distr. w/o replacement
# N : Population Size
# K : Number of success states
# n : Number of draws
# k : Number of observed successes
def hgpdf(N, K, n, k):
    return exp(lncr(K, k) + lncr(N - K, n - k) - lncr(N, n))