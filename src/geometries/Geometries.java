package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Ray;

/**
 * this class represents a collection of Intersectable shapes
 * 
 * @author David&Yishai
 *
 */
public class Geometries implements Intersectable {

	private List<Intersectable> geometric;

	/**
	 * empty ctor that Initialize the class list
	 */
	public Geometries() {
		geometric = new LinkedList<Intersectable>();
	}

	/**
	 * ctor that get an array in type of intersectable and add that into the class
	 * list
	 * 
	 * @param geometries
	 */
	public Geometries(Intersectable... geometries) {
		this();
		for (Intersectable i : geometries)
			geometric.add(i);
	}

	/**
	 * add the gets array into the class list of Intersectable
	 * 
	 * @param geometries array in type of Intersectable
	 */
	public void add(Intersectable... geometries) {
		for (Intersectable i : geometries)
			geometric.add(i);
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		List<GeoPoint> allCuts = null;
		// loop on all geometries
		for (Intersectable geo : geometric) {
			List<GeoPoint> cuts = geo.findGeoIntersections(ray, maxDistance); // ray cuts points in the geometric
			if (cuts != null) {
				if (allCuts == null) {// for the first time
					allCuts = new LinkedList<GeoPoint>();
				}
				allCuts.addAll(cuts);
			}
		}
		return allCuts;
	}

}
