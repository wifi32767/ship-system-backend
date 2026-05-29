package com.wifi32767.interfaces.http.admin;

import com.wifi32767.common.Response;
import com.wifi32767.domain.system.model.ClassEntity;
import com.wifi32767.domain.system.model.TypeEntity;

import java.util.List;

public interface ClassController {

    /**
     * 获取所有一级分类列表。
     * 数据格式：JSON
     *
     * @return 一级分类列表 {@code List<ClassVO>}
     */
    Response<List<ClassEntity>> getClasses();

    /**
     * 搜索一级分类：根据关键词模糊匹配分类名称。
     * 数据格式：JSON
     *
     * @param keyword 搜索关键词
     * @return 匹配的分类列表 {@code List<ClassVO>}
     */
    Response<List<ClassEntity>> searchClasses(String keyword);

    /**
     * 创建一级分类。
     * 数据格式：JSON
     *
     * @param classVO 一级分类信息 {@link TypeEntity}
     * @return 创建结果消息 {@code String}
     */
    Response<String> createClass(TypeEntity classVO);

    /**
     * 更新一级分类信息。
     * 数据格式：JSON
     *
     * @param classVO 包含更新内容的一级分类信息 {@link TypeEntity}
     * @return 更新结果消息 {@code String}
     */
    Response<String> updateClass(TypeEntity classVO);

    /**
     * 删除一级分类：根据ID删除指定的一级分类。
     * 数据格式：JSON
     *
     * @param classId 一级分类ID
     * @return 删除结果消息 {@code String}
     */
    Response<String> deleteClass(Integer classId);

    /**
     * 创建二级分类（样式）：在指定父级分类下创建。
     * 数据格式：JSON
     *
     * @param styleVO  二级分类信息 {@link TypeEntity}
     * @param parentId 父级分类（一级分类）ID
     * @return 创建结果消息 {@code String}
     */
    Response<String> createStyle(TypeEntity styleVO, Integer parentId);

    /**
     * 更新二级分类（样式）信息。
     * 数据格式：JSON
     *
     * @param styleVO 包含更新内容的二级分类信息 {@link TypeEntity}
     * @return 更新结果消息 {@code String}
     */
    Response<String> updateStyle(TypeEntity styleVO);

    /**
     * 删除二级分类（样式）：根据ID删除指定的二级分类。
     * 数据格式：JSON
     *
     * @param styleId 二级分类ID
     * @return 删除结果消息 {@code String}
     */
    Response<String> deleteStyle(Integer styleId);

    /**
     * 创建三级分类（类型）：在指定父级分类下创建。
     * 数据格式：JSON
     *
     * @param typeEntity 三级分类信息 {@link TypeEntity}
     * @param parentId   父级分类（二级分类）ID
     * @return 创建结果消息 {@code String}
     */
    Response<String> createType(TypeEntity typeEntity, Integer parentId);

    /**
     * 更新三级分类（类型）信息。
     * 数据格式：JSON
     *
     * @param typeEntity 包含更新内容的三级分类信息 {@link TypeEntity}
     * @return 更新结果消息 {@code String}
     */
    Response<String> updateType(TypeEntity typeEntity);

    /**
     * 删除三级分类（类型）：根据ID删除指定的三级分类。
     * 数据格式：JSON
     *
     * @param typeId 三级分类ID
     * @return 删除结果消息 {@code String}
     */
    Response<String> deleteType(Integer typeId);
}
