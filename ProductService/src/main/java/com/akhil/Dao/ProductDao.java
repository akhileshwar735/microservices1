package com.akhil.Dao;

import com.akhil.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("dao") // instructs spring to create an instance of this type and keep in container with name "dao"
public interface ProductDao extends PagingAndSortingRepository<Product, Integer> {

    // method convention --> findByXxx --> xxx is the name of the field in the entity class
    public Iterable<Product> findByUnitsInStock(Integer unitsInStock);

    @Query("from Product where unitPrice between ?1 and ?2")
    public Iterable<Product> findByPriceRange(Double min, Double max);
}