package com.wifi32767.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordCreateBody {
    private int modelId;
    private String keywordName;
    private int useFlag;
}
