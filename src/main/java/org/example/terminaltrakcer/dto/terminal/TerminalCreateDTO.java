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
public class TerminalCreateDTO {
    @NotBlank
    private String terminalCode;

    @NotNull
    private Double x;

    @NotNull
    private Double y;

    private TerminalMetadata terminalMetadata;
}
