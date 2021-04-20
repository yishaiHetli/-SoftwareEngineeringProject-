package renderer;

import primitives.*;
import scene.*;

public abstract class RayTracerBase {

	protected Scene scene; 
    abstract Color traceRay(Ray ray);
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}
	
	
}
