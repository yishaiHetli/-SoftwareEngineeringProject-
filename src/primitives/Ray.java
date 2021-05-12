package primitives;

import java.util.List;

import geometries.Intersectable.GeoPoint;

/**
 * 
 * @author david&yishai
 *
 */
public class Ray {
	private static final double DELTA = 0.1;
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

	/**
	 * Instantiates a new Ray and moves the point by 0.1 in the normal direction
	 *
	 * @param point     point in 3D
	 * @param direction the direction
	 * @param n         vector normal to point
	 */
	public Ray(Point3D point, Vector direction, Vector n) {
		dir = direction;
		Vector delta = n.scale(Util.alignZero(n.dotProduct(dir)) > 0 ? DELTA : -DELTA);// n*delta
		p0 = point.add(delta);// point + (n*delta)
	}

	/**
	 * 
	 * @return point of start
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * 
	 * @return direction vector
	 */
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
		try {
			return p0.add(dir.scale(t)); // p0 +v*t
		} catch (Exception e) {
			return new Point3D(p0.x, p0.y, p0.z);
		}
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
		Point3D closesPoint = null;
		double minDistance = Double.MAX_VALUE;
		for (Point3D point : lstPoint) {
			double distance = p0.distance(point);
			if (distance < minDistance) {
				minDistance = distance;
				closesPoint = point;
			}
		}
		return closesPoint;
	}

	/**
	 * 
	 * @param lstGeoPoint list of intersections points
	 * @return the closes geoPoint of intersection to the ray start point
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> lstGeoPoint) {
		GeoPoint closesPoint = null;
		double minDistance = Double.MAX_VALUE;
		for (GeoPoint geoPoint : lstGeoPoint) {
			double distance = p0.distance(geoPoint.point);
			if (distance < minDistance) {
				minDistance = distance;
				closesPoint = geoPoint;
			}
		}
		return closesPoint;
	}
}
