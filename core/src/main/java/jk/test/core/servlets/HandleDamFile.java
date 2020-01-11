package jk.test.core.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.rmi.ServerException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;

//This is a component so it can provide or consume services

@Component(service = Servlet.class)
@SlingServletPaths("/bin/updamfile")
public class HandleDamFile extends
		org.apache.sling.api.servlets.SlingAllMethodsServlet {
	private static final long serialVersionUID = 2598426539166789515L;
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServerException,
			IOException {

	}

	@Override
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServerException,
			IOException {
		log.info("inside servlet@@@@@@@@@@");
		try {
			final boolean isMultipart = org.apache.commons.fileupload.servlet.ServletFileUpload
					.isMultipartContent(request);
			PrintWriter out = null;
			log.info("final boolean isMultipart-----" + isMultipart);
			out = response.getWriter();
			if (isMultipart) {
				log.info("------JKP-------");
				final java.util.Map<String, org.apache.sling.api.request.RequestParameter[]> params = request
						.getRequestParameterMap();
				for (final java.util.Map.Entry<String, org.apache.sling.api.request.RequestParameter[]> pairs : params
						.entrySet()) {
					// final String k = pairs.getKey();
					final org.apache.sling.api.request.RequestParameter[] pArr = pairs
							.getValue();
					final org.apache.sling.api.request.RequestParameter param = pArr[0];
					final InputStream stream = param.getInputStream();

					// final org.apache.sling.api.request.RequestParameter
					// param1 = pArr[1];
					// final InputStream stream1 = param1.getInputStream();
					// log.info("------JKP-------"
					// // + writeToDam(stream, param.getFileName(), request)
					// + "\n" + "------JKP-------"
					// + writeToDam(stream, param1.getFileName(), request));
					// Save the uploaded file into the Adobe CQ DAM
					out.println("The Sling Servlet placed the uploaded file here: "
							+ writeToDam(stream, param.getFileName(), request));

				}
			}
			if (!isMultipart) {
				log.info("------JKPpp-------" + !isMultipart);
				final java.util.Map<String, org.apache.sling.api.request.RequestParameter[]> params = request
						.getRequestParameterMap();
				for (final java.util.Map.Entry<String, org.apache.sling.api.request.RequestParameter[]> pairs : params
						.entrySet()) {
					// final String k = pairs.getKey();
					final org.apache.sling.api.request.RequestParameter[] pArr = pairs
							.getValue();
					for (int i = 0; i <= pArr.length; i++) {
						final org.apache.sling.api.request.RequestParameter param = pArr[i];
						final InputStream stream = param.getInputStream();
						out.println("The Sling Servlet placed the uploaded file here: "
								+ writeToDam(stream, param.getFileName(),
										request));
					}

					// Save the uploaded file into the Adobe CQ DAM

				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Save the uploaded file into the AEM DAM using AssetManager APIs
	private String writeToDam(InputStream is, String fileName,
			SlingHttpServletRequest request) {
		try {
			// Inject a ResourceResolver
			// ResourceResolver resourceResolver = resolverFactory
			// .getAdministrativeResourceResolver(null);

			// Use AssetManager to place the file into the AEM DAM
			com.day.cq.dam.api.AssetManager assetMgr = request
					.getResourceResolver().adaptTo(
							com.day.cq.dam.api.AssetManager.class);
			String newFile = "/content/dam/jk/" + fileName;
			assetMgr.createAsset(newFile, is, "image/jpeg", true);

			// Return the path to the file was stored
			return newFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}