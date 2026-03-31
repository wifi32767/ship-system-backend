package com.wifi32767.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassStatsDTO {
    String className; // 类名
    int number; // 该类的对象数量
}
