package cn.gaein.java.course_evaluation.utils;

import java.util.Random;

public class VerifyCodeUtil {

    private static final Random random = new Random();

    public static String generateVerifyCode(int length) {
        var sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
