package com.myspace.handling_wih_exception;

import org.kie.api.runtime.process.ProcessWorkItemHandlerException;
import org.kie.api.runtime.process.ProcessWorkItemHandlerException.HandlingStrategy;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;


public class ErrornousWorkItemHandler implements WorkItemHandler {
    
    private String processId;
    private HandlingStrategy strategy;
    private int counter;
    
    private WorkItem workItem;
    
    public ErrornousWorkItemHandler(String processId, HandlingStrategy strategy) {
        super();
        this.processId = processId;
        this.strategy = strategy;
    }
    
    public ErrornousWorkItemHandler(String processId, String strategy) {
        super();
        this.processId = processId;
        this.strategy = HandlingStrategy.valueOf(strategy);
    }

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        this.workItem = workItem;
        if (processId != null && strategy != null) {
            
         //   if (counter >= 3) {
         //       manager.completeWorkItem(workItem.getId(), workItem.getParameters());
         //   } else {
         //       counter++;
                throw new ProcessWorkItemHandlerException(processId, strategy, new RuntimeException("On purpose"));
         //    }
        }
        
        manager.completeWorkItem(workItem.getId(), null);
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        this.workItem = workItem;

    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public int getCounter() {
        return counter;
    }

}