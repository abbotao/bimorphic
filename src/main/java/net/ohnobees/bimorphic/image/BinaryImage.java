package net.ohnobees.bimorphic.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.ohnobees.bimorphic.functors.BinarizationFunctor;
import net.ohnobees.bimorphic.functors.FalseBinarizationFunctor;

public class BinaryImage {
	protected int width, height;
	protected int data[];
	protected BinarizationFunctor binMethod;
	
	protected BinaryImage() { 
		this.width = 0;
		this.height = 0;
		data = null;
		binMethod = new FalseBinarizationFunctor();
	}
	
	protected BinaryImage(int width, int height) {
		this.width = width;
		this.height = height;
		data = new int[width * height];
		binMethod = new FalseBinarizationFunctor();
	}
	
	public static BinaryImage LoadImage(File f) {
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(f);
		} catch (IOException e) {
			
		}
		return LoadImage(bi);
	}
	
	public static BinaryImage LoadImage(BufferedImage bi) {
		BinaryImage img = new BinaryImage(bi.getWidth(), bi.getHeight());
		for (int y = 0; y < img.height; y++)
			for (int x = 0; x < img.width; x++)
				img.data[(y * img.width) + x] = (img.binMethod.binarize(bi.getRGB(x, y))?1:0);
		
		return img;
	}
}
