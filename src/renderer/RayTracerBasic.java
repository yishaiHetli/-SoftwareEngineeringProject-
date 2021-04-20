package renderer;

import java.util.List;
import primitives.*;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase {

	@Override
	public Color traceRay(Ray ray) {
		List<Point3D> intersections = scene.geometries.findIntersections(ray);
		if(intersections == null)
			return scene.background;
		Point3D closestPoint = ray.findClosestPoint(intersections);
		return calcColor(closestPoint);
	}

	private Color calcColor(Point3D point) {
		return scene.ambientLight.getIntensity();
	}

	public RayTracerBasic(Scene _scene) {
		super(_scene);
	}

}
