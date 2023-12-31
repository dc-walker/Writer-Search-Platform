package com.group10.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("timeLine")
public class TimeLine {
    @TableField("authorId")
    private int authorId;
    @TableField("children")
    private String children;
}
