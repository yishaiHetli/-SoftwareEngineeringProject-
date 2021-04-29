package renderer;

import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;
import static primitives.Util.*;

import elements.LightSource;

/**
 * the class help the render to process the image by the ray intersection with
 * the geometries
 * 
 * @author david&yishai
 *
 */
public class RayTracerBasic extends RayTracerBase {

	@Override
	public Color traceRay(Ray ray) {
		var intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return scene.background; // if there are no intersections return the color background
		GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);// find the closest point to the ray from all the
																		// intersections
		return calcColor(closestPoint, ray);
	}

	/**
	 * 
	 * @param intersection yet to be explained
	 * @param ray
	 * @return the point color
	 */
	private Color calcColor(GeoPoint intersection, Ray ray) {
		return scene.ambientLight.getIntensity().add(intersection.geometry.getEmission()).//
				add(calcLocalEffects(intersection, ray));
	}

	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		Material material = intersection.geometry.getMaterial();
		int nShininess = material.nShininess;
		double kd = material.kD, ks = material.kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity)
						.add(calcSpecular(ks, l, n, v, nShininess, lightIntensity)));
			}
		}
		return color;
	}

	/**
	 * 
	 * @param ks
	 * @param l
	 * @param n
	 * @param v
	 * @param nShininess
	 * @param lightIntensity
	 * @return
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r;
		try {
			r = l.subtract(n.scale(2 * (l.dotProduct(n)))); // r = l - 2 *( l*n)*n
		} catch (Exception e) {
			return Color.BLACK;
		}
		double vDotR = v.scale(-1).dotProduct(r);
		if (alignZero(vDotR) <= 0) {
			return Color.BLACK;
		}
		vDotR = Math.pow(vDotR, nShininess);
		return lightIntensity.scale(ks * vDotR);
	}

	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		return lightIntensity.scale(kd * (Math.abs(l.dotProduct(n))));
	}

	/**
	 * ctor that call super to initialize super scene feild
	 * 
	 * @param _scene
	 */
	public RayTracerBasic(Scene _scene) {
		super(_scene);
	}

}
