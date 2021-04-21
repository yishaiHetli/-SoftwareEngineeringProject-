package renderer;


import java.util.*;

import elements.*;
import primitives.*;
import scene.*;
/**
 * 
 * @author david&yishai
 *
 */
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
/**
 * the function crate (nX/interval  *  nY/interval) squares
 * @param interval  the number of which to divide nX and nY 
 * @param color  the wanted color to paint the pixels with
 */
	public void printGrid(int interval, Color color) {
		if(imageWriter == null || camera == null || rayTracer == null || scene == null )
			throw new  MissingResourceException("argument was missing","Render","object");
		int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {//for every column 
            for (int j = 0; j < nX; j++) {//for every row
                if (i % interval == 0 || j % interval == 0) { 
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
	}
/**
 * 
 */
	public void writeToImage() {
		if(imageWriter == null || camera == null || rayTracer == null || scene == null )
			throw new  MissingResourceException("argument was missing","Render","object");
		 imageWriter.writeToImage();
	}
/**
 * 
 * @param scene  value of Scene to put in the field scene
 * @return this.scene
 */
	public Render setScene(Scene scene) {
		this.scene = scene;
		return this;
	}
/**
 * 
 * @param camera  value of Camera to put in the field camera
 * @return this.camera
 */
	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}
/**
 * 
 * @param rayTracer  value of RayTracer to put in the field rayTracer
 * @return this.rayTracer
 */
	public Render setRayTracer(RayTracerBase rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}
/**
 * 
 * @param imageWriter  value of ImageWriter to put in the field imageWriter
 * @return this.imageWriter
 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

}
