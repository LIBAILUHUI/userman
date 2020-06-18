package com.lh.userman.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lh
 * @since 2020-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Dept implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "did", type = IdType.AUTO)
    private Integer did;

    private String dname;


}
