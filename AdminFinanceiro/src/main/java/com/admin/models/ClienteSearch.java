package com.admin.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ClienteSearch {
	
	@Autowired
	  private EntityManager entityManager;

	/* @Autowired
	 public ClienteSearch(EntityManager em) {
	       super();
	        this.entityManager = em;
	 }*/
	 
	    public void initializeHibernateSearch() {

	        try {
	            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
	            fullTextEntityManager.createIndexer().startAndWait();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @SuppressWarnings("unchecked")
		@Transactional
	    public List<Cliente> search(String searchTerm) {

	        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
	        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Cliente.class).get();
	        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("nome","cpf")
	                .matching(searchTerm).createQuery();

	        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Cliente.class);

	        // execute search

	        List<Cliente> listC = null;
	        try {
	        	listC = jpaQuery.getResultList();
	        } catch (NoResultException nre) {
	            ;// do nothing

	        }

	        return listC;


	    }
	 
}
