package cn.xiaoyu.zuul.studentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private String name;
    private String address;
    private String cls;
}
