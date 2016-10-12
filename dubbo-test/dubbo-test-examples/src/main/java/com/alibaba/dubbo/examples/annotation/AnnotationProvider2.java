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
public class AnnotationProvider2 {

    public static void main(final String[] args) throws Exception {
        final String config = AnnotationProvider2.class.getPackage().getName().replace('.', '/')
                              + "/annotation-provider2.xml";
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();
        System.in.read();
    }

}
