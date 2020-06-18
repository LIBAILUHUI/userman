package com.lh.userman;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.userman.entity.User;
import com.lh.userman.entity.UserVO;
import com.lh.userman.mapper.UserMapper;
import com.lh.userman.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.List;

@SpringBootTest
class UsermanApplicationTests {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }


    @Test
    public void test_page() {
        Page<User> page = new Page<>(1,100);
        UserVO vo = new UserVO();
        //vo.setName("o");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,5,2);
        vo.setStartDate(calendar.getTime());
        IPage<User> info = userMapper.selectPageVo(page, vo);
        List<User> ulist = info.getRecords();
        ulist.forEach(System.out::println);
    }

}
