package geometries;

import java.util.List;

import primitives.*;

/**
 * this class is used to represent a tube shape in 3D
 * 
 * @author David&Yishai
 *
 */
public class Tube extends Geometry {

	protected Ray axisRay;
	protected double radius;

	/**
	 * ctor that get to values ray and double
	 * 
	 * @param axisRay ray that have point of start and vector for direction
	 * @param radius
	 */
	public Tube(Ray axisRay, double radius) {
		this.axisRay = axisRay;
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "axisRay= " + axisRay + ", radius=" + radius;
	}

	/**
	 * get point and calculate the normal to it
	 * 
	 * @param p point in 3D of which we want the normal
	 * @return the normal to the sending point
	 */
	public Vector getNormal(Point3D p) {
		Vector getDir = axisRay.getDir(); // v
		Point3D getP0 = axisRay.getP0(); // p0
		double t;
		try {
			t = p.subtract(getP0).dotProduct(getDir); // t = v * (p - p0)
		} catch (IllegalArgumentException e) { // if (p - p0) == 0 the normal is v
			return getDir;
		}

		if (!Util.isZero(t)) { // if t == 0 the normal is just normalize(p - p0)
			// projection of P - P0 on the ray:
			getP0 = getP0.add(getDir.scale(t)); // O = p0 + (t*v)
		}
		return p.subtract(getP0).normalize(); // normalized vector of (p-O)
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		return null;
	}

}
