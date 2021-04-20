package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 *  this class represents a collection of Intersectable shapes 
 * @author David&Yishai
 *
 */
public class Geometries implements Intersectable {

	private List<Intersectable> cuts;

	/**
	 * empty ctor that Initialize the class list
	 */
	public Geometries() {
		cuts = new LinkedList<Intersectable>();
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
			cuts.add(i);
	}

	/**
	 * add the gets array into the class list of Intersectable
	 * 
	 * @param geometries  array in type of Intersectable
	 */
	public void add(Intersectable... geometries) {
		for (Intersectable i : geometries)
			cuts.add(i);
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		List<Point3D> allIntersection  = null;

        //loop on all geometries
        for (Intersectable cut :cuts) {
            List<Point3D> intersections = cut.findIntersections(ray);

            if (intersections != null) {
                if (allIntersection  == null) {//for the first time
                	allIntersection  = new ArrayList<Point3D>();
                }
                allIntersection .addAll(intersections);
            }
        }
        return allIntersection ;
	}

}
