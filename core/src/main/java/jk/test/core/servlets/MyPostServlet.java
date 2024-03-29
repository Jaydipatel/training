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
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

/**
 * @author Jaydeep Patel------ HERE WE ARE SENDING RESPONSE BACK TO AJAX CALL AS
 * WELL AS CHANGING PROPERTY IN JCR SlingPostServlet Demo
 */

@Component(service = Servlet.class)
@SlingServletPaths("/bin/submitdata")
public class MyPostServlet extends SlingSafeMethodsServlet {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = -159625176093879129L;

    /**
     * Overridden doPost() method which is invoked when an HTTP post request is
     * made
     */
    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) {

        // Setting responseType, This is needed for ajax resonse
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try {
            String id = UUID.randomUUID().toString();
            String firstName =  new StringBuilder(request.getParameter("firstName")).reverse().toString();
            String lastName = new StringBuilder(request.getParameter("lastName")).reverse().toString();


            // Encode the submitted form data to JSON
            JSONObject obj = new JSONObject();
            obj.put("id", id);
            obj.put("firstname", firstName);

            obj.put("lastname", lastName);
            String jsonData = obj.toString();

            // Return the JSON formatted data

            response.getWriter().write(jsonData);

            /**
             * Getting the instance of resource resolver from the request
             */

            ResourceResolver resourceResolver = request.getResourceResolver();

            /**
             * Getting the resource object via path
             *
             *
             *
             */
            Resource resource = resourceResolver
                    .getResource("/content/jk/en/jcr:content");

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
            node.setProperty("hello", false);

            /**
             * Commit the changes to JCR
             */
            resourceResolver.commit();

        } catch (RepositoryException e) {

            e.printStackTrace();

        } catch (PersistenceException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}