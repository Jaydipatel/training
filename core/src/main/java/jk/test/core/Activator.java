package jk.test.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator implements BundleActivator  {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		logger.info("##################Bundle Started##################");
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		logger.info("##################Bundle Started##################");
	}

}
