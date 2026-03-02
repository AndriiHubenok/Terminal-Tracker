package org.example.terminaltrakcer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Point;

import java.util.UUID;

@Entity
@Table(
        name = "terminals",
        indexes = {
                @Index(name = "idx_terminals_location_gist", columnList = "location")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String terminalCode;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point location;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "terminal_metadata", columnDefinition = "jsonb")
    private TerminalMetadata terminalMetadata;
}
