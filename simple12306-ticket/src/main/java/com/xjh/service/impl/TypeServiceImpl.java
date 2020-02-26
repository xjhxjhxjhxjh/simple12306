package com.xjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.entity.Type;
import com.xjh.mapper.TypeMapper;
import com.xjh.service.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 15:50
 */
@Service
@Transactional
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
}
