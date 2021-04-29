package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import static primitives.Util.alignZero;

public class SpotLight extends PointLight {

	private Vector direction;

	/**
	 *  ctor for spotLight class
	 * @param _intensity 
	 * @param _position
	 * @param _kC
	 * @param _kL
	 * @param _kQ
	 * @param _direction the light  direction vector
	 */
	public SpotLight(Color _intensity, Point3D _position, double _kC, double _kL, double _kQ, Vector _direction) {
		super(_intensity, _position, _kC, _kL, _kQ);
		this.direction = _direction.normalize();
	}

	@Override
	public Color getIntensity(Point3D p) {
		Vector l = super.getL(p);
		if(l == null)
		{
			 return Color.BLACK;
		}
		double cosDirL = direction.dotProduct(l);
		if (alignZero(cosDirL) <= 0)
            return Color.BLACK;
		 return super.getIntensity(p).scale(cosDirL);
	}
}