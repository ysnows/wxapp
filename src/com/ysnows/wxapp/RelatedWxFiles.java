package com.ysnows.wxapp;

import com.intellij.ide.util.TreeClassChooserFactory;
import com.intellij.ide.util.TreeFileChooser;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.file.impl.FileManager;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiUtilBase;

import java.io.File;

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
            jsFile = getPsiFileByName(project, fileName);
            FileEditorManager.getInstance(project).openFile(jsFile.getVirtualFile(), true);

        } else if (currentFile.getName().endsWith("js")) {
            jsFile = currentFile;
            String wxmlFileName = jsFile.getName().replace("js", "wxml");
            wxmlFile = getPsiFileByName(project, wxmlFileName);

            if (wxmlFile == null) {
                return;
            }

            FileEditorManager.getInstance(project).openFile(wxmlFile.getVirtualFile(), true,true);
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


    private PsiFile getPsiFileByName(Project project, String wxmlFileName) {
        PsiFile[] wxmlFiles = FilenameIndex.getFilesByName(project, wxmlFileName, GlobalSearchScope.allScope(project));
        if (wxmlFiles.length < 1) {
            Utils.showErrorNotification(project, Constants.Message.ERROR_NOT_FOUND);
            return null;
        }
        if (wxmlFiles.length > 1) {
            Utils.showErrorNotification(project, Constants.Message.ERROR_MORE_THAN_ONE_FILE + wxmlFileName);
            return null;
        }

        return wxmlFiles[0];
    }

}
