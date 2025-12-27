package com.university.core.service;

import com.university.core.utils.OTPGenerator;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    // email -> otp
    private final Map<String, String> otpCache = new ConcurrentHashMap<>();

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void generateAndSendOTP(String email) {
        String otp = OTPGenerator.generateOTP();
        otpCache.put(email, otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP for registration");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);
    }

    public boolean validateOTP(String email, String otp) {
        String cached = otpCache.get(email);
        if (cached != null && cached.equals(otp)) {
            otpCache.remove(email); // one-time use
            return true;
        }
        return false;
    }
}
