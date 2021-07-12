package com.dz.springboottravel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author dz
 * @since 2021-07-07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Budget对象", description="")
public class DetailBudget extends BaseRowModel {

    @ExcelProperty(value = "活动", index = 0)
    private String place;
    @ExcelProperty(value = "时间", index = 1)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date date;
    @ExcelProperty(value = "费用", index = 2)
    private Integer cost;
    @ExcelProperty(value = "参与人", index = 3)
    private String people;

}
