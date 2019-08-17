package team.qiruan.service;

import java.util.List;

import team.qiruan.domain.Message;

/**
 * MessageService
 */
public interface MessageService {

    /**
     * 显示所有评论
     * @param belong 评论从属于
     * @return
     */
    List<Message> getAllMessage(String belong);

    /**
     * 添加评论
     * @param belong 从属页面
     * @param uid 发出评论的用户名
     * @param content 评论内容
     * @return
     */
    Boolean addMessage(String belong,String username,String content);
}