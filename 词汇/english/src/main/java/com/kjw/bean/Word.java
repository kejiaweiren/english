package com.kjw.bean;

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

    public Word getCore()
    {
        return core;
    }

    public void setCore(Word core)
    {
        this.core = core;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese == null ? null : chinese.trim();
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root == null ? null : root.trim();
    }

    public String getCoreword() {
        return coreword;
    }

    public void setCoreword(String coreword) {
        this.coreword = coreword == null ? null : coreword.trim();
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence == null ? null : sentence.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic == null ? null : phonetic.trim();
    }

    @Override
    public String toString()
    {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", chinese='" + chinese + '\'' +
                ", root='" + root + '\'' +
                ", coreword='" + coreword + '\'' +
                ", sentence='" + sentence + '\'' +
                ", note='" + note + '\'' +
                ", phonetic='" + phonetic + '\'' +
                ", core=" + core +
                '}';
    }
}