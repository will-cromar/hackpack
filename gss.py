from __future__ import division
from math import sqrt

# Golden-section search
# Find the extremum of f in a range [a, b] in which 
# f is unimodal
phi = (1 + sqrt(5)) / 2
def gss(f, a, b, eps=1e-5):
	c = b - (b - a) / phi
	d = a + (b - a) / phi

	while abs(c - d) > eps:
		# Consider caching results of f
		if f(c) < f(d):
			b = d
		else:
			a = c

		c = b - (b - a) / phi
		d = a + (b - a) / phi

	return (b + a) / 2
