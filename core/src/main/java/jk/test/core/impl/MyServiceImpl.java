package jk.test.core.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jk.test.core.MyService;

public class MyServiceImpl implements MyService {

	private ResourceResolverFactory resolverFactory;

	@Override
	public void updateMynode() {

		final Logger log = LoggerFactory.getLogger(this.getClass());
		log.info("****Wahbefore******");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, "datawrite");
		ResourceResolver resolver = null;

		try {
			resolver = resolverFactory.getServiceResourceResolver(param);

			Resource pageResource = resolver
					.getResource("/content/jk/en/test/jcr:content");
			Node myNode = pageResource.adaptTo(Node.class);
			try {
				log.info("****Wah******");
				myNode.setProperty("Awesome", true);
				Session session = resolver.adaptTo(Session.class);
				session.save();
			} catch (ValueFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (VersionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LockException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
