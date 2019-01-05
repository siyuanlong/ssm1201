package com.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class MyTag extends SimpleTagSupport {

    private String value;
    private Boolean test;

    public Boolean getTest() {
        return test;
    }

    public void setTest(Boolean test) {
        this.test = test;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        StringWriter stringWriter = new StringWriter();
        getJspBody().invoke(stringWriter);
        out.print(stringWriter+" "+value+" "+test);
    }
}
