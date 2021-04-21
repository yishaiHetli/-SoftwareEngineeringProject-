package renderer;


import java.util.*;

import elements.*;
import primitives.*;
import scene.*;
/**
 *  class to process the image color by calculating the color for each pixel 
 *     
 * @author david&yishai
 *
 */
public class Render {

	private Scene scene;
	private Camera camera;
	private RayTracerBase rayTracer;
	private ImageWriter imageWriter;

	/**
	 * calculate the color for each point in the grid by the ray-camera intersection
	 */
	public void renderImage() {
		if(imageWriter == null || camera == null || rayTracer == null || scene == null ) // if one or more objects are massing 
			throw new  MissingResourceException("argument was missing","Render","object");
		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();
		for (int i = 0; i < nY; i++) { // loop on all the columns 
			for (int j = 0; j < nX; j++) {  // loop on all the rows
				Ray ray = camera.constructRayThroughPixel(nX, nY, j, i); // fine the location of the ray on the view plane
				Color color = rayTracer.traceRay(ray); // the point color 
				imageWriter.writePixel(j, i, color); // paint the pixel in sent color 
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
        for (int i = 0; i < nY; i++) {//loop on all the columns 
            for (int j = 0; j < nX; j++) {//loop on all the rows
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
