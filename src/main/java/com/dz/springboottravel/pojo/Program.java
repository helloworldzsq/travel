package com.dz.springboottravel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;

import java.io.File;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *
 * </p>
 *
 * @author dz
 * @since 2021-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Program对象", description="")
public class Program implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private String place;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date endTime;

    private String budget;

    private String userNum;

    private String pictureUrl;

    private Integer created;

    private String joined;
    //想加入的人
    private String wanted;

    private String refused;

    private String info;

    private Long uid;

}
