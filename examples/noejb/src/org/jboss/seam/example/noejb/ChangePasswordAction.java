//$Id$
package org.jboss.seam.example.noejb;

import static org.jboss.seam.ScopeType.EVENT;
import static org.jboss.seam.annotations.Outcome.REDISPLAY;

import org.hibernate.Session;
import org.hibernate.validator.Valid;
import org.jboss.logging.Logger;
import org.jboss.seam.annotations.IfInvalid;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

@Scope(EVENT)
@Name("changePassword")
@LoggedIn
public class ChangePasswordAction
{
   
   private static final Logger log = Logger.getLogger(ChangePasswordAction.class);

   @In @Out @Valid
   private User user;
   
   @In(create=true)
   private Session bookingDatabase;
   
   private String verify;
   
   @IfInvalid(outcome=REDISPLAY)
   public String changePassword()
   {
      if ( user.getPassword().equals(verify) )
      {
         log.info("updating password to: " + verify);
         user = (User) bookingDatabase.merge(user);
         return "main";
      }
      else 
      {
         log.info("password not verified");
         bookingDatabase.refresh(user);
         verify=null;
         return null;
      }
   }

   public String getVerify()
   {
      return verify;
   }

   public void setVerify(String verify)
   {
      this.verify = verify;
   }

}
