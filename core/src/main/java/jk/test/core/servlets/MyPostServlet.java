package jk.test.core.servlets;

import java.util.UUID;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jaydeep Patel
 *
 *         SlingPostServlet Demo
 */

@Component(service = Servlet.class)
@SlingServletPaths("/bin/submitdata")
public class MyPostServlet extends SlingAllMethodsServlet {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -159625176093879129L;

	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(MyPostServlet.class);

	/**
	 * Overridden doPost() method which is invoked when an HTTP post request is
	 * made
	 */
	@Override
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) {
		log.info("***MyPostServlet is invoked****");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		try {
			String id = UUID.randomUUID().toString();
			String firstName = request.getParameter("firstName")
					+ " _newFirstName";
			String lastName = request.getParameter("lastName")
					+ " _newLastName";

			// Encode the submitted form data to JSON
			JSONObject obj = new JSONObject();
			obj.put("id", id);
			obj.put("firstname", firstName);
			obj.put("lastname", lastName);
			String jsonData = obj.toString();
			log.info("***Servlet Invokmed*******22" + jsonData);

			// Return the JSON formatted data
			log.info("fffffffffffff" + jsonData);
			response.getWriter().write(jsonData);
			log.info("fffffffffffff" + "response senttttttt");
			/**
			 * Getting the instance of resource resolver from the request
			 */
			ResourceResolver resourceResolver = request.getResourceResolver();

			/**
			 * Getting the resource object via path
			 */
			Resource resource = resourceResolver
					.getResource("/content/jk/en/test/jcr:content");

			log.info("Resource is at path {}", resource.getPath());

			/**
			 * Adapt the resource to javax.jcr.Node type
			 */
			Node node = resource.adaptTo(Node.class);

			/**
			 * Create a new node with name and primary type and add it below the
			 * path specified by the resource
			 */
			// Node newNode = node.addNode("demoNode", "nt:unstructured");

			/**
			 * Setting a name property for this node
			 */
			node.setProperty("kakku", false);

			/**
			 * Commit the changes to JCR
			 */
			resourceResolver.commit();

		} catch (RepositoryException e) {

			log.error(e.getMessage(), e);

			e.printStackTrace();

		} catch (PersistenceException e) {

			log.error(e.getMessage(), e);

			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}