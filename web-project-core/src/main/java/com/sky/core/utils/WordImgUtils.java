package com.sky.core.utils;
import java.io.File;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
/**
 * Created by ThinkPad on 2019/12/14.
 */
public class WordImgUtils {

    // word运行程序对象
    private ActiveXComponent word;
    // 所有word文档集合
    private Dispatch documents;
    // word文档
    private Dispatch doc;
    // 选定的范围或插入点
    private Dispatch selection;
    // 保存退出
    private boolean saveOnExit;

    /**
     * 是否可见word程序
     * @param visible true-可见word程序，false-后台默默执行。
     */
    public WordImgUtils(boolean visible) {
        word = new ActiveXComponent("Word.Application");
        word.setProperty("Visible", new Variant(visible));
        documents = word.getProperty("Documents").toDispatch();
    }
    /**
     * 打开一个已经存在的Word文档
     * @param docPath 文件的路径
     */
    public void openDocument(String docPath) {
        doc = Dispatch.call(documents, "Open", docPath).toDispatch();
        selection = Dispatch.get(word, "Selection").toDispatch();
    }

    /**
     * 全局将指定的文本替换成图片
     * @param findText
     * @param imagePath
     */
    public void replaceAllImage(String findText, String imagePath, int width, int height){
        moveStart();
        while (find(findText)){
            Dispatch picture = Dispatch.call(Dispatch.get(getSelection(), "InLineShapes").toDispatch(), "AddPicture", imagePath).toDispatch();
            Dispatch.call(picture, "Select");
            Dispatch.put(picture, "Width", new Variant(width));
            Dispatch.put(picture, "Height", new Variant(height));
            moveStart();
        }
    }

    /**
     * 把插入点移动到文件首位置
     */
    public void moveStart(){
        Dispatch.call(getSelection(), "HomeKey", new Variant(6));
    }

    /**
     * 获取当前的选定的内容或者插入点
     * @return 当前的选定的内容或者插入点
     */
    public Dispatch getSelection(){
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        return selection;
    }

    /**
     * 从选定内容或插入点开始查找文本
     * @param findText 要查找的文本
     * @return boolean true-查找到并选中该文本，false-未查找到文本
     */
    public boolean find(String findText){
        if(findText == null || findText.equals("")){
            return false;
        }
        // 从selection所在位置开始查询
        Dispatch find = Dispatch.call(getSelection(), "Find").toDispatch();
        // 设置要查找的内容
        Dispatch.put(find, "Text", findText);
        // 向前查找
        Dispatch.put(find, "Forward", "True");
        // 设置格式
        Dispatch.put(find, "Format", "True");
        // 大小写匹配
        Dispatch.put(find, "MatchCase", "True");
        // 全字匹配
        Dispatch.put(find, "MatchWholeWord", "True");
        // 查找并选中
        return Dispatch.call(find, "Execute").getBoolean();
    }

    /**
     * 文档另存为
     * @param savePath
     */
    public void saveAs(String savePath){
        Dispatch.call(doc, "SaveAs", savePath);
    }

    /**
     * 关闭word文档
     */
    public void closeDocument(){
        if (doc != null) {
            Dispatch.call(doc, "Close", new Variant(saveOnExit));
            doc = null;
        }
    }
}
