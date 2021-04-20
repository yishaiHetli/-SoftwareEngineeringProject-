package scene;

import elements.AmbientLight;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

public class Scene {

	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight = new AmbientLight(Color.BLACK, 0.0);
	public Geometries geometries = new Geometries();

	public Scene(String _name) {
		this.name = _name;
	}

	public Scene setName(String name) {
		this.name = name;
		return this;
	}

	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	public Scene addGeometry(Intersectable geo) {
		geometries.add(geo);
		return this;
	}

}
