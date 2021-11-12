package com.projetointegrador.repository;

import com.projetointegrador.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehousePersistence extends JpaRepository<Warehouse, String> {

    Optional<Warehouse> findByWarehouseCode(String code);

    @Query(value = "SELECT se.warehouse_code, ps.product_id, sum(bs.current_quantity) as totalQuantidade FROM inbound_order i " +
            " JOIN batch_stock bs on bs.inbound_order_id = i.inbound_order_id " +
            " JOIN product_seller ps on ps.product_seller_id = bs.product_seller_id " +
            " JOIN section se on se.section_code = i.section_code " +
            " WHERE ps.product_id = :productId" +
            " GROUP BY se.warehouse_code, ps.product_id", nativeQuery = true)

    List<listWarehouseByProduct> listWarehouseByProduct(@Param("productId") String productId);

    public interface listWarehouseByProduct{
        String getWarehouse_code();
        String getProduct_id();
        Integer getTotalQuantidade();
    }
}
