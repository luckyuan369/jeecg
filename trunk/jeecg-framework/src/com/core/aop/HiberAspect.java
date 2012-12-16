package com.core.aop;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import sun.def.ConstantsDefs;
import sy.pageModel.SessionInfo;
import sy.util.ResourceUtil;
/**
 * Hiberate拦截器：实现创建人，创建时间，创建人名称自动注入;
 *                修改人,修改时间,修改人名自动注入;
 */
@Component
public class HiberAspect extends EmptyInterceptor {
	private static final Logger logger = Logger.getLogger(HiberAspect.class);
	private static final long serialVersionUID = 1L;


@Override
public boolean onSave(Object entity, Serializable id, Object[] state,
		String[] propertyNames, Type[] types) {
	
	logger.info("--------------HiberAspect-----------------------onSave-------------------");
	SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
	//添加数据
     for (int index=0;index<propertyNames.length;index++)
     {
         /*找到名为"创建时间"的属性*/
         if ("createDt".equals(propertyNames[index]))
         {
             /*使用拦截器将对象的"创建时间"属性赋上值*/
        	 if(state[index]==null){
        		 state[index] = new Date();
        	 }
             continue;
         }
         /*找到名为"删除标示"的属性*/
         //update-begin author:zhangdaihao date:20121115 for:新数据插入的时候，逻辑删除字段自动赋值为0:未删除
         if ("delflag".equals(propertyNames[index]))
         {
             /*使用拦截器将对象的"删除标示"属性赋上值*/
        	 if(state[index]==null){
        		state[index] = ConstantsDefs.DELETE_FLG_0;
        	 }
             continue;
         }
         //update-end author:zhangdaihao date:20121115 for:新数据插入的时候，逻辑删除字段自动赋值为0:未删除
         /*找到名为"创建人"的属性*/
         else if ("crtuser".equals(propertyNames[index]))
         {
             /*使用拦截器将对象的"创建人"属性赋上值*/
        	 if(state[index]==null){
        		  state[index] = sessionInfo.getUserId();
        	 }
             continue;
         }
         /*找到名为"创建人名称"的属性*/
         else if ("crtuserName".equals(propertyNames[index]))
         {
             /*使用拦截器将对象的"创建人名称"属性赋上值*/
        	 if(state[index]==null){
        		 state[index] = sessionInfo.getRealName(); 
        	 }
             continue;
         }
     }

	 return true;
}


@Override
public boolean onFlushDirty(Object entity, Serializable id,
		Object[] currentState, Object[] previousState,
		String[] propertyNames, Type[] types) {
	logger.info("--------------HiberAspect-----------------------onFlushDirty-------------------");
	SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
	//添加数据
     for (int index=0;index<propertyNames.length;index++)
     {
         /*找到名为"修改时间"的属性*/
         if ("modifyDt".equals(propertyNames[index]))
         {
             /*使用拦截器将对象的"修改时间"属性赋上值*/
        	 previousState[index] = new Date();
             continue;
         }
         /*找到名为"修改人"的属性*/
         else if ("modifier".equals(propertyNames[index]))
         {
             /*使用拦截器将对象的"修改人"属性赋上值*/
        	 previousState[index] = sessionInfo.getUserId();
        	 continue;
         }
         /*找到名为"修改人名称"的属性*/
         else if ("modifierName".equals(propertyNames[index]))
         {
             /*使用拦截器将对象的"修改人名称"属性赋上值*/
        	 previousState[index] = sessionInfo.getRealName();
        	 continue;
         }
     }

	 return true;
}
}
