package top.snake.fast.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.snake.fast.pojo.TAssociation;
import top.snake.fast.pojo.TAssociationExample;

public interface TAssociationMapper {
    int countByExample(TAssociationExample example);

    int deleteByExample(TAssociationExample example);

    int deleteByPrimaryKey(String assid);

    int insert(TAssociation record);

    int insertSelective(TAssociation record);

    List<TAssociation> selectByExampleWithBLOBs(TAssociationExample example);

    List<TAssociation> selectByExample(TAssociationExample example);

    TAssociation selectByPrimaryKey(String assid);

    int updateByExampleSelective(@Param("record") TAssociation record, @Param("example") TAssociationExample example);

    int updateByExampleWithBLOBs(@Param("record") TAssociation record, @Param("example") TAssociationExample example);

    int updateByExample(@Param("record") TAssociation record, @Param("example") TAssociationExample example);

    int updateByPrimaryKeySelective(TAssociation record);

    int updateByPrimaryKeyWithBLOBs(TAssociation record);

    int updateByPrimaryKey(TAssociation record);
}