package renderer;

import java.util.*;

import elements.*;
import primitives.*;

/**
 * class to process the image color by calculating the color for each pixel
 * 
 * @author David&Yishai
 *
 */
public class Render {
	private Camera camera;
	private RayTracerBase rayTracer;
	private ImageWriter imageWriter;

	/**
	 * calculate the color for each point in the grid by the ray-camera intersection
	 */
	public void renderImage() {
		builder();
		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();
		for (int i = 0; i < nY; i++) { // loop on all the columns
			for (int j = 0; j < nX; j++) { // loop on all the rows
				Ray ray = camera.constructRayThroughPixel(nX, nY, j, i); // fine the location of the ray on the view
																			// plane
				Color color = rayTracer.traceRay(ray); // the point color
				imageWriter.writePixel(j, i, color); // paint the pixel in sent color
			}
		}
	}

	/**
	 * the function crate (nX/interval * nY/interval) squares in the image
	 * 
	 * @param interval the number of which to divide nX and nY
	 * @param color    the wanted color to paint the pixels with
	 */
	public void printGrid(int interval, Color color) {
		builder();
		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();
		for (int i = 0; i < nY; i++) {// loop on all the columns
			for (int j = 0; j < nX; j++) {// loop on all the rows
				if (i % interval == 0 || j % interval == 0) {
					imageWriter.writePixel(j, i, color);
				}
			}
		}
	}

	/**
	 * Create the image after geting all the image data
	 */
	public void writeToImage() {
		builder();
		imageWriter.writeToImage();
	}

	/**
	 * Check if some faild was missing and if so throws an exception
	 */
	private void builder() {
		if (imageWriter == null)
			throw new MissingResourceException("argument was missing", "Render", "imageWriter");
		if (camera == null)
			throw new MissingResourceException("argument was missing", "Render", "camera");
		if (rayTracer == null)
			throw new MissingResourceException("argument was missing", "Render", "rayTracer");
		if (rayTracer.scene == null)
			throw new MissingResourceException("argument was missing", "Render", "scene");
	}



	/**
	 * 
	 * @param camera value of Camera to put in the field camera
	 * @return the class object
	 */
	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}

	/**
	 * 
	 * @param rayTracer value of RayTracer to put in the field rayTracer
	 * @return the class object
	 */
	public Render setRayTracer(RayTracerBase rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}

	/**
	 * 
	 * @param imageWriter value of ImageWriter to put in the field imageWriter
	 * @return the class object
	 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

}
