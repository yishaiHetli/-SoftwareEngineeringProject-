package geometries;

import primitives.*;

/**
 * interface that all that inherited from him must actualize it's contract that
 * is to actualize getNormal function
 * 
 * @author User
 *
 */
public interface Geometry {

	public Vector getNormal(Point3D a);
}
