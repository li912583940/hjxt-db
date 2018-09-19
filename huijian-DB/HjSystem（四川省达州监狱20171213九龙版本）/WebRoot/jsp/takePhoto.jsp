<%@ page contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>控件测试页面</title>
        <script type="text/javascript">

            function startCam(){
                var capActivexObject=document.getElementById('cap1');
                //启动摄像头
                capActivexObject.start();
            }

            function capPicture1(){
                var capActivexObject=document.getElementById('cap1');
                capActivexObject.cap(); //控制摄像头拍照
            }
            
            function selectPic(){
                var capActivexObject=document.getElementById('cap1');
                capActivexObject.selectRect(0.3,0.25,0.6,0.8);//具体含义请查看文档
            }
            
            function cutSelectedPic(){
                var capActivexObject=document.getElementById('cap1');
                capActivexObject.cutSelected();
                
            }

            function submitToServer(){
                //读取控件的拍照结果到hidden输入项中
                var base64_data1 = document.getElementById('cap1').jpegBase64Data;
                if (base64_data1.length==0) {
                    alert('请先拍照!');
                    return false;
                }
                document.getElementById('picData1').value=base64_data1;
                document.getElementById('picExt1').value='.jpg';


                /*注意不同的服务器端技术要配置不同的接收数据的url,可以参考submit.html的示
                如asp.net的程序员可以查看submit.aspx，php程序员可以查看submit.php，asp程序员可以查看submit.asp
                */

                //document.forms[0].action="http://localhost:8080/pages/submit.jsp";

                alert('请先打开demo6.html配置服务器端程序参数再继续测试!');
                return false;
                document.forms[0].submit();
            }

        </script>
    </head>
    <body>
        <form  method="post" >
            <input type="hidden" id="picData1" name="picData1"/>
            <input type="hidden" id="picExt1"  name="picExt1"/>
            <input type="hidden" id="picData2" name="picData2"/>
            <input type="hidden" id="picExt2"  name="picExt2"/>
            <p>
                <input type="button" value="启动摄像头" onclick="javascript:startCam();"  />
                <input type="button" value="拍照片" onclick="javascript:capPicture1();"  />
                <input type="button" value="选中头像区域" onclick="javascript:selectPic();"  />
                <input type="button" value="裁剪选中区域" onclick="javascript:cutSelectedPic()"  />
                <input type="button" value="提交到服务器端" onclick="javascript:submitToServer();"  /> <br/>
                <input type="button" value="清除结果" onclick="javascript:document.getElementById('cap1').clear();"  />
            </p>
            <object classid="clsid:34681DB3-58E6-4512-86F2-9477F1A9F3D8" id="cap1" width="200" height="150" codebase="<%=basePath %>/ocx/ImageCapOnWeb.ocx#version=2,0,2,14">
                <param name="Visible" value="0" />
                <param name="AutoScroll" value="0" />
                <param name="AutoSize" value="0" />
                <param name="AxBorderStyle" value="1" />
                <param name="Caption" value="scaner" />
                <param name="Color" value="4278190095" />
                <param name="Font" value="宋体" />
                <param name="KeyPreview" value="0" />
                <param name="PixelsPerInch" value="96" />
                <param name="PrintScale" value="1" />
                <param name="Scaled" value="-1" />
                <param name="DropTarget" value="0" />
                <param name="HelpFile" value />
                <param name="PopupMode" value="0" />
                <param name="ScreenSnap" value="0" />
                <param name="SnapBuffer" value="10" />
                <param name="DockSite" value="0" />
                <param name="DoubleBuffered" value="0" />
                <param name="ParentDoubleBuffered" value="0" />
                <param name="UseDockManager" value="0" />
                <param name="Enabled" value="-1" />
                <param name="AlignWithMargins" value="0" />
                <param name="ParentCustomHint" value="-1" />
                <param name="licenseMode" value="2" />
                <param name="key1" value="" />
                <param name="key2" value="" />
            </object>
        </form>
        <script type="text/javascript">
            document.all.cap1.SwitchWatchOnly();  //切换到只显示摄像头画面形式，隐藏编辑按钮等图标.
        </script>
    </body>
</html>

