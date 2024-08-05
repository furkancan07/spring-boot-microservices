package com.rf.product_server.repository;

import com.rf.product_server.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByUserId(Long userId);
    @Modifying
    @Query("DELETE FROM Product p WHERE p.userId = :userId")
    void deleteByUserId(Long userId);

}
