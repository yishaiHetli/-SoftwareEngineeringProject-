package renderer;

import java.util.List;
import primitives.*;
import scene.Scene;
/**
 * the class help the render to process the image by the ray intersection with the geometries
 * @author david&yishai
 *
 */
public class RayTracerBasic extends RayTracerBase {

	@Override
	public Color traceRay(Ray ray) {
		List<Point3D> intersections = scene.geometries.findIntersections(ray);
		if(intersections == null) // if there are no intersections return the color background 
			return scene.background;
		Point3D closestPoint = ray.findClosestPoint(intersections); // find  the closest point to the ray from all the intersections
		return calcColor(closestPoint); 
	}
/**
 * 
 * @param point yet to be explained
 * @return the color of the point
 */
	private Color calcColor(Point3D point) {
		return scene.ambientLight.getIntensity();
	}
/**
 * 
 * @param _scene type of Scene
 */
	public RayTracerBasic(Scene _scene) {
		super(_scene);
	}

}
