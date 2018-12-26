package com.kjw.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.kjw.bean.Word;
import com.kjw.bean.WordContainer;
import com.kjw.bean.WordExample;
import com.kjw.common.Constants;
import com.kjw.controller.WordController;
import com.kjw.dao.WordMapper;
import com.kjw.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
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
    @Value("${randNum}")
    private Integer randNum;
    private static Logger logger = LoggerFactory.getLogger(WordServiceImpl.class);
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
        List<Word> datas = wordMapper.selectByExample(example);
        for (Word data : datas)
        {
            setCordWord(data);
        }
        return datas;
    }

    /**
     * 改进随机抽取单词方法，调用此方法时先取缓存，
     * 如果缓存中没有数据， 查询数据库，并放入缓存。
     * 然后取一条数据,并在缓存删除这条数据
     * @return
     */
    public Word randomWord(){
        Map<String,Word> map= WordContainer.wordMap;
        Word word;
        if (map!=null&&map.size()>0){//如果有数据
            String[] keys = map.keySet().toArray(new String[0]);
            Random random = new Random();
            String randomKey = keys[random.nextInt(keys.length)];
            word=map.get(randomKey);
            WordContainer.wordMap.remove(randomKey);//删除这条数据
            logger.info("取出单词{},现还有{}条数据",randomKey,WordContainer.wordMap.size());
        }else{
            List<Word> words=wordMapper.randomWord(randNum);//如果没有数据，去数据库查询
            word=words.get(0);
            words.remove(0);
            logger.info("缓存中没有数据，取数据库.现有数据共{}条",+words.size());
            WordContainer.wordMap=words.stream().collect(Collectors.toMap(Word::getWord,x->x));//放入map
        }
        setCordWord(word);//设置核心词对象
        return word;
    }

    /**
     * 设置核心词
     * @param word
     */
    private void setCordWord(Word word){
        if (StringUtil.isNotEmpty(word.getCoreword())){//得到核心词对象
            String corewordstr= word.getCoreword();
            if (!corewordstr.equals(word.getWord()) ){//如果当前不是核心词本身
                word.setCore(getCoreWord(corewordstr));
            }
        }
    }
    @Cacheable
    public Word getCoreWord(String word){
        WordExample example=new WordExample();
        WordExample.Criteria criteria=example.createCriteria();
        criteria.andWordEqualTo(word);
        List<Word> corewords= wordMapper.selectByExample(example);
        if (!corewords.isEmpty()){
            return corewords.get(0);
        }else{
            return new Word();
        }
    }
    public List<Word> listByCoreWord(String coreWord){
        WordExample example=new WordExample();
        WordExample.Criteria criteria=example.createCriteria();
        if (StringUtil.isNotEmpty(coreWord)){
            criteria.andCorewordEqualTo(coreWord);
        }
        return wordMapper.selectByExample(example);
    }
}
