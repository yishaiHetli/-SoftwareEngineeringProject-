package unittests.lights;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class GlossySurfaceAndDiffuseGlass {

//	 * Produce a picture of a two Polygons, a triangle,two spheres,a tube, 4
//	 * cylinders lighted by a 2 point lights and 3 spotlighs with transparent Sphere
//	 * producing partial shadow
//	 */
//	@Test
//	public void bonusTest() {
//		Camera camera = new Camera(new Point3D(-1000, 0, 120), new Vector(1, 0, -0.07), new Vector(0.07, 0, 1)) //
//				.setViewPlaneSize(100, 100).setDistance(1000);
//		scene.background = (new Color(10, 10, 70));
//		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.05));
//		scene.geometries.add(
////				new Polygon(new Point3D(0, 90, -30), new Point3D(0, 90, 0), new Point3D(0, -90, 0),
////						new Point3D(0, -90, -30)).setEmission(new Color(20, 20, 20)).setMaterial(
////								new Material().setkD(0.5).setkS(0.5).setnShininess(100).setkT(0).setkR(0.7)),
//				new Polygon(new Point3D(0, 200, 0), new Point3D(400, 200, 0), new Point3D(400, -200, 0),
//						new Point3D(0, -200, 0)).setEmission(new Color(20, 20, 20)).setMaterial(
//								new Material().setkD(0.5).setkS(0.5).setnShininess(100).setkT(0).setkR(0.5)),
////				,
//				new Sphere(new Point3D(200, 0, 12), 8).setEmission(new Color(java.awt.Color.CYAN)).setMaterial(
//						new Material().setkD(0.5).setkS(0.5).setnShininess(100).setkT(0).setkR(0.3).setkMatteG(0)));
////double kD, double kS, int nShininess, double kT, double kR
//		scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point3D(-25, 0, 3), new Vector(1200, 0, -100))
//				.setkC(1).setkL(0.00001).setkQ(0.0001));
//		scene.lights.add(new SpotLight(new Color(400, 400, 400), new Point3D(-10, 85, -28), new Vector(1, -1, 1))
//				.setkC(1).setkL(0.0000000001).setkQ(0.0000000001));
//		scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point3D(200, 0, 42.369), new Vector(1, 0, 1))
//				.setkC(1).setkL(0.0000000001).setkQ(0.0000000001));
////		scene.lights.add(new PointLight(new Color(255, 255, 255), new Point3D(100, 0, 90)).setkC(1).setkL(0.0001)
////				.setkQ(0.00001));
//		scene.lights.add(
//				new PointLight(new Color(255, 255, 255), new Point3D(-25, 0, 3)).setkC(1).setkL(0.0001).setkQ(0.00001));
//		Render render = new Render() //
//				.setImageWriter(new ImageWriter("testReflectionBeam", 800, 800)) //
//				.setCamera(camera) //
//				.setRayTracer(new RayTracerBasic(scene, 70));
//		render.renderImage();
//		render.writeToImage();
//	}

//	/**
//	 * Produce a picture of a sphere that reflected on glossy surface
//	 */
//	@Test
//	public void glossySurfaceTest() {
//		Scene scene = new Scene("Test scene");
//		Camera camera = new Camera(new Point3D(-1000, 0, 90), new Vector(1, 0, -0.05), new Vector(0.05, 0, 1))
//				.setViewPlaneSize(130, 130).setDistance(1000);
//		scene.setBackground(new Color(java.awt.Color.red));
//		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
//		scene.geometries.add(new Plane(new Point3D(300, 0, 0), new Vector(1, 0, 0))//
//				.setEmission(new Color(java.awt.Color.BLACK))
//				.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
//		scene.geometries.add(
//				new Sphere(new Point3D(50, 0, 20), 20).setEmission(new Color(10, 100, 100))
//						.setMaterial((new Material().setkD(0.5).setkS(0.5).setnShininess(100)).setkR(0.7)),
//				new Sphere(new Point3D(-50, 10, 10), 10).setEmission(new Color(10, 10, 100))
//						.setMaterial((new Material().setkD(0.5).setkS(0.5).setnShininess(100))),
//				new Sphere(new Point3D(-50, -10, 10), 10).setEmission(new Color(129, 129, 129))
//						.setMaterial((new Material().setkD(0.5).setkS(0.5).setnShininess(100))),
//				new Sphere(new Point3D(-50, 30, 10), 10).setEmission(new Color(0, 129, 129))
//						.setMaterial((new Material().setkD(0.5).setkS(0.5).setnShininess(100))),
//				new Sphere(new Point3D(-50, -30, 10), 10).setEmission(new Color(129, 129, 0))
//						.setMaterial((new Material().setkD(0.5).setkS(0.5).setnShininess(100))),
//				new Polygon(new Point3D(-700, -75, 0), new Point3D(900, -75, 0), new Point3D(900, 75, 0),
//						new Point3D(-700, 75, 0)).setEmission(new Color(10, 100, 100))
//								.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
//
//		scene.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(1, 0, -0.09)));
//		Render render = new Render() //
//				.setImageWriter(new ImageWriter("glossySurface", 1000, 1000)) //
//				.setCamera(camera) //
//				.setRayTracer(new RayTracerBasic(scene, 200));
//		render.renderImage();
//		render.writeToImage();
//	}

//	@Test
//	public void diffusedGlass() {
//		Scene scene = new Scene("Test scene");
//		Camera camera = new Camera(new Point3D(-450, 25, 10), new Vector(1, 0.05, 0.02), new Vector(-0.02, 0, 1))
//				.setViewPlaneSize(520, 520).setDistance(1100);
//		scene.setBackground(new Color(java.awt.Color.red));
//		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
//
//		// fron wall
//		scene.geometries
//				.add(new Plane(new Point3D(60, 0, 0), new Vector(1, 0, 0)).setEmission(new Color(java.awt.Color.gray))
//						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
//		// floor
//		scene.geometries.add(new Plane(new Point3D(0, 0, 0), new Vector(0, 0, 1)).setEmission(new Color(Color.BLACK))
//				.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
////		// ceiling
////		scene.geometries
////				.add(new Plane(new Point3D(0, 0, 400), new Vector(0, 0, 1)).setEmission(new Color(java.awt.Color.WHITE))
////						.setMaterial(new Material().setkD(0).setkS(1).setnShininess(100)));
//		scene.geometries.add(
//				new Polygon(new Point3D(0, 150, 0), new Point3D(0, 150, 90), new Point3D(0, 120, 90),
//						new Point3D(0, 120, 0)).setEmission(new Color(new Color(10, 10, 10))).setMaterial(
//								new Material().setkD(0.5).setkS(0.5).setnShininess(100).setkT(0.8).setkMatteD(0.05)),
//
//				new Polygon(new Point3D(0, 110, 0), new Point3D(0, 110, 90), new Point3D(0, 80, 90),
//						new Point3D(0, 80, 0)).setEmission(new Color(new Color(10, 10, 10))).setMaterial(
//								new Material().setkD(0.5).setkS(0.5).setnShininess(100).setkT(0.8).setkMatteD(0.15)),
//
//				new Polygon(new Point3D(0, 70, 0), new Point3D(0, 70, 90), new Point3D(0, 40, 90),
//						new Point3D(0, 40, 0)).setEmission(new Color(new Color(10, 10, 10))).setMaterial(
//								new Material().setkD(0.5).setkS(0.5).setnShininess(100).setkT(0.8).setkMatteD(0.25)),
//
//				new Polygon(new Point3D(0, 30, 0), new Point3D(0, 30, 90), new Point3D(0, 0, 90), new Point3D(0, 0, 0))
//						.setEmission(new Color(new Color(10, 10, 10))).setMaterial(
//								new Material().setkD(0.5).setkS(0.5).setnShininess(100).setkT(0.8).setkMatteD(0.35)),
//
//				new Polygon(new Point3D(0, -50, 0), new Point3D(0, -50, 90), new Point3D(0, -20, 90),
//						new Point3D(0, -20, 0)).setEmission(new Color(new Color(10, 10, 10))).setMaterial(
//								new Material().setkD(0.5).setkS(0.5).setnShininess(100).setkT(0.8).setkMatteD(1)),
//				new Sphere(new Point3D(-100, 55, 30), 20));
//		scene.lights.add(new SpotLight(new Color(500, 500, 500), new Point3D(-400, 55, 100), new Vector(-0.1, 0, -1), 4)
//				.setkC(1).setkL(0.0000001).setkQ(0.000000001));
////		scene.lights.add(
////				new SpotLight(new Color(java.awt.Color.yellow),new Point3D(-100, 55, 30), new Vector(1, 0, 0))
////				.setkC(1).setkL(0.0000001).setkQ(0.0000001));
//		scene.lights.add(new SpotLight(new Color(500, 500, 500), new Point3D(-600, 55, 100), new Vector(-0.1, 0, -1), 4)
//				.setkC(1).setkL(0.0000001).setkQ(0.000000001));
//		Render render = new Render() //
//				.setImageWriter(new ImageWriter("diffusedGlass", 500, 500)) //
//				.setCamera(camera) //
//				.setRayTracer(new RayTracerBasic(scene, 10));
//		render.renderImage();
//		render.writeToImage();
//	}

	@Test
	public void diffusedGlass() {
		Scene scene = new Scene("Test scene");
		Camera camera = new Camera(new Point3D(22, -100, 10), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
				.setViewPlaneSize(200, 200).setDistance(400);
		
		Point3D a1 = new Point3D(0, 0, 0);
		Point3D b1 = new Point3D(7, 0, 0);
		Point3D c1 = new Point3D(7, 0, 20);
		Point3D d1 = new Point3D(0, 0, 20);

		Point3D a2 = new Point3D(12, 0, 0);
		Point3D b2 = new Point3D(19, 0, 0);
		Point3D c2 = new Point3D(19, 0, 20);
		Point3D d2 = new Point3D(12, 0, 20);
		Point3D a3 = new Point3D(24, 0, 0);
		Point3D b3 = new Point3D(31, 0, 0);
		Point3D c3 = new Point3D(31, 0, 20);
		Point3D d3 = new Point3D(24, 0, 20);
		Point3D a4 = new Point3D(36, 0, 0);
		Point3D b4 = new Point3D(43, 0, 0);
		Point3D c4 = new Point3D(43, 0, 20);
		Point3D d4 = new Point3D(36, 0, 20);
//(12,-100,-10)
		scene.geometries.add( //
				
				//table
				new Plane(a1,new Vector(0,0,1)) //
				.setEmission(new Color(255, 209, 254)) //
				.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100)),
				new Plane(new Point3D(0,100,0),new Vector(0,1,0)) //
				.setEmission(new Color(182, 245, 244)) //
				.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100)),
				new Polygon(a1, b1, c1, d1) //
						.setEmission(new Color(0, 0, 255)) //
						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(1).setkMatteD(0)),
				new Polygon(a2, b2, c2, d2) //
						.setEmission(new Color(0, 0, 255)) //
						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(1).setkMatteD(0.3)),
				new Polygon(a3, b3, c3, d3) //
						.setEmission(new Color(0, 0, 255)) //
						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(1).setkMatteD(0.8)),
				new Polygon(a4, b4, c4, d4) //
						.setEmission(new Color(0, 0, 255)) //
						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(1).setkMatteD(1.5)),
				new Sphere(new Point3D(8.5, 10, 5), 5) //
						.setEmission(new Color(java.awt.Color.CYAN)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
				new Sphere(new Point3D(21.5, 10, 5), 5) //
						.setEmission(new Color(java.awt.Color.CYAN)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
				new Sphere(new Point3D(34.5, 10, 5), 5) //
						.setEmission(new Color(java.awt.Color.CYAN)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
		scene.lights.add( //
				new DirectionalLight(new Color(1000, 600, 0) , new Vector(12,-50,-30)));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("4mirrors", 1800, 1800)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene,100));
		render.renderImage();
		render.writeToImage();

	}
}
