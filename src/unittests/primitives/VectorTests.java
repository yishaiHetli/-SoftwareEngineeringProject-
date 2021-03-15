package unittests.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

public class VectorTests {

	@Test
	public void testAdd() {
		Vector x = new Vector(1,2,3);
		Vector val = x.add(x);
		assertEquals(new Vector(2,4,6), val);
	}

	@Test
	public void testSubtract() {
		Vector x = new Vector(2,4,6);
		Vector sub = new Vector(1,2,3);
		Vector val = x.subtract(sub);
		assertEquals(sub, val);
		try {
			x.subtract(x);
			fail("the vector can't be zero");	
		}
		catch (Exception e) {
			//the method should return error
		}
	}

	@Test
	public void testScale() {
		Vector x = new Vector(1,2,3);
		Vector val = x.scale(2);
		assertEquals(new Vector(2,4,6), val);
	}

	@Test
	public void testCrossProduct() {
		Vector x = new Vector(1,0,0);
		Vector val = x.crossProduct(new Vector(0,1,0));
		assertEquals(new Vector(0,0,1), val);
		try {
		 x.crossProduct(x);
		fail("the vector can't be zero");	
		}
		catch (Exception e) {
			//the method should return error
		}
	}

	@Test
	public void testDotProduct() {
		Vector x = new Vector(1,2,3);
		double val = x.dotProduct(x);
		assertEquals(14, val,0.00001);
	}

	@Test
	public void testLengthSquared() {
		Vector x = new Vector(1,2,3);
		double val = x.lengthSquared();
		assertEquals(14, val,0.00001);
	}

	@Test
	public void testLength() {
		Vector x = new Vector(1,2,3);
		double val = x.length();
		assertEquals(Math.sqrt(14), val,0.00001);
	}

	@Test
	public void testNormalize() {
		Vector x = new Vector(1,1,1);
		x.normalize();
		double root_3 = 1/Math.sqrt(3);
		assertEquals(new Vector(root_3,root_3,root_3), x);
	}

	@Test
	public void testNormalized() {
		Vector x = new Vector(1,1,1);
		Vector y = x.normalized();
		double root_3 = 1/Math.sqrt(3);
		assertEquals(new Vector(root_3,root_3,root_3), y);
		assertEquals(new Vector(1,1,1), x);
	}

}
