from __future__ import division

# Efficient and stable-ish way to compute binomial coefs
# May want to do this in log space for extra stability
def binom(n, k):
    # Generator for inside of "pi"" expression
    vals = ((n - i) / (k - i) for i in range(0, k))

    # Multiply out values and return
    return reduce(mul, vals, 1)

# PDF for hypergeometric distribution
# Essential binomial distr. w/o replacement
# N : Population Size
# K : Number of success states
# n : Number of draws
# k : Number of observed successes
def HGpdf(N, K, n, k):
    return binom(K, k) * binom(N - K, n - k) / binom(N, n)