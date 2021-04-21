package unittests.renderer;

import org.junit.Test;

import renderer.ImageWriter;
import primitives.*;

public class ImageWriterTests {

	@Test
	public void writeImageTest() {
		ImageWriter image = new ImageWriter("image1", 800, 500);
		Color colorBlue = new Color(java.awt.Color.blue);
		Color colorRed = new Color(java.awt.Color.red);
		for (int i = 0; i < 500; i++) {
			for (int j = 0; j < 800; j++) {
				if (i % 50 == 0 || j % 50 == 0) {
					image.writePixel(j, i, colorRed);
				} else
					image.writePixel(j, i, colorBlue);
			}
		}
		image.writeToImage();
	}
}
