package com.wifi32767.domain.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryVO {
    private Integer countryId; // 国家ID
    private String countryName; // 国家名
    private String englishName; // 英文名
    private Integer dataCount; // 关联数据量
}
