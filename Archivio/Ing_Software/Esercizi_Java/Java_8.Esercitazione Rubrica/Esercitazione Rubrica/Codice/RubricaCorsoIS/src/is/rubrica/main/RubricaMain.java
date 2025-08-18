package is.rubrica.main;

import java.io.IOException;

import is.rubrica.esempioaula.Contatto;
import is.rubrica.esempioaula.ContattoDiAzienda;
import is.rubrica.esempioaula.ContattoDiPersona;
import is.rubrica.esempioaula.GroupDeletionException;
import is.rubrica.esempioaula.Gruppo;
import is.rubrica.esempioaula.Rubrica;
import is.rubrica.esempioaula.RubricaException;

public class RubricaMain {
	
	protected static String askUser(String message) throws IOException {
		System.out.print(message);
		System.out.flush();
		return inputReader.readLine();
	}
	
	
	public static void stampaContatti() {
		stampaContatti(false);
	}
	
	public static void stampaContatti(boolean numbered) {
		System.out.println("Lista contatti:");
		Contatto[] listaContatti = Rubrica.getListaContatti();
		
		for (int i = 0; i < listaContatti.length; i++) {
			System.out.print("\t");
			if (numbered) {
				System.out.format("%d) ", i);
			}
			listaContatti[i].print();
		}
		System.out.flush();
	}
	
	public static void stampaGruppi() {
		stampaGruppi(false, true);
	}
	
	public static void stampaGruppi(boolean numbered, boolean showContacts) {
		final Gruppo[] listaGruppi = Rubrica.getListaGruppi(); 
		System.out.println("Lista gruppi:");
		for (int i = 0; i < listaGruppi.length; i++) {
			System.out.print("\t");
			if (numbered) {
				System.out.format("%d) ", i);
			}
			System.out.println(listaGruppi[i].getLabel());
			if (showContacts) {
				/*
				 * Utilizza il construtto Java foreach per scorrere ordinatamente
				 * gli elementi della lista. Il costrutto foreach solleva una eccezione 
				 * se il riferimento alla collection/array da iterare e' null.  
				 */
				for (Contatto c : listaGruppi[i].getContatti()) {
					System.out.print("\t\t");
					c.print();
				}
			}
		}
		System.out.flush();
	}
	
	public static void stampaRubrica() {
		stampaContatti();
		stampaGruppi();
	}
	
	public static void aggiungiContatto() {
		try {
			String r = askUser("Contatto di privato o di azienda? (p/a): ");
			
			Contatto c;
			if (r.equalsIgnoreCase("p")) {
				String nome = askUser("Nome: ");
				String cognome = askUser("Cognome: ");
				String ddn = askUser("Data di nascita: ");
				String telefono = askUser("Telefono: ");
				String email = askUser("Email: ");
				
				c = new ContattoDiPersona(telefono, email, nome, cognome, ddn);
			} else if (r.equalsIgnoreCase("a")) {
				String ragioneSociale = askUser("Ragione Sociale: ");
				String telefono = askUser("Telefono: ");
				String email = askUser("Email: ");
				c = new ContattoDiAzienda(telefono, email, ragioneSociale);
			} else {
				System.out.println("Input invalido!");
				return;
			}
			
			Rubrica.addContatto(c);
			System.out.println("Contatto aggiunto in rubrica con successo!");
		} catch (Exception e) {
			System.out.println("Un errore si e' verificato durante l'aggiunta di un contatto: " + e.getMessage());
//			e.printStackTrace();
		}
	}
	
	public static void rimuoviContatto() {
		try {
			Contatto[] listaContatti = Rubrica.getListaContatti();
			if (listaContatti.length == 0) {
				System.out.println("Non ci sono contatti da eliminare!");
			} else {
				stampaContatti(true);
				int index = Integer.parseInt(askUser("Quale e' l'indice del contatto eliminare?: "));
				
				Rubrica.removeContatto(listaContatti[index]);
				System.out.println("Contatto rimosso dalla rubrica con successo!");
			}
		} catch (GroupDeletionException e) {
			System.out.println("Impossibile rimuovere il contatto perche' contenuto in almeno un gruppo!");
		} catch (Exception e) {
			System.out.println("Un errore si e' verificato durante la rimozione di un contatto: " + e.getMessage());
//			e.printStackTrace();
		}
	}
	
