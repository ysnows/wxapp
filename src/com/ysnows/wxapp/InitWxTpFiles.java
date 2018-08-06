package com.ysnows.wxapp;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

public class InitWxTpFiles extends AnAction {
    String ajsText = "// pages/a/a.js\n" +
            "Page({\n" +
            "\n" +
            "  /**\n" +
            "   * 页面的初始数据\n" +
            "   */\n" +
            "  data: {\n" +
            "  \n" +
            "  },\n" +
            "\n" +
            "  /**\n" +
            "   * 生命周期函数--监听页面加载\n" +
            "   */\n" +
            "  onLoad: function (options) {\n" +
            "  \n" +
            "  },\n" +
            "\n" +
            "  /**\n" +
            "   * 生命周期函数--监听页面初次渲染完成\n" +
            "   */\n" +
            "  onReady: function () {\n" +
            "  \n" +
            "  },\n" +
            "\n" +
            "  /**\n" +
            "   * 生命周期函数--监听页面显示\n" +
            "   */\n" +
            "  onShow: function () {\n" +
            "  \n" +
            "  },\n" +
            "\n" +
            "  /**\n" +
            "   * 生命周期函数--监听页面隐藏\n" +
            "   */\n" +
            "  onHide: function () {\n" +
            "  \n" +
            "  },\n" +
            "\n" +
            "  /**\n" +
            "   * 生命周期函数--监听页面卸载\n" +
            "   */\n" +
            "  onUnload: function () {\n" +
            "  \n" +
            "  },\n" +
            "\n" +
            "  /**\n" +
            "   * 页面相关事件处理函数--监听用户下拉动作\n" +
            "   */\n" +
            "  onPullDownRefresh: function () {\n" +
            "  \n" +
            "  },\n" +
            "\n" +
            "  /**\n" +
            "   * 页面上拉触底事件的处理函数\n" +
            "   */\n" +
            "  onReachBottom: function () {\n" +
            "  \n" +
            "  },\n" +
            "\n" +
            "  /**\n" +
            "   * 用户点击右上角分享\n" +
            "   */\n" +
            "  onShareAppMessage: function () {\n" +
            "  \n" +
            "  }\n" +
            "})";

    private String awxmlText = "<!--pages/a/a.wxml-->\n" +
            "<text>pages/a/a.wxml</text>\n";

    private String awxssText = "/* pages/a/a.wxss */";
    private String ajsonText = "{}";


    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here

        Project project = e.getProject();

        VirtualFile baseDir = project.getBaseDir();

        VirtualFile wxtp = baseDir.findChild("wxtp");

        if (wxtp != null) {
            Utils.showErrorNotification(project, "模板文件已经存在");
            return;
        }

        new WriteCommandAction.Simple(project) {

            @Override
            protected void run() throws Throwable {
                try {
                    VirtualFile childDirectory = baseDir.createChildDirectory(null, "wxtp");
                    VirtualFile ajs = childDirectory.createChildData(null, "wxtp.js");
                    ajs.setBinaryContent(ajsText.getBytes());

                    VirtualFile awxml = childDirectory.createChildData(null, "wxtp.wxml");
                    awxml.setBinaryContent(awxmlText.getBytes());

                    VirtualFile awxss = childDirectory.createChildData(null, "wxtp.wxss");
                    awxss.setBinaryContent(awxssText.getBytes());

                    VirtualFile ajson = childDirectory.createChildData(null, "wxtp.json");
                    ajson.setBinaryContent(ajsonText.getBytes());


                    Utils.showNotification(project,Constants.Message.MESSAGE_INIT_TP_SUCCESSFULLY, MessageType.INFO);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }.execute();


    }
}
