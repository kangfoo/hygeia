/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kangfoo.study.hygeia.utils.common.impl;

import com.kangfoo.study.hygeia.utils.common.FileNameGenerator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *  class [FileNameGeneratorImpl] is public, should be declared in a file named [FileNameGeneratorImpl].java
 */
public class FileNameGeneratorImpl implements FileNameGenerator {

    DateTimeFormatter yyyyMMddHHmmssformat = DateTimeFormat.forPattern("yyyyMMddHHmmss");
    DateTimeFormatter yyyyMMddformat = DateTimeFormat.forPattern("yyyyMMdd");

    @Override
    public String generateFileName(String input) {
        // compute the filename
        return input + "-" + DateTime.now().toString(yyyyMMddHHmmssformat)  +".log";
    }

    @Override
    public String generateDirNameByTime() {
        return DateTime.now().toString(yyyyMMddformat);  //To change body of implemented methods use File | Settings | File Templates.
    }

}