package is.rubrica.esempioaula;

/*
 * L'interfaccia ci permette di esporre il metodo sia tra Contatto
 * che tra Gruppo senza che i due abbiano un antenato in comune.
 */
public interface EmailAddressable {
	public String[] getEmailAddress();
}