	public static void aggiungiGruppo() {
		try {
			String label = askUser("Etichetta del gruppo?: ");
			
			Gruppo g = new Gruppo(label);
			
			Contatto[] listaContatti = Rubrica.getListaContatti();
			stampaContatti(true);
			String userReply = askUser("Indicare la lista indici dei contatti da aggiungere separandoli con uno spazio:\n");
			
			String[] contactsToAdd = userReply.split(" +");
			for (int i = 0; i < contactsToAdd.length; i++) {
				int idToAdd = Integer.parseInt(contactsToAdd[i]);
				Contatto c = listaContatti[idToAdd]; 
				g.addContatto(c);
				
				System.out.print("Aggiunto al gruppo il contatto: ");
				c.print();
			}
			
			Rubrica.addGruppo(g);
			System.out.println("Gruppo aggiunto con successo!");
		} catch (Exception e) {
			System.out.println("Un errore si e' verificato durante l'aggiunta di un contatto: " + e.getMessage());
//			e.printStackTrace();
		}
	}
	
	public static void rimuoviGruppo() {
		try {
			Gruppo[] listaGruppi = Rubrica.getListaGruppi();
			if (listaGruppi.length == 0) {
				System.out.println("Non ci sono gruppi da eliminare!");
			} else {
				stampaGruppi(true, false);
				int index = Integer.parseInt(askUser("Quale e' l'indice del gruppo eliminare?: "));
				
				Rubrica.removeGruppo(listaGruppi[index]);
				System.out.println("Gruppo rimosso dalla rubrica con successo!");
			}
		} catch (Exception e) {
			System.out.println("Un errore si e' verificato durante la rimozione di un contatto: " + e.getMessage());
//			e.printStackTrace();
		}
	}
 	
	
	/**
	 * @param args
	 * @throws RubricaException 
	 */
	public static void main(String[] args) {
		try {
			
			Rubrica.loadRubrica();
			
			inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
			int option = 0;
			do {
				System.out.println("Seleziona operazione: \n" +
						"\t1) Stampa contatti in rubrica\n" +
						"\t2) Stampa gruppi in rubrica\n" +
						"\t3) Stampa intera rubrica\n" +
						"\t4) Aggiungi contatto\n" +
						"\t5) Rimuovi contatto\n" +
						"\t6) Aggiungi gruppo\n" +
						"\t7) Rimuovi gruppo\n" +
						"\t9) Esci");
				System.out.flush();
				
				try {
					option = Integer.parseInt(inputReader.readLine());
				} catch (NumberFormatException e) {
					option = 0;
				}
				
				switch (option) {
					case 1: { stampaContatti(); break; }
					case 2: { stampaGruppi(); break; }
					case 3: { stampaRubrica(); break; }
					case 4: { aggiungiContatto(); break; }
					case 5: { rimuoviContatto(); break; }
					case 6: { aggiungiGruppo(); break; }
					case 7: { rimuoviGruppo(); break; }
					case 9: {
						System.out.println("La rubrica sara' salvata. Goodbye!\n");
						break; 
					}
					default: {
						System.out.println("Carattere inserito non riconosciuto!\n");
					}
				}
			} while (option != 9);
		} catch (IOException e) {
			System.err.println("Si e' verificato un errore di I/O: " + e.getMessage());
			e.printStackTrace();
		} catch (RubricaException e) {
			System.err.println("Si e' verificato un errore interno della rubrica: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				Rubrica.saveRubrica();
			} catch (RubricaException e) {
				System.err.println("A causa di un errore interno non e' stato possibile salvare lo stato della rubrica: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	protected static java.io.BufferedReader inputReader;
}
