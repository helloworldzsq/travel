package com.dz.springboottravel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseBudget extends BaseRowModel {
    @ExcelProperty(value = "姓名", index = 0)
    public String name;
    @ExcelProperty(value = "费用", index = 1)
    public Integer cost;
}
