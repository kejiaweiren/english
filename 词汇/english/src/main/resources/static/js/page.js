/**
 *
 * @param pageInfo page's data
 * @param pageMethodName loadPage method name
 * @returns {string} pagehtml
 */
function drawPageDiv(pageInfo,pageMethodName){
    //分页文字信息/
    var html="<div class='col-md-6'>current <font color='blue'>"
        + pageInfo.pageNum+
        "</font> page,"
        +pageInfo.pages+
        "    pages,"
        + pageInfo.total+
        " records</div>";
    //分页条信息/
    html+="<div class='col-md-6'>";
    html+="<nav aria-label='Page navigation'>";
    html+="<ul class='pagination'>";
    html +="<li><a href='javascript:void(0)' onclick='";
    html+=pageMethodName;
        html+="(1)'>First</a></li>";
    if(pageInfo.hasPreviousPage){
        html+="<li><a 'javascript:void(0)' onclick=';"
        html+=pageMethodName;
        html+="("+(pageInfo.pageNum-1)+")' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>";
    }
    for(var i=0;i<pageInfo.navigatepageNums.length;i++){
        var page_Num=pageInfo.navigatepageNums[i];
        if(page_Num == pageInfo.pageNum){
            html+="<li class='active'><a href='#'>"+page_Num+"</a></li>";
        }else{
            html+="<li><a href='javascript:void(0)' onclick='";
            html+=pageMethodName;
            html+="("+page_Num+")'>"+page_Num+"</a></li>";
        }
    }
    if(pageInfo.hasNextPage){
        html+="<li><a  href='javascript:void(0)' onclick='";
        html+=pageMethodName;
        html+="("+(pageInfo.pageNum+1)+")' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>";
    }
    html +="<li><a href='javascript:void(0)' onclick='";
    html +=pageMethodName;
    html+="("+pageInfo.pages+")'>End</a></li>";
    html+="</ul>";
    html+="</nav>";
    html+="</div>";
    return html;
}