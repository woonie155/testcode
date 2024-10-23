package com.example.demo.spring;

import com.example.demo.spring.api.client.mail.MailSendClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

/**
 * 비용절감: 동일한 환경 (단일 스프링부트) 에서 테스트들을 실행하도록 한다.
 */
@ActiveProfiles("test")
@SpringBootTest
public abstract class IntegrationTestSupport {

    //이 또한 환경이 달라지므로 스프링부트를 다시 띄우게 되는 것을 방지
    @MockBean protected MailSendClient mailSendClient;
}
