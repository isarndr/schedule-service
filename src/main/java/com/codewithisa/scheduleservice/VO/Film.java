package com.codewithisa.scheduleservice.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Film {

    @Schema(example = "1")
    private Long filmCode;

    @Schema(example = "Nemo")
    private String filmName;

    @Schema(example = "true")
    private boolean sedangTayang;
}
