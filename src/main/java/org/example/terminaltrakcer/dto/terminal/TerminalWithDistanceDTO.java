package org.example.terminaltrakcer.dto.terminal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminalWithDistanceDTO {
    private String id;

    private String terminalCode;

    private Double x;

    private Double y;

    private Double distance;
}
