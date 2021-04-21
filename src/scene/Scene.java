package scene;

import elements.AmbientLight;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;
/**
 * 
 * @author david&yishai
 *
 */
public class Scene {

	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight = new AmbientLight(Color.BLACK, 0.0);
	public Geometries geometries = new Geometries();
/**
 * 
 * @param _name  the value to put in the field name
 */
	public Scene(String _name) {
		this.name = _name;
	}
/**
 * 
 * @param name  the value to put in the field name
 * @return this.name
 */
	public Scene setName(String name) {
		this.name = name;
		return this;
	}
/**
 * 
 * @param background  the value to put in the field background
 * @return this.background
 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}
/**
 * 
 * @param ambientLight  the value to put in the field ambientLight 
 * @return this.ambientLight with the value of ambientLight
 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}
/**
 * 
 * @param geo  intersectable shape
 * @return a Scene performance with the new shape in the fields geometries
 */
	public Scene addGeometry(Intersectable geo) {
		geometries.add(geo);
		return this;
	}

}
