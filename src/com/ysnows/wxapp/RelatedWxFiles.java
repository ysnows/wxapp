package com.ysnows.wxapp;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;

public class RelatedWxFiles extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Project project = e.getProject();
        PsiFile currentFile = e.getData(LangDataKeys.PSI_FILE);
        PsiFile wxmlFile;
        PsiFile jsFile;

        if (currentFile.getName().endsWith("wxml")) {
            wxmlFile = currentFile;
            String fileName = wxmlFile.getName().replace("wxml", "js");
            jsFile = Utils.getPsiFileByNameInSameDir(project, fileName, currentFile);
            FileEditorManager.getInstance(project).openFile(jsFile.getVirtualFile(), true);

        } else if (currentFile.getName().endsWith("js")) {
            jsFile = currentFile;
            String wxmlFileName = jsFile.getName().replace("js", "wxml");
            wxmlFile = Utils.getPsiFileByNameInSameDir(project, wxmlFileName, currentFile);

            if (wxmlFile == null) {
                return;
            }

            FileEditorManager.getInstance(project).openFile(wxmlFile.getVirtualFile(), true, true);
        }

//        Project project = e.getProject();
//
//        TreeFileChooser.PsiFileFilter psiFileFilter = new TreeFileChooser.PsiFileFilter() {
//            @Override
//            public boolean accept(PsiFile psiFile) {
//                return psiFile.getName().contains("article");
//            }
//        };
//
//
//
//        TreeClassChooserFactory instance = TreeClassChooserFactory.getInstance(project);
//        TreeFileChooser fileChooser = instance.createFileChooser("请选择", null, null, psiFileFilter, false, false);
//        fileChooser.showDialog();


    }


}
