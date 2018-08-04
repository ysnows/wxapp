package com.ysnows.wxapp;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.util.ProgressWindow;
import com.intellij.openapi.ui.MessageType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SyncTs extends AnAction {

    private ProgressWindow progressWindow;

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here

        progressWindow = new ProgressWindow(false, e.getProject());
        progressWindow.setTitle("正在下载wx.d.ts...");
        progressWindow.start();

        new Thread(() -> {

            try {
                URL url = new URL("https://gitee.com/ysnow/wechat_small_program_api/raw/master/wx.d.ts");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3 * 1000);
                InputStream inputStream = conn.getInputStream();
                byte[] getData = Utils.readInputStream(inputStream);


                String basePath = e.getProject().getBasePath();
                File file = new File(basePath + File.separator + "wx.d.ts");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(getData);
                fos.close();

                inputStream.close();

                Utils.showNotification(e.getProject(), Constants.Message.MESSAGE_DOWNLOAD_SUCCESS, MessageType.INFO);
                progressWindow.stop();
            } catch (IOException ex) {
                ex.printStackTrace();
                progressWindow.stop();

            }

        }).start();
    }
}
