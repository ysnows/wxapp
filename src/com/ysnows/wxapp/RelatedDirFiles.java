package com.ysnows.wxapp;

import com.intellij.ide.util.TreeClassChooserFactory;
import com.intellij.ide.util.TreeFileChooser;
import com.intellij.ide.util.TreeFileChooserFactory;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;

public class RelatedDirFiles extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here

        Project project = e.getProject();
        PsiFile currentFile = e.getData(LangDataKeys.PSI_FILE);


        TreeFileChooser.PsiFileFilter psiFileFilter = new TreeFileChooser.PsiFileFilter() {
            @Override
            public boolean accept(PsiFile psiFile) {
                return psiFile.getName().contains(currentFile.getName().substring(0, currentFile.getName().lastIndexOf(".")));
            }
        };


        TreeFileChooserFactory instance = TreeFileChooserFactory.getInstance(project);
        TreeFileChooser fileChooser = instance.createFileChooser("请选择", null, null, psiFileFilter, false, false);
        fileChooser.showDialog();
        PsiFile selectedFile = fileChooser.getSelectedFile();

        FileEditorManager.getInstance(project).openFile(selectedFile.getVirtualFile(),true,true);


    }
}
