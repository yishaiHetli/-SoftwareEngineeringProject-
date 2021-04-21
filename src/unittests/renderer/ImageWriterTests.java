package unittests.renderer;

import org.junit.Test;

import renderer.ImageWriter;
import primitives.*;
/**
 * Tests for ImageWriter class
 * @author David&&Yishai
 *
 */
public class ImageWriterTests {

	/**
	 * Test method for {@link rander.ImageWriter#writeImage(String,int,int)}.
	 */
	@Test
	public void writeImageTest() {
		ImageWriter image = new ImageWriter("image1", 800, 500); // image in 800*500 resolution
		Color colorBlue = new Color(java.awt.Color.blue);
		Color colorRed = new Color(java.awt.Color.red);
		for (int i = 0; i < 500; i++) { // loop on the lines
			for (int j = 0; j < 800; j++) { // loop on the columns
				if (i % 50 == 0 || j % 50 == 0) { //16*10 squares
					image.writePixel(j, i, colorRed);
				} else // inside the Squares
					image.writePixel(j, i, colorBlue);
			}
		}
		image.writeToImage();// create the image
	}
}
