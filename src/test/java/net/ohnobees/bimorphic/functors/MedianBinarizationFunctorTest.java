package net.ohnobees.bimorphic.functors;

import static org.junit.Assert.*;

import org.junit.Test;

public class MedianBinarizationFunctorTest {

	@Test(expected=BinarizationException.class)
	public void testInitNull() throws BinarizationException {
		MedianBinarizationFunctor mbf = new MedianBinarizationFunctor();
		mbf.init(null);
	}

	@Test(expected=BinarizationException.class)
	public void testInitEmpty() throws BinarizationException {
		MedianBinarizationFunctor mbf = new MedianBinarizationFunctor();
		int[] data = new int[0];
		mbf.init(data);
	}
	
	@Test(expected=BinarizationException.class)
	public void testInitTwice() throws BinarizationException {
		MedianBinarizationFunctor mbf = new MedianBinarizationFunctor();
		int[] data = {0, 1, 2, 3, 4, 5, 6};
		mbf.init(data);
		mbf.init(data);	
	}
	
	
	@Test
	public void testInit() throws BinarizationException {
		MedianBinarizationFunctor mbf = new MedianBinarizationFunctor();
		int[] first = {1, 2, 3, 4, 5, 6};
		int median = 4;
		mbf.init(first);

		assertEquals(median, mbf.median);
		
		mbf = new MedianBinarizationFunctor();
		int[] second = {1, 2, 3, 4, 5, 6, 7};
		median = 4;
		mbf.init(second);
		
		assertEquals(median, mbf.median);
		
		mbf = new MedianBinarizationFunctor();
		int[] third = {5, 2, 3, 6, 1, 4};
		median = 4;
		mbf.init(third);

		assertEquals(median, mbf.median);
		
		mbf = new MedianBinarizationFunctor();
		int[] fourth = {2, 1, 6, 4, 7, 3, 5};
		median = 4;
		mbf.init(fourth);
		
		assertEquals(median, mbf.median);
	}
	
	@Test
	public void testBinarize() throws BinarizationException {
		MedianBinarizationFunctor mbf = new MedianBinarizationFunctor();
		int[] first = {1, 2, 3, 4, 5, 6};
		int median = 4;
		mbf.init(first);
		for (int i = 0; i < 8; i++)
			assertEquals("Failed on " + Integer.toString(i), i < median, mbf.binarize(i));
		
		mbf = new MedianBinarizationFunctor();
		int[] second = {1, 2, 3, 4, 5, 6, 7};
		median = 4;
		mbf.init(second);
		for (int i = 0; i < 9; i++)
			assertEquals("Failed on " + Integer.toString(i), i < median, mbf.binarize(i));
		
	}

}
