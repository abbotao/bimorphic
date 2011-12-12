package net.ohnobees.bimorphic.utils;

import java.io.File;
import java.net.URISyntaxException;

public class TestUtils {
	public static File fileFromResource(String resourceName) throws URISyntaxException { 
		return new File(TestUtils.class.getClassLoader().getResource(resourceName).toURI());
	}
}
