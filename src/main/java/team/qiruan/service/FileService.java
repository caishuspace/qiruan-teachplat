package team.qiruan.service;

import java.util.List;

import team.qiruan.domain.File;

/**
 * FileService
 */
public interface FileService {

    /**
     * 添加文件
     * @param filename 文件名
     * @param type 文件类型（后缀名）
     * @return 是否添加成功
     */
    Boolean addFile(String filename,String type);
    
    /**
     * 获取所有未使用的图片
     * @return
     */
    List<File> getUnusedFile();

    /**
     * 一次性删除多个文件
     * @param files
     * @return
     */
    void deleteFiles(List<File> files);

    void updateFileRelationShip(String filename,List<String> itemNames);
}