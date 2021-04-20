package primitives;

import java.util.List;

public class Ray {
	private Point3D p0;
	private Vector dir;

	@Override
	public String toString() {
		return "p0=" + p0 + ", dir= " + dir;
	}

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

	public Point3D findClosestPoint(List<Point3D> lstPoint) {
		if (lstPoint == null || lstPoint.size() == 0)
			return null;
		Point3D point = lstPoint.get(0);
		double min = p0.distanceSquared(point);
		for (Point3D pi : lstPoint) {
			if (p0.distanceSquared(pi) < min) {
				point = pi;
			}
		}
		return point;
	}
}
