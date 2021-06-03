package primitives;

/**
 * Point3D class is used to define a point in a 3D that consisting with three
 * coordinates
 * 
 * @author David&Yishai
 *
 */
public class Point3D {
	@Override
	public String toString() {
		return "x=" + x + ", y=" + y + ", z=" + z;
	}

	final public Coordinate x;
	final public Coordinate y;
	final public Coordinate z;


	public final static Point3D ZERO = new Point3D(0, 0, 0);

	/**
	 * ctor: get 3 Coordinates and set class Coordinates
	 * 
	 * @param coord_x first Coordinate
	 * @param coord_y second Coordinate
	 * @param coord_z third Coordinate
	 */
	public Point3D(Coordinate coord_x, Coordinate coord_y, Coordinate coord_z) {
		x = new Coordinate(coord_x.coord);
		y = new Coordinate(coord_y.coord);
		z = new Coordinate(coord_z.coord);
	}

	/**
	 * ctor: get 3 numbers and set class Coordinates
	 * 
	 * @param coord_x first Coordinate
	 * @param coord_y second Coordinate
	 * @param coord_z third Coordinate
	 */
	public Point3D(double coord_x, double coord_y, double coord_z) {
		x = new Coordinate(coord_x);
		y = new Coordinate(coord_y);
		z = new Coordinate(coord_z);
	}

	/**
	 * ctor: get point 3D and and set class Coordinates by calling another ctor
	 * 
	 * @param point3D point in the class type
	 */
	public Point3D(Point3D point3D) {
		this(point3D.x, point3D.y, point3D.z);
	}

	/**
	 * add the class coordinates to the sent vector
	 * 
	 * @param vec vector to add
	 * @return return vector of their sum
	 */
	public Point3D add(Vector vec) {
		Point3D head = vec.head;
		return new Point3D(x.coord + head.x.coord, y.coord + head.y.coord, z.coord + head.z.coord);
	}

	/**
	 * gets point and reduce her from this coordinates
	 * 
	 * @param other point in 3D to reduce from
	 * @return the reduce value as vector
	 */
	public Vector subtract(Point3D other) {
		return new Vector(x.coord - other.x.coord, y.coord - other.y.coord, z.coord - other.z.coord);
	}

	/**
	 * calculate the distance between the sent point to this coordinates
	 * 
	 * @param other point to calculate the distance from
	 * @return the distance squared
	 */
	public double distanceSquared(Point3D other) {
		return (x.coord - other.x.coord) * (x.coord - other.x.coord)
				+ (y.coord - other.y.coord) * (y.coord - other.y.coord)
				+ (z.coord - other.z.coord) * (z.coord - other.z.coord);
	}

	/**
	 * calculate the distance between the sent point to this coordinates
	 * 
	 * @param other point to calculate the distance from
	 * @return the distance
	 */
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
