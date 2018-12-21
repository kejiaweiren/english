package com.kjw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kjw.bean.Msg;
import com.kjw.bean.Word;
import com.kjw.bean.WordContainer;
import com.kjw.common.Constants;
import com.kjw.poi.WordImport;
import com.kjw.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kejiawei
 * @create 2018-11-27 11:28
 */
@RestController
public class WordController
{
    private static Logger logger = LoggerFactory.getLogger(WordController.class);
    @Autowired
    private WordService wordService;
    /**
     * 删除方法
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "word/{id}")
    public Msg deleteWord(Integer id)
    {
        wordService.delete(id);
        return Msg.success();
    }
    /**
     * 更新方法
     *
     * @param word
     * @return
     */
    @PutMapping(value = "/word/{id}")
    public Msg updateWord(Word word)
    {
        wordService.update(word);
        return Msg.success();
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/word/{id}")
    public Msg getWord(@PathVariable("id") Integer id)
    {
        Word data = wordService.getWord(id);
        return Msg.success().add("word", data);
    }


    /**
     * 保存
     *
     * @return
     */
    @PostMapping("/word")
    public Msg saveWord(Word word)
    {
        wordService.save(word);
        return Msg.success();
    }

    /**
     * 查询word 分页
     * @return
     */
    @PostMapping("/word/page")
    public Msg page(Integer pn,String word)
    {
        logger.info("page method: pn:{},word:{}", pn,word);
        if (pn == null)
        {
            pn = 1;
        }
        PageHelper.startPage(pn, Constants.PAGESIZE);
        List<Word> words=wordService.list(word);
        PageInfo page = new PageInfo(words, Constants.PAGESIZE);
        return Msg.success().add("pageInfo", page);
    }
    @PostMapping("/importWord")
    public Msg importWord(@RequestParam(value="uploadFile")MultipartFile file){
        try
        {
            WordImport wordImport=new WordImport();
            List<Word> words=wordImport.importWord(file);//根据文件得到数据
            List<Word> existWords= wordService.batchInsert(words);//批量插入
            if (!existWords.isEmpty()){
                return Msg.fail().add("errMsg","已经存在WORDS，请确认").add("words",existWords);
            }
            return Msg.success();
        }catch (Exception e){
            logger.error(e.toString());
            return Msg.fail().add("errMsg",e.toString());
        }
    }
    /**
     * @return
     */
    @GetMapping("/randomWord")
    public Msg randomWord()
    {
        Word word= wordService.randomWord();
        return Msg.success().add("word",word);
    }

    /**
     * 查询word 分页
     * @return
     */
    @GetMapping("/coreWord/page")
    public Msg coreWordPage(Integer pn,String coreWord)
    {
        logger.info("coreWordPage method: pn:{},coreWord:{}", pn,coreWord);
        List<Word> words=wordService.listByCoreWord(coreWord);
        return Msg.success().add("words", words);
    }
    /**
     * 根据word查询
     * @param word
     * @return
     */
    @GetMapping(value = "/getDetailByWord")
    public Msg getWordByWord(String word)
    {
        Word data=new Word();
        List<Word> words = wordService.selectByWords(Arrays.asList(word));
        if (!words.isEmpty()){
            data=words.get(0);
        }
        return Msg.success().add("word", data);
    }

}
