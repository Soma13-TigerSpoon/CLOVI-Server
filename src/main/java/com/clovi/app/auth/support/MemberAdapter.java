package com.clovi.app.auth.support;

import com.clovi.app.member.Member;
import com.clovi.app.auth.service.PrincipalDetails;
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