package com.wifi32767.interfaces.http.admin;

import com.wifi32767.common.Response;
import com.wifi32767.interfaces.client.SpiderClient;
import com.wifi32767.interfaces.dto.KeywordCreateBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/model")
@Tag(name = "模型管理接口", description = "模型管理接口")
public class ModelControllerImp implements ModelController {

    @Resource
    private SpiderClient spiderClient;

    @Override
    @GetMapping("/list")
    @Operation(summary = "分页查询爬虫模型列表", description = "分页查询爬虫模型列表")
    public String getModelList(@RequestParam int pageNum, @RequestParam int pageSize) {
        try {
            return spiderClient.getModelList(pageNum, pageSize);
        } catch (Exception e) {
            log.error("Error fetching model list", e);
            return new Response<String>(Response.ERROR, "Failed to fetch model list: " + e.getMessage()).toString();
        }
    }

    @Override
    @GetMapping("/{id}/detail")
    @Operation(summary = "查询模型详情", description = "查询模型详情（含关键词）")
    public String getModel(@PathVariable("id") int id) {
        try {
            return spiderClient.getModelDetail(id);
        } catch (Exception e) {
            log.error("Error fetching model detail, id: {}", id, e);
            return new Response<String>(Response.ERROR, "Failed to fetch model detail: " + e.getMessage()).toString();
        }
    }

    @Override
    @PostMapping("/save")
    @Operation(summary = "新增/编辑模型", description = "新增或更新模型信息（FormData + 文件上传）")
    public String saveModel(@RequestParam(value = "mReptileModelId", required = false) String mReptileModelId,
                            @RequestParam("mReptileModelName") String mReptileModelName,
                            @RequestParam("mReptileModelIntroduce") String mReptileModelIntroduce,
                            @RequestParam("mReptileModelWeb") String mReptileModelWeb,
                            @RequestParam("cronExpression") String cronExpression,
                            @RequestParam("keywords") String keywords,
                            @RequestPart(value = "scriptFile", required = false) MultipartFile scriptFile,
                            @RequestPart(value = "startupFile", required = false) MultipartFile startupFile) {
        try {
            return spiderClient.saveModel(mReptileModelId, mReptileModelName, mReptileModelIntroduce, mReptileModelWeb, cronExpression, keywords, scriptFile, startupFile);
        } catch (Exception e) {
            log.error("Error saving model", e);
            return new Response<String>(Response.ERROR, "Failed to save model: " + e.getMessage()).toString();
        }
    }

    @Override
    @DeleteMapping("/{id}/delete")
    @Operation(summary = "逻辑删除模型", description = "根据ID逻辑删除模型")
    public String deleteModel(@PathVariable("id") int id) {
        try {
            return spiderClient.deleteModel(id);
        } catch (Exception e) {
            log.error("Error deleting model, id: {}", id, e);
            return new Response<String>(Response.ERROR, "Failed to delete model: " + e.getMessage()).toString();
        }
    }

    @Override
    @PostMapping("/{id}/start")
    @Operation(summary = "手动触发流水线", description = "根据ID启动模型（后台线程）")
    public String startModel(@PathVariable("id") int id) {
        try {
            return spiderClient.startModel(id);
        } catch (Exception e) {
            log.error("Error starting model, id: {}", id, e);
            return new Response<String>(Response.ERROR, "Failed to start model: " + e.getMessage()).toString();
        }
    }

    @Override
    @PostMapping("/{id}/stop")
    @Operation(summary = "停止运行中的模型", description = "根据ID停止模型")
    public String stopModel(@PathVariable("id") int id) {
        try {
            return spiderClient.stopModel(id);
        } catch (Exception e) {
            log.error("Error stopping model, id: {}", id, e);
            return new Response<String>(Response.ERROR, "Failed to stop model: " + e.getMessage()).toString();
        }
    }

    @Override
    @GetMapping("/{id}/keywords")
    @Operation(summary = "查询关键词列表", description = "根据模型ID获取关键词列表")
    public String getKeywords(@PathVariable("id") int id) {
        try {
            return spiderClient.getKeywords(id);
        } catch (Exception e) {
            log.error("Error fetching keywords, id: {}", id, e);
            return new Response<String>(Response.ERROR, "Failed to fetch keywords: " + e.getMessage()).toString();
        }
    }

    @Override
    @PostMapping("/keyword")
    @Operation(summary = "新增关键词", description = "为模型添加关键词")
    public String addKeyword(@RequestBody KeywordCreateBody body) {
        try {
            return spiderClient.addKeyword(body);
        } catch (Exception e) {
            log.error("Error adding keyword", e);
            return new Response<String>(Response.ERROR, "Failed to add keyword: " + e.getMessage()).toString();
        }
    }

    @Override
    @DeleteMapping("/keyword/{id}")
    @Operation(summary = "删除关键词", description = "根据ID删除关键词")
    public String deleteKeyword(@PathVariable("id") int id) {
        try {
            return spiderClient.deleteKeyword(id);
        } catch (Exception e) {
            log.error("Error deleting keyword, id: {}", id, e);
            return new Response<String>(Response.ERROR, "Failed to delete keyword: " + e.getMessage()).toString();
        }
    }

    @Override
    @GetMapping("/fetchers")
    @Operation(summary = "查询采集器列表", description = "查询采集器列表")
    public String getFetcherList() {
        try {
            return spiderClient.getFetcherList();
        } catch (Exception e) {
            log.error("Error fetching fetcher list", e);
            return new Response<String>(Response.ERROR, "Failed to fetch fetcher list: " + e.getMessage()).toString();
        }
    }
}
