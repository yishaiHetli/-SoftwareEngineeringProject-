package geometries;

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
	 * @param point3D point in the cylinder
	 * @return normal vector
	 **/
	@Override
	public Vector getNormal(Point3D point3D) {
		Point3D o = axisRay.getP0();
		Vector v = axisRay.getDir();

		// projection of P-O on the ray:
		double t;
		try {
			t = Util.alignZero(point3D.subtract(o).dotProduct(v));
		} catch (IllegalArgumentException e) { // P = O
			return v;
		}

		// if the point is at a base
		if (t == 0 || Util.isZero(height - t))
			return v;

		// point is outside
		o = o.add(v.scale(t));
		return point3D.subtract(o).normalize();
	}

}
