package com.ysnows.wxapp;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

import java.io.OutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 执行将方法写入js文件里的操作
 * <p>
 * Created by lypeer on 2016/9/29.
 */
class Writer extends WriteCommandAction.Simple {

    private final PsiFile mFile;

    //存放wxml文件里包含的所有方法头的列表
    private final List<String> mFunctionsName;
    private Project project;
    private Editor editor;

    Writer(PsiFile psiFile, List<String> functionsName, Project project, Editor editor) {
        super(psiFile.getProject(), psiFile);

        mFile = psiFile;
        mFunctionsName = functionsName;
        this.project = project;
        this.editor = editor;
    }

    @Override
    protected void run() throws Throwable {
        VirtualFile virtualFile = mFile.getVirtualFile();
        String content = mFile.getText();
        int injectNum = 0;

        int index = content.lastIndexOf("}");
        String substring = content.substring(0, index);
        index = substring.lastIndexOf("}") + 1;

        StringBuilder contentBuffer = new StringBuilder(content);


        for (String functionName : mFunctionsName) {
            Pattern pattern = Pattern.compile("\\n([\\s]*)" + functionName);
            Matcher matcher = pattern.matcher(content);
            if (matcher.find())
                continue;
            injectNum++;
            String functionBuffer = ",\n\t" + functionName + ": function (e) {\n\t\t\n\t}";
            contentBuffer.insert(index, "\n\t" + functionBuffer);

//            PsiManager.getInstance(project).findViewProvider(virtualFile).getDocument().insertString(index, "\n\t" + functionBuffer);

        }

        if (injectNum == 0) {
            Utils.showErrorNotification(mFile.getProject(), Constants.Message.MESSAGE_INJECT_NOTHING);
            return;
        }

        OutputStream outputStream = virtualFile.getOutputStream(this);
        outputStream.write(contentBuffer.toString().getBytes());
        outputStream.flush();
        outputStream.close();

        //从磁盘中加载文件
        PsiManager.getInstance(project).reloadFromDisk(mFile);
        //打开js文件
        FileEditorManager.getInstance(this.project).openFile(virtualFile, true, true);

//        this.editor.getCaretModel().getCurrentCaret().moveToOffset(index);

        // 移动光标
        index = contentBuffer.toString().lastIndexOf("}");
        substring = contentBuffer.toString().substring(0, index);
        index = substring.lastIndexOf("}") - 2;

        Editor editor = EditorFactory.getInstance().getEditors(PsiManager.getInstance(project).findViewProvider(virtualFile).getDocument(), project)[0];
        editor.getCaretModel().moveToOffset(index);

        //滑动文件位置到光标的位置

        editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);


        Utils.showNotification(mFile.getProject(), String.format(Constants.Message.MESSAGE_INJECT_SUCCESSFULLY, injectNum), MessageType.INFO);
    }
}
