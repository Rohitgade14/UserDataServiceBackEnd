package com.spcodage.service;

import com.spcodage.dtos.LoginReqDto;
import com.spcodage.dtos.LoginResDto;

public interface LoginService {
    LoginResDto loginReq(LoginReqDto loginReqDto);
}
