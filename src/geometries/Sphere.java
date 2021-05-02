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
public class Sphere extends Geometry {
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sphere other = (Sphere) obj;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		Point3D p0 = ray.getP0();
		Vector v = ray.getDir();
		if (center.equals(p0)) {
			return List.of(new GeoPoint(this, ray.getPoint(radius)));
		}
		Vector u;
		try {
			u = center.subtract(p0);
		} catch (IllegalArgumentException e) { // if p0 == center
			return List.of(new GeoPoint(this, ray.getPoint(this.radius))); // p0 + r*v
		}
		double tm = Util.alignZero(u.dotProduct(v));
		double length = Util.alignZero(u.lengthSquared() - tm * tm); // length square of u reduce square of tm
		if (length < 0)
			return null;
		double d = Math.sqrt(length);
		if (d > radius)
			return null;
		double th = Math.sqrt(radius * radius - d * d);
		if (Util.isZero(th)) // if the ray is tangent to the sphere
			return null;
		List<GeoPoint> lst = new LinkedList<GeoPoint>();
		double t1 = tm + th;
		double t2 = tm - th;
		if (t1 > 0) {
			lst.add(new GeoPoint(this, ray.getPoint(t1))); // p1 = p0 + t1 * v
		}
		if (t2 > 0 && t1 != t2) {
			lst.add(new GeoPoint(this, ray.getPoint(t2))); // p2 = p0 + t2 * v
		}
		if (lst.isEmpty())
			return null;
		return lst;
	}
}
