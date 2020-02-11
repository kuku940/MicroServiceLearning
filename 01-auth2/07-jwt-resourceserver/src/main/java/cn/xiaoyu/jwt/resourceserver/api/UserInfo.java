package cn.xiaoyu.jwt.resourceserver.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
    private String name;
    private String email;
}
