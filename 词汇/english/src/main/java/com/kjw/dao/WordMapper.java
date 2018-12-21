package com.kjw.dao;

import com.kjw.bean.Word;
import com.kjw.bean.WordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

public interface WordMapper {
    long countByExample(WordExample example);

    int deleteByExample(WordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Word record);

    int insertSelective(Word record);

    @Cacheable
    List<Word> selectByExample(WordExample example);

    Word selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Word record, @Param("example") WordExample example);

    int updateByExample(@Param("record") Word record, @Param("example") WordExample example);

    int updateByPrimaryKeySelective(Word record);

    int updateByPrimaryKey(Word record);

    void batchInsert(List<Word> temp);

    List<Word> randomWord(Integer randomNum);
}