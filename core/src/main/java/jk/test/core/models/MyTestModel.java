package jk.test.core.models;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

@Model(adaptables = Resource.class, resourceType = { "jk/components/content/slingModel" }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson", extensions = "json", options = { @ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value = "true") })
public class MyTestModel {

	@SlingObject
	private Resource currentResource;
	// @SlingObject
	// private ResourceResolver resourceResolver;

	@Inject
	private String text;

	@Inject
	private String label;

	

	public String getText() {
		return new StringBuffer(text).reverse().toString();
	}

	public String getLabel() {
		return new StringBuffer(label).reverse().toString();
	}

	public String getPath() {
		return currentResource.getPath();
	}
	
//	@ValueMapValue
//	@Named ("cq:template")
//	private String template; 
//	
//	
//
//	public String getTemplate() {
//		return template;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public String getLastModified() {
//		return lastModified;
//	}
//
//	@ValueMapValue
//	@Named ("jcr:title")
//	private String title;
//	
//	@ValueMapValue
//	@Named ("cq:lastModified")
//	private String lastModified;
	
	
}
