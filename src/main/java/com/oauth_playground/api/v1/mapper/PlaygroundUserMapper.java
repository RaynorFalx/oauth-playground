package com.oauth_playground.api.v1.mapper;

import com.oauth_playground.api.v1.model.PlaygroundUser;
import com.oauth_playground.api.v1.record.PlaygroundUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlaygroundUserMapper {
    PlaygroundUserMapper MAPPER = Mappers.getMapper(PlaygroundUserMapper.class);

    PlaygroundUser toPlaygroundUser(PlaygroundUserDTO playgroundUserDTO);
    PlaygroundUserDTO toPlaygroundUserDTO(PlaygroundUser playgroundUser);
}
