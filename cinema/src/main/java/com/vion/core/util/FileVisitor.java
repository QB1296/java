/**
 * 文件名：asdf.java  
 *  
 * 版本信息：  
 * 日期：2013-6-3  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.util;

import java.io.File;

/**
 * <b>功能描述</b> <br>
 * 文件观察器
 * @author YUJB
 * @date 2013-6-3 上午11:30:40
 */
public interface FileVisitor {

    /**
     * 观察文件
     * @param file
     */
    void visit(File file);

}
