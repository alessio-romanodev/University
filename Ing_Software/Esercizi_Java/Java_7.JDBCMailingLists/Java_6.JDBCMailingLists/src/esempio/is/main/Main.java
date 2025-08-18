package esempio.is.main;

import esempio.is.entity.Customer;
import esempio.is.entity.MailingList;
import esempio.is.main.MailingListService.MailingListServiceException;

public class Main {

	public static void main(String[] args) {
		try {
			MailingListService mailingListService = MailingListService.getInstance();
			
			Customer c1 = mailingListService.createCustomer("Luca Rossi", "luca.rossi@gmail.com");
			Customer c2 = mailingListService.createCustomer("Paolo Bolli", "paolo.bolli@unina.it");
			
			MailingList informatica = mailingListService.createMailingList("Informatica");
			
			mailingListService.registerCustomerToMailingList(c1, informatica);
			mailingListService.registerCustomerToMailingList(c2, informatica);
			
		} catch (MailingListServiceException e) {
			System.err.println("Eccezione del servizio di mailing list avvenuta durante l'esecuzione!");
			e.printStackTrace();
		}
		
	}

}
