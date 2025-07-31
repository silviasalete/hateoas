package com.hateoas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ItemEntity extends RepresentationModel<ItemEntity> {

    private Long id;
    private String context;
}
