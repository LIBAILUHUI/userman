package com.lh.userman.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.userman.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lh.userman.entity.UserVO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lh
 * @since 2020-06-16
 */
public interface UserMapper extends BaseMapper<User> {

    //自定义分页查询

    /**
     *
     * @param page  分页对象
     * @param vo  查询条件
     * @return
     */
    IPage<User> selectPageVo(Page<?> page, UserVO vo);
}
