package geometries;

import primitives.*;

/**
 * interface that all that inherited from him must actualize it's contract that
 * is to actualize getNormal function
 * 
 * @author David&Yishai
 *
 */
public interface Geometry extends Intersectable {

	/**
	 * find the normal to the point that receive
	 * 
	 * @param point the point to check it's normal
	 * @return vector that normal to the received point
	 */
	public Vector getNormal(Point3D point);
}
