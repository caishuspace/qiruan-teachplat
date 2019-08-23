package team.qiruan.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
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

}