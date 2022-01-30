package com.mono.repository;

import com.mono.entity.Flight;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class FlightsRepository {

    private final EntityManager entityManager;

    @Transactional
    public List searchAll() {
        Query wildcardQuery = getQueryBuilder()
                .keyword()
                .wildcard()
                .onField("whence")
                .matching("*")
                .createQuery();
        return getJpaQuery(wildcardQuery).getResultList();
    }

    @Transactional
    public Object getById(Long id) {
        Query wildcardQuery = getQueryBuilder()
                .keyword()
                .onField("id")
                .matching(id)
                .createQuery();
        return getJpaQuery(wildcardQuery).getSingleResult();
    }

    private FullTextQuery getJpaQuery(Query luceneQuery) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        return fullTextEntityManager.createFullTextQuery(luceneQuery, Flight.class);
    }

    private QueryBuilder getQueryBuilder() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Flight.class)
                .get();
    }
}
