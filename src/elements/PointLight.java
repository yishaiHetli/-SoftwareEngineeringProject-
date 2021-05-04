package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for light in type of point light
 * 
 * @author David&Yishai
 *
 */
public class PointLight extends Light implements LightSource {

	private Point3D position;
	private double kC, kL, kQ;

	/**
	 * ctor for PointLight class
	 * 
	 * @param _intensity the light intensity color
	 * @param _position  the light position point
	 * @param _kC
	 * @param _kL
	 * @param _kQ
	 */
	public PointLight(Color _intensity, Point3D _position, double _kC, double _kL, double _kQ) {
		super(_intensity);
		this.position = _position;
		this.kC = _kC;
		this.kL = _kL;
		this.kQ = _kQ;
	}

	@Override
	public Color getIntensity(Point3D p) {
		double dSquare = position.distanceSquared(p);
		double d = position.distance(p);
		return getIntensity().reduce(kC + kL * d + kQ * dSquare);
	}

	@Override
	public Vector getL(Point3D p) {
		if (p.equals(position))
			return null;
		return p.subtract(position).normalize();
	}

}
