package net.ohnobees.bimorphic.image;

import java.io.IOException;

import net.ohnobees.bimorphic.image.BinaryImage;
import net.ohnobees.bimorphic.image.Morphology;
import net.ohnobees.bimorphic.image.StructuringElement;
import net.ohnobees.bimorphic.utils.BinaryImageIO;


public class TestImageGenerator {
	public static void setupTestImages() throws IOException {
		BinaryImage bi = BinaryImageFactory.getImageInstance(256,256);
		int origin = bi.data.length/2 + 128;
		bi.data[origin] = 1;
		BinaryImageIO.write(bi, "src/test/resources/singlePixel.png");
		
		StructuringElement se = new StructuringElement(3,3);
		se.setPixel(0, 1, true);
		se.setPixel(2, 1, true);
		se.setPixel(1, 1, true);
		se.setPixel(1, 0, true);
		se.setPixel(1, 2, true);
		
		BinaryImage cross = Morphology.dilate(bi, se);
		BinaryImageIO.write(cross, "src/test/resources/cross.png");
		
		se = new StructuringElement(128,1);
		for (int i = 0; i < 128; i++)
			se.setPixel(i, 0, true);
		BinaryImage hline = Morphology.dilate(bi, se);
		BinaryImageIO.write(hline, "src/test/resources/hline.png");
		
		se = new StructuringElement(1,128);
		for (int i = 0; i < 128; i++)
			se.setPixel(0, i, true);
		BinaryImage vline = Morphology.dilate(bi, se);
		BinaryImageIO.write(vline, "src/test/resources/vline.png");
		BinaryImage square = Morphology.dilate(hline, se);
		BinaryImageIO.write(square, "src/test/resources/square.png");
		
		square.data[origin] = 0;
		se = new StructuringElement(1,60);
		for (int i = 0; i < 60; i++)
		{
			se.setPixel(0, i, true);
		}
		BinaryImage border = Morphology.erode(square, se);
		se = new StructuringElement(60,1);
		for (int i = 0; i < 60; i++)
		{
			se.setPixel(i, 0, true);
		}
		border = Morphology.erode(border, se);
		BinaryImageIO.write(border, "src/test/resources/border.png");
	}
}
