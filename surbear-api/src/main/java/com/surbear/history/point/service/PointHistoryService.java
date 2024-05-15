package com.surbear.history.point.service;


import com.surbear.history.point.constant.PaymentType;
import com.surbear.history.point.controller.dto.PointHistoryAdminPage;
import com.surbear.history.point.entity.PointHistoryEntity;
import com.surbear.history.point.mapper.PointHistoryMapper;
import com.surbear.history.point.model.PointHistory;
import com.surbear.history.point.repository.PointHistoryRepository;
import com.surbear.member.entity.MemberEntity;
import com.surbear.member.mapper.MemberMapper;
import com.surbear.member.model.Member;
import com.surbear.member.repository.MemberRepository;
import com.surbear.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PointHistoryService {

    private final PointHistoryMapper pointHistoryMapper;
    private final PointHistoryRepository pointHistoryRepository;
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    @Transactional
    public void cancelPayment(Long adminId, Long pointHistoryId) {
        PointHistoryEntity pointHistoryEntity = pointHistoryRepository.findByIdAndDeletedIsFalse(pointHistoryId);

        Long memberId = pointHistoryEntity.getRecipientId();
        Integer paidPoint = pointHistoryEntity.getPaidPoint();

        MemberEntity finedMember = memberRepository.findByIdAndDeletedIsFalse(memberId);
        finedMember.setPoint(finedMember.getPoint() - paidPoint);

        memberRepository.save(finedMember);

        PointHistory pointHistory = createPointHistoryByCancel(adminId, memberId , paidPoint);
        create(pointHistory);
        deletePointHistory(pointHistoryId);
    }

    @Transactional
    public void pointPayment(Long adminId, String nickname, Integer paidPoint) {
        Member member = memberService.findByNicknameAndDeletedIsFalse(nickname);
        MemberEntity memberEntity = memberMapper.toEntity(member);

        Integer currentPoint = memberEntity.getPoint();
        Integer changedPoint = currentPoint + paidPoint;

        memberEntity.setPoint(changedPoint);
        memberRepository.save(memberEntity);

        PointHistory pointHistory = createPointHistoryByAdmin(adminId, member.id(), paidPoint);
        create(pointHistory);
    }

    @Transactional
    public PointHistory createPointHistoryBySurvey(Long memberId, Integer paidPoint) {
        return PointHistory.builder()
                .payerId(-1L)
                .recipientId(memberId)
                .description("설문조사 참여")
                .paymentType(PaymentType.SURVEY)
                .paidPoint(paidPoint)
                .deleted(false)
                .build();
    }

    @Transactional
    public PointHistory createPointHistoryByAdmin(Long adminId, Long memberId, Integer paidPoint) {
        return PointHistory.builder()
                .payerId(adminId)
                .recipientId(memberId)
                .description("관리자가 지급")
                .paymentType(PaymentType.ADMIN)
                .paidPoint(paidPoint)
                .deleted(false)
                .build();
    }

    @Transactional
    public PointHistory createPointHistoryByCancel(Long adminId, Long memberId, Integer paidPoint) {
        return PointHistory.builder()
                .payerId(adminId)
                .recipientId(memberId)
                .description("관리자가 취소")
                .paymentType(PaymentType.CANCEL)
                .paidPoint(paidPoint)
                .deleted(false)
                .build();
    }

    @Transactional
    public PointHistory createPointHistoryByBuyProduct(Long memberId, Integer paidPoint) {
        return PointHistory.builder()
                .payerId(-10L)
                .recipientId(memberId)
                .description("상품구매")
                .paymentType(PaymentType.BUY_PRODUCT)
                .paidPoint(paidPoint)
                .deleted(false)
                .build();
    }


    public Long create(PointHistory pointHistory) {
        PointHistoryEntity newEntity = pointHistoryMapper.toEntity(pointHistory);

        PointHistoryEntity savedEntity = pointHistoryRepository.save(newEntity);
        return savedEntity.getId();
    }

    public List<PointHistoryAdminPage> getPointHistoryForAdmin() {
        List<PointHistory> pointHistoryList = pointHistoryRepository.findAllByPaymentTypeAndDeletedIsFalse(PaymentType.ADMIN);
        return pointHistoryList.stream()
                .map(pointHistory -> PointHistoryAdminPage.builder()
                        .id(pointHistory.id())
                        .description(pointHistory.description())
                        .payer(memberRepository.findByIdAndDeletedIsFalse(pointHistory.payerId()).getNickname())
                        .recipient(memberRepository.findByIdAndDeletedIsFalse(pointHistory.recipientId()).getNickname())
                        .paidPoint(pointHistory.paidPoint())
                        .paymentType(pointHistory.paymentType())
                        .updatedAt(pointHistory.updatedAt())
                        .deleted(pointHistory.deleted())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public List<PointHistory> getPointHistory(Long memberId) {
        return pointHistoryRepository.findAllByRecipientId(memberId);
    }

    public List<PointHistory> getPointHistory(String nickname) {
        Long id = nicknameToId(nickname);
        return getPointHistory(id);
    }

    private Long nicknameToId(String nickname){
        return memberService.findByNicknameAndDeletedIsFalse(nickname).id();
    }

    private void deletePointHistory(Long pointHistoryId){
        PointHistoryEntity pointHistoryEntity = pointHistoryRepository.findById(pointHistoryId).get();

        pointHistoryEntity.delete();

        pointHistoryRepository.save(pointHistoryEntity);
    }


}
