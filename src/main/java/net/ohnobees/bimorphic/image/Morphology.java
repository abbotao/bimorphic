package net.ohnobees.bimorphic.image;

public class Morphology {
	public static BinaryImage erode(final BinaryImage img, final StructuringElement se) {
		return null;
	}
	
	public static BinaryImage dilate(final BinaryImage img, final StructuringElement se) {
		return null;
	}
	
	public static BinaryImage open(final BinaryImage img, final StructuringElement se) {
		return Morphology.dilate(Morphology.erode(img, se), se);
	}
	
	public static BinaryImage close(final BinaryImage img, final StructuringElement se) {
		return Morphology.erode(Morphology.dilate(img, se), se);
	}
}
