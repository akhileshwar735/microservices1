package com.akhil.service;

import com.akhil.Dao.ProductDao;
import com.akhil.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service("service")
// instructs spring to create an object of this class and keep in the container with the name "service"
public class DefaultProductService implements ProductService {

    // dependency
    @Autowired
    ProductDao dao;

    @Override
    public Product getProductById(Integer id) {
        Optional<Product> op = dao.findById(id);
        return op.isPresent() ? op.get() : null;
    }

    @Override
    public Product addNewProduct(Product product) {
        product.setProductId(null);
        return dao.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if(product.getProductId()==null){
            throw new RuntimeException("issue");
        }
        if(!dao.existsById(product.getProductId())){
            throw new RuntimeException("no id is present with provided id");
        }
        return dao.save(product);
    }

    @Override
    public Product deleteProduct(Integer id) {
        if(id==null || !dao.existsById(id)){
            throw new RuntimeException("Id is either null or dosen't exist");
        }
        Product p=dao.findById(id).get();
        p.setDiscontinued(1);
        return dao.save(p);

    }

    @Override
    public Iterable<Product> getAllProducts(Integer pageNum, Integer pageSize) {
        Page<Product> result = dao.findAll(PageRequest.of(pageNum-1, pageSize));
        return result.hasContent() ? result.getContent() : new ArrayList<>();
        // return result;
    }

    @Override
    public Iterable<Product> getOutOfStockProducts() {
        return dao.findByUnitsInStock(0);
    }

    @Override
    public Iterable<Product> getProductsInPriceRange(Double min, Double max) {
        return dao.findByPriceRange(min, max);
    }
}