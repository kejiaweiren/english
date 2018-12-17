package com.kjw.service;

import com.kjw.bean.Word;
import com.kjw.bean.WordExample;

import java.util.List;

/**
 * @author kejiawei
 * @create 2018-11-27 11:13
 */

public interface WordService
{
    /**
     * 保存
     * @param word
     */
    void save(Word word);


    /**
     * 按照id查询
     * @param id
     * @return
     */
    Word getWord(Integer id);

    /**
     * 更新
     * @param word
     */
    void update(Word word);
    /**
     * 更新
     * @param word
     */
    List<Word> batchInsert(List<Word> word);
    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    List<Word> list(String word);

    List<Word> selectByWords(List<String> words);

    Word randomWord();

    List<Word> listByCoreWord(String coreWord);
}
