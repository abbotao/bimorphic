package net.ohnobees.bimorphic.functors;

import java.util.Arrays;

public class MedianBinarizationFunctor implements BinarizationFunctor {
	protected int median;
	protected boolean hasInit;
	
	public MedianBinarizationFunctor() { 
		median = 0;
		hasInit = false;
	}
	
	public boolean binarize(int i) throws BinarizationException {
		if (!hasInit)
			throw new BinarizationException("Functor not initialized.");
		
		return i < median;
	}

	public void init(int[] data) throws BinarizationException {
		if (hasInit) throw new BinarizationException("Functor already initialized.");
		hasInit = true;
		if (data == null || data.length == 0)
			throw new BinarizationException("Invalid data array.");
		
		int[] medianArray = Arrays.copyOf(data, data.length);
		Arrays.sort(medianArray);
		//The integer division in the even case will truncate down, mathematically, we should round up, so we'll add one to that case.
		median = (medianArray.length%2==0?(medianArray[medianArray.length/2] + medianArray[medianArray.length/2-1])/2 + 1:medianArray[medianArray.length/2]);
		return;
	}

}
