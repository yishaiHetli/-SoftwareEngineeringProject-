package unittests.renderer;

import static primitives.Util.alignZero;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Plane;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

public class MultiTriangleInCircle {
	private static final Material mat = new Material().setkD(0.5).setkS(0.5).setnShininess(60);
	private static final double pi = Math.PI;

	@Test
	public void MultiTrianglestest() {
		Camera camera = new Camera(new Point3D(0, 0, 5), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(3.5, 3.5).setDistance(5);
		Scene scene = new Scene("Test scene").setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2)); //
		scene.background = new Color(10,100,100);
		Vector addP2 = new Vector(0.03, 0, 1);
		Vector addP3 = new Vector(0, 0.03, 1);
		Point3D t1, t2, t3;

		for (int i = 0; i < 3000; ++i) { // creates multiple triangles
			t1 = t2 = t3 = randomPointIncircle();
			t2 = t2.add(addP2);
			t3 = t3.add(addP3);
			scene.geometries.add(new Triangle(t1, t2, t3).setEmission(randomColor()).setMaterial(mat));
		}
		scene.geometries.add(new Plane(new Point3D(0, 0, 0), new Vector(0, 0, 1)).setEmission(new Color(10, 100, 100))
				.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
		
		scene.geometries.createBox();
		scene.geometries.callMakeTree();
		
		camera.rotateX(45);
		camera.moveForth(1);
		camera.moveUp(0.5);
		scene.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-200, -160, 300), new Vector(-1, -1, -4)) //
				.setkL(0.00001).setkQ(0.000005));
		scene.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(75, 75, -150), new Vector(-136, -96, 69)) //
				.setkL(0.00001).setkQ(0.000005));
		
		ImageWriter imageWriter = new ImageWriter("MultiTrianglesRotate", 1000, 1000);
		Render render = new Render() //
				.setCamera(camera) //
				.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)).setMultithreading(3).setDebugPrint();
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * gives a random point in circle when the radius is one and the center is point
	 * ZERO
	 * 
	 * @return random 3D point in circle
	 */
	private Point3D randomPointIncircle() {
		double r = Math.sqrt(Math.random()); // multiply by radius(1 in our case)
		double theta = Math.random() * 2 * pi;
		double x = alignZero(r * Math.cos(theta)); // add to center coord x (0 in our case)
		double y = alignZero(r * Math.sin(theta)); // add to center coord y (0 in our case)
		return new Point3D(x, y, 0);
	}

	/**
	 * Gives a random color of rgb
	 * 
	 * @return random color
	 */
	private Color randomColor() {
		double r = Util.random(0, 255);
		double g = Util.random(0, 255);
		double b = Util.random(0, 255);
		return new Color(r, g, b);
	}

}
