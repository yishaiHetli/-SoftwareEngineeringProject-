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

	public Vector getNormal(Point3D a) {
		return (a.subtract(center)).normalize();
	}
}
