package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;
import geometries.*;
import primitives.*;
public class SphereTests {

	@Test
	public void testGetNormal() {
	 Sphere sp = new Sphere(new Point3D(1,2,3), 0);
	  double root_12 =  2/Math.sqrt(12);
	  assertEquals(new Vector(root_12, root_12, root_12),sp.getNormal(new Point3D(3,4,5)));
	}

}
