package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.*;

/**
 * this class is used to represent a Sphere shape in 3D
 * 
 * @author David&Yishai
 *
 */
public class Sphere implements Geometry {
	Point3D center;
	double radius;

	public Sphere(Point3D center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "center= " + center + ", radius=" + radius;
	}

	/**
	 * calculate the normal to sphere in specific point
	 */
	public Vector getNormal(Point3D point) {
		return (point.subtract(center)).normalize(); // normalized of (p-O)
	}

	@Override
	public List<Point3D> findIntsersections(Ray ray) {
		Point3D p0 = ray.getP0();
		Vector v = ray.getDir();
		if (center.equals(p0)) {
			return List.of(ray.getPoint(radius));
		}
		Vector u;
		try {
			u = center.subtract(p0);
		} catch (IllegalArgumentException e) { // if p0 == center
			return List.of(ray.getPoint(this.radius)); // p0 + r*v
		}
		double tm = Util.alignZero(u.dotProduct(v));
		double length = Util.alignZero(u.lengthSquared() - tm * tm); // length square of u reduce square of tm
		if (length <= 0 || Util.isZero(length))
			return null;
		double d = Math.sqrt(length);
		if (d > radius)
			return null;
		double th = Math.sqrt(radius * radius - d * d);
		List<Point3D> lst = new LinkedList<Point3D>();
		double t1 = tm + th;
		double t2 = tm - th;
		if (t1 > 0 && !Util.isZero(t1)) {
			lst.add(ray.getPoint(t1)); // p1 = p0 + t1 * v
		}
		if (t2 > 0 && !Util.isZero(t2)) {
			lst.add(ray.getPoint(t2)); // p2 = p0 + t2 * v
		}
		if (lst.isEmpty())
			return null;
		return lst;
	}
}
