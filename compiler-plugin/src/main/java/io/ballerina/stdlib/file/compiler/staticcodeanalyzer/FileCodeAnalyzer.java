/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.ballerina.stdlib.file.compiler.staticcodeanalyzer;

import io.ballerina.compiler.syntax.tree.SyntaxKind;
import io.ballerina.projects.plugins.CodeAnalysisContext;
import io.ballerina.projects.plugins.CodeAnalyzer;
import io.ballerina.scan.Reporter;

/**
 * File code analyser.
 */
public class FileCodeAnalyzer extends CodeAnalyzer {
    private final Reporter reporter;

    public FileCodeAnalyzer(Reporter reporter) {
        this.reporter = reporter;
    }

    @Override
    public void init(CodeAnalysisContext codeAnalysisContext) {
        codeAnalysisContext.addSyntaxNodeAnalysisTask(new FileServiceValidator(), SyntaxKind.SERVICE_DECLARATION);
        codeAnalysisContext.addSyntaxNodeAnalysisTask(new InsecureDirectoryAccessAnalyzer(reporter),
                SyntaxKind.FUNCTION_CALL);
        codeAnalysisContext.addSyntaxNodeAnalysisTask(new FilePathInjectionAnalyzer(reporter),
                SyntaxKind.FUNCTION_CALL);
    }
}
