/**
 * 文件名： Hello.groovy
 *  
 * 版本信息：  
 * 日期：2015年8月3日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package groovy

import java.awt.event.ItemEvent;

import groovy.json.StringEscapeUtils
import groovy.swing.impl.DefaultAction

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年8月3日 下午5:05:10
 */
class Hello {

	def name
	
	void say(def str){
		println  str
		println  str.class
	}
	
	void loop(str){
		for (i in 1..5) {
			 println "This is ${i}:${str}"
		}
	}
	
	def showlist(str){
		def list=['name','john','age',14,'sex','boy']
		list.each {
			i -> println i;
		}
		println  "------------------------------------";
		list.each {
			item -> if(item.equals(str))println  item;
		}
	}
	
	def showmap(){
		def map=['name':'john','age':14,'sex':'boy']
		map=map+['weight':25]       //添加john的体重
		map.put('length',1.27)      //添加john的身高
		map.father='Keller'         //添加john的父亲
		map.each({key,value->    //key,value两个参数用于接受每个元素的键/值
			println "$key:$value"})
	}
	/**
	 * @param args
	 */
	public static void main(def args){
	   // TODO Auto-generated method stub
		println 12
	}
	
}
