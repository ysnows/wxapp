package com.ysnows.wxapp;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.ui.awt.RelativePoint;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 工具类，用来写一些公共的方法
 * <p>
 * Created by lypeer on 2016/9/28.
 */
class Utils {

    /**
     * 显示错误通知
     */
    static void showErrorNotification(Project project, String text) {
        showNotification(project, MessageType.ERROR, text);
    }

    static void showNotification(Project project, String text, MessageType type) {
        showNotification(project, type, text);
    }

    private static void showNotification(Project project, MessageType type, String text) {
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);

        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(text, type, null)
                .setFadeoutTime(7500)
                .createBalloon()
                .show(RelativePoint.getCenterOf(statusBar.getComponent()), Balloon.Position.atRight);
    }

    /**
     * 获得匹配属性头的正则表达式
     */
    static Pattern getRegexPattern() {
        List<String> attributesName = Constants.sAttributesName;
        String regex = "";
        for (int i = 0; i < attributesName.size(); i++) {
            if (i != (attributesName.size() - 1)) {
                regex += attributesName.get(i).concat("|");
            } else {
                regex += attributesName.get(i);
            }
        }
        return Pattern.compile("(" + regex + ")[A-Za-z]*=[\\S]*");
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     *
     * @return
     *
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();

    }


    public static PsiFile getPsiFileByNameInSameDir(Project project, String fileName, PsiFile currentFile) {
        VirtualFile virtualFile = currentFile.getVirtualFile();
        VirtualFile child = virtualFile.getParent().findChild(fileName);
        if (child == null) {
            Utils.showErrorNotification(project, Constants.Message.ERROR_NOT_FOUND);
            return null;
        }

        if (!child.exists()) {
            Utils.showErrorNotification(project, Constants.Message.ERROR_NOT_FOUND);
            return null;
        }

        return PsiManager.getInstance(project).findFile(child);

    }

}
