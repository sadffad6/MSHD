package com.mshd.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @TableId(value = "id",type= IdType.AUTO)
    private int id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

}
