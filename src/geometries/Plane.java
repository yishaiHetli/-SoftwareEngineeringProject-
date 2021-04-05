package geometries;

import java.util.LinkedList;
import java.util.List;

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
	 * @param point_a first point
	 * @param point_b second point
	 * @param point_c third point
	 */
	public Plane(Point3D point_a, Point3D point_b, Point3D point_c) {
		Vector v1 = point_b.subtract(point_a);
		Vector v2 = point_c.subtract(point_a); 
		Vector n = v1.crossProduct(v2);
		this.normal = n.normalize();
		q0 = point_a;
	}

	/**
	 * ctor that gets two parameters
	 * 
	 * @param point_a some point in 3D
	 * @param normal
	 */
	public Plane(Point3D point_a, Vector normal) {
		this.normal = normal;
		q0 = point_a;
	}

	/**
	 * return the normal that calculate in the ctor
	 */
	public Vector getNormal(Point3D a) {
		return normal;
	}

	public Point3D getQ0() {
		return q0;
	}

	/**
	 * return the normal that calculate in the ctor
	 */
	public Vector getNormal() {
		return normal;
	}

	@Override
	public List<Point3D> findIntersections (Ray ray) {
		Vector v = ray.getDir();
		Point3D p0 = ray.getP0();
		if (q0.equals(p0))
			return null;	
		double numerator = normal.dotProduct(q0.subtract(p0));
		double denominator = normal.dotProduct(v);
		if (denominator == 0)
			return null;
		double t = numerator / denominator;
		if (t <= 0 || Util.isZero(t)) 
			return null;
		return List.of(ray.getPoint(t)); // p0 + v*t
	}
}
