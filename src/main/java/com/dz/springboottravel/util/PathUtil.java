package com.dz.springboottravel.util;

import com.dz.springboottravel.pojo.Program;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

public class PathUtil {
      //保存地址的方法
      public static void savePath(Program program,MultipartFile file) throws IOException {
          //获得SpringBoot当前项目的路径：System.getProperty("user.dir")
          String path= "D:/Google/springboot-travel/src/main/resources/static/image/";
          int len = path.length()-6;
          //按照月份进行分类：
          Calendar instance = Calendar.getInstance();
          String month = (instance.get(Calendar.MONTH) + 1)+"月";
          path = path+month;
          File realPath = new File(path);
          if (!realPath.exists()){
              realPath.mkdirs();
          }
          //上传文件地址
          System.out.println("上传文件保存地址："+realPath);
          //解决文件名字问题：我们使用uuid;
          String filename = "pg-"+ UUID.randomUUID().toString().replaceAll("-", "")+".jpg";
          File newfile = new File(realPath, filename);
          //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
          file.transferTo(newfile);
          String substring = path.substring(len);
          program.setPictureUrl(substring+"/"+filename);
      }
}
