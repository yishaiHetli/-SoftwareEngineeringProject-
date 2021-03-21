package unittests.geometries;

import geometries.*;
import primitives.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PlanTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 * 
	 */
	@Test
	public void GetNormal() {
		Plane v = new Plane(new Point3D(1, 0, 0), new Point3D(0, 0, 1), new Point3D(0, 1, 0));
		double root_3 = -1/Math.sqrt(3);  
		assertEquals(new Vector(root_3, root_3, root_3), v.getNormal());
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Plane v = new Plane(new Point3D(1, 0, 0), new Point3D(0, 0, 1), new Point3D(0, 1, 0));
		double root_3 = -1/Math.sqrt(3);  
		assertEquals(new Vector(root_3, root_3, root_3),v.getNormal(new Point3D(1, 1, 1)));
	}

}
