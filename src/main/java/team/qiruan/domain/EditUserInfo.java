package team.qiruan.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class EditUserInfo {
    @NotBlank
    String name;
    @Pattern(regexp = "[男女]")
    String sex;
    String birthday;
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$")
    String cardid;
    String avatar;
    // @SafeHtml
    String introduce;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "EditUserInfo [avatar=" + avatar + ", birthday=" + birthday + ", cardid=" + cardid + ", introduce="
                + introduce + ", name=" + name + ", sex=" + sex + "]";
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

}