package com.projetointegrador.service;

import com.projetointegrador.dto.BlackFridayResponseDto;
import com.projetointegrador.entity.BlackFriday;
import com.projetointegrador.repository.BlackFridayPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlackFridayService {

    @Autowired
    BlackFridayPersistence blackFridayPersistence;

    public BlackFridayService(BlackFridayPersistence blackFridayPersistence) {
        this.blackFridayPersistence = blackFridayPersistence;
    }

    public BlackFriday insert(BlackFriday blackFriday) {
        return blackFridayPersistence.save(blackFriday);
    }

    /**
     * @return retorna a lista blackFridayResponseDtoList
     * @author - Grupo 5
     */
    public List<BlackFridayResponseDto> listProduct() {
        List<BlackFridayResponseDto> blackFridayResponseDtoList = new ArrayList<>();
        List<BlackFriday> blackFridays = blackFridayPersistence.findAll();
        for (BlackFriday item : blackFridays) {
            BlackFridayResponseDto blackFridayResponseDto = new BlackFridayResponseDto();
            blackFridayResponseDto.setProductId(item.getProductId());
            blackFridayResponseDto.setDiscount(item.getDiscount());
            blackFridayResponseDto.setInitialDate(item.getInitialDate());
            blackFridayResponseDto.setFinalDate(item.getFinalDate());
            blackFridayResponseDtoList.add(blackFridayResponseDto);
        }
        return blackFridayResponseDtoList;
    }

    /**
     * @param blackFriday é esperado um objeto do tipo blackFriday
     * @param productId   é esperado um parametro do tipo productId
     * @return retorna o produto atualizado
     * @author - Grupo 5
     */
    public BlackFriday update(BlackFriday blackFriday, String productId) {
        Optional<BlackFriday> blackFriday1 = blackFridayPersistence.findByProductId(productId);

        if (blackFriday1.isPresent()) {
            blackFriday1.get().setProductId(blackFriday.getProductId());
            blackFriday1.get().setDiscount(blackFriday.getDiscount());
            blackFriday1.get().setInitialDate(blackFriday.getInitialDate());
            blackFriday1.get().setFinalDate(blackFriday.getFinalDate());

            blackFridayPersistence.save(blackFriday1.get());
            return blackFriday1.get();
        }
        throw new RuntimeException("Não existe esse produto no hall de descontos!");
    }

    /**
     * @param productId é esperado um parametro do tipo productId
     * @author - Grupo 5
     */
    public void delete(String productId) {
        Optional<BlackFriday> blackFriday1 = blackFridayPersistence.findByProductId(productId);
        if (blackFriday1.isPresent()) {
            blackFridayPersistence.delete(blackFriday1.get());
        }
    }

    /**
     * @param productId é esperado um parametro do tipo productId
     * @return retorna o produto pelo productId
     * @author - Grupo 5
     */
    public BlackFriday getByIdProduct(String productId) {
        Optional<BlackFriday> val = blackFridayPersistence.findByProductId(productId);
        if (val.isPresent()) {
            return val.get();
        } else {
            return null;
        }
    }
}
