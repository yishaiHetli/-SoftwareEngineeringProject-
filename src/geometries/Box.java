package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;

public abstract class Box implements Intersectable {

	protected boolean boudingVolume = false; // turn off the bouding volume
	protected double minX, minY, minZ, maxX, maxY, maxZ;
	protected Point3D middlePoint;
	protected boolean finity = false;

	/**
	 * creating boxes for all shapes in the geometries list and setting the bounding
	 * to be true
	 */
	public void createBox() {
		boudingVolume = true;
		setBox();
	}

	/**
	 * abstract function to sat boxes for all shapes
	 */
	protected abstract void setBox();

	/**
	 * @return the middle point of a bouding volume box.
	 */
	public Point3D getMiddlePoint() {
		return new Point3D(minX + ((maxX - minX) / 2), minY + ((maxY - minY) / 2), minZ + ((maxZ - minZ) / 2));
	}

	/**
	 * check if ray intersect with the box
	 *
	 * @param ray the ray
	 * @return true if intersect
	 */
	public boolean isIntersect(Ray ray) {
		Point3D head = ray.dir.head;
		Point3D p = ray.p0;
		double temp;

		double dirX = head.x.coord, dirY = head.y.coord, dirZ = head.z.coord;
		double origX = p.x.coord, origY = p.y.coord, origZ = p.z.coord;

		// Min/Max starts with X
		double tXmin = (minX - origX) / dirX, tXmax = (maxX - origX) / dirX;
		if (tXmin > tXmax) { // swap between tmax and tmin
			temp = tXmin;
			tXmin = tXmax;
			tXmax = temp;
		}

		double tYmin = (minY - origY) / dirY, tYmax = (maxY - origY) / dirY;
		if (tYmin > tYmax) { // swap between tmax and tmin
			temp = tYmin;
			tYmin = tYmax;
			tYmax = temp;
		}
		if ((tXmin > tYmax) || (tYmin > tXmax))// if the min of some coord bigger from min of other
			return false;
		if (tYmin > tXmin) // the absolute min is tXmin
			tXmin = tYmin;
		if (tYmax < tXmax)// the absolute max is tXmax
			tXmax = tYmax;

		double tZMmin = (minZ - origZ) / dirZ, tZmax = (maxZ - origZ) / dirZ;
		if (tZMmin > tZmax) { // swap between tmax and tmin
			temp = tZMmin;
			tZMmin = tZmax;
			tZmax = temp;
		}
		return tXmin <= tZmax && tZMmin <= tXmax;
	}

	/**
	 * List of Intersection points found in tests Find intersections list between
	 * giving ray and geometries shapes in a bound box. the returned list can be
	 * null or empty if there is no intersections or there is no geometries shape in
	 * Geometries.
	 * 
	 * @param ray         the ray
	 * @param maxDistance maximum distance to geometry for intersetion
	 * @return the list of Points 3D Intersections or a empty list in case the ray
	 *         is not in the box.
	 */
	public List<GeoPoint> findIntsersectionsBound(Ray ray, double maxDistance) {
		return boudingVolume && !isIntersect(ray) ? null : findGeoIntersections(ray, maxDistance);
	}

}
