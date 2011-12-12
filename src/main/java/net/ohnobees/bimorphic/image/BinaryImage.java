package net.ohnobees.bimorphic.image;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import net.ohnobees.bimorphic.functors.BinarizationException;
import net.ohnobees.bimorphic.functors.BinarizationFunctor;
import net.ohnobees.bimorphic.functors.MedianBinarizationFunctor;

public class BinaryImage {
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
		binMethod = new MedianBinarizationFunctor();
	}
	
	protected BinaryImage(int width, int height) {
		this.width = width;
		this.height = height;
		data = new int[width * height];
		binMethod = new MedianBinarizationFunctor();
	}
	
	protected BinaryImage(int width, int height, BinarizationFunctor f) {
		this.width = width;
		this.height = height;
		data = new int[width * height];
		if (f == null)
			f = new MedianBinarizationFunctor();
		binMethod = f;
	}
	
	public static BinaryImage loadImage(BufferedImage bi, BinarizationFunctor f) throws BinarizationException {
		if (f == null) f = new MedianBinarizationFunctor();
		//TODO: write this to pack into something we can perform bitwise ops
		//on for improved performance.
		BinaryImage img = new BinaryImage(bi.getWidth(), bi.getHeight(), f);
		for (int y = 0; y < img.height; y++)
			for (int x = 0; x < img.width; x++)
				img.data[(y * img.width) + x] = bi.getRGB(x,y);
		f.init(img.data);
		for (int i = 0; i < img.data.length; i++)
				img.data[i] = (img.binMethod.binarize(img.data[i])?1:0);
		
		return img;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((binMethod == null) ? 0 : binMethod.hashCode());
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BinaryImage other = (BinaryImage) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
}
