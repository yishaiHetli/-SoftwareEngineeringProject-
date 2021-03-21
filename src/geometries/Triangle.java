package geometries;

import primitives.Point3D;

/**
 * this class represent a triangle in a 3D that inherited from a plane shape
 * 
 * @author David&Yishai
 *
 */
public class Triangle extends Plane {

	@Override
	public String toString() {
		return  super.toString();
	}
/**
 *  ctor that get three points 
 * @param point_a first point 
 * @param point_b second point
 * @param point_c third point
 */
	public Triangle(Point3D point_a, Point3D point_b, Point3D point_c) {
		super(point_a, point_b, point_c);
	}
	

}
