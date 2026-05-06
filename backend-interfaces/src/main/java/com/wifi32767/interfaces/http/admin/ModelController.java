package com.wifi32767.interfaces.http.admin;

import com.wifi32767.interfaces.dto.KeywordCreateBody;
import org.springframework.web.multipart.MultipartFile;

public interface ModelController {
    String getModelList(int pageNum, int pageSize);

    String getModel(int id);

    String deleteModel(int id);

    String saveModel(String mReptileModelId,
                     String mReptileModelName,
                     String mReptileModelIntroduce,
                     String mReptileModelWeb,
                     String cronExpression,
                     String keywords,
                     MultipartFile scriptFile,
                     MultipartFile startupFile
    );

    String startModel(int id);

    String stopModel(int id);

    String getKeywords(int id);

    String addKeyword(KeywordCreateBody body);

    String deleteKeyword(int id);
}
