package indi.cloud.oauth.center.base.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface Service<T> {
    void save(T model);//持久化
    void save(List<T> models);//批量持久化
    void deleteById(Integer id);//通过主鍵刪除
    void deleteByIds(String ids);//批量刪除 eg：ids -> “1,2,3,4”
    void update(T model);//更新
    T selectById(Integer id);//通过ID查找
    T selectBy(String fieldName, Object value) throws TooManyResultsException; //通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
    List<T> selectByIds(String ids);//通过多个ID查找//eg：ids -> “1,2,3,4”
    List<T> selectAll();//获取所有
    List<T> selectByCondition(Condition condition);//根据条件查找
    int selectCountByCondition(Condition condition);//Condition条件进行查询
    int deleteByCondition(Condition condition); // 根据Condition条件删除数据，返回删除的条数
    int updateByCondition(T record, Condition condition); // 根据Condition条件更新实体`record`包含的全部属性，null值会被更新，返回更新的条数
    int updateByConditionSelective(T record, Condition condition); // 根据Condition条件更新实体`record`包含的全部属性，null值会被更新，返回更新的条数
}
