package net.ohnobees.bimorphic.functors;


public class FalseBinarizationFunctor implements BinarizationFunctor {
		public boolean binarize(int i) {
			return false;
		}

		//We don't need to do anything in this one.
		public void init(int[] data) {
			return;
		}	
}
