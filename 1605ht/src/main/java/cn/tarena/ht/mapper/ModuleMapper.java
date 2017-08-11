package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import cn.tarena.ht.pojo.Module;

public interface ModuleMapper {

	List<Module> findAllModule();
	
	@Select("select * from module_p")
	List<Module> findByAnnotation();
	//第一个参数要指定provider的class类型
	//第二个参数要指定provider的对应的方法名
	@SelectProvider(type=ModuleMapperProvider.class,method="select")
	List<Module> findByProvider();
	//在新增的时候，别忘了设置主键值，要不会报null值错误
	@Insert("insert into module_p (module_id,name,parent_id,ctype,state)"
			+ "values(#{moduleId},#{name},#{parentId},#{ctype},#{state})")
	void insertByAnnotation(Module module);
	
	@Delete("delete from module_p where module_id=#{moduleId}")
	void deleteByAnnotation(String moduleId);

}
