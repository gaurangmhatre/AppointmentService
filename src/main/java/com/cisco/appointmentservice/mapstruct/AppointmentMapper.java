package com.cisco.appointmentservice.mapstruct;

import io.swagger.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.ZoneId;

@Mapper
//@DecoratedWith(AppointmentMapperDecorator.class)
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    @Mapping(target = "host", expression = "java(appointmentBean.getHost() != null ? appointmentBean.getHost().getEmail() : null)")
    @Mapping(target = "from", expression = "java(com.cisco.appointmentservice.util.DateUtil.getLocalDateTime(appointmentBean.getStart(), zoneId))")
    @Mapping(target = "to", expression = "java(com.cisco.appointmentservice.util.DateUtil.getLocalDateTime(appointmentBean.getEnd(), zoneId))")
    @Mapping(target = "id", source = "appointmentBean.id")
    @Mapping(target = "participants", expression = "java(com.cisco.appointmentservice.util.ServiceUtil.getParticipants(appointmentBean.getParticipants()))")
    @Mapping(target = "allowOverlap", expression = "java(null)")
    Appointment getAppointment(com.cisco.appointmentservice.dao.beans.Appointment appointmentBean, ZoneId zoneId);

}
