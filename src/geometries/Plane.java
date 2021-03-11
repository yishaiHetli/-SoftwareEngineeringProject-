package geometries;

import primitives.*;

public class Plane implements Geometry {

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
		normal = null;
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
