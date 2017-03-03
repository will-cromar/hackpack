public class Point {
	double x, y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point() {
		this(Double.NaN, Double.NaN);
	}
	
	Point add(Point other) {
		return new Point(x + other.x, y + other.y);
	}
	
	Point sub(Point other) {
		return new Point(x - other.x, y - other.y); 
	}
	
	Point scale(double s) {
		return new Point(s * x, s * y);
	}
	
	double distSq(Point other) {
		double dx = this.x - other.x;
		double dy = this.y - other.y;
		
		return dx * dx + dy * dy;
	}
	
	double dist(Point other) {
		return Math.sqrt(this.distSq(other));
	}
	
	double cross(Point other) {
		return this.x * other.y - other.x * this.y;
	}
	
	boolean nanpt() {
		return Double.isNaN(x) && Double.isNaN(y);
	}
	
	public String toString() {
		return String.format("Point %.2f %.2f", x, y);
	}
	
	public int hashCode() {
		return this.toString().hashCode();
	}
}