package com.wifi32767.interfaces.client;

import com.wifi32767.interfaces.dto.KeywordCreateBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Service
@FeignClient(name = "spider-service")
public interface SpiderClient {
    @RequestMapping(value = "/api/model/list", method = RequestMethod.GET)
    String getModelList();

    @RequestMapping(value = "/api/model/list", method = RequestMethod.GET)
    String getModelList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "/api/model/{modelId}", method = RequestMethod.GET)
    String getModelDetail(@PathVariable("modelId") int modelId);

    @RequestMapping(value = "/api/model/save", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String saveModel(
            @RequestParam("mReptileModelId") String mReptileModelId,
            @RequestParam("mReptileModelName") String mReptileModelName,
            @RequestParam("mReptileModelIntroduce") String mReptileModelIntroduce,
            @RequestParam("mReptileModelWeb") String mReptileModelWeb,
            @RequestParam("cronExpression") String cronExpression,
            @RequestParam("keywords") String keywords,
            @RequestPart("scriptFile") MultipartFile scriptFile,
            @RequestPart("startupFile") MultipartFile startupFile
    );

    @RequestMapping(value = "/api/model/{modelId}", method = RequestMethod.DELETE)
    String deleteModel(@PathVariable("modelId") int modelId);

    @RequestMapping(value = "/api/model/{modelId}/start", method = RequestMethod.POST)
    String startModel(@PathVariable("modelId") int modelId);

    @RequestMapping(value = "/api/model/{modelId}/stop", method = RequestMethod.POST)
    String stopModel(@PathVariable("modelId") int modelId);

    @RequestMapping(value = "/api/model/{modelId}/keyword", method = RequestMethod.GET)
    String getKeywords(@PathVariable("modelId") int modelId);

    @RequestMapping(value = "/api/model/keyword", method = RequestMethod.POST)
    String addKeyword(@RequestBody KeywordCreateBody body);

    @RequestMapping(value = "/api/model/keyword/{id}", method = RequestMethod.DELETE)
    String deleteKeyword(@PathVariable("id") int id);

}
