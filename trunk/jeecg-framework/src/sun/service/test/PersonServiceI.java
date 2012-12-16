package sun.service.test;

import sun.entity.test.PersonEntity;
import sy.pageModel.DataGrid;
import sun.page.test.PersonPage;
import sy.service.BaseServiceI;
import java.util.List;

/**   
 * @Title: Service
 * @Description: 用户管理
 * @author zhangdaihao
 * @date 2011-12-12 17:02:35
 * @version V1.0   
 *
 */
public interface PersonServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(PersonPage personPage);

	/**
	 * 添加
	 * 
	 * @param personPage
	 */
	public void add(PersonPage personPage);

	/**
	 * 修改
	 * 
	 * @param personPage
	 */
	public void update(PersonPage personPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Person
	 * @return
	 */
	public PersonEntity get(PersonPage personPage);
	
	
	/**
	 * 获得
	 * 
	 * @param obid
	 * @return
	 */
	public PersonEntity get(String obid);
	
	/**
	 * 获取所有数据
	 */
	public List<PersonEntity> listAll(PersonPage personPage);

}
