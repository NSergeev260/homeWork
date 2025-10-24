package com.example.pageable.usercesses.mapper;

import com.example.pageable.persistence.model.AuthorEntity;
import com.example.pageable.usercesses.dto.AuthorRequestDto;
import com.example.pageable.usercesses.dto.AuthorResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface AuthorMapper {

    AuthorEntity fromDtoToEntity(AuthorRequestDto authorRequestDto);

    AuthorResponseDto fromEntityToDto(AuthorEntity authorEntity);

}
