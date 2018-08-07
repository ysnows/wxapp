package com.ysnows.wxapp;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.progress.util.ProgressWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SyncTs extends AnAction {

    private ProgressWindow progressWindow;

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Project project = e.getProject();
        progressWindow = new ProgressWindow(false, project);
        progressWindow.setTitle("正在下载wx.d.ts...");
        progressWindow.start();


        new WriteCommandAction.Simple(project) {

            @Override
            protected void run() throws Throwable {
                String urlStr = "https://gitee.com/ysnow/wechat_small_program_api/raw/master/wx.d.ts";

//                ArrayList<String> urls = new ArrayList<>();
//                urls.add("https://raw.githubusercontent.com/qiu8310/minapp/master/packages/minapp-wx/typing/wx.d.ts");
//                urls.add("https://raw.githubusercontent.com/qiu8310/minapp/master/packages/minapp-wx/typing/page.d.ts");
//                urls.add("https://raw.githubusercontent.com/qiu8310/minapp/master/packages/minapp-wx/typing/component.d.ts");
//                urls.add("https://raw.githubusercontent.com/qiu8310/minapp/master/packages/minapp-wx/typing/behavior.d.ts");
//                urls.add("https://raw.githubusercontent.com/qiu8310/minapp/master/packages/minapp-wx/typing/app.d.ts");
//
//                for (String url : urls) {
//                    download(url);
//                }

                download(urlStr);

                Utils.showNotification(project, Constants.Message.MESSAGE_DOWNLOAD_SUCCESS, MessageType.INFO);
                progressWindow.stop();
            }

            private void download(String urlStr) {
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(3 * 1000);
                    InputStream inputStream = conn.getInputStream();
                    byte[] getData = Utils.readInputStream(inputStream);

                    VirtualFile baseDir = this.getProject().getBaseDir();

                    VirtualFile typings = baseDir.findChild("typings");
                    if (typings == null) {
                        typings = baseDir.createChildDirectory(null, "typings");
                    }

                    String name = urlStr.substring(urlStr.lastIndexOf("/") + 1);

                    VirtualFile wxdts = typings.findChild(name);
                    if (wxdts != null) {
                        wxdts.delete(null);
                    }
                    VirtualFile childData = typings.createChildData(null, name);
                    childData.setBinaryContent(getData);

//                    String basePath = e.getProject().getBasePath();
//                    File file = new File(basePath + File.separator + "wx.d.ts");
//                    FileOutputStream fos = new FileOutputStream(file);
//                    fos.write(getData);
//                    fos.close();
//
//                    inputStream.close();


                } catch (IOException ex) {
                    ex.printStackTrace();
                    progressWindow.stop();

                }
            }
        }.execute();

    }
}
