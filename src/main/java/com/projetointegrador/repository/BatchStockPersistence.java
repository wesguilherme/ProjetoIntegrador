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

    @Query(value = "SELECT bs.* FROM batch_stock bs" +
            " JOIN inbound_order i on i.inbound_order_id = bs.inbound_order_inbound_order_id" +
            " where i.section_code = ':sectionCode' and" +
            " due_date BETWEEN CURDATE() and DATE_ADD(CURDATE(), INTERVAL +:quantityOfDays DAY)", nativeQuery = true)
    List<BatchStock> listbatchByDays(@Param("sectionCode") String sectionCode, @Param("quantityOfDays") Integer quantityOfDays);
}
