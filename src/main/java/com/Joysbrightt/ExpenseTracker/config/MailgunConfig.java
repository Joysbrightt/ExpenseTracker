package com.Joysbrightt.ExpenseTracker.config;

import com.mailgun.client.MailgunClient;
import lombok.Data;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MailgunConfig  {

    @Value(staticConstructor = "${mailgun.api.key}")
    private String apikey;


    @Value("${mailgun.domain}")
    private String domain;

@Bean
    public MailgunClient mailgunClient(){
    return new MailgunClient(apikey, domain);
}

}
