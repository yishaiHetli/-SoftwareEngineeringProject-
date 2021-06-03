package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Ray;

/**
 * this class represents a collection of Intersectable shapes
 * 
 * @author David&Yishai
 *
 */
public class Geometries extends Box {

	private List<Box> geometric;

	/**
	 * empty ctor that Initialize the class list
	 */
	public Geometries() {
		geometric = new ArrayList<Box>();
	}

	/**
	 * ctor that get an array in type of intersectable and add that into the class
	 * list
	 * 
	 * @param geometries
	 */
	public Geometries(Box... geometries) {
		this();
		for (Box i : geometries)
			geometric.add(i);
	}

	/**
	 * add the gets array into the class list of Intersectable
	 * 
	 * @param geometries array in type of Intersectable
	 */
	public void add(Box... geometries) {
		for (Box i : geometries)
			geometric.add(i);
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		List<GeoPoint> intersections = null;
		for (Box shape : geometric) {
			List<GeoPoint> tempIntersections = shape.findIntsersectionsBound(ray, maxDistance);
			if (tempIntersections != null) {
				if (intersections == null)
					intersections = new ArrayList<GeoPoint>();
				intersections.addAll(tempIntersections);
			}
		}
		return intersections;
	}

	@Override
	public void setBox() {
		for (Box geo : geometric) {
			geo.createBox(); // set box for each geometry
		}
	}
}
