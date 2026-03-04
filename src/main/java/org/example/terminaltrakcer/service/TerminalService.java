package org.example.terminaltrakcer.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.terminaltrakcer.dto.mappers.TerminalMapper;
import org.example.terminaltrakcer.dto.terminal.TerminalCreateDTO;
import org.example.terminaltrakcer.dto.terminal.TerminalReadDTO;
import org.example.terminaltrakcer.dto.terminal.TerminalUpdateDTO;
import org.example.terminaltrakcer.dto.terminal.TerminalWithDistanceDTO;
import org.example.terminaltrakcer.entity.Terminal;
import org.example.terminaltrakcer.repository.TerminalRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TerminalService {
    private final TerminalRepository terminalRepository;
    private final TerminalMapper terminalMapper;

    public TerminalReadDTO addTerminal(@Valid @RequestBody TerminalCreateDTO terminalCreateDTO) {
        Terminal terminal = terminalMapper.toEntity(terminalCreateDTO);
        Terminal savedTerminal = terminalRepository.save(terminal);
        return terminalMapper.toReadDTO(savedTerminal);
    }

    public TerminalReadDTO getTerminalById(String id) {
        return terminalRepository.findById(UUID.fromString(id))
                .map(terminalMapper::toReadDTO)
                .orElse(null);
    }

    public List<TerminalWithDistanceDTO> getNearbyTerminals(
            Double x, Double y, Double radius,
            String workingHours,
            List<String> currenciesAvailable,
            String collectionStatus,
            List<String> accessibility) {

        String currenciesArray = currenciesAvailable != null
                ? "{" + String.join(",", currenciesAvailable) + "}"
                : null;

        String accessibilityArray = accessibility != null
                ? "{" + String.join(",", accessibility) + "}"
                : null;

        List<Object[]> rawResults = terminalRepository.findTerminalsWithFilters(
                x, y, radius,
                workingHours,
                collectionStatus,
                currenciesArray,
                accessibilityArray
        );

        return rawResults.stream().map(row -> {
            TerminalWithDistanceDTO dto = new TerminalWithDistanceDTO();
            dto.setId((String) row[0]);
            dto.setTerminalCode((String) row[1]);
            dto.setX((Double) row[2]);
            dto.setY((Double) row[3]);
            dto.setDistance((Double) row[4]);
            return dto;
        }).collect(Collectors.toList());
    }

    public TerminalReadDTO updateTerminal(TerminalUpdateDTO terminalUpdateDTO) {
        Terminal existingTerminal = terminalRepository.findById(UUID.fromString(terminalUpdateDTO.getId()))
                .orElse(null);
        if (existingTerminal == null) return null;

        terminalMapper.updateEntity(terminalUpdateDTO, existingTerminal);
        Terminal updatedTerminal = terminalRepository.save(existingTerminal);
        return terminalMapper.toReadDTO(updatedTerminal);
    }

    public boolean deleteTerminal(String id) {
        UUID terminalId = UUID.fromString(id);
        if (terminalRepository.existsById(terminalId)) {
            terminalRepository.deleteById(terminalId);
            return true;
        }
        return false;
    }
}
