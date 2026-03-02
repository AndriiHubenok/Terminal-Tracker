package org.example.terminaltrakcer.dto.mappers;

import org.example.terminaltrakcer.dto.terminal.TerminalCreateDTO;
import org.example.terminaltrakcer.dto.terminal.TerminalReadDTO;
import org.example.terminaltrakcer.dto.terminal.TerminalWithDistanceDTO;
import org.example.terminaltrakcer.entity.Terminal;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TerminalMapper {

    @Mapping(target = "location", expression = "java(toPoint(dto.getX(), dto.getY()))")
    @Mapping(target = "id", ignore = true)
    Terminal toEntity(TerminalCreateDTO dto);

    @Mapping(target = "id", expression = "java(terminal.getId().toString())")
    @Mapping(target = "x", expression = "java(terminal.getLocation().getX())")
    @Mapping(target = "y", expression = "java(terminal.getLocation().getY())")
    TerminalReadDTO toReadDTO(Terminal terminal);

    default Point toPoint(Double x, Double y) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        return geometryFactory.createPoint(new Coordinate(x, y));
    }
}
