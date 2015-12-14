package dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<K, E> extends Serializable {
	E rechercher(K id);
	E enregistrer(E entité);
	E mettreAJour(E entité);
	E recharger(K id);
	void supprimer(K id);
	List<E> lister();
}
