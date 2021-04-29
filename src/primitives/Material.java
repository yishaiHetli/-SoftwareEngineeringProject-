package primitives;

/**
 * class for the lights material
 * 
 * @author David&Yishai
 *
 */
public class Material {

	public double kS = 0, kD = 0;
	public int nShininess = 0;

	/**
	 * setter for kS feild
	 * 
	 * @param kS
	 * @return the class object
	 */
	public Material setkS(double kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * setter for kD feild
	 * 
	 * @param kD
	 * @return the class object
	 */
	public Material setkD(double kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * setter for nShininess feild
	 * 
	 * @param nShininess
	 * @return the class object
	 */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}

}
