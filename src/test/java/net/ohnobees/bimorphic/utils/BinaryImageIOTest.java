package net.ohnobees.bimorphic.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import net.ohnobees.bimorphic.functors.BinarizationException;
import net.ohnobees.bimorphic.image.BinaryImage;
import net.ohnobees.bimorphic.image.BinaryImageFactory;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryImageIOTest {
	@Test
	public void testRead() throws IOException, BinarizationException, URISyntaxException {
		BinaryImage bi = BinaryImageIO.readBinaryImage(TestUtils.fileFromResource("lines.png"));
		int[] expected = new int[bi.getData().length];

		for (int i = 0; i < expected.length; i += 2)
			expected[i] = 1;
		assertArrayEquals(expected, bi.getData());
	}
	
	@Test
	public void testWriteRead() throws IOException, BinarizationException {
		BinaryImage bi = BinaryImageFactory.getImageInstance(256, 256);
		int[] data = bi.getData();
		for (int i = 0; i < data.length; i += 2)
			data[i] = 1;
		File tmpImg = File.createTempFile("binaryimageio", ".png");
		tmpImg.deleteOnExit();
		BinaryImageIO.write(bi, tmpImg);
		BinaryImage bir = BinaryImageIO.readBinaryImage(tmpImg);
		assertEquals(bi, bir);
	}
}
