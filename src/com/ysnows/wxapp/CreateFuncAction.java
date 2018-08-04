package com.ysnows.wxapp;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiUtilBase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateFuncAction extends AnAction {


    private Project project;

    @Override
    public void actionPerformed(AnActionEvent e) {

        List<String> functionsName = new ArrayList<>();
        project = e.getData(PlatformDataKeys.PROJECT);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        //验空
        if (project == null || editor == null) {
            Utils.showErrorNotification(project, Constants.Message.ERROR_FILE_NULL);
            return;
        }

        //如果是js文件，就去读取wxml文件；如果是wxml文件，就读取方法，写入的js文件

        PsiFile currentFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        if (currentFile == null) {
            Utils.showErrorNotification(project, Constants.Message.ERROR_FILE_NULL);
            return;
        }

        PsiFile wxmlFile;
        PsiFile jsFile;
        if (currentFile.getName().endsWith("wxml")) {
            wxmlFile = currentFile;
            String fileName = wxmlFile.getName().replace("wxml", "js");
            jsFile = getPsiFileByName(project, fileName);

            createFuncToJs(functionsName, wxmlFile, jsFile);

        } else if (currentFile.getName().endsWith("js")) {
            jsFile = currentFile;
            String wxmlFileName = jsFile.getName().replace("js", "wxml");
            wxmlFile = getPsiFileByName(project, wxmlFileName);

            if (wxmlFile == null) {
                return;
            }

            createFuncToJs(functionsName, wxmlFile, jsFile);
        } else {
            Utils.showErrorNotification(project, Constants.Message.ERROR_FILE_NOT_SUPPORT);
            return;
        }
    }

    private void createFuncToJs(List<String> functionsName, PsiFile wxmlFile, PsiFile jsFile) {
        String wxmlContent = wxmlFile.getText();

        Pattern re = Utils.getRegexPattern();
        Matcher matcher = re.matcher(wxmlContent);

        List<String> tempList = new ArrayList<>();
        while (matcher.find()) {
            tempList.add(matcher.group());
        }

        for (String s : tempList) {
            String quotes = "";
            if (s.contains("\"")) {
                quotes = "\"";
            } else if (s.contains("\'")) {
                quotes = "\'";
            }
            if (s.equals("") || s.split(quotes).length < 2) {
                continue;
            }
            functionsName.add(s.split(quotes)[1]);
        }


        new Writer(jsFile, functionsName,project).execute();
    }


    private PsiFile getPsiFileByName(Project project, String wxmlFileName) {
        PsiFile[] wxmlFiles = FilenameIndex.getFilesByName(project, wxmlFileName, GlobalSearchScope.projectScope(project));
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
