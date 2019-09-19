package jk.test.core.workflow;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import com.day.cq.workflow.model.WorkflowNode;

@Component(service = WorkflowProcess.class, property = { "process.label = AA WorkflowModel" })
public class MySecondWorkflow implements WorkflowProcess {

	@Override
	public void execute(WorkItem item, WorkflowSession wfSession,
			MetaDataMap args) throws WorkflowException {
		final Logger log = LoggerFactory.getLogger(this.getClass());
		WorkflowNode wn = item.getNode();
		// String title = wn.getTitle();
		String dsc = wn.getDescription();
		wn.setTitle("setTitile");
		String lala = args.get("PROCESS_ARGS", "string").toString();
		log.info("*****WorkFlowNode dsc****" + dsc + "********");
		log.info("*****WorkFlowNode New title****" + wn.getTitle() + "********");
		log.info("*****getting args****" + lala + "********");

	}

}
