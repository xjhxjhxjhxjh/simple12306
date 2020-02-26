package com.xjh.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/16 10:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {
    private StatusCode code;
    private T data;
}
