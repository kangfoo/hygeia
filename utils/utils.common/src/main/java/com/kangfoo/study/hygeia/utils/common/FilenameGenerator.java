package com.kangfoo.study.hygeia.utils.common;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-5
 * Time: 下午11:56
 * To change this template use File | Settings | File Templates.
 */
public interface FileNameGenerator {

    String generateFileName(String input);

    /**
     * 默认返回当前系统时间 yyyyMMdd 格式。
     * @return
     */
    String generateDirNameByTime();
}
