package dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<K, E> extends Serializable {
	E rechercher(K id);
	E enregistrer(E entit�);
	E mettreAJour(E entit�);
	E recharger(K id);
	void supprimer(K id);
	List<E> lister();
}
