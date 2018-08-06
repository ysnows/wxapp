package com.ysnows.wxapp;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.UIBundle;

import java.io.IOException;

public class CreateWxFiles extends AnAction {


    private Project project;

    @Override
    public void actionPerformed(AnActionEvent e) {
        String newFileName;
        project = e.getProject();

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


        VirtualFile newFile = selectedFile.findChild(newFileName);
        if (newFile != null) {
            if (newFile.exists()) {
                Utils.showErrorNotification(e.getProject(), "文件已存在");
                return;
            }
        }

        VirtualFile projectFile = project.getBaseDir();

        VirtualFile wxtp = projectFile.findChild("wxtp");
        if (wxtp == null) {
            Utils.showErrorNotification(e.getProject(), "模板文件不存在");
            return;
        }

        new WriteCommandAction.Simple(project) {
            @Override
            protected void run() throws Throwable {
                try {

                    VirtualFile copyFile = wxtp.copy(null, selectedFile, newFileName);
                    VirtualFile[] children = copyFile.getChildren();

                    for (VirtualFile child : children) {
                        String name = child.getName();
                        int lastIndexOf = name.lastIndexOf(".");
                        String substring = name.substring(0, lastIndexOf);
                        String replace = name.replace(substring, newFileName);
                        child.rename(null, replace);
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }.execute();


        System.out.print("Hello");
//        if (file.mkdirs()) {
//            String tpFileName = "wxtp";
//            tpFile = new File(path, tpFileName);
//            if (!tpFile.exists()) {//如果模板文件不存在
//                Utils.showErrorNotification(e.getProject(), "模板文件不存在");
//                return;
//            }
//        }


    }


}
