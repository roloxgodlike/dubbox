/**
 * DongJun.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.alibaba.dubbo.examples.annotation;

import java.util.List;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;

/**
 * 消费者点对点指定生产者方式的LB
 *
 * 东骏科技扩展, 专用于netty网络服务层
 *
 * @author jinyiding
 * @version $Id: DjNettyLoadBalance.java, v 0.1 2016年10月12日 下午12:06:00 jinyiding Exp $
 */
public class DjNettyLoadBalance extends AbstractLoadBalance {

    /** LB插件名称对应properties中的key */
    public static final String NAME = "djnetty";

    @Override
    protected <T> Invoker<T> doSelect(final List<Invoker<T>> invokers, final URL url,
                                      final Invocation invocation) {
        return invokers.get(invokers.size() - 1);
    }

}
