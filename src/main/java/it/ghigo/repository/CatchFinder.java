package it.ghigo.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import it.ghigo.model.Catch;
import it.ghigo.model.parameter.CatchSearchParameter;

public class CatchFinder {
	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public Page<Catch> findByCatchSearchParameter(CatchSearchParameter catchSearchParameter) {
		if (catchSearchParameter.isEmpty())
			return new PageImpl<>(new ArrayList<>());

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Catch> querySelect = builder.createQuery(Catch.class);
		//
		prepeareQuerySelect(builder, querySelect, catchSearchParameter);
		//
		List<Catch> catchList = entityManager.createQuery(querySelect).getResultList();
		Pageable pageable = catchSearchParameter.getPageable();
		int pageNumber = pageable.getPageNumber();
		int pageSize = pageable.getPageSize();
		int first = pageNumber * pageSize;
		int last = first + pageSize;
		if (last > catchList.size())
			last = catchList.size();
		return new PageImpl<>(catchList.subList(first, last), pageable, catchList.size());
	}

	private void prepeareQuerySelect(CriteriaBuilder builder, CriteriaQuery<Catch> query,
			CatchSearchParameter catchSearchParameter) {
		Root<Catch> c = query.from(Catch.class);
		//
		Path<String> locationName = c.join("location").get("name");
		Path<String> fishName = c.join("fish").get("name");
		Path<String> lureName = c.join("lure").get("name");
		Path<Double> weightKg = c.get("weightKg");
		Path<Date> dt = c.get("dt");
		//
		List<Order> orderByList = new ArrayList<>();
		orderByList.add(builder.asc(locationName));
		orderByList.add(builder.asc(fishName));
		orderByList.add(builder.desc(weightKg));
		orderByList.add(builder.desc(dt));
		orderByList.add(builder.asc(lureName));
		//
		List<Predicate> predicateList = getPredicateList(builder, c, catchSearchParameter);
		query.select(c).where(builder.and(predicateList.toArray(new Predicate[predicateList.size()])))
				.orderBy(orderByList);
	}

	private List<Predicate> getPredicateList(CriteriaBuilder builder, Root<Catch> c,
			CatchSearchParameter catchSearchParameter) {
		Path<String> locationName = c.join("location").get("name");
		Path<String> fishName = c.join("fish").get("name");
		Path<String> lureName = c.join("lure").get("name");
		Path<Date> dt = c.get("dt");
		//
		List<Predicate> predicateList = new ArrayList<>();
		if (StringUtils.isNotBlank(catchSearchParameter.getLocationName())) {
			predicateList.add(builder.like(builder.upper(locationName),
					"%" + catchSearchParameter.getLocationName().toUpperCase() + "%"));
		}
		if (StringUtils.isNotBlank(catchSearchParameter.getFishName()))
			predicateList.add(getPredicateFish(builder, fishName, catchSearchParameter));
		if (StringUtils.isNotBlank(catchSearchParameter.getLureName()))
			predicateList.add(getPredicateLure(builder, lureName, catchSearchParameter));
		if (catchSearchParameter.getDt() != null)
			predicateList.add(builder.greaterThanOrEqualTo(dt, catchSearchParameter.getDt()));
		//
		return predicateList;
	}

	private Predicate getPredicateFish(CriteriaBuilder builder, Path<String> fishName,
			CatchSearchParameter catchSearchParameter) {
		if (catchSearchParameter.isExactFishName())
			return builder.equal(builder.upper(fishName), catchSearchParameter.getFishName().toUpperCase());
		return builder.like(builder.upper(fishName), "%" + catchSearchParameter.getFishName().toUpperCase() + "%");
	}

	private Predicate getPredicateLure(CriteriaBuilder builder, Path<String> lureName,
			CatchSearchParameter catchSearchParameter) {
		if (catchSearchParameter.isExactLureName()) {
			if (!catchSearchParameter.isNotLureName())
				return builder.equal(builder.upper(lureName), catchSearchParameter.getLureName().toUpperCase());
			return builder.notEqual(builder.upper(lureName), catchSearchParameter.getLureName().toUpperCase());
		}
		if (!catchSearchParameter.isNotLureName())
			return builder.like(builder.upper(lureName), "%" + catchSearchParameter.getLureName().toUpperCase() + "%");
		return builder.notLike(builder.upper(lureName), "%" + catchSearchParameter.getLureName().toUpperCase() + "%");
	}
}