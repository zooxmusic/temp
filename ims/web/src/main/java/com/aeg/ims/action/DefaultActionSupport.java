package com.aeg.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public abstract class DefaultActionSupport extends ActionSupport implements SessionAware{

    private Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    private boolean sessionExpired() {
        return null == session;
    }

    private void redirectToLogin() {

    }

    @Override
    public String execute() throws Exception {
        if(sessionExpired()) {
            redirectToLogin();
        }

        try {
            perform();
        }catch(Exception e) {
            //handle this here
            return getFailedResult();
        }

        return getSuccessResult();
    }

    protected abstract String perform() throws Exception;
    protected abstract String getSuccessResult();
    protected abstract String getFailedResult();
}
