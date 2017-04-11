package webmvct.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import webmvct.cmd.Productor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/main.xml", "classpath*:/spring-servlet.xml" })   

public class MyJunitTest {
	
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    // 执行测试方法之前初始化模拟request,response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }
    
    @Value("${mq.queue.ip}")
    private String value;
    
    @Test
    public void hello() {
    	System.out.println(value);
    }
    
    
    @Autowired
    private Productor productor;
    
    @Test
    public void test_send() {
    	int num = Integer.MAX_VALUE;
    	while(num-- > 0){
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		productor.sendMessage(num+"");
    	}
    }
    
    @Test
    public void testTopic() throws Exception {
    	 for(int i = 1; i <= 10; i++) {
 	        String str = "hello" + i;
 	        
 	       try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 	       productor.send_Topic_Message(str);
    	 }
    }
    
    
    @Test
    public void testFanout() throws Exception {
    	 for(int i = 1; i <= 10; i++) {
 	        String str = "hello" + i;
 	        
 	       try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 	       productor.send_Fanout_Message(str);
    	 }
    }
    
    
    @Autowired
    private Productor productor2;
    @Test
    public void testHOTEL() throws Exception {
    	
    	new Thread(new Runnable() {
			public void run() {
				 for(int i = 1; i <= 100; i++) {
			 	        String str = "testHOTEL11" + i;
			 	      
			 	       productor.sendMessage_1(str);
			    	 }
			}
		}).start();
    	
    	
    	
    	new Thread(new Runnable() {
			public void run() {
				 for(int i = 1; i <= 100; i++) {
			 	        String str = "testHOTEL22" + i;
			 	        
			 	      
			 	       productor2.sendMessage_2(str);
			    	 }
			}
		}).start();
    }
    
    
    
    
    
    
    /**这个jar只需要测试service，不需要测试controller 且JNDDI...*/
//  @Autowired
//    public CertificateAuthorityController certificateAuthorityController;
//    @Test
//    public void testInsert() {
//        try {
//
//            request.setParameter("userId", "1");
//            request.setParameter("email", "wly@qq.com");
//            request.setParameter("phone", "15511112222");
//            request.setParameter("realName", "");
//            request.setParameter("loginPwd", "123456");
//            request.setParameter("pwdFlag", "9");
//            certificateAuthorityController.insertUserUniformBean(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}