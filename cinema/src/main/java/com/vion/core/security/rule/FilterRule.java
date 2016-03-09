package com.vion.core.security.rule;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;

import com.vion.core.security.FragmentSQL;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月20日 下午4:50:07
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
	 @Type(value=SingleFilterRule.class, name="single"), 
	 @Type(value=SQLSimpleFilterRule.class, name="sql")}
)
public interface FilterRule extends Serializable{

	boolean isSupport(FragmentSQL fragmentSQL);
}
