package com.wifi32767.infra.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

@TableName("country")
public class Country {

    /**
     * 国家ID
     */
    @TableId(type = IdType.AUTO)
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
