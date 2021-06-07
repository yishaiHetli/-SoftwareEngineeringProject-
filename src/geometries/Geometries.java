package geometries;

import java.util.ArrayList;
//import java.util.LinkedList;
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
		minX = Double.MAX_VALUE;
		minY = Double.MAX_VALUE;
		minZ = Double.MAX_VALUE;
		maxX = Double.MIN_VALUE;
		maxY = Double.MIN_VALUE;
		maxZ = Double.MIN_VALUE;
		for (Box geo : geometric) {
			geo.createBox(); // set box for each geometry
			// get the absolute min and max
			if (geo.minX < minX)
				minX = geo.minX;
			if (geo.maxX > maxX)
				maxX = geo.maxX;
			if (geo.minY < minY)
				minY = geo.minY;
			if (geo.maxY > maxY)
				maxY = geo.maxY;
			if (geo.minZ < minZ)
				minZ = geo.minZ;
			if (geo.maxZ > maxZ)
				maxZ = geo.maxZ;
		}
		middlePoint = this.getMiddlePoint();
	}

	/**
	*
	 */
	public void callMakeTree() {
		geometric = makeTree(geometric);
	}

	/**
	 * the function is making pears of two closes geometries until the list is empty
	 * the function calls itself until the list contains one geometry node
	 *
	 * @param shapes the list of finite shapes
	 * @return a list of shapes in a binary way
	 */
	private List<Box> makeTree(List<Box> shapes) {
		if (shapes.size() == 1)
			return shapes;
		ArrayList<Box> newShapes = null;
		while (!shapes.isEmpty()) {
			Box first = shapes.remove(0), nextTo = shapes.get(0);
			double minD = first.middlePoint.distance(nextTo.middlePoint);
			int min = 0;
			for (int i = 1; i < shapes.size(); ++i) {
				if (minD == 0)
					break;
				double temp = first.middlePoint.distance(shapes.get(i).middlePoint);
				if (temp < minD) {
					minD = temp;
					nextTo = shapes.get(i);
					min = i;
				}
			}
			if (newShapes == null)
				newShapes = new ArrayList<Box>();
			shapes.remove(min);
			Geometries newGeo = new Geometries(first, nextTo);
			newGeo.updateBoxSize(first, nextTo);
			newShapes.add(newGeo);
			if (shapes.size() == 1)
				newShapes.add(shapes.remove(0));
		}
		return makeTree(newShapes);
	}

	/**
	 * update box size after every new geometry we add to geometries list.
	 */
	protected void updateBoxSize(Box a, Box b) {
		boudingVolume = true;
		minX = Double.MAX_VALUE;
		minY = Double.MAX_VALUE;
		minZ = Double.MAX_VALUE;
		maxX = Double.MIN_VALUE;
		maxY = Double.MIN_VALUE;
		maxZ = Double.MIN_VALUE;
		minX = Math.min(a.minX, b.minX);
		minY = Math.min(a.minY, b.minY);
		minZ = Math.min(a.minZ, b.minZ);
		maxX = Math.max(a.maxX, b.maxX);
		maxY = Math.max(a.maxY, b.maxY);
		maxZ = Math.max(a.maxZ, b.maxZ);
		middlePoint = getMiddlePoint();
	}

}
