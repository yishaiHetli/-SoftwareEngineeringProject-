package elements;

import primitives.*;
/**
 * class that calculate the light
 * @author david&yishai
 *
 */
public class AmbientLight {

	private Color intensity;
/**
 * ctor calculate the intensity by multiply iA with kA
 * @param iA  Color
 * @param kA  double
 */
	public AmbientLight(Color iA, double kA) {

		intensity = iA.scale(kA);
	}
/**
 * 
 * @return  intensity
 */
	public Color getIntensity() {
		return intensity;
	}

}
