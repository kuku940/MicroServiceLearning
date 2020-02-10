package cn.xiaoyu.clientserver.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roin.zhang on 2020/1/3
 */
@Controller
@RequestMapping("api")
public class DevOpsController {

    @RequestMapping("/userlist")
    public ResponseEntity<List<UserInfo>> getUserInfo() {
        List<UserInfo> users = new ArrayList<>();
        users.add(new UserInfo("Jack", "Jack@spring.com"));
        users.add(new UserInfo("Tony", "Tony@spring.com"));

        return ResponseEntity.ok(users);
    }
}
