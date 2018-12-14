package com.kjw.poi;

import com.github.pagehelper.util.StringUtil;
import com.kjw.bean.Word;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kejiawei
 * @create 2018-11-16 17:12
 */
public class WordImport extends POIBase
{
    /**
     * 导入word
     */
    public List<Word> importWord(MultipartFile file) throws  Exception{
        List<Word> words=new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        String fileName=file.getOriginalFilename();
        if (fileName.endsWith("xlsx")){
            workbook = new XSSFWorkbook(inputStream);//Excel 2007
        }else if (fileName.endsWith("xls")){
            workbook = new HSSFWorkbook(inputStream);//Excel 2003
        }
        sheet =workbook.getSheetAt(0);//得到第一个sheet页
        if (sheet!=null){
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {//遍历所有行
                row= sheet.getRow(rowNum);//得到行
                if (row!=null){
                    Word word=new Word();//根据该行构建expense
                    cell=row.getCell(0);
                    word.setWord(getImportCellStringValue(cell));
                    cell=row.getCell(1);
                    word.setRoot(getImportCellStringValue(cell));
                    cell=row.getCell(2);
                    word.setCoreword(getImportCellStringValue(cell));
                    cell=row.getCell(3);
                    word.setChinese(getImportCellStringValue(cell));
                    cell=row.getCell(4);
                    word.setNote(getImportCellStringValue(cell));
                    formatWord(word);
                    if (word.getWord()!=null&&!"".equals(word.getWord().trim())){
                        words.add(word);
                    }
                }
            }
        }
        return words;
    }
    private void formatWord(Word wordData){
        String word= wordData.getWord();
        if (StringUtil.isNotEmpty(word)&&word.indexOf("[")!=-1){
            int index=word.indexOf("[");
            String phonetic=word.substring(index,word.length()).trim();
            wordData.setPhonetic(phonetic);
            wordData.setWord(word.substring(0,index).trim());
        }
    }

    public static void main(String[] args)
    {
        WordImport wordImport=new WordImport();
        Word word=new Word();
        word.setWord("aa[  123]");
        wordImport.formatWord(word);
        System.out.println(word);
    }
}
