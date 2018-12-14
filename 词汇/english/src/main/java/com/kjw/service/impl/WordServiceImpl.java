package com.kjw.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.kjw.bean.Word;
import com.kjw.bean.WordExample;
import com.kjw.dao.WordMapper;
import com.kjw.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kejiawei
 * @create 2018-11-27 11:17
 */
@Service
public class WordServiceImpl implements WordService
{
    @Autowired
    private WordMapper wordMapper;
    /**
     * 保存
     * @param word
     */
    public void save(Word word){
        wordMapper.insertSelective(word);
    }


    /**
     * 按照id查询
     * @param id
     * @return
     */
    public Word getWord(Integer id){
        return wordMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新
     * @param word
     */
    public void update(Word word){
        wordMapper.updateByPrimaryKeySelective(word);
    }
    /**
     * 批量插入
     * 如果返回NULL，说明 没有重复，插入成功
     * @param words
     */
    public List<Word> batchInsert(List<Word> words){
        List<String> wordStrList=words.stream().map(Word::getWord).collect(Collectors.toList());
        List<Word> existWords= selectByWords(wordStrList);
        if (!existWords.isEmpty()){
            return existWords;
        }
        int count=0;
        List<Word> temp=new ArrayList<>();
        for (Word word : words)
        {
            temp.add(word);
            count++;
            if (count>=3){
                wordMapper.batchInsert(temp);
                count=0;
                temp.clear();
            }
        }
        if (count > 0 && count < 3) {
            wordMapper.batchInsert(temp);//分批跑后还剩余的数据
        }
        return new ArrayList<>();
    }
    /**
     * 删除
     * @param id
     */
    public void delete(Integer id){
        wordMapper.deleteByPrimaryKey(id);
    }


    public List<Word> list(String word){
        WordExample example=new WordExample();
        WordExample.Criteria criteria=example.createCriteria();
        if (StringUtil.isNotEmpty(word)){
            criteria.andWordLike("%"+word+"%");
        }
        return wordMapper.selectByExample(example);
    }
    public List<Word> selectByWords(List<String> words){
        WordExample example=new WordExample();
        WordExample.Criteria criteria=example.createCriteria();
        if (!words.isEmpty()){
            criteria.andWordIn(words);
        }
        return wordMapper.selectByExample(example);
    }
    public Word randomWord(){
        Word word=wordMapper.randomWord();
        if (StringUtil.isNotEmpty(word.getCoreword())){//得到核心词对象
            String corewordstr= word.getCoreword();
            if (!corewordstr.equals(word.getWord()) ){//如果当前不是核心词本身
                WordExample example=new WordExample();
                WordExample.Criteria criteria=example.createCriteria();
                criteria.andWordEqualTo(corewordstr);
                List<Word> corewords= wordMapper.selectByExample(example);
                if (!corewords.isEmpty()){
                    word.setCore(corewords.get(0));
                }
            }

        }
        return word;
    }
}
