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
        //判断uri里面是否存在"?",如果存在则在uri后面加上"&"
        if (uri.indexOf("?")!=-1){

            uri = uri + "&";

        }else {

            uri = uri + "?";

        }

            stringBuffer.append("<a style='text-decoration: none'; href='"+uri+"&size="+pageInfo.getPageSize()+"'>首页</a>&nbsp;&nbsp;");
            stringBuffer.append("<a style='text-decoration: none;' href='"+uri+"index="+(pageInfo.getPageNum()-1<=1?1:pageInfo.getPageNum()-1)+"&size="+pageInfo.getPageSize()+"'>上一页</a>&nbsp;&nbsp;");

            for (int i=1;i<=pageInfo.getPages();i++){
                stringBuffer.append("<a style='text-decoration: none;' href='"+uri+"index="+i+"&size="+pageInfo.getPageSize()+"'>"+i+"</a>&nbsp;&nbsp;");
            }

            stringBuffer.append("<a style='text-decoration: none;' href='"+uri+"index="+(pageInfo.getPageNum()+1>=pageInfo.getPages()?pageInfo.getPages():pageInfo.getPageNum()+1)+"&size="+pageInfo.getPageSize()+"'>下一页</a>&nbsp;&nbsp;");
            stringBuffer.append("<a style='text-decoration: none'; href='"+uri+"index="+(pageInfo.getPages())+"&size="+pageInfo.getPageSize()+"'>尾页</a>&nbsp;&nbsp;");
            stringBuffer.append("共"+(pageInfo.getTotal())+"条&nbsp;&nbsp;每页显示"+(pageInfo.getPageNum())+"/"+(pageInfo.getPages()));

            stringBuffer.append("<select id='psize' name='size'>");
            stringBuffer.append("<option value='5' "+(pageInfo.getPageSize()==5?"selected":"")+" >5</option>");
            stringBuffer.append("<option value='10' "+(pageInfo.getPageSize()==10?"selected":"")+" >10</option>");
            stringBuffer.append("<option value='15' "+(pageInfo.getPageSize()==15?"selected":"")+" >15</option>");
            stringBuffer.append("</select>");

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
