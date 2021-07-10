package com.dz.springboottravel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author dz
 * @since 2021-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Budget对象", description="")
public class Budget implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String place;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date date;

    private Integer cost;

    private String people;

    private Long pid;


}
