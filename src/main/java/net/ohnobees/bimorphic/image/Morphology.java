package net.ohnobees.bimorphic.image;

public class Morphology {
	public static BinaryImage erode(final BinaryImage img, final StructuringElement se) {
		BinaryImage result = new BinaryImage(img.width, img.height);
		int[] data = result.data;
		int eoX = se.width/2;
		int eoY = se.height/2;
		//For elements with odd width/height, we need lose 1 pixel because of the
		//integer divide above.
		int stopEX = (eoX*2==se.width?eoX:eoX+1);
		int stopEY = (eoY*2==se.height?eoY:eoY+1);
	
		for (int sY = 0; sY < img.height; sY++)
			pixel: for (int sX = 0; sX < img.width; sX++)
			{
				if (img.data[(sY * img.width)+sX] == 1)
				{
					for (int eY = -eoY; eY < stopEY; eY++)
					    for (int eX = -eoX; eX < stopEX; eX++)
					    {
					    	//Make sure we're in bounds
					    	if ((sY + eY >= img.height || sY + eY < 0 || sX + eX >= img.width || sX + eX < 0) ||
					    	    (img.data[((sY + eY) * img.width) + (sX + eX)] != se.data[((eoY+eY)*se.width)+(eoX+eX)])) // and if the img doesn't match
					    	{
					    		continue pixel; //go to the next pixel
					    	}
					    }
					//We will have gotten to this point by not continuing to the outer loop already, so that means everything matched
			    	data[(sY * img.width)+sX] = 1;
				}
			}
						
		return result;
	}
	
	public static BinaryImage dilate(final BinaryImage img, final StructuringElement se) {
		BinaryImage result = new BinaryImage(img.width, img.height);
		int[] data = result.data;
		int eoX = se.width/2;
		int eoY = se.height/2;
		//For elements with odd width/height, we need lose 1 pixel because of the
		//integer divide above.
		int stopEX = (eoX*2==se.width?eoX:eoX+1);
		int stopEY = (eoY*2==se.height?eoY:eoY+1);
		
		for (int sY = 0; sY < img.height; sY++)
			for (int sX = 0; sX < img.width; sX++)
				if (se.data[(eoY * se.width)+ eoX] == 1 && img.data[(sY * img.width) + sX] == se.data[(eoY * se.width)+ eoX])
					for (int eY = -eoY; eY < stopEY; eY++)
					    for (int eX = -eoX; eX < stopEX; eX++)
					    {
					    	if (sY + eY >= img.height || sY + eY < 0 || sX + eX >= img.width || sX + eX < 0 )
					    		continue;
					    	data[((sY + eY)*img.width)+(sX + eX)] = (data[((sY + eY)*img.width)+(sX + eX)]  | se.data[((eoY + eY)*se.width)+(eoX + eX)]);
					    }
				else
					data[(sY * img.width) + sX] = (data[(sY * img.width) + sX] | img.data[(sY * img.width) + sX]);
		return result;
	}
	
	public static BinaryImage open(final BinaryImage img, final StructuringElement se) {
		return Morphology.dilate(Morphology.erode(img, se), se);
	}
	
	public static BinaryImage close(final BinaryImage img, final StructuringElement se) {
		return Morphology.erode(Morphology.dilate(img, se), se);
	}
}
