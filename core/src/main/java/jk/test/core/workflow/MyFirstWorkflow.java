package jk.test.core.workflow;

import javax.jcr.Node;
import javax.jcr.Session;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;

//import com.adobe.granite.workflow.WorkflowException;
//import com.adobe.granite.workflow.WorkflowSession;
//import com.adobe.granite.workflow.exec.WorkItem;
//import com.adobe.granite.workflow.exec.WorkflowData;
//import com.adobe.granite.workflow.exec.WorkflowProcess;
//import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = { "process.label = My First WorkflowModel" })
public class MyFirstWorkflow implements WorkflowProcess {

	@Override
	public final void execute(WorkItem workItem,
			WorkflowSession workflowSession, MetaDataMap args)
			throws WorkflowException {
		final Logger log = LoggerFactory.getLogger(this.getClass());
		log.info("****talo******");
		try {
			log.info("****talo1******");
			String payloadPath = workItem.getWorkflowData().getPayload()
					.toString()
					+ "/jcr:content";
			log.info("****talo2******");
			// get session to get node.
			Session session = workflowSession.getSession();

			log.info("****talo3******");
			// get the node for the workflow payload
			Node payloadNode = session.getNode(payloadPath);
			log.info("****talo4******");

			// payloadNode.setProperty("ctalo", false);
			payloadNode.setProperty("kjkjkjkjk", "fgbfcbcbc");
			log.info("****talo5******");
			// add the node
			Node kaka = payloadNode.addNode("KBC", "nt:unstructured");

			log.info("****talo6******");
			kaka.setProperty("love", "crap");
			log.info("****talo7******");
			session.save();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
