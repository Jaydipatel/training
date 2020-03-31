package jk.test.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import com.google.common.net.MediaType;

import javax.servlet.Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component(service = Servlet.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "=Image Viewer Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=" + "/bin/imageViewerServlet"
        })

public class ImageViewServlet extends SlingAllMethodsServlet {
    private static final long serialVersionUID = 2598426539166789515L;

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException {
        final PrintWriter out = response.getWriter();
        response.setContentType(MediaType.HTML_UTF_8.toString());

        final String assetPathParam = request.getParameter("assetPath");

        // Attempt to create preview. In case of exception, present feedback message.
        out.println("<html><body>");
        try {
            final String assetPath = URLDecoder.decode(assetPathParam, StandardCharsets.UTF_8.name());
            out.println("<img src=\"" + assetPath + "\"/>");
        } catch (Exception e) {
            String message = "Could not preview asset at " + assetPathParam;
           
            out.println("<span>" + message + "</span>");
        }
        out.println("</html></body>");
    }
}
