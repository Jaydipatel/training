package jk.test.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

@Model(adaptables = Resource.class)
public class MyTestModel {

	@SlingObject
	private Resource currentResource;
	// @SlingObject
	// private ResourceResolver resourceResolver;

	@Inject
	private String text;

	@Inject
	private String label;

	//
	// public String getMessage() throws RepositoryException {
	// PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
	// Page currentPage = pageManager.getContainingPage(currentResource);
	// Node node = currentResource.adaptTo(Node.class);
	// // node.setProperty("kakaka", true);
	// String nodePath = node.getPath().toString();
	// // To get value of property at this node
	// // String tt = node.getProperty("kaka").getValue().toString();
	//
	// return "\tHello World!\n" + "\tResource type is: "
	// + currentResource.getResourceType() + "\n"
	// + "\tNodePath type is: " + nodePath + "\n"
	// + "\tCurrent page is: "
	// + (currentPage != null ? currentPage.getPath() : "");
	// // + "\tPropertyName is " + tt;
	//
	// }

	public String getText() {
		return new StringBuffer(text).reverse().toString();
	}

	public String getLabel() {
		return new StringBuffer(label).reverse().toString();
	}

	public String getPath() {
		return currentResource.getPath();
	}
}
