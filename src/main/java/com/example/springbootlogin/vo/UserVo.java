package com.example.springbootlogin.vo;

import lombok.Getter;
import lombok.Setter;

// 사용자 인터페이스에서 사용하는 객체입니다. UI와 가장 밀접하게 연관되어 있습니다.
@Getter
@Setter
public class UserVo {
    private String id;  // 사용자 아이디
    private String password;  // 사용자 비밀번호
}
