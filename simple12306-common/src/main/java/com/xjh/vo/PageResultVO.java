package com.xjh.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/16 10:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class PageResultVO<T> {
    private Long total;
    private List<T> rows;
}
