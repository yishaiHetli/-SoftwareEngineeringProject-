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

	public Tube(Ray axisRay, double radius) {
		this.axisRay = axisRay;
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "axisRay= " + axisRay + ", radius=" + radius;
	}

	public Vector getNormal(Point3D p) { // (2,3,4)
		Vector getDir = axisRay.getDir(); // v (1,0,2)
		Point3D getP0 = axisRay.getP0(); // P0 (1,1,0)
		double t = getDir.dotProduct(p.subtract(getP0)); // t = v * (p - p0) (1,2,4) == 9
		if (!Util.isZero(t)) {
			// projection of P-p0 on the ray:
			getP0 = getP0.add(getDir.scale(t)); // O = p0 + (t*v) (1,1,0) + (9,0,18) = (10,1,18)
		}
		Vector check = p.subtract(getP0);
		return check.normalize();// normalized vector of (p-O) (-8,2,-14) / sqrt (264)
	}

}
