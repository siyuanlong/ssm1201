package com.util;

import com.github.pagehelper.PageInfo;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class PageTag extends SimpleTagSupport {
    private String uri;
    private PageInfo pageInfo;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<a style='text-decoration: none'; href='"+uri+"'>首页</a>&nbsp;&nbsp;");
        stringBuffer.append("<a style='text-decoration: none;' href='"+uri+"&index="+(pageInfo.getPageNum()-1<=1?1:pageInfo.getPageNum()-1)+"'>上一页</a>&nbsp;&nbsp;");

        for (int i=1;i<=pageInfo.getPages();i++){
            stringBuffer.append("<a style='text-decoration: none;' href='"+uri+"&index="+i+"'>"+i+"</a>&nbsp;&nbsp;");
        }

        stringBuffer.append("<a style='text-decoration: none;' href='"+uri+"&index="+(pageInfo.getPageNum()+1>=pageInfo.getPages()?pageInfo.getPages():pageInfo.getPageNum()+1)+"'>下一页</a>&nbsp;&nbsp;");
        stringBuffer.append("<a style='text-decoration: none'; href='"+uri+"&index="+(pageInfo.getPages())+"'>尾页</a>&nbsp;&nbsp;");
        stringBuffer.append("共"+(pageInfo.getTotal())+"条&nbsp;&nbsp;每页显示"+(pageInfo.getPageNum())+"/"+(pageInfo.getPages()));
        out.print(stringBuffer.toString());
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

}
