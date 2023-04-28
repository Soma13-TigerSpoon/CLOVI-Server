package com.clovi.app.member.service;

import com.clovi.app.auth.domain.RegisterKey;
import com.clovi.app.channel.domain.Channel;
import com.clovi.app.member.repository.MemberRepository;
import com.clovi.app.member.domain.Member;
import com.clovi.app.member.dto.request.MemberCreateRequest;
import com.clovi.app.member.dto.response.MemberResponse;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.exception.member.DuplicateMemberIdException;
import com.clovi.exception.member.MemberNotFoundException;
import com.clovi.app.channel.repository.ChannelRepository;
import com.clovi.app.auth.repository.RegisterKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  private final RegisterKeyRepository registerKeyRepository;
  private final ChannelRepository channelRepository;
  @Transactional
  public Long register(MemberCreateRequest memberCreateRequest) {
    //회원가입 키 있는지 검사
    RegisterKey registerKey = registerKeyRepository.findBySecretKeyAndDeletedIsFalse(memberCreateRequest.getSecretKey()).orElseThrow(() -> new ResourceNotFoundException("회원가입 키","회원가입"));

    // 회원 아이디 중복 검사
    if(isDuplicateMemberId(memberCreateRequest.getMemberId())){
      throw new DuplicateMemberIdException();
    }

    Channel channel = channelRepository.findById(registerKey.getChannelId()).orElseThrow(() -> new ResourceNotFoundException("channel",registerKey.getChannelId()));

    Member member = new Member(memberCreateRequest,channel);

    member.setEncodedPassword(passwordEncoder.encode(memberCreateRequest.getPassword()));

    Member savedMember = memberRepository.save(member);
    registerKey.useBy(savedMember.getId());
    registerKeyRepository.save(registerKey);
    return savedMember.getId();
  }

  public MemberResponse findById(Long findMemberId) {
    Member member = memberRepository.findById(findMemberId).orElseThrow(() -> new MemberNotFoundException(findMemberId));
    return new MemberResponse(member);
  }

  private boolean isDuplicateMemberId(String memberId) {
    return memberRepository.existsByMemberId(memberId);
  }

}