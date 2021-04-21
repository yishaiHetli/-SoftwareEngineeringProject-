package unittests.primitives;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import primitives.*;

public class RayTests {

	@Test
	public void findClosestPointTest() {
		Ray ray = new Ray(Point3D.ZERO, new Vector(1, 1, 0));
		assertNull(ray.findClosestPoint(List.of()));
		Point3D point = ray.findClosestPoint(List.of(new Point3D(1, 1, 1), new Point3D(2, 2, 1), new Point3D(3, 3, 1)));
		assertEquals(new Point3D(1, 1, 1), point);
		point = ray.findClosestPoint(List.of(new Point3D(3, 3, 0), new Point3D(2, 2, 0), new Point3D(1, 1, 0)));
		assertEquals(new Point3D(1, 1, 0), point);
	}

}
