package com.aeg.ims.action;

import org.springframework.stereotype.Controller;

@Controller
public class FindUserAction extends DefaultActionSupport {

    private static String SUCCESS = "login-success";
    private static String FAILURE = "login-failure";
    @Override
    protected String perform() throws Exception {
        return null;
    }

    @Override
    protected String getSuccessResult() {
        return SUCCESS;
    }

    @Override
    protected String getFailedResult() {
        return FAILURE;
    }


}
