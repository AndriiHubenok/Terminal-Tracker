package org.example.terminaltrakcer.dto.mappers;

import org.example.terminaltrakcer.dto.terminal.TerminalCreateDTO;
import org.example.terminaltrakcer.dto.terminal.TerminalReadDTO;
import org.example.terminaltrakcer.dto.terminal.TerminalUpdateDTO;
import org.example.terminaltrakcer.dto.terminal.TerminalWithDistanceDTO;
import org.example.terminaltrakcer.entity.Terminal;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TerminalMapper {

    @Mapping(target = "location", expression = "java(toPoint(dto.getX(), dto.getY()))")
    @Mapping(target = "id", ignore = true)
    Terminal toEntity(TerminalCreateDTO dto);

    @Mapping(target = "id", expression = "java(terminal.getId().toString())")
    @Mapping(target = "x", expression = "java(terminal.getLocation().getX())")
    @Mapping(target = "y", expression = "java(terminal.getLocation().getY())")
    TerminalReadDTO toReadDTO(Terminal terminal);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "location", expression = "java(dto.getX() != null && dto.getY() != null ? toPoint(dto.getX(), dto.getY()) : terminal.getLocation())")
    @Mapping(target = "terminalCode", expression = "java(dto.getTerminalCode() != null ? dto.getTerminalCode() : terminal.getTerminalCode())")
    @Mapping(target = "terminalMetadata", expression = "java(dto.getTerminalMetadata() != null ? dto.getTerminalMetadata() : terminal.getTerminalMetadata())")
    void updateEntity(TerminalUpdateDTO dto, @MappingTarget Terminal terminal);

    default Point toPoint(Double x, Double y) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        return geometryFactory.createPoint(new Coordinate(x, y));
    }
}
