package top.snake.fast.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.snake.fast.pojo.TUserAssociation;
import top.snake.fast.pojo.TUserAssociationExample;

public interface TUserAssociationMapper {
    int countByExample(TUserAssociationExample example);

    int deleteByExample(TUserAssociationExample example);

    int deleteByPrimaryKey(String uaid);

    int insert(TUserAssociation record);

    int insertSelective(TUserAssociation record);

    List<TUserAssociation> selectByExample(TUserAssociationExample example);

    TUserAssociation selectByPrimaryKey(String uaid);

    int updateByExampleSelective(@Param("record") TUserAssociation record, @Param("example") TUserAssociationExample example);

    int updateByExample(@Param("record") TUserAssociation record, @Param("example") TUserAssociationExample example);

    int updateByPrimaryKeySelective(TUserAssociation record);

    int updateByPrimaryKey(TUserAssociation record);
}