package net.ohnobees.bimorphic.functors;

import java.util.Arrays;

public class MedianBinarizationFunctor implements BinarizationFunctor {
	protected int median;
	
	public boolean binarize(int i) {
		return i < median;
	}

	public void init(int[] data) {
		int[] medianArray = Arrays.copyOf(data, data.length);
		Arrays.sort(medianArray);
		median = (medianArray.length%2==0?(medianArray[medianArray.length/2] + medianArray[medianArray.length/2-1])/2:medianArray[medianArray.length/2]);
		return;
	}

}
