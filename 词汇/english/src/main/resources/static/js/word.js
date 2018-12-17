$(function() {
    loadWord();
    $("#rootSpan").click(function () {
        $("#root").toggle();
    });
    $("#coreWordSpan").click(function () {
        $("#coreWord").toggle();
        $("#note").toggle();
        $("#wordtbody").toggle();
    });
    $("#chineseSpan").click(function () {
        $("#chinese").toggle();
        $("#sentence").toggle();
    });
    $("#nextSpan").click(loadWord);
});
/**
 * 加载word
 */
function loadWord(){
    $(".wordExplain").hide();
    $.ajax({
        url:"/randomWord",
        type:"GET",
        dataType:"JSON",
        success:function(data){
            if(data.code==200){//如果成功
                var worddata=data.extend.word;//得到数据
                $("#word").html("<h1>"+worddata.word+worddata.phonetic+"</h1>");
                $("#root").html(worddata.root);//词根
                $("#coreWord").html(worddata.coreword);//核心词
                var note=""
                if(worddata.coreword){
                    loadWordsByCoreWord(1,worddata.coreword);//加载核心词相关单词
                }
                if(worddata.core){//如果存在核心词对象
                    note+="---------核心词----------<br/>";
                    note+=worddata.core.note+"<br/>";
                    note+=getWordImgHtml(worddata.core.word);
                    note+="-------------------<br/>";
                }
                note+=worddata.note;//解释
                note+="<br/>";
                var word=worddata.word;//单词
                note+=getWordImgHtml(word);
                $("#note").html(note);
                $("#chinese").html(worddata.chinese);
                if(worddata.coreword){
                    $("#sentence").html(worddata.sentence);
                }
            }else{
                alert(data.extend.errMsg);
            }
        }
    });
}
function getWordImgHtml(word){
    var imageFullPath="/images/"+word+".png";//图片以imageDir//单词名.png来命名
    if(isHasImg(imageFullPath)){
        return "<img src='"+imageFullPath+"'/><br/>";
    }else{
        return "";
    }
}
/**
 * 判断是否存在图片
 * @param pathImg
 * @returns {boolean}
 */
function isHasImg(pathImg){
    var ImgObj=new Image();
    ImgObj.src= pathImg;
    if(ImgObj.fileSize > 0 || (ImgObj.width > 0 && ImgObj.height > 0))
    {
        return true;
    } else {
        return false;
    }
}
/**
 * 加载word
 * @param pageNum页数
 */
function loadWordsByCoreWord(pageNum,coreWord){
    var pn=1;//默认为第1页
    if(pageNum){
        pn=pageNum;
    }
    var param={
        pn:pn,
        coreWord:coreWord
    };
    $.ajax({
        url:"/coreWord/page",
        type:"GET",
        dataType:"JSON",
        data:param,
        success:function(data){
            if(data.code==200){//如果成功
                var datas=data.extend.words;//得到数据
                var datahtml="";
                $.each(datas,function (i,word) {
                    datahtml+="<tr>";
                    datahtml+="<td>";
                    datahtml+=word.word;
                    datahtml+="</td>";
                    datahtml+="<td>";
                    datahtml+=word.root;
                    datahtml+="</td>";
                    datahtml+="<td>";
                    datahtml+=word.chinese;
                    datahtml+="</td>";
                    datahtml+="</tr>";
                });
                $("#wordtbody").html(datahtml);//加载到tbody中
            }else{
                alert(data.extend.errMsg);
            }
        }
    });
}