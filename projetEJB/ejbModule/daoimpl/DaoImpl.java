package daoimpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import dao.Dao;

@SuppressWarnings("serial")
public abstract class DaoImpl<K, E> implements Dao<K, E> {
	private Class<E> entityClass;

	@PersistenceContext(unitName="black")
	private EntityManager entityManager;
	
	public DaoImpl(Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	
	public E rechercher(K id) {
		return (E) entityManager.find(entityClass, id);
	}

	public E enregistrer(E entité) {
		entityManager.persist(entité);
		return entité;
	}

	public E mettreAJour(E entité) {
		return entityManager.merge(entité);		
	}

	public E recharger(K id) {
		E entité = rechercher(id);
		entityManager.refresh(entité);
		return entité;
	}

	public void supprimer(K id) {
		E entité = rechercher(id);
		entityManager.remove(entité);
	}

	public List<E> lister() {
		return liste("select x from " + entityClass.getName() + " x");
	}

	protected List<E> liste(String queryString, Object... params) {
		List<E> entités = null;
		TypedQuery<E> query = entityManager.createQuery(queryString, entityClass);
		int i = 0, j = 1;
		while(i < params.length) {
			if (params[i] instanceof Date ) {
				query.setParameter(j, (Date)params[i], (TemporalType) params[i+1]);
				i+=2; 
			} else if (params[i] instanceof Calendar) {
				query.setParameter(j, (Calendar)params[i], (TemporalType) params[i+1]);
				i+=2; 
			} else {
				query.setParameter(j, params[i]);
				i++; 
			}
			j++;
		}
		entités = query.getResultList();
		return entités;
	}

	protected E recherche(String queryString, Object... params) {
		try {
			TypedQuery<E> query = entityManager.createQuery(queryString, entityClass);
			int i = 0, j = 1;
			while(i < params.length) {
				if (params[i] instanceof Date ) {
					query.setParameter(j, (Date)params[i], (TemporalType) params[i+1]);
					i+=2; 
				} else if (params[i] instanceof Calendar) {
					query.setParameter(j, (Calendar)params[i], (TemporalType) params[i+1]);
					i+=2; 
				} else {
					query.setParameter(j, params[i]);
					i++; 
				}
				j++;
			}
			return (E) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			return null; // throw new InternalError();
		}
	}
}
