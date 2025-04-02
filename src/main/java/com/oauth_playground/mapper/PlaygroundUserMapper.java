package com.oauth_playground.mapper;

import com.oauth_playground.model.PlaygroundUser;
import com.oauth_playground.record.PlaygroundUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlaygroundUserMapper {
    PlaygroundUserMapper MAPPER = Mappers.getMapper(PlaygroundUserMapper.class);

    PlaygroundUser toPlaygroundUser(com.oauth_playground.record.PlaygroundUserDTO playgroundUserDTO);
    PlaygroundUserDTO toPlaygroundUserDTO(PlaygroundUser playgroundUser);
}
