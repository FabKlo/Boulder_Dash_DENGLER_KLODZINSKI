package modele.obs;

import java.util.ArrayList;

import modele.exceptions.BoulderException;

public class Observable {
	private	ArrayList<Observateur> listeObservateur = new ArrayList<Observateur>();
	
	public Observable() {
	}
	
	
	public void add(Observateur obs) throws BoulderException {
		if (obs == null) {
			throw new BoulderException("Observateur null");
		}
		
		listeObservateur.add(obs);		
	}
	
	public void notifier() {
		for (Observateur observateur : listeObservateur) {
			observateur.recevoirNotification(this);
		}
	}
}
