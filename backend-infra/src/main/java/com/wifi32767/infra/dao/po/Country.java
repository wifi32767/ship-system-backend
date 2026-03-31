package com.wifi32767.infra.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 国家地区表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Country {

    /**
     * 国家ID
     */
    private Integer countryId;
    /**
     * 国家名称
     */
    private String countryName;
    /**
     * 英文名称
     */
    private String englishName;
}
