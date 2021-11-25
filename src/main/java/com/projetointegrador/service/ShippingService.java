package com.projetointegrador.service;

import com.projetointegrador.dto.ShippingResponseDto;
import com.projetointegrador.dto.StateDto;
import com.projetointegrador.entity.Shipping;
import com.projetointegrador.entity.States;
import com.projetointegrador.repository.ShippingPersistence;
import com.projetointegrador.repository.StatesPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        return shippingPersistence.save(shipping);
    }


    public Shipping update(Shipping shipping, String id) {
        Optional<Shipping> shipping1 = shippingPersistence.findById(id);

        if (shipping1.isPresent()){
            shipping1.get().setWarehouse(shipping.getWarehouse());
            shipping1.get().setBuyer(shipping.getBuyer());
            shipping1.get().setProduct(shipping.getProduct());
            shipping1.get().setStates(shipping.getStates());

            return shippingPersistence.save(shipping1.get());
        }

        throw new RuntimeException("Não existe entrega com esse id!");
    }


//    public Shipping remover(Shipping shippings){
//        List<Shipping> shipping = shippingPersistence.findAll();
//        Iterator<Shipping> iterator = shipping.iterator();
//        while (iterator.hasNext()) {
////            Shipping shipping1 = iterator.next();
//            ShippingDto shippingDto = iterator.next();
//            if (shippingDto.getShippingId().equalsIgnoreCase(String.valueOf(shippings))) {
////                if (shipping1.getShippingId().equalsIgnoreCase(shippingId)) {
//                    iterator.remove();
////                }
//            }
//        }
//        try {
//            shippingDto.cadastro(shipping);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("erro ao deletar shipping");
//        }
//
//        return null;
//    }

    public List<ShippingResponseDto> listShipping() {
        List<ShippingResponseDto> shippingResponseDtoList = new ArrayList<>();
        List<Shipping> shipping = shippingPersistence.findAll();
        for (Shipping item : shipping) {
            ShippingResponseDto shippingResponseDto = new ShippingResponseDto();
            shippingResponseDto.setShippingId(item.getShippingId());
            shippingResponseDto.setWarehouseCode(item.getWarehouse().getWarehouseCode());
            shippingResponseDto.setBuyerId(item.getBuyer().getName());
            shippingResponseDto.setProductId(item.getProduct().getDescription());

            shippingResponseDto.setCep(item.getStates().getCep());
            shippingResponseDto.setNome(item.getStates().getNome());
            shippingResponseDto.setValorFrete(item.getStates().getValorFrete());

            shippingResponseDtoList.add(shippingResponseDto);
        }

        return shippingResponseDtoList;
    }

}
