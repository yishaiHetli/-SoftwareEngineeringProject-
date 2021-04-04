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

	public Vector getNormal(Point3D a);
}
