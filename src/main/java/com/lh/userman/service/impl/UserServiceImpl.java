package com.lh.userman.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.userman.entity.User;
import com.lh.userman.entity.UserVO;
import com.lh.userman.mapper.UserMapper;
import com.lh.userman.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lh
 * @since 2020-06-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public IPage<User> selectPageVo(Page<?> page, UserVO vo) {
        return baseMapper.selectPageVo(page,vo);
    }
}
