package com.clovi.support.auth;

import com.clovi.domain.user.Member;
import com.clovi.service.auth.PrincipalDetails;
import lombok.Getter;

import java.util.Map;

@Getter
public class MemberAdapter extends PrincipalDetails{

    private Member member;
    private Map<String, Object> attributes;

    public MemberAdapter(Member member){
        super(member);
        this.member = member;
    }
}