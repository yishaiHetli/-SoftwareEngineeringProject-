package geometries;

import primitives.*;

/**
 * this class is used to represent a tube shape in 3D
 * 
 * @author David&Yishai
 *
 */
public class Tube {

	protected Ray axisRay;
	protected double radius;

	@Override
	public String toString() {
		return "axisRay= " + axisRay + ", radius=" + radius;
	}

	public Vector getNormal(Point3D p) {
		Vector getDir = axisRay.getDir(); // v
		Point3D getP0 = axisRay.getP0(); // P0
		double t = getDir.dotProduct(p.subtract(getP0)); // t = v * (p - p0)
		if (!Util.isZero(t)) {
			// projection of P-p0 on the ray:
			getP0 = getP0.add(getDir.scale(t)); //  O = p0 + (t*v)
		}
		return p.subtract(getP0).normalize(); // normalized vector of (p-O)
	}
}
