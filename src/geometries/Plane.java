package geometries;

import primitives.*;

public class Plane implements Geometry {

	@Override
	public String toString() {
		return "q0= " + q0 + ", normal=" + normal;
	}

	Point3D q0;

	Vector normal;

	/**
	 * ctor that gets three parameters
	 * 
	 * @param a first point
	 * @param b second point
	 * @param c third point
	 */
	public Plane(Point3D a, Point3D b, Point3D c) {
		Vector v1 = b.subtract(a);
		Vector v2 = c.subtract(a);
		Vector n = v1.crossProduct(v2);
		this.normal = n.normalize();
		q0 = a;
	}

	/**
	 * ctor that gets two parameters
	 * 
	 * @param a      some point in 3D
	 * @param normal
	 */
	public Plane(Point3D a, Vector normal) {
		this.normal = normal;
		q0 = a;
	}

	public Vector getNormal(Point3D a) {
		return null;
	}

	public Point3D getQ0() {
		return q0;
	}

	public Vector getNormal() {
		return normal;
	}
}
