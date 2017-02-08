import java.util.*;

public class Line {
	public static final double EPS = 1e-6;
	double a, b, c;
	Point u, v;
	ArrayList<Point> junctions;
	
	public Line(Point p1, Point p2) {
		u = p1;
		v = p2;
		
		if (Math.abs(p1.x - p2.x) < EPS) {
			this.a = 1;
			this.b = 0.0;
			this.c = -p1.x;
		} else {
			this.a = -(double) (p1.y - p2.y) / (p1.x - p2.x);
			this.b = 1.0;
			this.c = -(double) (this.a * p1.x) - p1.y;
		}
	}
	
	boolean parallel(Line l) {
		return Math.abs(this.a - l.a) < EPS &&
				Math.abs(this.b - l.b) < EPS;
	}
	
	boolean same(Line l) {
		return this.parallel(l) && Math.abs(this.c - l.c) < EPS;
	}
	
	Point intersect(Line l) {
		if (this.same(l)) return new Point();
		if (this.parallel(l)) return null;
		
		Point p = new Point();
		p.x = (l.b * this.c - this.b * l.c) /
				(l.a * this.b - this.a * l.b);
		
		if (Math.abs(this.b) > EPS)
			p.y = -(this.a * p.x + this.c);
		else
			p.y = -(l.a * p.x + l.c);
		
		return p;
	}
	
	Point intersectSeg(Line l) {
		Point p = this.intersect(l);

		if (p == null)
			return null;
		else if (p.nanpt() && this.segOverlap(l))
			return p;
		else if (!p.nanpt() && this.pointOnSeg(p) && l.pointOnSeg(p))
			return p;
		else
			return null;
	}
	
	boolean segOverlap(Line l) {
		return this.pointOnSeg(l.u) || this.pointOnSeg(l.v) ||
				l.pointOnSeg(u) || l.pointOnSeg(v);
	}
	
	boolean pointOnSeg(Point p) {
		return (u.distSq(p) + p.distSq(v)) - u.distSq(v) < EPS;
	}
	
	public String toString() {
		return String.format("Line through %s and %s", u.toString(), v.toString());
	}
}