package com.sales.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sales.model.Product;

@Repository
public interface SalesRepository extends CrudRepository<Product, Integer> {

	Product findByName(String name);

	@Modifying
	@Query("update Product set orderDescription = :orderDescription where name = :name")
	public int updateProductByName(@Param("orderDescription") String orderDescription, @Param("name") String name);

	@Modifying
	@Query("update Product set orderDescription = :cancel where name = :name")
	public int cancelProductByName(@Param("cancel") String cancel, @Param("name") String name);

}
