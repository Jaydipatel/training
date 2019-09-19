package jk.test.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "jk/components/content/mytestmodel", extensions = { "html" })
public class MyDemoServlet extends SlingSafeMethodsServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final SlingHttpServletRequest req,
			final SlingHttpServletResponse resp) throws ServletException,
			IOException {
		final Resource resource = req.getResource();
		resp.setContentType("text/plain");
		resp.getWriter().write(
				"Title = " + resource.getResourceType() + " hello kem chho?");
	}
}
