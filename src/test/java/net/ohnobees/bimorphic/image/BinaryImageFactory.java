package net.ohnobees.bimorphic.image;

public class BinaryImageFactory {
	public static BinaryImage getImageInstance(int width, int height) {
		return new BinaryImage(width, height);
	}
}
