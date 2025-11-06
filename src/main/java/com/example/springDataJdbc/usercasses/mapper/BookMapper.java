package com.example.springDataJdbc.usercasses.mapper;

import com.example.springDataJdbc.persistence.model.BookData;
import com.example.springDataJdbc.usercasses.dto.BookRequestDto;
import com.example.springDataJdbc.usercasses.dto.BookResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface BookMapper {

    BookData fromDtoToData(BookRequestDto bookRequestDto);

    BookResponseDto fromDataToDto(BookData bookData);

}
