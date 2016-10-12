/**
 * DongJun.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.alibaba.dubbo.rpc.cluster.loadbalance;

import java.util.List;
import java.util.Random;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;

/**
 * 消费者点对点指定生产者方式的LB
 *
 * 东骏科技扩展, 专用于netty网络服务层
 *
 * @author jinyiding
 * @version $Id: DjNettyLoadBalance.java, v 0.1 2016年10月12日 下午12:06:00 jinyiding Exp $
 */
public class DjNettyLoadBalance extends AbstractLoadBalance {

    public static final String NAME   = "djnetty";

    private final Random       random = new Random();

    @Override
    protected <T> Invoker<T> doSelect(final List<Invoker<T>> invokers, final URL url,
                                      final Invocation invocation) {
        final int length = invokers.size(); // 总个数
        int totalWeight = 0; // 总权重
        boolean sameWeight = true; // 权重是否都一样
        for (int i = 0; i < length; i++) {
            final int weight = this.getWeight(invokers.get(i), invocation);
            totalWeight += weight; // 累计总权重
            if (sameWeight && (i > 0)
                && (weight != this.getWeight(invokers.get(i - 1), invocation))) {
                sameWeight = false; // 计算所有权重是否一样
            }
        }
        if ((totalWeight > 0) && !sameWeight) {
            // 如果权重不相同且权重大于0则按总权重数随机
            int offset = this.random.nextInt(totalWeight);
            // 并确定随机值落在哪个片断上
            for (int i = 0; i < length; i++) {
                offset -= this.getWeight(invokers.get(i), invocation);
                if (offset < 0) {
                    return invokers.get(i);
                }
            }
        }
        // 如果权重相同或权重为0则均等随机
        return invokers.get(this.random.nextInt(length));
    }

}
