/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huawei.streaming.cql.semanticanalyzer.analyzecontext.expressiondesc;

import java.util.List;

import com.huawei.streaming.api.streams.Schema;
import com.huawei.streaming.cql.exception.SemanticAnalyzerException;
import com.huawei.streaming.cql.semanticanalyzer.parser.context.ExpressionContext;

/**
 * 表达式实例工厂
 * 
 */
public class ExpressionDescFactory
{
    /**
     * 创建表达式实例
     * @param expressionContext 表达式语法解析结果
     * @param schemas 表达式中用到的schema
     * @return 表达式实例
     * @throws SemanticAnalyzerException 语义分析异常
     */
    public static ExpressionDescribe createExpressionDesc(ExpressionContext expressionContext, List<Schema> schemas)
        throws SemanticAnalyzerException
    {
        return expressionContext.createExpressionDesc(schemas);
    }
}
