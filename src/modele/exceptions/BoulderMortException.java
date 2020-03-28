package modele.exceptions;

@SuppressWarnings("serial")
public class BoulderMortException extends BoulderException {

	public BoulderMortException(String string) {
		super(string);
		System.exit(0);
	}

}
