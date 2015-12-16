package dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface Dao<K, E> extends Serializable {
	E rechercher(K id);
	E enregistrer(E entite);
	E mettreAJour(E entite);
	E recharger(K id);
	void supprimer(K id);
	List<E> lister();
}
