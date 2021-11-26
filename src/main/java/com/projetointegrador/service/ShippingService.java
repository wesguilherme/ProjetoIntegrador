package com.projetointegrador.service;

import com.projetointegrador.dto.ShippingResponseDto;
import com.projetointegrador.entity.Shipping;
import com.projetointegrador.repository.ShippingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingService {

    @Autowired
    private ShippingPersistence shippingPersistence;

    public ShippingService() {}

    /**
     * @param shippingPersistence é esperado um parâmetro do tipo shippingPersistence para injeção de dependência
     * @author Rafael
     */
    public ShippingService(ShippingPersistence shippingPersistence) {
        this.shippingPersistence = shippingPersistence;
    }

    /**
     * @param shipping é esperado um parâmetro do tipo shipping
     * @return o shipping cadastrado na lista
     * @author Rafael
     */
    public Shipping insert(Shipping shipping) {
        return shippingPersistence.save(shipping);
    }


    // Metodo criado, mais seria necessário adicionar atributos e não daria tempo.


//    public Shipping update(Shipping shipping, String id) {
//        Optional<Shipping> shipping1 = shippingPersistence.findById(id);
//
//        if (shipping1.isPresent()){
//            shipping1.get().setWarehouse(shipping.getWarehouse());
//            shipping1.get().setBuyer(shipping.getBuyer());
//            shipping1.get().setProduct(shipping.getProduct());
//            shipping1.get().setStates(shipping.getStates());
//
//            return shippingPersistence.save(shipping1.get());
//        }
//
//        throw new RuntimeException("Não existe entrega com esse id!");
//    }

    /**
     * @return uma lista de ShippingResponseDto
     * @author Rafael
     */
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
