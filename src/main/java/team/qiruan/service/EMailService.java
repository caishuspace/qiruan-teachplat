package team.qiruan.service;

/**
 * EMailService
 */
public interface EMailService {

    /**
     * 发送绑定验证电子邮件
     * @param username
     * @param toAddress
     * @param valiCode
     */
    void sendEmailValiCode(String username,String toAddress,String valiCode);
}