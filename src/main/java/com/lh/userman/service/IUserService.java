package com.lh.userman.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.userman.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.userman.entity.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lh
 * @since 2020-06-16
 */
public interface IUserService extends IService<User> {

    IPage<User> selectPageVo(Page<?> page, UserVO vo);
}
