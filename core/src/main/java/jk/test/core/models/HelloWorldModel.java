/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package jk.test.core.models;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

import jk.test.core.MyService;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.settings.SlingSettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = Resource.class)
public class HelloWorldModel {

	@Inject
	private MyService myServ;
	@ValueMapValue(name = PROPERTY_RESOURCE_TYPE, injectionStrategy = InjectionStrategy.OPTIONAL)
	@Default(values = "No resourceType")
	protected String resourceType;

	@OSGiService
	private SlingSettingsService settings;
	@SlingObject
	private Resource currentResource;
	@SlingObject
	private ResourceResolver resourceResolver;

	final Logger log = LoggerFactory.getLogger(this.getClass());

	public String getMessage() throws RepositoryException {
		log.info("****ggggg-inside postConstruct****");
		PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
		Page currentPage = pageManager.getContainingPage(currentResource);
		Node node = currentResource.adaptTo(Node.class);
		String nodePath = node.getPath().toString();
		log.info("****ggggg-before service****");
		myServ.updateMynode();
		log.info("****ggggg-after service****");
		return "\tHello World!\n" + "\tThis is instance: "
				+ settings.getSlingId() + "\n" + "\tResource type is: "
				+ resourceType + "\n" + "\tNodePath type is: " + nodePath
				+ "\n" + "\tCurrent page is: "
				+ (currentPage != null ? currentPage.getPath() : "") + "\n";

	}

}
