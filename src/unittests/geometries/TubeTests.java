package unittests.geometries;

import static org.junit.Assert.*;
import primitives.*;
import geometries.*;

import org.junit.Test;

public class TubeTests {

	@Test
	public void testGetNormal() {
		Ray ray = new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 2));
		Tube tube = new Tube(ray, 0);
		Vector val = tube.getNormal(new Point3D(2, 3, 4));
		double root_264 = Math.sqrt(264);
		assertEquals(new Vector(-8 / root_264, 2 / root_264, -14 / root_264), val);

	}

}
