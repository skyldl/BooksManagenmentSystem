package com.example.bms.web;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/update")
public class UpdateController {
    public static void main(String[] args) {
        try {
            System.out.println(new ClassPathResource("").getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 我的上传方法
     * @param req
     * @return
     */
    private String myUpdate(HttpServletRequest req) {
        String res = null;  // 返回网络路径
        try {
            String staticDir = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files\\";
//            String staticDir = ResourceUtils.getURL("classpath:").getPath() + "static";  // 得到classes/static目录
            // 如果结果目录不存在，则创建目录
            File resDirFile = new File(staticDir);
            if(!resDirFile.exists()) {
                boolean flag = resDirFile.mkdirs();
                if(!flag) throw new RuntimeException("创建结果目录失败");
            }
            //先判断上传的数据是否多段数据（只有是多段的数据，才是文件上传的）
            if (ServletFileUpload.isMultipartContent(req)) {
                // 创建 FileItemFactory 工厂实现类
                FileItemFactory fileItemFactory = new DiskFileItemFactory();
                // 创建用于解析上传数据的工具类 ServletFileUpload 类
                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
                // 解析上传的数据，得到每一个表单项 FileItem
                List<FileItem> list = servletFileUpload.parseRequest(new ServletRequestContext(req));
                // 循环判断，每一个表单项，是普通类型，还是上传的文件
                for (FileItem fileItem : list) {
                    if ( !fileItem.isFormField()) { // 是上传的文件
                        // 上传的文件
                        System.out.println("表单项的 name 属性值：" + fileItem.getFieldName());
                        System.out.println("上传的文件名：" + fileItem.getName());
                        // 加个时间戳防止重名
                        String newFileName = System.currentTimeMillis() + fileItem.getName();
                        // 写文件
                        File file = new File(staticDir + "/" + newFileName);
                        fileItem.write(file);
                        // 返回值
                        res = "/files/" + newFileName;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 上传图片
     * @param req
     * @return
     */
    @RequestMapping("/updateImg")
    @ResponseBody
    public Map<String,Object> updateImg(HttpServletRequest req){
        String resPath = myUpdate(req);

        Map<String,Object> res = new HashMap<>();
        res.put("code",0);
        res.put("data", resPath);

        return res;
    }

}
