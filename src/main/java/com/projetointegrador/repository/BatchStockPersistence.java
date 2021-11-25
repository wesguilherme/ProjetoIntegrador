package com.projetointegrador.repository;

import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.entity.ProductSeller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface BatchStockPersistence extends JpaRepository<BatchStock, Long> {

    List<BatchStock> findByProductSeller(ProductSeller productSeller);

    @Query(value = "SELECT ps.product_id, bs.current_quantity, bs.batch_stock_id" +
                " FROM batch_stock bs" +
                " JOIN product_seller ps on ps.product_seller_id = bs.product_seller_id" +
                " WHERE ps.product_id = :productId limit 1", nativeQuery = true)
    BatchStockByProductId batchStockByProductId(@Param("productId") String productId);

    Optional<BatchStock> findByBatchStockNumber(Long batchStockNumber);

    public interface BatchStockByProductId{
        Long getBatch_stock_id();
        String getProduct_id();
        Integer getCurrent_quantity();
    }


    @Query(value = "SELECT bs.batch_stock_number, ps.product_id, t.environment_type, bs.due_date, bs.current_quantity " +
            " FROM batch_stock bs" +
            " JOIN inbound_order i on i.inbound_order_id = bs.inbound_order_id" +
            " JOIN product_seller ps on ps.product_seller_id = bs.product_seller_id" +
            " JOIN product p on p.product_id = ps.product_id" +
            " JOIN type t on t.type_id = p.type_id" +
            " where i.section_code = :sectionCode" +
            " and due_date BETWEEN CURDATE() and DATE_ADD(CURDATE(), INTERVAL + :quantityOfDays DAY)", nativeQuery = true)
    List<BatchStockListByDays> listbatchByDays(@Param("sectionCode") String sectionCode, @Param("quantityOfDays") Integer quantityOfDays);

    @Query(value = "SELECT bs.batch_stock_number, ps.product_id, t.environment_type, bs.due_date, bs.current_quantity" +
            " FROM batch_stock bs" +
            " JOIN inbound_order i on i.inbound_order_id = bs.inbound_order_id" +
            " JOIN product_seller ps on ps.product_seller_id = bs.product_seller_id" +
            " JOIN product p on p.product_id = ps.product_id" +
            " JOIN type t on t.type_id = p.type_id" +
            " where bs.due_date BETWEEN CURDATE() and DATE_ADD(CURDATE(), INTERVAL + :quantityOfDays DAY) and t.type_id = :typeId"
            , nativeQuery = true)
    List<BatchStockListByFilter> listbatchByFilter(@Param("quantityOfDays") Integer quantityOfDays, @Param("typeId") Long typeId, Pageable pageable);

    public interface BatchStockListByDays {
        Long getBatch_stock_number();

        String getProduct_id();

        String getEnvironment_type();

        LocalDate getDue_date();

        Integer getCurrent_quantity();
    }

    public interface BatchStockListByFilter {
        Long getBatch_stock_number();

        String getProduct_id();

        String getEnvironment_type();

        LocalDate getDue_date();

        Integer getCurrent_quantity();
    }


}
