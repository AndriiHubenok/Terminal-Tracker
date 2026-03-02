package org.example.terminaltrakcer.repository;

import org.example.terminaltrakcer.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, UUID> {
    @Query(value = """
    SELECT
        CAST(t.id AS VARCHAR) AS id,
        t.terminal_code AS terminalCode,
        ST_X(t.location::geometry) AS x,
        ST_Y(t.location::geometry) AS y,
        ST_Distance(
            t.location::geography,
            ST_SetSRID(ST_MakePoint(:lon, :lat), 4326)::geography
        ) AS distance
    FROM terminals t
    WHERE ST_DWithin(
            t.location::geography,
            ST_SetSRID(ST_MakePoint(:lon, :lat), 4326)::geography,
            :radiusInMeters
        )
        AND (:workingHours IS NULL OR t.terminal_metadata->>'working_hours' = :workingHours)
        AND (:collectionStatus IS NULL OR t.terminal_metadata->>'collection_status' = :collectionStatus)
        AND (:currenciesAvailable IS NULL OR jsonb_exists_any(t.terminal_metadata->'currencies_available', CAST(:currenciesAvailable AS text[])))
        AND (:accessibility IS NULL OR jsonb_exists_any(t.terminal_metadata->'accessibility', CAST(:accessibility AS text[])))
    ORDER BY distance ASC
    """, nativeQuery = true)
    List<Object[]> findTerminalsWithFilters(
            @Param("lon") double lon,
            @Param("lat") double lat,
            @Param("radiusInMeters") double radiusInMeters,
            @Param("workingHours") String workingHours,
            @Param("collectionStatus") String collectionStatus,
            @Param("currenciesAvailable") String currenciesAvailable,
            @Param("accessibility") String accessibility
    );
}
