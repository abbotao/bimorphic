package net.ohnobees.bimorphic.image;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import net.ohnobees.bimorphic.functors.BinarizationException;
import net.ohnobees.bimorphic.functors.FalseBinarizationFunctor;
import net.ohnobees.bimorphic.functors.MedianBinarizationFunctor;
import net.ohnobees.bimorphic.utils.BinaryImageIO;
import net.ohnobees.bimorphic.utils.TestUtils;

import org.junit.Test;

public class BinaryImageTest {

	@Test
	public void testLoadImage() throws BinarizationException, IOException, URISyntaxException {
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
		BinaryImage bin = BinaryImage.loadImage(bi, new FalseBinarizationFunctor());
		assertArrayEquals(new int[4096*4096], bin.getData());
		bin = BinaryImage.loadImage(bi, new MedianBinarizationFunctor());
		
		BinaryImage bir = BinaryImageIO.readBinaryImage(TestUtils.fileFromResource("24bitMedianBinarized.png"));
		assertEquals(bir,bin);
	}
}
