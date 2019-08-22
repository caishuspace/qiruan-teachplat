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
    
    List<File> getUnusedFile();
}