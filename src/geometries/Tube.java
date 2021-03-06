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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tube other = (Tube) obj;
		if (axisRay == null) {
			if (other.axisRay != null)
				return false;
		} else if (!axisRay.equals(other.axisRay))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
	}

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
		Vector getDir = axisRay.dir; // v
		Point3D getP0 = axisRay.p0; // p0
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
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		return null;
	}

	@Override
	protected void setBox() {
		// TODO Auto-generated method stub
		
	}
	

}
