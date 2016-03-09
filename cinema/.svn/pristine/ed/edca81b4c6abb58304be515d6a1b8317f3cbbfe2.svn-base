/**
 * 文件名：BinaryOperation.java  
 *  
 * 版本信息：  
 * 日期：2015年2月28日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;


import java.util.Date;

import bsh.ParserConstants;
import bsh.Primitive;


/**
 * <b>功能描述</b> <br>
 * 操作
 * @author YUJB
 * @date 2015年3月6日 上午10:00:57
 */
public class BinaryOperation implements ParserConstants {
	
    static Object eval(Object lhs, Object rhs, int kind) {
        return Primitive.unwrap(_eval(lhs, rhs, kind));
    }

    static Object _eval(Object lhs, Object rhs, int kind) {
        try {
            boolean isLhsWrapper = isWrapper(lhs);
            boolean isRhsWrapper = isWrapper(rhs);

            if (isLhsWrapper && isRhsWrapper) {
                return Primitive.binaryOperation(lhs, rhs, kind);
            }

            switch (kind) {
            case EQ:

                if (lhs instanceof String) {
                	if (rhs instanceof String) {
                		return lhs.equals(rhs);
					}else {
						return lhs.equals(rhs.toString());
					}
                } else {
                    return (lhs == rhs);
                }

            case NE:

                if (lhs instanceof String || lhs instanceof Date) {
                    return !lhs.equals(rhs);
                } else {
                    return lhs != rhs;
                }

            case PLUS:

                if (lhs instanceof String || rhs instanceof String) {
                    return lhs.toString() + rhs.toString();
                }

            default:
                return null;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private static boolean isWrapper(Object obj) {
        return (obj instanceof Boolean || obj instanceof Character || obj instanceof Number);
    }
}

