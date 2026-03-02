-- Підключення розширення PostGIS (якщо ще не підключено)
CREATE EXTENSION IF NOT EXISTS postgis;

-- Створення таблиці terminals
CREATE TABLE IF NOT EXISTS terminals (
                                         id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    terminal_code VARCHAR(255) NOT NULL UNIQUE,
    location      GEOMETRY(Point, 4326),
    terminal_metadata JSONB
    );

-- Створення просторового індексу GiST для поля location
CREATE INDEX IF NOT EXISTS idx_terminals_location_gist
    ON terminals USING GIST (location);