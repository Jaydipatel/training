package jk.test.core.impl;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;

import com.day.cq.wcm.api.PageManager;

import jk.test.core.services.MyService;

@Component(service = MyService.class, immediate = true)
public class MyServiceImpl implements MyService {

	@Override
	public void updateMynode(Resource res) {

		try {

			ResourceResolver resolver = res.getResourceResolver();
			PageManager pm = resolver.adaptTo(PageManager.class);
			String pagePath = pm.getContainingPage(res).getPath()
					+ "/jcr:content";
			Resource pageResource = resolver.getResource(pagePath);

			Node myNode = pageResource.adaptTo(Node.class);

			myNode.setProperty("newProperty", true);

			resolver.commit();

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
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
