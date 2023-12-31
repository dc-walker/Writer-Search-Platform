package com.group10.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("wordCloud")
public class WordCloud {
    @TableField("authorId")
    private int authorId;
    @TableField("name")
    private String name;
    @TableField("value")
    private int value;
}
