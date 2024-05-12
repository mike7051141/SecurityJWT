package com.springboot.securityjwt.service.Impl;

import com.springboot.securityjwt.dto.CommonResponse;
import com.springboot.securityjwt.dto.SignDto.SignInResultDto;
import com.springboot.securityjwt.dto.SignDto.SignUpDto;
import com.springboot.securityjwt.dto.SignDto.SignUpResultDto;
import com.springboot.securityjwt.entity.User;
import com.springboot.securityjwt.jwt.JwtTokenProvider;
import com.springboot.securityjwt.repository.UserRepository;
import com.springboot.securityjwt.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SignServiceImpl implements SignService {
    private Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);

    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public SignServiceImpl(UserRepository userRepository , JwtTokenProvider jwtTokenProvider,
                           PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public SignUpResultDto SignUp(SignUpDto signUpDto, String roles){

        User user;
        if(roles.equalsIgnoreCase("admin")){
            user = User.builder()
                    .uid(signUpDto.getUid())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .name(signUpDto.getName())
                    .number(signUpDto.getNumber())
                    .address(signUpDto.getAddress())
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        }else{
            user = User.builder()
                    .uid(signUpDto.getUid())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .name(signUpDto.getName())
                    .number(signUpDto.getNumber())
                    .address(signUpDto.getAddress())
                    .roles(Collections.singletonList("ROLE_babyLion"))
                    .build();
        }

        User savedUser = userRepository.save(user);

        SignUpResultDto signUpResultDto = new SignUpResultDto();
        logger.info("[getSignResultDto] user 정보 들어왔는지 확인 후 결과값 주입");

        if(!savedUser.getUid().isEmpty()){
            setSuccess(signUpResultDto);
        }else{
            setFail(signUpResultDto);
        }

        return signUpResultDto;

    }

    @Override
    public SignInResultDto SignIn(String uid, String password)throws RuntimeException{
        User user = userRepository.getByUid(uid);
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException();
        }
        logger.info("[getSignInResult] 패스워드 일치");

        logger.info("[getSignInResult] SignInResultDto 객체 생성");
        SignInResultDto signInResultDto = SignInResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUid()),
                        user.getRoles()))
                .build();
        logger.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSuccess(signInResultDto);
        return signInResultDto;
    }

    private void setSuccess(SignUpResultDto result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFail(SignUpResultDto result){
        result.setSuccess(true);
        result.setCode(CommonResponse.Fail.getCode());
        result.setMsg(CommonResponse.Fail.getMsg());

    }

}