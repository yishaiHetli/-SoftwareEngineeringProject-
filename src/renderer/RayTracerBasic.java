package renderer;

import geometries.Geometry;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;
import static primitives.Util.*;

import java.util.List;

import elements.LightSource;

/**
 * the class help the render to process the image by the ray intersection with
 * the geometries
 * 
 * @author David&Yishai
 *
 */
public class RayTracerBasic extends RayTracerBase {
	private static final double DELTA = 0.1;
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;

	/**
	 * 
	 * @param l  vector from the light source to point
	 * @param n  the normal to the point
	 * @param gp intersection point and geometry
	 * @return true if the point unshaded and false otherwise
	 */
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint gp) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA); // if n*(n*lD)>0 +delta else -delta
		Point3D point = gp.point.add(delta);// point += delta
		Ray lightRay = new Ray(point, lightDirection);// ray from point to light source
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));
		return intersections == null;
	}

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
	 * calculate the pixel color by adding the geometry color and the light effects
	 * 
	 * @param intersection intersection point and geometry
	 * @param ray          ray from the pixel in camera
	 * @return the point color
	 */
	private Color calcColor(GeoPoint intersection, Ray ray) {
		return scene.ambientLight.getIntensity().add(intersection.geometry.getEmission()).//
				add(calcLocalEffects(intersection, ray));
	}

	/**
	 * calculate all the lights effects on the intersection point
	 * 
	 * @param intersection intersection point and geometry
	 * @param ray          ray from the pixel in camera
	 * @return the calculated color
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Point3D point = intersection.point;
		Geometry geometry = intersection.geometry;
		Vector v = ray.getDir();
		Vector n = geometry.getNormal(point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		Material material = geometry.getMaterial();
		int nShininess = material.nShininess;
		double kd = material.kD, ks = material.kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) { // add to the color the sum of all lights effect
			Vector l = lightSource.getL(point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0 && unshaded(lightSource, l, n, intersection)) { // sign(nl) == sing(nv) and point unsheded
				Color lightIntensity = lightSource.getIntensity(point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity)
						.add(calcSpecular(ks, l, n, v, nShininess, lightIntensity)));
			}
		}
		return color;
	}

	/**
	 * calculate by the formula: Specular = kS*((-v*r)^nSh)*lightIntensity
	 * 
	 * @param ks
	 * @param l
	 * @param n
	 * @param v
	 * @param nShininess
	 * @param lightIntensity
	 * @return the color of Specular effects
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r;
		try {
			r = l.subtract(n.scale(2 * (l.dotProduct(n)))); // r = l - 2 *( l*n)*n
		} catch (Exception e) {
			return Color.BLACK;
		}
		double vDotR = v.scale(-1).dotProduct(r); // -v*r
		if (alignZero(vDotR) <= 0) { // if the angle is more than 90 degrees
			return Color.BLACK;
		}
		vDotR = Math.pow(vDotR, nShininess); // (-v*r)^nSh
		return lightIntensity.scale(ks * vDotR);
	}

	/**
	 * calculate by the formula: Diffusive = kD*|l*n|*lightIntensity
	 * 
	 * @param kd
	 * @param l
	 * @param n
	 * @param lightIntensity
	 * @return the color of Diffusive effects
	 */
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
