package com.kjw.bean;

import lombok.Data;

@Data
public class Word {
    private Integer id;

    private String word;

    private String chinese;

    private String root;

    private String coreword;

    private String sentence;

    private String note;

    private String phonetic;

    private Word core;

}