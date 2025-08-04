package com.hateoas.service;

import com.hateoas.dto.ItemDTO;
import com.hateoas.dto.ItemWeb;
import com.hateoas.model.ItemEntity;
import com.hateoas.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public ItemEntity createItem(ItemDTO dto){
        return  repository.save(new ItemEntity(dto.context()));
    }

    public ItemEntity getItemById(Long id) {
        return  new ItemEntity(1L, "Lorem ipsum");
    }

    public ItemEntity updateItem(Long id, ItemWeb web) {
        return  new ItemEntity(1L, "Lorem ipsum");
    }

    public Boolean deleteItemById(Long id) {
        return true;
    }

    public List<ItemEntity> getAllItem() {
        return List.of(new ItemEntity(1L, "A"),new ItemEntity(2L, "B"));
    }
}
