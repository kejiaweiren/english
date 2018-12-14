package com.kjw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kjw.bean.Msg;
import com.kjw.bean.Word;
import com.kjw.common.Constants;
import com.kjw.poi.WordImport;
import com.kjw.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        // 这不是一个分页查询
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, Constants.PAGESIZE);
        // startPage后面紧跟的这个查询就是一个分页查询
        Map<String ,Object> map=new HashMap();//构建查询MAP参数
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
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
     * 随机WORD
     *
     * @return
     */
    @GetMapping("/randomWord")
    public Msg randomWord()
    {
        Word word= wordService.randomWord();
        return Msg.success().add("word",word);
    }
}
