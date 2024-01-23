package com.wb.jpa.bookmanager.domain.listener;

import com.wb.jpa.bookmanager.domain.Member;
import com.wb.jpa.bookmanager.domain.MemberHistory;
import com.wb.jpa.bookmanager.repository.MemberHistoryRepository;
import com.wb.jpa.bookmanager.support.BeanUtils;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class MemberEntityListener {

    @PrePersist
    @PreUpdate
    public void prePersistAndPreUpdate(Object o){
        MemberHistoryRepository memberHistoryRepository = BeanUtils.getBean(MemberHistoryRepository.class);

        Member member = (Member) o;

        MemberHistory memberHistory = new MemberHistory();
        memberHistory.setMemberId(member.getId());
        memberHistory.setName(member.getName());
        memberHistory.setEmail(member.getEmail());

        memberHistoryRepository.save(memberHistory);
    }
}
