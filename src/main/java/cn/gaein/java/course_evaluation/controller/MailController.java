package cn.gaein.java.course_evaluation.controller;

import javax.servlet.http.HttpSession;

import cn.gaein.java.course_evaluation.mail.EmailServiceImpl;
import cn.gaein.java.course_evaluation.param.MailParam;
import cn.gaein.java.course_evaluation.responseHelper.Response;
import cn.gaein.java.course_evaluation.utils.VerifyCodeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

    private final EmailServiceImpl emailService;

    private static final String MAIL_SUBJECT = "Course Evaluation Register";

    private static final String MAIL_TEXT_TEMPLATE = "%s is your registration verification code.";

    @Autowired
    public MailController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @PostMapping("")
    public Response sendMail(HttpSession session, @RequestBody MailParam param) {
        String to = param.getReceive();
        String verifyCode = VerifyCodeUtil.generateVerifyCode(6);
        session.setAttribute("verifyCode", verifyCode);
        String mailText = String.format(MAIL_TEXT_TEMPLATE, verifyCode);
        try {
            emailService.sendSimpleMessage(to, MAIL_SUBJECT, mailText);
        } catch (MailException e) {
            return Response.internalServerError(e.getMessage());
        }
        return Response.success("Mail sent");
    }
}
