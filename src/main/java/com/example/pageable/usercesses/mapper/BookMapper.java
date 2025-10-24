package com.example.pageable.usercesses.mapper;

import com.example.pageable.persistence.model.BookEntity;
import com.example.pageable.usercesses.dto.BookRequestDto;
import com.example.pageable.usercesses.dto.BookResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface BookMapper {

    BookEntity fromDtoToEntity(BookRequestDto bookRequestDto);

    BookResponseDto fromEntityToDto(BookEntity bookEntity);


}
