package org.example.terminaltrakcer.dto.terminal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.terminaltrakcer.entity.TerminalMetadata;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminalReadDTO {
    private String id;

    private String terminalCode;

    private Double x;

    private Double y;

    private TerminalMetadata terminalMetadata;
}
