package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Ray;
import primitives.Util;

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
			if (geo.infinite)
				infinite = true;
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
	 * call to make tree for the uninfinite shapes
	 */
	public void callMakeTree() {
		if (infinite) {
			List<Box> withoutBox = new ArrayList<>();
			for (int i = 0; i < geometric.size(); ++i) {
				if (geometric.get(i).infinite)
					withoutBox.add(geometric.remove(i));// removes and add
			}
			geometric = makeTree(geometric);
			geometric.addAll(withoutBox);
		} else
			geometric = makeTree(geometric);
	}

	/**
	 * the function is making pears of two closes geometries until the list is empty
	 * the function calls itself until the list contains only one geometry node
	 * 
	 * for example: {t1,t2,t3,t4,t5} => {{t1,t3} = t1',{t2,t5}= t2',{t4}= t3'} =>
	 * {{t1',t3'} = t1'',{t2'} = t2''} => {{t1'',t2''}}
	 *
	 * @param shapes the list of finite shapes
	 * @return a list of shapes in a binary way
	 */
	private List<Box> makeTree(List<Box> shapes) {
		if (shapes.size() == 1) // recursion stop condition
			return shapes;
		List<Box> newShapes = null;
		while (!shapes.isEmpty()) { // loop until shapes is empty
			Box first = shapes.remove(0), nextTo = shapes.get(0); // remove to first and get second
			double minD1 = first.middlePoint.distance(nextTo.middlePoint); // distance between first snd second middles
			int minIndex = 0;
			int size = shapes.size();
			for (int i = 1; i < size; ++i) { // loop to find the absolute minimum from first shape
				if (Util.isZero(minD1))
					break;
				double minD2 = first.middlePoint.distance(shapes.get(i).middlePoint);
				if (minD2 < minD1) {
					minD1 = minD2;
					nextTo = shapes.get(i); // keep the shape of the minimum distance from first
					minIndex = i; // keep the index of the minimum to remove
				}
			}
			if (newShapes == null)
				newShapes = new ArrayList<Box>();
			shapes.remove(minIndex);
			Geometries newGeo = new Geometries(first, nextTo);// making pears of two closes geometries
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
		minX = a.minX <= b.minX ? a.minX : b.minX;
		minY = a.minY <= b.minY ? a.minY : b.minY;
		minZ = a.minZ <= b.minZ ? a.minZ : b.minZ;
		maxX = a.maxX >= b.maxX ? a.maxX : b.maxX;
		maxY = a.maxY >= b.maxY ? a.maxY : b.maxY;
		maxZ = a.maxZ >= b.maxZ ? a.maxZ : b.maxZ;
		middlePoint = getMiddlePoint();
	}

}
