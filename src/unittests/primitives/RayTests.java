package unittests.primitives;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import primitives.*;

/**
 * Testing for Ray class  
 * @author David&Yishai
 *
 */
public class RayTests {

	/**
	 *  Test method for {@link primitives.Ray#findClosestPoint(List<primitives.Point3D>)}.
	 */
	@Test
	public void findClosestPointTest() {
		Ray ray = new Ray(Point3D.ZERO, new Vector(1, 1, 0));
		//TC01 find the closest point from an empty list of points 
		assertNull(ray.findClosestPoint(List.of()));
		//TC02 find the closest point when the closest point is in the first place in the list 
		Point3D point = ray.findClosestPoint(List.of(new Point3D(1, 1, 0), new Point3D(2, 2, 0), new Point3D(3, 3, 0)));
		assertEquals(new Point3D(1, 1, 0), point);
		//TC03 find the closest point when the closest point is in the last place in the list
		point = ray.findClosestPoint(List.of(new Point3D(3, 3, 0), new Point3D(2, 2, 0), new Point3D(1, 1, 0)));
		assertEquals(new Point3D(1, 1, 0), point);
	}

}
