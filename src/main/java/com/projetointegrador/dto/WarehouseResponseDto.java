package com.projetointegrador.dto;

import com.projetointegrador.repository.WarehousePersistence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseResponseDto {

    private String productId;
    private List<WarehousesDto> warehouses;


    public static WarehouseResponseDto convert(List<WarehousePersistence.listWarehouseByProduct> warehousesByProduct){

        WarehouseResponseDto warehouseResponseDto1 = new WarehouseResponseDto();
        List<WarehousesDto> warehousesDtosList = new ArrayList<>();
        int cont = 0;
        for (WarehousePersistence.listWarehouseByProduct item: warehousesByProduct) {
            if (cont == 0)
                warehouseResponseDto1.setProductId(item.getProduct_id());

            WarehousesDto warehousesDto = new WarehousesDto();
            warehousesDto.setWarehouseCode(item.getWarehouse_code());
            warehousesDto.setQuantidade(item.getTotalQuantidade());

            warehousesDtosList.add(warehousesDto);

            cont = 1;
        }
        warehouseResponseDto1.setWarehouses(warehousesDtosList);

        return warehouseResponseDto1;
    }

}
