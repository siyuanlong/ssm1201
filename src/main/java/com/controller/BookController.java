package com.controller;

import com.bean.Information;
import com.github.pagehelper.PageInfo;
import com.service.BookService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    //上传information查询
    @RequestMapping("/book/getinfo")
    public String findInfo(String title, @RequestParam(defaultValue = "5") int size,
                           @RequestParam(value = "index",defaultValue = "1") int pageindex, ModelMap map){
        System.out.println("size"+size);
        PageInfo<Information> infopi = bookService.findInfo(title, pageindex, size);
        map.put("infopi",infopi);
        return "/book/list-ziliao";
    }

    //学习中心下载information查询
    @RequestMapping("/study/getinfo2")
    public String findInfo2(String title2, @RequestParam(defaultValue = "5") int size,
                           @RequestParam(value = "index",defaultValue = "1") int pageindex, ModelMap map){
        System.out.println("size"+size);
        PageInfo<Information> infopi2 = bookService.findInfo(title2, pageindex, size);
        map.put("infopi2",infopi2);
        return "/study/StudentMaterial";
    }

    @RequestMapping("/book/getinfotype")
    public String getInfoType(ModelMap map){
        List itlist = bookService.findInfoType();
        map.put("itlist",itlist);
        return "/book/add";
    }

    @RequestMapping("/book/upload")
    public String upload(Information information,MultipartFile myfile,
                         HttpServletRequest request){
        //上传文件
        String realPath = request.getRealPath("/uploadfile");

        //获得文件名称
        String filename = myfile.getOriginalFilename();

        //文件上传
        try {
            myfile.transferTo(new File(realPath+"/"+filename));
            //给数据库添加type
            information.setFiletype(myfile.getContentType());
            information.setUrl("/uploadfile/"+filename);
            bookService.insert(information);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/book/getinfo";
    }

    @RequestMapping("/study/download")
    public ResponseEntity<byte[]> download(String file, HttpServletRequest request){
        System.out.println("前端传来数据:"+file);
        ResponseEntity<byte[]> entity = null;
        try {
        //获取资源的路径
        String servletPath = request.getRealPath(file);
        //创建http头信息的对象
        HttpHeaders headers = new HttpHeaders();
        //设置以流的方式做出响应
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String file1 = file.substring(file.lastIndexOf("/")+1);
        //attachment表示附件形式
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(file1,"UTF-8"));
        //下载
        File f = new File(servletPath);
        // FileUtils.readFileToByteArray(f)读取文件到一个byte数组
            //HttpStatus.CREATED服务器创建了文档
            //ResponseEntity标识整个http相应:状态码、头部信息以及相应体内容。
            // 因此我们可以使用其对http响应实现完整配置。
            entity =
                    new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(f),headers, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
