package jk.test.core.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.QueryBuilder;
import com.day.cq.search.Query;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.result.SearchResult;

import jk.test.core.services.MyQueryBuilderService;

@Component(service = MyQueryBuilderService.class, immediate = true)
public class MyQueryBuilderServiceImpl implements MyQueryBuilderService {

	@Reference
	private ResourceResolverFactory resolverFactory;
	@Reference
	private QueryBuilder builder;

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<String> allQueries() {
		log.info("******insideQuery----");
		List<String> al = new ArrayList<String>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, "data");
		ResourceResolver resolver = null;
		try {
			resolver = resolverFactory.getServiceResourceResolver(param);
			Session session = resolver.adaptTo(Session.class);
			Map<String, String> map = new HashMap<String, String>();

			// create query description as hash map (simplest way, same as form
			// post)

			map.put("path", "/content/catalogs/we-retail/en");
			map.put("type", "cq:Page");
			map.put("p.limit", "-1"); // same as query.setHitsPerPage(20)
			// below

			Query query = builder.createQuery(PredicateGroup.create(map),
					session);

			SearchResult result = query.getResult();
			Iterator<Resource> itr = result.getResources();

			while (itr.hasNext()) {

				Resource rp = itr.next();

				log.info("Resource  path  is "
						+ rp.getChildren().iterator().next().getPath()

						+ "****************\n");
				al.add(rp.getChildren().iterator().next().getPath().toString()
						+ "\n");

			}

			// while (itr.hasNext()) {
			//
			// Resource rp = itr.next();
			//
			// log.info("Resource  path  is "
			// + rp.getChildren().iterator().next().getValueMap()
			// .get("cq:template", String.class)
			// + "****************\n");
			//
			// }

			Iterator<Node> nodeItr = result.getNodes();
			while (nodeItr.hasNext()) {
				Node node = nodeItr.next();

				log.info("node path  is " + node.getPath().toString());

			}

		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}
}
