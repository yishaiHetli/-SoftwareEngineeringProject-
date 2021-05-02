package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import static primitives.Util.alignZero;

public class SpotLight extends PointLight {

	private Vector direction;
	private double narrowBeam;

	/**
	 * 
	 * @param _intensity
	 * @param _position
	 * @param _direction
	 * @param _kC
	 * @param _kL
	 * @param _kQ
	 * @param _narrowBeam
	 */
	public SpotLight(Color _intensity, Point3D _position, Vector _direction, double _kC, double _kL, double _kQ,
			double _narrowBeam) {
		super(_intensity, _position, _kC, _kL, _kQ);
		this.direction = _direction.normalize();
		this.narrowBeam = _narrowBeam;
	}

	/**
	 * 
	 * @param _intensity
	 * @param _position
	 * @param _direction
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
		double cosDirL = direction.dotProduct(l);
		if (alignZero(cosDirL) <= 0)
			return Color.BLACK;
		cosDirL = Math.pow(cosDirL, narrowBeam);
		return super.getIntensity(p).scale(cosDirL);
	}
}