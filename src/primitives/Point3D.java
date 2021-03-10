package primitives;

/**
 * Point3D class is used to define a point in a 3D that consisting with three
 * coordinates
 * 
 * @author David&Yishai
 *
 */
public class Point3D {
	Coordinate x;
	Coordinate y;
	Coordinate z;
	public final static Point3D ZERO = new Point3D(0, 0, 0);

	/**
	 * ctor: get 3 Coordinates and set class Coordinates
	 * 
	 * @param a first Coordinate
	 * @param b second Coordinate
	 * @param c third Coordinate
	 */
	public Point3D(Coordinate a, Coordinate b, Coordinate c) {
		x = new Coordinate(a.coord);
		y = new Coordinate(b.coord);
		z = new Coordinate(c.coord);
	}

	/**
	 * ctor: get 3 numbers and set class Coordinates
	 * 
	 * @param a first Coordinate
	 * @param b second Coordinate
	 * @param c third Coordinate
	 */
	public Point3D(double a, double b, double c) {
		x = new Coordinate(a);
		y = new Coordinate(b);
		z = new Coordinate(c);
	}

	public Point3D add(Vector a) {
		return new Point3D(x.coord + a.head.x.coord, y.coord + a.head.y.coord, z.coord + a.head.z.coord);
	}

	public Vector subtract(Point3D other) {
		return new Vector(x.coord - other.x.coord, y.coord - other.y.coord, z.coord - other.z.coord);
	}

	public double distanceSquared(Point3D other) {
		return (x.coord - other.x.coord) * (x.coord - other.x.coord)
				+ (y.coord - other.y.coord) * (y.coord - other.y.coord)
				+ (z.coord - other.z.coord) * (z.coord - other.z.coord);
	}

	public double distance(Point3D other) {
		return Math.sqrt(distanceSquared(other));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return x.equals(other.x) && y.equals(other.y) && z.equals(other.z);
	}

}
