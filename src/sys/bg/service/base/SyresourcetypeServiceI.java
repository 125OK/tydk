package sys.bg.service.base;

import java.util.List;

import sys.bg.model.base.Syresourcetype;
import sys.bg.service.BaseServiceI;

/**
 * 资源类型业务
 * 
 * @author admin
 * 
 */
public interface SyresourcetypeServiceI extends BaseServiceI<Syresourcetype> {

	/**
	 * 获取所有资源类型
	 */
	public List<Syresourcetype> findResourcetype();

}
