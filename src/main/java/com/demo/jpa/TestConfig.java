package com.demo.jpa;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Q1sj
 * @date 2023.4.25 15:32
 */
@Data
@Entity(name = "test_config")
@TableName("test_config")
public class TestConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String value;
    @TableField(fill = FieldFill.INSERT)
    private Long userId;
}
