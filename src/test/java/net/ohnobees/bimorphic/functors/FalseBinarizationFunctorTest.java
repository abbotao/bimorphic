package net.ohnobees.bimorphic.functors;

import static org.junit.Assert.*;

import net.ohnobees.bimorphic.functors.FalseBinarizationFunctor;

import org.junit.Test;

public class FalseBinarizationFunctorTest {

	//This is clearly a kinda braindead test, but might as well...
	@Test
	public void testBinarize() {
		FalseBinarizationFunctor f = new FalseBinarizationFunctor();
		f.init(null);
		assertFalse(f.binarize(0));
		assertFalse(f.binarize(-1));
		assertFalse(f.binarize(Integer.MAX_VALUE));
		assertFalse(f.binarize(Integer.MIN_VALUE));
		for (int i = 0; i < 255; i++)
			assertFalse("Failed binarizing on " + Integer.toString(i), f.binarize(i));
	}

}
