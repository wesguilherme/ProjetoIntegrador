package com.projetointegrador.repository;

import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.entity.ProductSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BatchStockPersistence extends JpaRepository<BatchStock, Long> {
    List<BatchStock> findByProductSeller(ProductSeller productSeller);

    @Query(value = "SELECT * FROM pi_frescos.batch_stock " +
            " where due_date BETWEEN CURDATE() and DATE_ADD(CURDATE(), INTERVAL + :dias DAY)", nativeQuery = true)
    List<BatchStock> listbatchByDays(@Param("dias") Integer dias);
}
