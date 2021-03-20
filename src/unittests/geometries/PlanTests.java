package unittests.geometries;

import geometries.*;
import primitives.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PlanTests {

	@Test
	public void GetNormal() {
		Plane v = new Plane(new Point3D(1,0,0),new Point3D(0,0,1),new Point3D(0,1,0));
		assertEquals(new Vector (-1,0,0),v.getNormal());
	}
	@Test
	public void testGetNormal() {
		Plane v = new Plane(new Point3D(1,0,0),new Point3D(0,0,1),new Point3D(0,1,0));
		assertEquals(new Vector (-1,0,0),v.getNormal(new Point3D(1,1,1)));
	}

}
