package unittests.geometries;

import static org.junit.Assert.*;

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
	 Sphere sp = new Sphere(new Point3D(1,2,3), 0);
	  double root_12 =  2/Math.sqrt(12);
	  assertEquals(new Vector(root_12, root_12, root_12),sp.getNormal(new Point3D(3,4,5)));
	  // test 2
	  sp = new Sphere(new Point3D(0,0,0), 1);
      assertEquals(new Vector(new Point3D(0,0,1)),sp.getNormal(new Point3D(0,0,1)));
	}

}
