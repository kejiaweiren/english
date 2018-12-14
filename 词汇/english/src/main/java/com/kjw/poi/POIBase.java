package com.kjw.poi;

import org.apache.poi.ss.usermodel.*;

/**
 * 导入导出基类
 * @author kejiawei
 * @create 2018-11-19 10:48
 */

public class POIBase
{
    Workbook workbook = null;
    Sheet sheet=null;
    Row row=null;
    Cell cell=null;
    int rowNum = 0;
    CellStyle style;
    Font font;
    public String getImportCellStringValue(Cell cell){
        if (cell!=null){
            cell.setCellType(Cell.CELL_TYPE_STRING);
            return cell.getStringCellValue();
        }else {
            return "";
        }

    }
}
