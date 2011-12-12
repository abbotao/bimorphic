package net.ohnobees.bimorphic.functors;

public class BooleanBinaryFunctor implements BinarizationFunctor {

	public boolean binarize(int i) throws BinarizationException {
		//Black pixel = true, mask the alpha channel out.
		return (0xFFFFFF&i)==0x000000;
	}

	public void init(int[] data) throws BinarizationException {
	}

}
