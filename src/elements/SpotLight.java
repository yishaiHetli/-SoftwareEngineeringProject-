package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import static primitives.Util.alignZero;

/**
 * class for light in type of spotlight
 * @author David&Yishai
 *
 */
public class SpotLight extends PointLight {

	private Vector direction;
	private double narrowBeam;

	/**
	 * ctor for spot light class
	 * 
	 * @param _intensity  the light intensity
	 * @param _position   light starting point
	 * @param _direction  the light direction vector
	 * @param _kC
	 * @param _kL
	 * @param _kQ
	 * @param _narrowBeam exponent for the angle between the light direction and the
	 *                    object , get 1 by default
	 */
	public SpotLight(Color _intensity, Point3D _position, Vector _direction, double _kC, double _kL, double _kQ,
			double _narrowBeam) {
		super(_intensity, _position, _kC, _kL, _kQ);
		this.direction = _direction.normalize();
		this.narrowBeam = _narrowBeam;
	}

	/**
	 * ctor for spot light class
	 * 
	 * @param _intensity the light intensity
	 * @param _position  light starting point
	 * @param _direction the light direction vector
	 * @param _kC
	 * @param _kL
	 * @param _kQ
	 */
	public SpotLight(Color _intensity, Point3D _position, Vector _direction, double _kC, double _kL, double _kQ) {
		this(_intensity, _position, _direction, _kC, _kL, _kQ, 1d);
	}

	@Override
	public Color getIntensity(Point3D p) {
		Vector l = super.getL(p);
		if (l == null) {
			return Color.BLACK;
		}
		double cosDirL = direction.dotProduct(l); // dot product between two normelized vectors gives as the angle
													// between the light direction and the object
		if (alignZero(cosDirL) <= 0)
			return Color.BLACK;
		if (narrowBeam > 1)
			cosDirL = Math.pow(cosDirL, narrowBeam);
		return super.getIntensity(p).scale(cosDirL);
	}
}