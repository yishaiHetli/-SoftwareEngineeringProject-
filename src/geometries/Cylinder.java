package geometries;

import java.util.List;

import primitives.*;

/**
 * this class represents a Cylinder shape that exten from tube shape
 * 
 * @author David&Yishai
 *
 */
public class Cylinder extends Tube {
	double height;

	public Cylinder(Ray axisRay, double radius, double height) {
		super(axisRay, radius);
		this.height = height;
	}

	@Override
	public String toString() {
		return "height=" + height + super.toString();
	}

	/**
	 * Get Normal to Cylinder by the point3D
	 *
	 * @param p point in the cylinder
	 * @return normal vector
	 **/
	@Override
	public Vector getNormal(Point3D p) {
		Point3D getP0 = axisRay.getP0(); // p0
		Vector getDir = axisRay.getDir(); // v

		// projection of P-O on the ray:
		double t;
		try {
			t = p.subtract(getP0).dotProduct(getDir); // (p-p0) * v
		} catch (IllegalArgumentException e) { // if (p - p0) == 0
			return getDir;
		}

		// if the point is at a base
		if (Util.isZero(t) || Util.isZero(height - t))
			return getDir;

		// point is outside
		getP0 = getP0.add(getDir.scale(t)); // p0 + v*t
		return p.subtract(getP0).normalize(); // normalize of (p - p0)
	}

}
