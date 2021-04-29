package elements;

import primitives.Color;

/**
 * Abstract class for represent all source of light
 * 
 * @author User
 *
 */
abstract class Light {

	private Color intensity;

	protected Light(Color _intensity) {
		this.intensity = _intensity;
	}

	/**
	 * getter for the intensity field
	 * @return the intensity color
	 */
	public Color getIntensity() {
		return intensity;
	}

	
}
