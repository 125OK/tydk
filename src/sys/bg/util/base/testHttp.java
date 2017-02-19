package sys.bg.util.base;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class testHttp {
	
	//ʹ��StringBuffer��append���xml��ʽ���ַ�
	StringBuffer sub=new StringBuffer();
	BufferedReader br=null;
	URL url=null;
	HttpURLConnection con;
	String line;
	
	public static void main(String[] args) {
		
		xmlEntity xmlentity=new xmlEntity();
		String xml=null;
		testHttp t=new testHttp();
		
/*		//��ѯ���÷���
		xml=t.SelSum("1111", "xm000035", "xm000035234").toString();
		//���xml��ʽ���ַ�
		System.out.println("���xml"+xml);

		//��ֵ��xmlEntityʵ����
		xmlentity.setReturnstatus("returnstatus");
		xmlentity.setMessage("message");
		xmlentity.setPayinfo("payinfo");
		xmlentity.setOverage("overage");
		xmlentity.setSendTotal("sendTotal");
		//����XML�ַ����ͨ�÷���
		xmlentity=t.readStringXmlCommen(xmlentity, xml);
		System.out.println("����״̬Ϊ:"+xmlentity.getReturnstatus()+"\n"+"���ظ��ѷ�ʽ:"+xmlentity.getPayinfo());*/
		
//	    int zong=Integer.parseInt(xmlentity.getSendTotal());
//	    int yong=Integer.parseInt(xmlentity.getOverage());
//	    int sheng=zong-yong;
//	    System.out.println("������Ϊ��"+zong);
//	    System.out.println("��ʹ�ã�"+yong);
//	    System.out.println("ʣ�ࣺ��"+sheng);
	    
	    //���͵���
		xml=t.SendMessage("1111","xm000035", "xm000035234","15026516841", "�����֤�룺1439���ڷɡ�", "").toString();
        System.out.println(xml);
        xmlentity.setReturnstatus("returnstatus");
        xmlentity.setMessage("message");
        xmlentity.setRemainpoint("remainpoint");
        xmlentity.setTaskID("taskID");
        xmlentity.setSuccessCounts("successCounts");
        xmlentity=t.readStringXmlCommen(xmlentity, xml);
        System.out.println("״̬"+xmlentity.getReturnstatus()+"������Ϣ"+xmlentity.getMessage()+"�ɹ�����"+xmlentity.getSuccessCounts());
       	
		
		//״̬����
//		xml=t.StatusReport("1111", "qqqq", "mima").toString();
//		System.out.println(xml);
//		xmlentity.setStatusbox("statusbox");
//		xmlentity.setMobile("mobile");
//		xmlentity.setTaskid("taskid");
//		xmlentity.setStatus("status");
//		xmlentity.setReceivetime("receivetime");
//		xmlentity.setErrorstatus("errorstatus");
//		xmlentity.setError("error");
//		xmlentity.setRemark("remark");
//		xmlentity=t.readStringXmlCommen(xmlentity, xml);
//		System.out.println("��Ӧ�ֻ�ţ�"+xmlentity.getMobile()+"��Ӧ״̬"+xmlentity.getStatus()+"��Ӧ����ʱ��"+xmlentity.getReceivetime());
//		System.out.println("������룺"+xmlentity.getError());
	    
	}
	
	
	//��ѯ���
	public StringBuffer SelSum(String userid,String account,String password) 
	{
		try {
			url=new URL("http://114.113.154.5/sms.aspx?action=overage&userid="+userid+"&account="+account+"&password="+password+"");	
			con = (HttpURLConnection)url.openConnection();
			//br=new BufferedReader(new InputStreamReader(url.openStream()));
		    br=new  BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		     
		    while((line=br.readLine())!=null)
		    {
		    	//׷���ַ���XML��ʽ���ַ�
		    	sub.append(line+"");
		    	//System.out.println("��ȡ��� :  "+line);
		    }
		    br.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			return sub;
		}
	    
	}

	
	//���Ͷ���
	public StringBuffer SendMessage(String userid,String account,String password,String mobile,String content,String sendTime)
	{
		
		try {
			//���÷������ݵı��뷽ʽ
			String send_content=URLEncoder.encode(content.replaceAll("<br/>", " "), "UTF-8");//��������
			
			url=new URL("http://114.113.154.5/sms.aspx?action=send&userid="+userid+"&account="+account+"&password="+password+"&mobile="+mobile+"&content="+send_content+"&sendTime="+sendTime+"");
			System.out.println(url.toString());
			con = (HttpURLConnection)url.openConnection();
			
			br=new  BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			//br=new BufferedReader(new InputStreamReader(url.openStream()));
		     
		    while((line=br.readLine())!=null)
		    {
		    	//׷���ַ���XML��ʽ���ַ�
		    	sub.append(line+"");
		    	//System.out.println("��ȡ��� :  "+line);
		    }
		    br.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			return sub;
		}
	}

	//״̬����ӿ�
	public StringBuffer StatusReport(String userid,String account,String password )
	{
		try {
			url=new URL("http://114.113.154.5/statusApi.aspx?action=query&userid="+userid+"&account="+account+"&password="+password+"");	
			con = (HttpURLConnection)url.openConnection();
			
			//br=new BufferedReader(new InputStreamReader(url.openStream()));
		   
			br=new  BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		    
		    while((line=br.readLine())!=null)
		    {
		    	//׷���ַ���XML��ʽ���ַ�
		    	sub.append(line+"");
		    //	System.out.println("��ȡ��� :  "+line);
		    }
		    br.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			return sub;
		}
	}
	
	
	//����xml�ַ�
	public void readStringXml(String xml)
	{
		Document doc=null;
		
		try {
			//���ַ�ת��ΪXML
			doc=DocumentHelper.parseText(xml);
			//��ȡ��ڵ�
			Element rootElt=doc.getRootElement();
			
			//�õ���ڵ�����
			//System.out.println("��ڵ���ƣ�"+rootElt.getName());
			
			//��ȡ��ڵ��µ��ӽڵ��ֵ
			String returnstatus=rootElt.elementText("returnstatus").trim();
			String message=rootElt.elementText("message").trim();
			String payinfo=rootElt.elementText("payinfo").trim();
			String overage=rootElt.elementText("overage").trim();
			String sendTotal=rootElt.elementText("sendTotal").trim();
			
			System.out.println("����״̬Ϊ��"+returnstatus);
			System.out.println("������Ϣ��ʾ��"+message);
			System.out.println("����֧����ʽ��"+payinfo);
			System.out.println("����ʣ���������"+overage);
			System.out.println("����������"+sendTotal);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	//XML�ַ����ͨ�÷���
	public xmlEntity readStringXmlCommen(xmlEntity xmlentity,String xml)
	{
		xmlEntity xe=new xmlEntity();
		
		Document doc=null;
		
		try {
			//���ַ�ת��ΪXML
			doc=DocumentHelper.parseText(xml);
			//��ȡ��ڵ�
			Element rootElt=doc.getRootElement();
			//�õ���ڵ�����
			//System.out.println("��ڵ㣺" + rootElt.getName()); 
			
			//��ȡ��ڵ��µ��ӽڵ��ֵ
			if(xmlentity.getReturnstatus()!=null)
			{
				xe.setReturnstatus(rootElt.elementText(xmlentity.getReturnstatus()).trim());
			}
			if(xmlentity.getMessage()!=null)
			{
				xe.setMessage(rootElt.elementText(xmlentity.getMessage()).trim());
			}
			if(xmlentity.getRemainpoint()!=null)
			{
				xe.setRemainpoint(rootElt.elementText(xmlentity.getRemainpoint()).trim());
			}
			if(xmlentity.getTaskID()!=null)
			{
				xe.setTaskID(rootElt.elementText(xmlentity.getTaskID()).trim());
			}
			if(xmlentity.getSuccessCounts()!=null)
			{
				xe.setSuccessCounts(rootElt.elementText(xmlentity.getSuccessCounts()).trim());
			}
			if(xmlentity.getPayinfo()!=null)
			{
				xe.setPayinfo(rootElt.elementText(xmlentity.getPayinfo()).trim());
			}
			if(xmlentity.getOverage()!=null)
			{
				xe.setOverage(rootElt.elementText(xmlentity.getOverage()).trim());
			}
			if(xmlentity.getSendTotal()!=null)
			{
				xe.setSendTotal(rootElt.elementText(xmlentity.getSendTotal()).trim());
			}
			//����״̬���صı���
			if(rootElt.hasMixedContent()==false)
			{
				System.out.println("�޷���״̬��");
			}
			else
			{
				for (int i = 1; i <= rootElt.elements().size(); i++) {
					if(xmlentity.getStatusbox()!=null)
					{
						System.out.println("״̬"+i+":");
						//��ȡ��ڵ��µ��ӽڵ�statusbox
						 Iterator iter = rootElt.elementIterator(xmlentity.getStatusbox()); 
						// ����statusbox�ڵ� 
						 while(iter.hasNext())
						 {
							 Element recordEle = (Element) iter.next();
							 xe.setMobile(recordEle.elementText("mobile").trim());
							 xe.setTaskid(recordEle.elementText("taskid").trim());
							 xe.setStatus(recordEle.elementText("status").trim());
							 xe.setReceivetime(recordEle.elementText("receivetime").trim());
							 System.out.println("��Ӧ�ֻ�ţ�"+xe.getMobile());
							 System.out.println("ͬһ������ID��"+xe.getTaskid());
							 System.out.println("״̬����----10�����ͳɹ���20������ʧ�ܣ�"+xe.getStatus());
							 System.out.println("����ʱ�䣺"+xe.getReceivetime());
						 }	 
					 }
					
				}

			}
			
			//���󷵻صı���
			if(xmlentity.getErrorstatus()!=null)
			{
				//��ȡ��ڵ��µ��ӽڵ�errorstatus
				 Iterator itererr = rootElt.elementIterator(xmlentity.getErrorstatus()); 
				// ����errorstatus�ڵ�
	            while(itererr.hasNext())
	            {
	            	Element recordElerr = (Element) itererr.next();
	            	xe.setError(recordElerr.elementText("error").trim());
	            	xe.setRemark(recordElerr.elementText("remark").trim());
	            	System.out.println("������룺"+xe.getError());
	            	System.out.println("����������"+xe.getRemark());
	            }
			}
			
//			if(xmlentity.getCallbox()!=null)
//			{
//				//��ȡ��ڵ��µ��ӽڵ�errorstatus
//				Iterator itercallbox = rootElt.elementIterator("errorstatus"); 
//				// ����errorstatus�ڵ�
//				while(itercallbox.hasNext())
//				{
//					Element recordcallbox = (Element) itercallbox.next();
//					String content=recordcallbox.elementText("content").trim();
//					String receivetime=recordcallbox.elementText("receivetime").trim();
//					String mobile=recordcallbox.elementText("mobile").trim();
//					String taskid=recordcallbox.elementText("taskid").trim();
//					
//				}
//			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return xe;
	}
	
	
	
	
	
	
	
	
}
