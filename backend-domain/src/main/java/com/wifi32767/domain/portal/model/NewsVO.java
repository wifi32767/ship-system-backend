package com.wifi32767.domain.portal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsVO {
    private String title;           // 新闻标题
    private String introduce;         // 摘要
    private String sourceLink;       // 原文链接
    private LocalDateTime publishTime; // 发布时间
    private String img;             // 新闻图片链接
    private String video;           // 新闻视频链接
}