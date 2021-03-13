package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * this class represents a Cylinder shape that exten from tube shape
 * 
 * @author David&Yishai
 *
 */
public class Cylinder extends Tube {
	double height;

	@Override
	public String toString() {
		return "height=" + height +super.toString();
	}

	@Override
	public Vector getNormal(Point3D a) {
		return null;
	}

}
