package primitives;

import java.util.List;

import geometries.Intersectable.GeoPoint;

/**
 * 
 * @author david&yishai
 *
 */
public class Ray {
	private Point3D p0;
	private Vector dir;

	@Override
	public String toString() {
		return "p0=" + p0 + ", dir= " + dir;
	}

	/**
	 * 
	 * @param point  point of start for ray
	 * @param vector direction vector for ray
	 */
	public Ray(Point3D point, Vector vector) {
		p0 = point;
		dir = vector.normalized();
	}

	public Point3D getP0() {
		return p0;
	}

	public Vector getDir() {
		return dir;
	}

	/**
	 * 
	 * @param t number to multiply the vector with
	 * @return the point of start + (direction vector * t)
	 */
	public Point3D getPoint(double t) {
		if (Util.isZero(t)) {
			return new Point3D(p0.x, p0.y, p0.z);
		}
		return p0.add(dir.scale(t)); // p0 +v*t
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return this.p0.equals(other.p0) && this.dir.equals(other.dir);
	}

	/**
	 * 
	 * @param lstPoint list of intersections points
	 * @return the closes point of intersection to the ray start point
	 */
	public Point3D findClosestPoint(List<Point3D> lstPoint) {
		if (lstPoint == null || lstPoint.size() == 0)
			return null;// return null if one of the fields is empty
		Point3D point = lstPoint.get(0);// initializing the first point to be the closes
		double min = p0.distanceSquared(point);
		for (Point3D pi : lstPoint) {
			if (p0.distanceSquared(pi) < min)// check if there is a closer point
				point = pi;
		}
		return point;
	}

	/**
	 * 
	 * @param lstGeoPoint list of intersections points
	 * @return the closes point of intersection to the ray start point
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> lstGeoPoint) {
		if (lstGeoPoint == null || lstGeoPoint.size() == 0)
			return null;// return null if one of the fields is empty
		GeoPoint geoPoint = lstGeoPoint.get(0);// initializing the first point to be the closes
		double min = p0.distanceSquared(geoPoint.point);
		for (GeoPoint pi : lstGeoPoint) {
			if (p0.distanceSquared(pi.point) < min)// check if there is a closer point
				geoPoint = pi;
		}
		return geoPoint;
	}
}
