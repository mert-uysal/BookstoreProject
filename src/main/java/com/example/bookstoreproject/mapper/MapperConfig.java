package com.example.bookstoreproject.mapper;

import org.mapstruct.Builder;
import org.mapstruct.CollectionMappingStrategy;

@org.mapstruct.MapperConfig(componentModel = "spring",
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    builder = @Builder(disableBuilder = true))
public class MapperConfig {
}
