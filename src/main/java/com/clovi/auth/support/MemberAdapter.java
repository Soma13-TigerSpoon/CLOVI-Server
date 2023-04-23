package com.clovi.auth.support;

import com.clovi.member.Member;
import com.clovi.auth.service.PrincipalDetails;
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