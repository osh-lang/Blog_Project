package org.project.common.response;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

/**
 * 각 socialOauth에서 SocialLoginType를 받음
 * SocialLoginType를 변압기를 사용하여 String으로 변환해줌
 * 받은 소문자 타입을 대문자로 바꿔준다
 */


@Configuration
public class SocialLoginTypeConverter implements Converter<String, SocialLoginType> {
    @Override
    public SocialLoginType convert(String s) {
        return SocialLoginType.valueOf(s.toUpperCase());
    }
}
