package unittests.renderer;

import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import org.w3c.dom.*;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.*;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class RenderTests {
	private Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0)) //
			.setDistance(100) //
			.setViewPlaneSize(500, 500);

	/**
	 * Produce a scene with basic 3D model and render it into a jpeg image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {

		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1))//
				.setBackground(new Color(75, 127, 90));

		scene.geometries.add(new Sphere(new Point3D(0, 0, -100), 50),
				new Triangle(new Point3D(-100, 0, -100), new Point3D(0, 100, -100), new Point3D(-100, 100, -100)), // up
																													// left
				new Triangle(new Point3D(100, 0, -100), new Point3D(0, 100, -100), new Point3D(100, 100, -100)), // up
																													// right
				new Triangle(new Point3D(-100, 0, -100), new Point3D(0, -100, -100), new Point3D(-100, -100, -100)), // down
																														// left
				new Triangle(new Point3D(100, 0, -100), new Point3D(0, -100, -100), new Point3D(100, -100, -100))); // down
																													// right

		ImageWriter imageWriter = new ImageWriter("base render test", 1000, 1000);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setScene(scene) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.printGrid(100, new Color(java.awt.Color.YELLOW));
		render.writeToImage();
	}

	/**
	 * Test for XML based scene - for bonus
	 */
	@Test
	public void basicRenderXml() {
		Scene scene = new Scene("XML Test scene");
		// enter XML file name and parse from XML file into scene object
		// ...
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("testXmlFile.xml");
			doc.getDocumentElement().normalize();
			NodeList sceneList = doc.getElementsByTagName(doc.getDocumentElement().getNodeName());
			for (int i = 0; i < sceneList.getLength(); ++i) {
				Node sceneNode = sceneList.item(i);
				if (sceneNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) sceneNode;
					List<Integer> list = stringToInt(element.getAttribute("background-color"));
					// System.out.print(list.get(0) + " " + list.get(1) + " " + list.get(2));
					Color background = new Color(list.get(0), list.get(1), list.get(2));
					list = stringToInt(getElement("ambient-light", "color", element, 0));
					// System.out.print(list.get(0) + " " + list.get(1) + " " + list.get(2));
					Color light = new Color(list.get(0), list.get(1), list.get(2));
					scene.setBackground(background).setAmbientLight(new AmbientLight(light, 1));
					double radius = Double.parseDouble(getElement("sphere", "radius", element, 0));
					list = stringToInt(getElement("sphere", "center", element, 0));
					scene.geometries.add(new Sphere(new Point3D(list.get(0), list.get(1), list.get(2)), radius));
					int size = element.getElementsByTagName("triangle").getLength();
					for (int j = 0; j < size; ++j) {
						list = stringToInt(getElement("triangle", "p0", element, j));
						Point3D p0 = new Point3D(list.get(0), list.get(1), list.get(2));
						list = stringToInt(getElement("triangle", "p1", element, j));
						Point3D p1 = new Point3D(list.get(0), list.get(1), list.get(2));
						list = stringToInt(getElement("triangle", "p2", element, j));
						Point3D p2 = new Point3D(list.get(0), list.get(1), list.get(2));
						scene.geometries.add(new Triangle(p0, p1, p2));
					}
				}
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

		ImageWriter imageWriter = new ImageWriter("xml render test", 1000, 1000);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setScene(scene) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.printGrid(100, new Color(java.awt.Color.YELLOW));
		render.writeToImage();
	}

	public String getElement(String tag, String attribute, Element element, int item) {
		element = (Element) element.getElementsByTagName(tag).item(item);
		return element.getAttribute(attribute);
	}

	public List<Integer> stringToInt(String str) {
		Scanner scanner = new Scanner(str);
		List<Integer> list = new LinkedList<Integer>();
		while (scanner.hasNextInt()) {
			list.add(scanner.nextInt());
		}
		return list;
	}

}
