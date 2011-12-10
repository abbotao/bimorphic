package net.ohnobees.bimorphic.image;

public class StructuringElement {
	protected int width, height;
	protected int data[];
	public StructuringElement(int width, int height) {
		this.width = width;
		this.height = height;
		data = new int[width * height];
	}
	
	public void setPixel(int x, int y, boolean value) {
		data[(y * width) + x] = (value?1:0);
	}
	
	public int getPixel(int x, int y) {
		return data[(y * width) + x];
	}
}
