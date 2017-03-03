import java.util.*;

public class intersection {
	public static final double EPS = 1e-6;
	
	// Point 1.07 2.20 expected
	public static void main(String[] args) {
		Point p1 = new Point(0, 3);
		Point p2 = new Point(4, 0);
		Line l1 = new Line(p1, p2);
		
		p1 = new Point(1, 2);
		p2 = new Point(2, 5);
		Line l2 = new Line(p1, p2);
		
		if (l1.same(l2))
			System.out.println("Co-linear");
		else if (l1.parallel(l2))
			System.out.println("None");
		else
			System.out.println(l1.intersect(l2));
	}
	
	private static class Point {
		double x, y;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		public Point() {
			this(0, 0);
		}
		
		public String toString() {
			return String.format("Point %.2f %.2f", x, y);
		}
	}
	
	private static class Line {
		double a, b, c;
		
		public Line(Point p1, Point p2) {
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
	}

}
