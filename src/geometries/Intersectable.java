package geometries;

import java.util.List;

import primitives.*;

public interface Intersectable {
	public List<Point3D> findIntsersections(Ray ray);
}
