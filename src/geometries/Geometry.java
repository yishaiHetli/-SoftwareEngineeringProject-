package geometries;

import primitives.*;

/**
 * abstract class that all that inherited from him must implement its abstract
 * methods
 * 
 * @author David&Yishai
 *
 */
public abstract class Geometry implements Intersectable {

	protected Color emission = Color.BLACK;
	private Material material = new Material();

	/**
	 * getter for emission feild
	 * 
	 * @return the emission color
	 */
	public Color getEmission() {
		return emission;
	}

	/**
	 * getter for material feild
	 * 
	 * @return the light material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * setter for emission feild
	 * 
	 * @param emission the value to set
	 * @return the class object
	 */
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}

	/**
	 * setter for material feild
	 * 
	 * @param material object in type of Material to set
	 * @return the class object
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}

	/**
	 * find the normal to the point that receive
	 * 
	 * @param point the point to check it's normal
	 * @return vector that normal to the received point
	 */
	public abstract Vector getNormal(Point3D point);
}
