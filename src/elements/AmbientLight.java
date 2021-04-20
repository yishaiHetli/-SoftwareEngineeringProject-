package elements;

import primitives.*;

public class AmbientLight {

	private Color intensity;

	public AmbientLight(Color iA, double kA) {

		intensity = iA.scale(kA);
	}

	public Color getIntensity() {
		return intensity;
	}

}
