package cf.beishan.microposts;

import cf.beishan.microposts.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootTest
class MicropostsApplicationTests {

	@Autowired
	JavaMailSenderImpl mailSender;

	@Test
	void contextLoads() {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setSubject("test");
		message.setText("測試郵箱服務是否正常");
		message.setTo("wangbeishan9527@gmail.com");
		message.setFrom("305304442@qq.com");

		mailSender.send(message);
	}
}
