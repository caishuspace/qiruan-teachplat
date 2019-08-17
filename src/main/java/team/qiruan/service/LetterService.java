package team.qiruan.service;

import java.util.List;

import team.qiruan.domain.LetterDetail;
import team.qiruan.domain.LetterInBox;

/**
 * LetterService
 */
public interface LetterService {

    /**
     * 发送站内信
     * @param send 发送者用户名
     * @param to 接受者用户名
     * @param content 站内信内容
     * @return
     */
    Boolean sendLetter(String send,String to,String title,String content);

    /**
     * 获取某个用户的所有站内信
     * @param username
     * @return
     */
    List<LetterInBox> getAllLetters(String username);

    /**
     * 获取某个用户未读站内信
     * @param username
     * @return
     */
    List<LetterInBox> getUnreadLetters(String username);

    /**
     * 获取某个用户所有已经发送的邮件
     * @param username
     * @return
     */
    List<LetterInBox> getAllSentLetters(String username);

    /**
     * 通过id获取当前用户
     * @param username
     * @param id
     * @return
     */
    LetterDetail getLetterById(String username,Integer id);

    /**
     * 将自己收到的某封信件标记为已读
     * @param username
     * @param id
     * @return
     */
    Boolean read(String username,Integer id);
    
    /**
     * 删除信件
     * @param username
     * @param id
     * @return
     */
    Boolean del(String username,Integer id);
}