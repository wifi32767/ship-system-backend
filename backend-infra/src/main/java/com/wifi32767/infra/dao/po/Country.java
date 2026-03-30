package com.wifi32767.infra.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * 国家地区表
 */
public class Country {

    /** 国家ID */
    private Integer countryId;
    /** 国家名称 */
    private String countryName;
    /** 英文名称 */
    private String englishName;
}
