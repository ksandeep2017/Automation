package GenericLibrary;

import java.util.*;
import javax.mail.*;

public class GetEmail_GmailInbox
{
	static Folder inbox;
	static int count=1;
	static boolean status = false;
	
	public static void connectToMail()
	{
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try
        {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", "msupplystaging1@gmail.com", "msupply@123");
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);                  
        } 
        catch (Exception mex)
        {
            mex.printStackTrace();
        }
	}
   
	public static boolean mailCheck(final String Subject, boolean checkBodyContent, String content) throws Throwable
    {
		
		for(int i=0;i<=30;i++)
		{
		   WebDriverCommonFunctions.PrintinLogAndHTMLReports("Checking Inbox....");
      	   status=checkInbox(Subject,checkBodyContent,content);
      	   if(status==true)
      		   break;			
      	   Thread.sleep(7000);
		}				
          
		return status;
		
      }
   
    static boolean checkInbox(String Subject, boolean checkBodyContent, String content) throws Throwable
    {     
        try
        {         
        	for(int i=1;i<=inbox.getMessageCount();i++)
        	{        	   
        	   Calendar cal = Calendar.getInstance();
               Message msg = inbox.getMessage(i);
               ///WebDriverCommonFunctions.PrintinLogAndHTMLReports(msg.getSubject());
               if(msg.getSubject().contains(Subject)&&(msg.getSentDate().compareTo(cal.getTime()))>0)
               {
            	   //WebDriverCommonFunctions.PrintinLogAndHTMLReports(msg.getContent());
                   //WebDriverCommonFunctions.PrintinLogAndHTMLReports("Mail Recieved");
                   WebDriverCommonFunctions.PrintinLogAndHTMLReports("SENT DATE:" + msg.getSentDate());
                   WebDriverCommonFunctions.PrintinLogAndHTMLReports("SUBJECT:" + msg.getSubject());

                   if(checkBodyContent==true)
                   {
                	   if((msg.getContent().toString()).contains(content))
                       {                		   
                		   return true;
                       }
                	   else
                	   {
                		   return false;
                	   }
                	   
                   }
                   return true;                   
               }
               
        	}        	
        }
        catch (Exception mex)
        {
            mex.printStackTrace();
        }
		return false;
        
    }
    
    public static boolean mailVerfication(String Subject, boolean CheckBodyContent, String Content) throws Throwable
    {
    	connectToMail();
    	return(mailCheck(Subject,CheckBodyContent,Content));
    	
    }
}
