package com.projetointegrador.service;

import com.projetointegrador.entity.Shipping;
import com.projetointegrador.repository.ShippingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShippingService {

    @Autowired
    private ShippingPersistence shippingPersistence;

    public ShippingService() {}

    public ShippingService(ShippingPersistence shippingPersistence) {
        this.shippingPersistence = shippingPersistence;
    }


    public Shipping insert(Shipping shipping) {
        if (shipping.getWarehouse().getWarehouseCode() != null && shipping.getBuyer().getBuyerId() !=null) {
            return shippingPersistence.save(shipping);
        } else {
            throw new RuntimeException("Warehouse ou Buyer não existe");
        }
    }

    public Shipping update(Shipping shipping, String id) {
        Optional<Shipping> shipping1 = shippingPersistence.findById(id);

        if (shipping1.isPresent()){
            shipping1.get().setWarehouse(shipping.getWarehouse());
            shipping1.get().setBuyer(shipping.getBuyer());
            shipping1.get().setProduct(shipping.getProduct());

            return shippingPersistence.save(shipping1.get());
        }

        throw new RuntimeException("Não existe entrega com esse id!");
    }

}
