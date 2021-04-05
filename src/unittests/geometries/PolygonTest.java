/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Testing Polygons
 * 
 * @author Dan
 *
 */
public class PolygonTest {

	/**
	 * Test method for
	 * {@link geometries.Polygon#Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testConstructor() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: Correct concave quadrangular with vertices in correct order
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct polygon");
		}

		// TC02: Wrong vertices order
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0), new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
			fail("Constructed a polygon with wrong order of vertices");
		} catch (IllegalArgumentException e) {
		}

		// TC03: Not in the same plane
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 2, 2));
			fail("Constructed a polygon with vertices that are not in the same plane");
		} catch (IllegalArgumentException e) {
		}

		// TC04: Concave quadrangular
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
			fail("Constructed a concave polygon");
		} catch (IllegalArgumentException e) {
		}

		// =============== Boundary Values Tests ==================

		// TC10: Vertex on a side of a quadrangular
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
			fail("Constructed a polygon with vertix on a side");
		} catch (IllegalArgumentException e) {
		}

		// TC11: Last point = first point
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0, 1));
			fail("Constructed a polygon with vertice on a side");
		} catch (IllegalArgumentException e) {
		}

		// TC12: Colocated points
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 1, 0));
			fail("Constructed a polygon with vertice on a side");
		} catch (IllegalArgumentException e) {
		}

	}

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
				new Point3D(-1, 1, 1));
		double sqrt3 = Math.sqrt(1d / 3);
		assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
	}
	@Test
	public void testfindIntersections() {
		Polygon pol = new Polygon(new Point3D(0, 0, 0), new Point3D(1, 0, 0), new Point3D(1,1, 0), new Point3D(0,1, 0));

		// ============ Equivalence Partitions Tests ==============

		// TC01:Ray is intersecting in Polygon (1 Points)
		Ray ray1 = new Ray(new Point3D(0.5, 0.5, 1), new Vector(0, 0, -1));
		assertEquals(List.of(new Point3D(0.5, 0.5, 0)), pol.findIntersections(ray1));
		// TC02:Ray is not intersecting with Polygon and is parallel to the edge (0
		// Points)
		Ray ray2 = new Ray(new Point3D(0.5, -0.5, 1), new Vector(0, 0, -1));
		assertNull(pol.findIntersections(ray2));
		// TC03:Ray is not intersecting with Polygon and is parallel to the vertex (0
		// Points)
		Ray ray3 = new Ray(new Point3D(-0.5, -0.5, 1), new Vector(0, 0, -1));
		assertNull(pol.findIntersections(ray3));

		// =============== Boundary Values Tests ==================
		
		// TC04:Ray is intersecting with Polygon on edge (0 Points)
		Ray ray4 = new Ray(new Point3D(0, 0.5, 1), new Vector(new Point3D(0, 0, -1)));
		assertNull(pol.findIntersections(ray4));

		// TC05:Ray is intersecting with Polygon on vertex (0 Points)
		Ray ray5 = new Ray(new Point3D(1, 1, 1), new Vector(new Point3D(0, 0, -1)));
		assertNull(pol.findIntersections(ray5));

		// TC06:Ray is intersecting with the continuation of the edge (0 Points)
		Ray ray6 = new Ray(new Point3D(0, 2, 1), new Vector(new Point3D(0, 0, -1)));
		assertNull(pol.findIntersections(ray6));
	}

	
}
