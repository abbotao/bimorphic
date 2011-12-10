package net.ohnobees.bimorphic.image;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class BinaryImageTest {

	@Test
	public void test() throws IOException {
		BufferedImage bi = new BufferedImage(4096, 4096, BufferedImage.TYPE_3BYTE_BGR);
		int pixel = 0;
		for (int r = 0; r < 256; r++)
			for (int b = 0; b < 256; b++)
				for (int g = 0; g < 256; g++)
				{
					int rgb = r << 16 | g << 8 | b;
					bi.setRGB(pixel%4096, pixel/4096, rgb);
					pixel++;
				}
		//fail("Not yet implemented");
	}

}
