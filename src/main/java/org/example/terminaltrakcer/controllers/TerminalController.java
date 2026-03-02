package org.example.terminaltrakcer.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.terminaltrakcer.dto.terminal.TerminalCreateDTO;
import org.example.terminaltrakcer.dto.terminal.TerminalReadDTO;
import org.example.terminaltrakcer.entity.Terminal;
import org.example.terminaltrakcer.service.TerminalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/terminals")
@AllArgsConstructor
public class TerminalController {
    private final TerminalService terminalService;

    @PostMapping("/create")
    public ResponseEntity<TerminalReadDTO> createTerminal(@Valid @RequestBody TerminalCreateDTO terminalCreateDTO) {
        return ResponseEntity.status(201).body(terminalService.addTerminal(terminalCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerminalReadDTO> getTerminalById(@PathVariable String id) {
        TerminalReadDTO terminalReadDTO = terminalService.getTerminalById(id);
        if (terminalReadDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(terminalReadDTO);
    }

    @GetMapping("/nearby")
    public ResponseEntity<?> getNearbyTerminals(@RequestParam Double x, @RequestParam Double y, @RequestParam Double radius,
                                                @RequestParam(required = false) String workingHours,
                                                @RequestParam(required = false) List<String> currenciesAvailable,
                                                @RequestParam(required = false) String collectionStatus,
                                                @RequestParam(required = false) List<String> accessibility) {
        return ResponseEntity.ok().body(terminalService.getNearbyTerminals(x, y, radius, workingHours, currenciesAvailable, collectionStatus, accessibility));
    }
}
