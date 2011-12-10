package net.ohnobees.bimorphic.image;

import java.awt.image.BufferedImage;

import net.ohnobees.bimorphic.functors.BinarizationException;
import net.ohnobees.bimorphic.functors.BinarizationFunctor;
import net.ohnobees.bimorphic.functors.MedianBinarizationFunctor;

public class BinaryImage {
	private static BinarizationFunctor DEFAULT_FUNCTOR = new MedianBinarizationFunctor();
	protected int width, height;
	protected int data[];
	protected BinarizationFunctor binMethod;
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public final int[] getData() { return data; }
	
	public boolean getPixel(int x, int y) { return (data[(y*width) + x] != 0); }

	public BinarizationFunctor getBinarizationMethod() {
		return binMethod;
	}

	public void setBinarizationMethod(BinarizationFunctor functor) {
		this.binMethod = functor;
	}

	protected BinaryImage() { 
		this.width = 0;
		this.height = 0;
		data = null;
		binMethod = DEFAULT_FUNCTOR;
	}
	
	protected BinaryImage(int width, int height) {
		this.width = width;
		this.height = height;
		data = new int[width * height];
		binMethod = DEFAULT_FUNCTOR;
	}
	
	protected BinaryImage(int width, int height, BinarizationFunctor f) {
		this.width = width;
		this.height = height;
		data = new int[width * height];
		if (f == null)
			f = DEFAULT_FUNCTOR;
		binMethod = f;
	}
	
	public static BinaryImage loadImage(BufferedImage bi, BinarizationFunctor f) throws BinarizationException {
		//TODO: write this to pack into something we can perform bitwise ops
		//on for improved performance.
		BinaryImage img = new BinaryImage(bi.getWidth(), bi.getHeight(), f);
		for (int y = 0; y < img.height; y++)
			for (int x = 0; x < img.width; x++)
				img.data[(y * img.width) + x] = (img.binMethod.binarize(bi.getRGB(x, y))?1:0);
		
		return img;
	}
}
