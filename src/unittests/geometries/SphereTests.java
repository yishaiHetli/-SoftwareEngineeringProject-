package unittests.geometries;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import geometries.*;
import primitives.*;

public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// test 1
		Sphere sp = new Sphere(new Point3D(1, 2, 3), 0);
		double root_12 = 2 / Math.sqrt(12);
		assertEquals(new Vector(root_12, root_12, root_12), sp.getNormal(new Point3D(3, 4, 5)));
		// test 2
		sp = new Sphere(new Point3D(0, 0, 0), 1);
		assertEquals(new Vector(new Point3D(0, 0, 1)), sp.getNormal(new Point3D(0, 0, 1)));
	}

	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray's line is outside the sphere (0 points)
		assertNull("Ray's line out of sphere",
				sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

		// TC02: Ray starts before and crosses the sphere (2 points)
		Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
		Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
		List<Point3D> result = sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray crosses sphere", List.of(p1, p2), result);

		// TC03 Ray's line is outside the sphere and goes in the opposite direction(0
		// points)
		assertNull("Ray's line out of sphere and go in the opposite direction",
				sphere.findIntsersections(new Ray(new Point3D(3, 0, 0), new Vector(1, 1, 0))));
		// TC04 Ray's line is start inside the sphere and goes outside(1 points)
		result = sphere.findIntsersections(new Ray(new Point3D(0.5d, 0, 0), new Vector(1, 1, 0)));// *
		assertEquals("Wrong number of points", 1, result.size());
		// TC05: Ray starts at sphere and goes inside (1 points)
		result = sphere.findIntsersections(new Ray(new Point3D(2, 0, 0), new Vector(-1,1, 0)));// *
		assertEquals("Wrong number of points", 1, result.size());
		// TC06: Ray starts at sphere and goes outside (0 points)
		result = sphere.findIntsersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 1 , 0)));
		assertNull("Ray starts at sphere and goes outside", result);
		// TC07: Ray starts at sphere and goes outside in line of the center(0 points)
		result = sphere.findIntsersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0)));
		assertNull("Ray starts at sphere and goes outside", result);
		// TC08: Ray starts in the sphere center (1 points)
		result = sphere.findIntsersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		// TC09: Ray starts outside the sphere and goes opposite direction in line of
		// the center(0 points)
		result = sphere.findIntsersections(new Ray(new Point3D(3, 0, 0), new Vector(1, 0, 0)));
		assertNull("Ray starts outside the sphere and goes opposite direction in line of the center", result);
		// TC10: Ray starts on the sphere and go inside in line of center (1 points)
		result = sphere.findIntsersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, 0, 0)));
		assertEquals("Wrong number of points", 1, result.size());
	}
}
