package cn.xiaoyu.clientserver.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by roin.zhang on 2020/1/3
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String name;
    private String email;
}
