<%@ page contentType="text/html;charset=gb2312" language="java" import="com.jspsmart.upload.*"%>
<%@ page import="com.jspsmart.upload.*"%>
<%@ page import="java.util.*"%>
<%
String path = request.getContextPath();
%>
<%
request.setCharacterEncoding("utf-8"); 
response.setCharacterEncoding("utf-8"); 
%>
<%
    
    String newFile1Name=null;
    String file_name=null;
    
	SmartUpload mySmartUpload = new SmartUpload();

	//初始化上传
	mySmartUpload.initialize(pageContext);

	//只允许上载此类文件
	try 
	{
		mySmartUpload.setAllowedFilesList("jpg,png,jpeg,gif");
		mySmartUpload.upload();
	} 
	catch (Exception e)
    {
		out.println("<script language=javascript>alert('上传格式错误！');   history.back(-1);</script>");
		return;
	}
	
	try 
	{
		   com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(0);
		   if (myFile.isMissing())
		   {
			  out.println("<script language=javascript>alert('必须选择文件！');   history.back(-1);</script>");
			  return;

		   } 
		   else 
		   {
			   int file_size = myFile.getSize(); //取得文件的大小 (单位是b) 
			   file_name=myFile.getFileName();
			  // System.out.println("文件大小："+file_size+"文件名称："+file_name);
			   //if (file_size > 10*1024*1024)
			   //{
				  //out.println("<script language=javascript>alert('上传图片大小应控制在10K~1M之间！');   history.back(-1);</script>");
				  //return;
			   //}
               //else
               //{
                   newFile1Name=new Date().getTime()+file_name.substring(file_name.indexOf("."));
                   //newFile1Name=file_name;
	               //System.out.println("新文件名称："+newFile1Name);
				
				   String saveurl = request.getSession().getServletContext().getRealPath("upload");
				
				   saveurl = saveurl+"/"+newFile1Name;
				   //System.out.println("saveurl==="+saveurl);
				   myFile.saveAs(saveurl, mySmartUpload.SAVE_PHYSICAL);
	
              // }
			} 
	  } 
	  catch (Exception e)
	  {
	    e.toString();
	  }
%>
<script type="text/javascript" src="/onlineshopssm/layer/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/onlineshopssm/layer/layer.js"></script>
<script language="javascript">

    var str=location.toString()
    var Result=((((str.split('?'))[1]).split('='))[1]);
	//window.opener.Form1(Result).focus();	
	//window.parent.document.getElementById(Result).value="upload/<%= newFile1Name%>";	
	window.parent.document.getElementById(Result).value="<%= newFile1Name%>";					

    
	//window.opener=null;
	//document.write("上传成功");
    //window.close();
   parent.layer.closeAll();

</script> 