package net.ohnobees.bimorphic.functors;

public interface BinarizationFunctor {
	public boolean binarize(int i) throws BinarizationException;
	public void init(int data[]) throws BinarizationException;
}
