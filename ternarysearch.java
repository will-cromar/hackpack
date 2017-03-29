import java.util.*;

public class ternarysearch {
	static double EPS = .1;

	static private double tern(double lo, double hi) {
		while (hi - lo > EPS) {
			double ft = lo + (hi - lo) / 3;
			double st = hi - (hi - lo) / 3;
			
	        if (f(ft) >= f(st))
	            hi = st;
	        else
	            lo = ft;
		}
		
		return lo;
	}

    static double f(double arg) {
        return arg;
    }
}