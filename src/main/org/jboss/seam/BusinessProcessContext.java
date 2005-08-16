/*
 * JBoss, Home of Professional Open Source
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package org.jboss.seam;

import org.jbpm.graph.exe.ProcessInstance;

/**
 * @author <a href="mailto:theute@jboss.org">Thomas Heute </a>
 * @version $Revision$
 */
public class BusinessProcessContext implements Context
{

   private ProcessInstance processInstance;
   
   public BusinessProcessContext()
   {
   }
   
   public Object get(String name)
   {
      return processInstance.getContextInstance().getVariable(name);
   }

   public void set(String name, Object value)
   {
      processInstance.getContextInstance().setVariable(name, value);
   }

   public boolean isSet(String name)
   {
      return processInstance.getContextInstance().hasVariable(name);
   }

   public ProcessInstance getProcessInstance()
   {
      return processInstance;
   }

   public void setProcessInstance(ProcessInstance processInstance)
   {
      this.processInstance = processInstance;
   }

   public void remove(String name) 
   {
	   processInstance.getContextInstance().deleteVariable(name);
   }

   public String[] getNames() 
   {
	   return (String[]) processInstance.getContextInstance().getVariables()
	         .keySet().toArray( new String[]{} );
   }

   public void destroy() {}

}


