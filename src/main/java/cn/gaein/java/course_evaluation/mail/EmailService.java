package cn.gaein.java.course_evaluation.mail;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
