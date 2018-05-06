package com.cisco.appointmentservice.mapstruct;


import io.swagger.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "notification", source = "userPref")
    User getUserModel(com.cisco.appointmentservice.dao.beans.User userBean);

    @Mapping(target = "userPref", source = "notification")
    com.cisco.appointmentservice.dao.beans.User getUserBean(User userModel);

    void updateUserBean(@MappingTarget com.cisco.appointmentservice.dao.beans.User target, com.cisco.appointmentservice.dao.beans.User source);

}
