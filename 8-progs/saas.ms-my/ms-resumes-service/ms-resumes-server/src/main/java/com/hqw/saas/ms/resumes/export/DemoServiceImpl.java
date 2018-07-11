package com.hqw.saas.ms.resumes.export;

import com.hqw.saas.ms.resumes.service.DemoService;

/**
 * Copyright (C), 2018, saas.ms-my
 * Description:
 * Auther: HeQingwen
 * Date: 2018-07-11 16:53
 */
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}