import java.util.*;

public class Circle {
	public static final double EPS = 1e-6;
	Point c;
	double r;
	ArrayList<Point> junctions;
	
	public Circle(Point c, double r) {
		this.c = c;
		this.r = r;
	}
	
	double arclen(double alpha) {
		return alpha * r;
	}
	
	double chordlen(double alpha) {
		return 2 * r * Math.sin(alpha / 2);
	}
	
	Point[] intersect(Circle other) {
		Point[] pts = new Point[2];
		
		Point p0 = this.c;
        Point p1 = other.c;
        
        if (p0.distSq(p1) >= (this.r + other.r) * (this.r + other.r))
        	return null;
        
        double d = p0.dist(p1);
        // Concentric circles
        if (Math.abs(d) < EPS)
        	return null;
        
        double a = (r * r - other.r * other.r + d * d)/(2*d);
        double h = Math.sqrt(r*r - a*a);
        Point P2 = p1.sub(p0).scale(a / d).add(p0);
        
        double x3 = P2.x + h*(p1.y - p0.y)/d;
        double y3 = P2.y - h*(p1.x - p0.x)/d;
        double x4 = P2.x - h*(p1.y - p0.y)/d;
        double y4 = P2.y + h*(p1.x - p0.x)/d;

        pts[0] = new Point(x3, y3);
        pts[1] = new Point(x4, y4);
        
		return !pts[0].nanpt() && !pts[1].nanpt() ? pts : null;
	}
	
	Point[] intersectLine(Line line) {
		Point[] pts = new Point[2];
		
		Point p0 = line.u.sub(c),
				p1 = line.v.sub(c);
		
		double dx = p1.x - p0.x;
		double dy = p1.y - p0.y;
		double dr2 = dx * dx + dy * dy;
		double sgny = dy < 0 ? -1 : 1;
		double D = p0.cross(p1);
		double delta = r * r * dr2 - D * D;
		
		if (delta < 0)
			return null;
		
		double rad = Math.sqrt(delta);
		double x3 = (D * dy + sgny * dx * rad) / dr2;
		double x4 = (D * dy - sgny * dx * rad) / dr2;
		double y3 = (-D * dx + Math.abs(dy) * rad) / dr2;
		double y4 = (-D * dx - Math.abs(dy) * rad) / dr2;
		
		pts[0] = new Point(x3, y3).add(this.c);
		pts[1] = new Point(x4, y4).add(this.c);
		
		return pts;
	}
	
	Point[] intersectLineSeg(Line line) {
		Point[] pts = this.intersectLine(line);
		
		if (pts != null) {
			pts[0] = this.contains(pts[0]) && line.pointOnSeg(pts[0]) ? pts[0] : null;
			pts[1] = this.contains(pts[1]) && line.pointOnSeg(pts[1]) ? pts[1] : null;
			
			return pts;
		}
		else
			return null;
	}
	
	double angleOfPoint(Point p) {
		Point delta = p.sub(c);
		double alpha = Math.atan2(delta.y, delta.x);
		
		return alpha < 0 ? alpha + 2 * Math.PI : alpha;
	}
	
	double angleBetweenPoints(Point a, Point b) {
		double alpha1 = angleOfPoint(a);
		double alpha2 = angleOfPoint(b);
		
		double diff = Math.abs(alpha1 - alpha2);
		if (diff > Math.PI)
			diff = 2 * Math.PI - diff;
		return diff;
	}
	
	double circleDistance(Point p0, Point p1) {
		if (this.contains(p0) && this.contains(p1)) {
			double alpha = angleBetweenPoints(p0, p1);
			return this.arclen(alpha);
		} else {
			return Double.NaN;
		}
	}
	
	boolean contains(Point pt) {
		return Math.abs(c.distSq(pt) - r * r) < EPS;
	}
}
