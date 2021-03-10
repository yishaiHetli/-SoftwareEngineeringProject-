package primitives;

/**
 * Vector class is used to define a vector in a 3D and do some arithmetic
 * operations on this vector
 * 
 * @author David&Yishai
 *
 */
public class Vector {
	Point3D head;

	/**
	 * ctor that get one parameter that represent a point in 3D
	 * 
	 * @param a represent a point in 3D
	 */
	public Vector(Point3D a) {
		head = new Point3D(a.x, a.y, a.z);
				if (head.equals(Point3D.ZERO)) {
			throw new IllegalArgumentException("the vector can't be zero");
		}
	}

	/**
	 * ctor that gets three coordinates that represent a point in 3D
	 * 
	 * @param a the first Coordinate
	 * @param b the second Coordinate
	 * @param c the third Coordinate
	 */
	public Vector(Coordinate a, Coordinate b, Coordinate c) {
		head = new Point3D(a, b, c);
	}

	/**
	 * ctor that gets three numbers that represent a point in 3D
	 * 
	 * @param a the first Coordinate
	 * @param b the second Coordinate
	 * @param c the third Coordinate
	 */
	public Vector(double a, double b, double c) {
		head = new Point3D(new Coordinate(a), new Coordinate(b), new Coordinate(c));
		if (head.equals(Point3D.ZERO)) {
			throw new IllegalArgumentException("the vector can't be zero");
		}
	}

	/**
	 * add vector to point 3D and return the new vector value
	 * 
	 * @param other the vector to add
	 * @return the new vector value
	 */
	public Vector add(Vector other) {
		return new Vector(head.x.coord + other.head.x.coord, head.y.coord + other.head.y.coord,
				head.z.coord + other.head.z.coord);
	}

	/**
	 * subtract point from a given vector and return the new vector value
	 * 
	 * @param other the vector to subtract
	 * @return the new vector value
	 */
	public Vector subtract(Vector other) {
		return new Vector(head.x.coord - other.head.x.coord, head.y.coord - other.head.y.coord,
				head.z.coord - other.head.z.coord);
	}

	/**
	 * Scalar doubling, multiple scalar with class point and return the new vector
	 * value
	 * 
	 * @param other Scalar number for multiplication
	 * @return the new vector value
	 */
	public Vector scale(double num) {
		return new Vector(num * head.x.coord, num * head.y.coord, num * head.z.coord);
	}

	/**
	 * Vector multiplication between vector and dot
	 * 
	 * @param other vector for multiplication
	 * @return the new vector value
	 */
	public Vector crossProduct(Vector other) {
		return new Vector(head.y.coord * other.head.z.coord - head.z.coord * other.head.y.coord,
				head.z.coord * other.head.x.coord - head.x.coord * other.head.z.coord,
				head.x.coord * other.head.y.coord - head.y.coord * other.head.x.coord);
	}

	/**
	 * Scalar doubling between point and vector, mult vector with class point and
	 * return scalar number
	 * 
	 * @param other vector for multiplication
	 * @return scalar value, sum of the Scalar multiplication
	 */
	public double dotProduct(Vector other) {
		return head.x.coord * other.head.x.coord + head.y.coord * other.head.y.coord
				+ head.z.coord * other.head.z.coord;
	}

	/**
	 * Calculates the length of the class point as vector
	 * 
	 * @return the length of the vector Squared
	 */
	public double lengthSquared() {
		return head.x.coord * head.x.coord + head.y.coord * head.y.coord + head.z.coord * head.z.coord;
	}

	/**
	 * Calculates the length of the class point as vector
	 * 
	 * @return the length of the vector
	 */
	public double length() {
		return Math.sqrt(this.lengthSquared());
	}

	/**
	 * normalize the class point by dividing in its length and return its value
	 * 
	 * @return the normalized vector
	 */
	public Vector normalize() {
		Vector normal = new Vector(head.x.coord / length(), this.head.y.coord / this.length(),
				this.head.z.coord / this.length());
		this.head = normal.head;
		return this;
	}

	/**
	 * normalize the class point by dividing in its length but the point does not
	 * change
	 * 
	 * @return the normalized vector
	 */
	public Vector normalized() {
		return new Vector(this.normalize().head);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		return head.equals(other.head);
	}

	public Point3D getHead() {
		return head;
	}
}