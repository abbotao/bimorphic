package net.ohnobees.bimorphic.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.ohnobees.bimorphic.functors.BinarizationException;
import net.ohnobees.bimorphic.functors.BinarizationFunctor;
import net.ohnobees.bimorphic.functors.BooleanBinaryFunctor;
import net.ohnobees.bimorphic.image.BinaryImage;

public class BinaryImageIO {
	public static BinaryImage read(String filePath) throws IOException, BinarizationException {
		return read(new File(filePath));
	}
	
	public static BinaryImage read(File file) throws IOException, BinarizationException {
		return read(file, null);
	}
	
	public static BinaryImage readBinaryImage(String filePath) throws IOException, BinarizationException {
		return read(new File(filePath));
	}
	
	public static BinaryImage readBinaryImage(File file) throws IOException, BinarizationException {
		return read(file, new BooleanBinaryFunctor());
	}
	
	public static BinaryImage read(String filePath, BinarizationFunctor functor) throws IOException, BinarizationException {
		return read(new File(filePath), functor);
	}
	
	public static BinaryImage read(File file, BinarizationFunctor functor) throws IOException, BinarizationException {
		BufferedImage bi = ImageIO.read(file);
		
		return BinaryImage.loadImage(bi, functor);
	}
	
	public static void write(BinaryImage bi, String filePath) throws IOException {
		write(bi, new File(filePath));
	}
	
	public static void write(BinaryImage bi, File file) throws IOException {
		BufferedImage img = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for (int y = 0; y < bi.getHeight(); y++)
			for (int x = 0; x < bi.getWidth(); x++)
				img.setRGB(x, y, (bi.getPixel(x,y)?0x000000:0xFFFFFF));
		ImageIO.write(img, "png", file);
	}
}
