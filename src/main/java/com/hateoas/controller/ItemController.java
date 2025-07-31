package com.hateoas.controller;

import com.hateoas.dto.ItemDTO;
import com.hateoas.dto.ItemWeb;
import com.hateoas.model.ItemEntity;
import com.hateoas.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService service;

    @PostMapping
    public ResponseEntity<ItemEntity> createItem(ItemDTO dto){
        ItemEntity entity = service.createItem(dto);
        entity.add(linkTo(methodOn(ItemController.class).getItemById(entity.getId())).withSelfRel());
        return new ResponseEntity<ItemEntity>(entity,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemEntity> updateItemById(@PathVariable Long id, @RequestBody ItemWeb web){
        ItemEntity entity = service.updateItem(id, web);
        // TODO
//        if (entity.equals(web)){
//            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//        }
        return ResponseEntity.ok(entity);
    }

    @GetMapping
    public ResponseEntity<List<ItemEntity>> getAllItem(){
        List<ItemEntity> entities = service.getAllItem();
        if (entities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        entities.forEach(e -> e.add(linkTo(methodOn(ItemController.class).getItemById(e.getId())).withSelfRel()));
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemEntity> getItemById(@PathVariable Long id){
        ItemEntity entity = service.getItemById(id);
        if (entity == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        entity.add(linkTo(methodOn(ItemController.class).getAllItem()).withRel("list-all"));
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteItemById(@PathVariable Long id){
        Boolean isDeleted = service.deleteItemById(id);
        if (!isDeleted){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity.ok(isDeleted);
    }
}
