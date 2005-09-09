//$Id$
package org.jboss.seam.example.noejb;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.hibernate.Session;
import org.jboss.logging.Logger;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

@Name("bookingList")
@LoggedIn
public class BookingListAction implements Serializable
{
   private static final Logger log = Logger.getLogger(BookingListAction.class);
   
   @In(create=true)
   private Session bookingDatabase;
   
   @In
   private User user;
   
   @Out
   private DataModel bookingsDataModel = new ListDataModel();
   
   public String find()
   {
      List bookings = bookingDatabase.createQuery("from Booking b where b.user = :user order by b.checkinDate")
            .setEntity("user", user)
            .list();
      
      log.info(bookings.size() + " bookings found");
      
      bookingsDataModel.setWrappedData(bookings);
      
      return "bookings";
   }
   
}
