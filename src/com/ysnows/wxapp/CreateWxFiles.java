package com.ysnows.wxapp;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.UIBundle;

import java.io.File;

public class CreateWxFiles extends AnAction {


    @Override
    public void actionPerformed(AnActionEvent e) {
        String newFileName;

        newFileName = Messages.showInputDialog(UIBundle.message("create.new.file.enter.new.file.name.prompt.text"),
                UIBundle.message("new.file.dialog.title"), Messages.getQuestionIcon());
        if (newFileName == null) {
            return;
        }
        if ("".equals(newFileName.trim())) {
            Messages.showMessageDialog(UIBundle.message("create.new.file.file.name.cannot.be.empty.error.message"),
                    UIBundle.message("error.dialog.title"), Messages.getErrorIcon());
        }


        VirtualFile selectedFile = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        String path = selectedFile.getPath();

        File file = new File(path, newFileName);
        if (file.exists()) {
            Utils.showErrorNotification(e.getProject(), "文件已存在");
            return;
        }
        if (file.mkdirs()) {


        }
    }


}
