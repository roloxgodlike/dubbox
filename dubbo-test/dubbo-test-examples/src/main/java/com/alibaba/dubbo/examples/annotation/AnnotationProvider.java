/*
 * Copyright 1999-2012 Alibaba Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.examples.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * MergeProvider
 *
 * @author william.liangf
 */
public class AnnotationProvider {

    public static void main(final String[] args) throws Exception {
        final String packagePath = AnnotationProvider.class.getPackage().getName()
            .replace('.', '/');
        // 生产者1
        final String config = packagePath + "/annotation-provider.xml";
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();
        // 生产者2
        new Thread() {

            @Override
            public void run() {
                final String config = packagePath + "/annotation-provider2.xml";
                final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    config);
                context.start();
            }

        }.start();
        System.in.read();
    }
}
