package com.drf.wy.web.system.service;


import com.drf.wy.utils.Result;
import com.drf.wy.web.system.entity.LoginParam;

public interface LoginService {
    Result login(LoginParam loginParam);

    Result logout();
}
