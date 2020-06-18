package com.lh.userman.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.userman.entity.Dept;
import com.lh.userman.entity.User;
import com.lh.userman.entity.UserVO;
import com.lh.userman.service.IDeptService;
import com.lh.userman.service.IUserService;
import com.lh.userman.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lh
 * @since 2020-06-16
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;


    @Autowired
    private IDeptService iDeptService;


    //采用默认提供的方法
    /*@RequestMapping("listpage")
    public String list(Model model, User user, @RequestParam(defaultValue = "1") long current, @RequestParam(defaultValue = "3") long size){
        Page<User> page = new Page<>(current,size);
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if(null!=user){
            if(null!=user.getName() && !user.getName().trim().isEmpty()){
                wrapper.like("name",user.getName());
            }
            if(null!=user.getBirth()){
                wrapper.eq("birth",user.getBirth());
            }
            if(null!=user.getDid()){
                wrapper.eq("did",user.getDid());
            }

        }
        Page<User> info = iUserService.page(page, wrapper);

        List<User> records = info.getRecords();
        for(User u:records){
            System.out.println(u.getDid());
            if(null!=u.getDid()){
                Dept dept = iDeptService.getById(u.getDid());
                System.out.println(dept.getDname());
                u.setDname(dept.getDname());
            }
        }

        model.addAttribute("info",info);
        model.addAttribute("users",user);
        model.addAttribute("pages",info.getPages());
        model.addAttribute("dlist",iDeptService.list());
        return "user_list";
    }*/

    //自定义方法查询
    @RequestMapping("list")
    public String list(Model model, UserVO vo, @RequestParam(defaultValue = "1") long current, @RequestParam(defaultValue = "3") long size){
        Page<User> page = new Page<>(current,size);

        IPage<User> info = iUserService.selectPageVo(page, vo);


        model.addAttribute("info",info);
        model.addAttribute("users",vo);
        model.addAttribute("pages",info.getPages());
        model.addAttribute("dlist",iDeptService.list());
        return "user_list";
    }

    //添加
    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("dlist",iDeptService.list());
        return "user_add";
    }

    @ResponseBody
    @PostMapping("add")
    public boolean add(User user,MultipartFile myFile){
        try {
            String fileName = UploadUtil.upload(myFile);
            user.setHeadImg("http://localhost:8080/pic/"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return iUserService.save(user);
    }

    //修改
    @GetMapping("update")
    public String update(Long id,Model model){
        User user = iUserService.getById(id);
        if(null!=user.getDid()){
            user.setDname(iDeptService.getById(user.getDid()).getDname());
        }
        model.addAttribute("user",iUserService.getById(id));
        model.addAttribute("dlist",iDeptService.list());
        return "user_update";
    }


    @ResponseBody
    @PostMapping("update")
    public boolean update(User user){
        return iUserService.updateById(user);
    }

    //删除
    @ResponseBody
    @PostMapping("delete")
    public boolean delete(Long id){
        return iUserService.removeById(id);
    }

    //详情
    @GetMapping("detail")
    public String detail(Long id,Model model){
        User user = iUserService.getById(id);
        if(null!=user.getDid()){
            user.setDname(iDeptService.getById(user.getDid()).getDname());
        }
        model.addAttribute("user",user);
        return "user_detail";
    }



    //批量删除
    @ResponseBody
    @PostMapping("delBatch")
    public boolean delBatch(Long[] ids){
        return iUserService.removeByIds(Arrays.asList(ids));
    }

    @RequestMapping("toUpload")
    public String toUpload(){
        return "user_upload";
    }


    @RequestMapping("upload")
    public String upload(MultipartFile myFile){

        try {
            UploadUtil.upload(myFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //判断对象和文件不为空
        /*if(null!=myFile && !myFile.isEmpty()){
            String originalFilename = myFile.getOriginalFilename();
            String fileName = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
            File file = new File("d:/picpic",fileName);
            if(!file.getParentFile().exists()){
                file.mkdirs();
            }
            try {
                myFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
*/
        return "success";
    }

    @RequestMapping("toUploads")
    public String toUploads(){
        return "user_uploads";
    }

    @RequestMapping("uploads")
    public String uploads(List<MultipartFile> myFiles){

        for(MultipartFile myFile:myFiles){
            //判断对象和文件不为空
            if(null!=myFile && !myFile.isEmpty()){
                String originalFilename = myFile.getOriginalFilename();
                String fileName = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
                File file = new File("d:/picpic",fileName);
                if(!file.getParentFile().exists()){
                    file.mkdirs();
                }
                try {
                    myFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return "success";
    }

    @RequestMapping("toUploadAjax")
    public String toUploadAjax(){
        return "user_upload_ajax";
    }

    @ResponseBody
    @RequestMapping("uploadajax")
    public boolean uploadajax(MultipartFile file){
        System.out.println("coming");
        if(null!=file && !file.isEmpty()) {
            System.out.println("coming1");
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
            System.out.println(fileName);
            File file1 = new File("d:/picpic", fileName);
            if (!file1.getParentFile().exists()) {
                file1.mkdirs();
            }
            try {
                file.transferTo(file1);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        System.out.println("coming2");
        return false;
    }
}

