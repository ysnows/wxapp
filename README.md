### 微信小程序WebStorm插件，支持PHPSTORM,WEBSTORM等全部intellij产品


##### 功能

   1. 从`wxml`中生成方法体到`js`中,使用方法：在`js`文件或者`wxml`文件中`cmd+N`在弹出的列表中选择`CreateFuncFromWxml`如图:

   ![](http://ozk223z60.bkt.clouddn.com/15333511826576.jpg?imageView/2/w/375)
     
   ![QQ20180804-104955.mp4](http://ozk223z60.bkt.clouddn.com/QQ20180804-104955.mp4.gif)


   
   2. `wxml`和`js`文件快速切换：快捷键默认`shift+cmd+i`,如果不生效的话，在设置里找到快捷键设置，搜`RelatedWxFiles`:
        ![](http://ozk223z60.bkt.clouddn.com/15333507645739.jpg)
        
  3. 同步`wx.d.ts`文件，用于代码提示，文件内容基于github上的[wx.d.ts](https://github.com/hellopao/wx.d.ts),但是本码农会持续更新,为了访问速度，放到了oschina上[微信小程序API](https://gitee.com/ysnow/wechat_small_program_api);快捷键`ctrl+shift+n`
  
  ![QQ20180806-103511.mp4](http://ozk223z60.bkt.clouddn.com/QQ20180806-103511.mp4.gif)
  
 4. 根据模板创建page，默认根据项目根目录下的`wxtp`文件夹下的文件进行复制创建page,在`pages`目录`右键`->`New`->`Wx Page`->`输入page名称`
    
    ![](http://ozk223z60.bkt.clouddn.com/15335231513256.jpg?imageView/2/w/475)
    
 5. 也可以通过快捷键`shift+ctrl+s`同步插件自带的微信page模板
 6. 通过`ctrl+cmd+i`打开当前目录中的文件
 
    ![](http://ozk223z60.bkt.clouddn.com/15336016155392.jpg)

    
    

#### 安装
1. 从本地安装，[下载地址](https://github.com/ysnows/wxapp/blob/master/wxapp.jar)
2. 从intellij插件仓库安装，搜索 `Wxapp`,或者[到此下载](https://plugins.jetbrains.com/plugin/10991-wxapp)

#### Todo
  1. 后续将不断优化体验以及开发新功能，希望广大码农提出建设性的意见，或者直接贡献代码^^


    
    
#### 参考
    
1. 这款插件是基于[Matchmaker](https://github.com/lypeer/Matchmaker)修改而来，要感谢作者的辛苦劳动


