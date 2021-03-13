package primitives;

public class Ray {
	Point3D p0;
	@Override
	public String toString() {
		return "p0=" + p0 + ", dir= " + dir ;
	}

	Vector dir;
	public Ray(Point3D point, Vector vector)
	{
		p0  = point;
		dir = vector.normalized();
	}
	
	public Point3D getP0() {
		return p0;
	}

	public Vector getDir() {
		return dir;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(!(obj instanceof Ray)) return false;
		Ray other = (Ray)obj;
		return this.p0.equals(other.p0)&& this.dir.equals(other.dir);
	}

}
