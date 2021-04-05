package geometries;

import java.util.List;

import primitives.*;

/**
 * interface for intersectable shapes
 * 
 * @author David&Yishai
 *
 */
public interface Intersectable {
	/**
	 * find all the intersections of ray with the shape
	 * 
	 * @param ray ray to check for intersections
	 * @return list of intersections
	 */
	public List<Point3D> findIntersections(Ray ray);
}
