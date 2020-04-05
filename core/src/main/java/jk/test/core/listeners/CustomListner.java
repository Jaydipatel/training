package jk.test.core.listeners;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.PageManager;

import jk.test.core.services.*;
//import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

/**
 * A service to demonstrate how changes in the resource tree can be listened
 * for. It registers an event handler service. The component is activated
 * immediately after the bundle is started through the immediate flag. Please
 * note, that apart from EventHandler services, the immediate flag should not be
 * set on a service.
 */
@Component(immediate = true, service = EventHandler.class, property = {
		Constants.SERVICE_DESCRIPTION + "= This event handler listens the events on page activation",
		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/REMOVED",
		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED", EventConstants.EVENT_FILTER
				+ "=(path=/conf/global/settings/dam/adminui-extension/metadataschema/My-Test/items/tabs/items/tab4/items/*)" })
public class CustomListner implements EventHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@OSGiService
	private MyService myServ;

	private String mypath = "/content/jk/en/service-call/jcr:content/root/responsivegrid/myservicecall";

	@Reference
	private ResourceResolverFactory resolverFactory;

	@Override
	public void handleEvent(final Event event) {
		logger.info("*********jaydeep**********Resource event: {} at: {}", event.getTopic(),
				event.getProperty(SlingConstants.PROPERTY_PATH));
		logger.info("****** STEP0000********");

		Map<String, Object> param = new HashMap<String, Object>();
		logger.info("****** STEP1********");
		param.put(ResourceResolverFactory.SUBSERVICE, "datawrite");
		logger.info("****** STEP2********");
		ResourceResolver resolver = null;
		logger.info("****** STEP3********");
		try {
			logger.info("****** STEP4********");

			resolver = resolverFactory.getServiceResourceResolver(param);
			logger.info("****** STEP5********");
			Resource res = resolver.getResource(mypath);
			logger.info("****** STEP6********");
			PageManager pm = resolver.adaptTo(PageManager.class);
			logger.info("****** STEP7********");
			String pagePath = pm.getContainingPage(res).getPath() + "/jcr:content";

			logger.info("****** STEP8********");
             
			Resource pageResource = resolver.getResource(pagePath);
			logger.info("****** STEP9********");

			Node myNode = pageResource.adaptTo(Node.class);
			logger.info("****** STEP10********");
			myNode.setProperty("newProperty", true);
			logger.info("****** STEP11********");
			resolver.commit();

		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
