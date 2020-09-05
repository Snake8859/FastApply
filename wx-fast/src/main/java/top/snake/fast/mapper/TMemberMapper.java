package top.snake.fast.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.snake.fast.pojo.TMember;
import top.snake.fast.pojo.TMemberExample;

public interface TMemberMapper {
    int countByExample(TMemberExample example);

    int deleteByExample(TMemberExample example);

    int deleteByPrimaryKey(String aid);

    int insert(TMember record);

    int insertSelective(TMember record);

    List<TMember> selectByExampleWithBLOBs(TMemberExample example);

    List<TMember> selectByExample(TMemberExample example);

    TMember selectByPrimaryKey(String aid);

    int updateByExampleSelective(@Param("record") TMember record, @Param("example") TMemberExample example);

    int updateByExampleWithBLOBs(@Param("record") TMember record, @Param("example") TMemberExample example);

    int updateByExample(@Param("record") TMember record, @Param("example") TMemberExample example);

    int updateByPrimaryKeySelective(TMember record);

    int updateByPrimaryKeyWithBLOBs(TMember record);

    int updateByPrimaryKey(TMember record);
}