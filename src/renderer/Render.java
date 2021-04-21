package renderer;


import java.util.*;

import elements.*;
import primitives.*;
import scene.*;

public class Render {

	private Scene scene;
	private Camera camera;
	private RayTracerBase rayTracer;
	private ImageWriter imageWriter;

	public void renderImage() {
		if(imageWriter == null || camera == null || rayTracer == null || scene == null )
			throw new  MissingResourceException("argument was missing","Render","object");
		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();
		for (int i = 0; i < nY; i++) {
			for (int j = 0; j < nX; j++) {      
				Ray ray = camera.constructRayThroughPixel(nX, nY, j, i);
				Color color = rayTracer.traceRay(ray);
				imageWriter.writePixel(j, i, color);
			}
		}
	}

	public void printGrid(int interval, Color color) {
		if(imageWriter == null || camera == null || rayTracer == null || scene == null )
			throw new  MissingResourceException("argument was missing","Render","object");
		int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) { 
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
	}

	public void writeToImage() {
		if(imageWriter == null || camera == null || rayTracer == null || scene == null )
			throw new  MissingResourceException("argument was missing","Render","object");
		 imageWriter.writeToImage();
	}

	public Render setScene(Scene scene) {
		this.scene = scene;
		return this;
	}

	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}

	public Render setRayTracer(RayTracerBase rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}

	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

}
