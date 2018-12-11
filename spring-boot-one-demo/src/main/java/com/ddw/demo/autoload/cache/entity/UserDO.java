package com.ddw.demo.autoload.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1932703849895844645L;

    private Long id;

    private String name;

    private String password;

    private Integer status;

}
