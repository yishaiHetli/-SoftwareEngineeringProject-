package geometries;

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
}
