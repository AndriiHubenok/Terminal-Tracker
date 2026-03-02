package org.example.terminaltrakcer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminalMetadata {

    /**
     * Operating hours of the terminal.
     * Examples: "24/7", "08:00-22:00"
     */
    @JsonProperty("working_hours")
    private String workingHours;

    /**
     * Array of available services at the terminal.
     * Examples: ["UAH", "USD", "EUR"]
     */
    @JsonProperty("currencies_available")
    private List<String> currenciesAvailable;

    /**
     * Status of cash collection at the terminal.
     * Examples: "NEEDS_COLLECTION", "IN_PROGRESS", "COMPLETED"
     */
    @JsonProperty("collection_status")
    private String collectionStatus;

    /**
     * Accessibility features available at the terminal.
     * Examples: ["ramp", "audio_guide", "braille"]
     */
    @JsonProperty("accessibility")
    private List<String> accessibility;
}

