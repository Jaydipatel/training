package jk.test.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class)
public class MyTestModel2 {

	@ScriptVariable(name = "currentPage")
	Page page;

	public String getPagePath() {
		return page.getPath();
	}

	// Injects title from ValuMap annotation using its attributes
	@ValueMapValue(name = "text", via = "resource", injectionStrategy = InjectionStrategy.REQUIRED)
	String text;

	public String getText() {
		return text;
	}

}
