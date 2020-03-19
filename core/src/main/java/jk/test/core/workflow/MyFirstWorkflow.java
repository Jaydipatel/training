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

@Component(service = WorkflowProcess.class, property = { "process.label = My First WorkflowModel" })
public class MyFirstWorkflow implements WorkflowProcess {

	@Override
	public final void execute(WorkItem workItem,
			WorkflowSession workflowSession, MetaDataMap args)
			throws WorkflowException {
		final Logger log = LoggerFactory.getLogger(this.getClass());
		try {

			String payloadPath = workItem.getWorkflowData().getPayload()
					.toString()
					+ "/jcr:content";
			
			String myId = "helloji";
			workItem.getWorkflowData().getMetaDataMap().put("My Id", myId);

			// get session to get node.
			Session session = workflowSession.getSession();

			// get the node for the workflow payload
			Node payloadNode = session.getNode(payloadPath);

			// payloadNode.setProperty("ctalo", false);
			payloadNode.setProperty("kjkjkjkjk", "fgbfcbcbc");

			// add the node
			Node kaka = payloadNode.addNode("KBC", "nt:unstructured");

			kaka.setProperty("love", "crap");
			log.info("Inside of this@@@@@++++1");
			session.save();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
