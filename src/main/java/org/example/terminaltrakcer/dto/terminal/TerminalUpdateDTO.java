package org.example.terminaltrakcer.dto.terminal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.terminaltrakcer.entity.TerminalMetadata;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminalUpdateDTO {
    @NotBlank
    private String id;

    private String terminalCode;

    private Double x;

    private Double y;

    private TerminalMetadata terminalMetadata;
}
