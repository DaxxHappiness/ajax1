package org.zerock.ajax1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ajax1.entity.Memo;
import org.zerock.ajax1.repository.MemoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class Ajax1ApplicationTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    void contextLoads() {
        System.out.println("Test");
    }

    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample... "+i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect(){
        Long mno = 101L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("=====================================");

        if (result.isPresent()){
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();

        memoRepository.save(memo);
    }

//    @Test
//    public void testDelete() {
//        Long mno = 100L;
//        memoRepository.deleteById(mno);
//    }

    @Test
    public void testPageDefault() {
        Sort sort1 = Sort.by("mno").descending();
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2);

        Pageable pageable = PageRequest.of(0, 10, sortAll);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println(result);
        System.out.println("--------------------");
        System.out.println("Total Pages: " + result.getTotalPages());
        System.out.println("Total Count: " + result.getTotalElements());
        System.out.println("Page Number: " + result.getNumber());
        System.out.println("Page Size: " + result.getSize());
        System.out.println("has next page?: " + result.hasNext());
        System.out.println("first page?: " + result.isFirst());
        System.out.println("--------------------");
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethods() {
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodWithPageable() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);
//        result.get().forEach(memo -> System.out.println(memo));
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }
    
    @Test
    public void testShortCut() {
        // shift + enter : 새 줄에서 시작
        // ctrl + shift + enter : 세미콜론 붙여줌
        Memo memo = new Memo();

        // sout : System.out.println();
        // iter : 향상된 for 문 자동 생성
        // ifn : if 문 null 조건
        // inn : if 문 not null 조건
    }
    @Commit  // 적지 않으면 데이터베이스에 해당 쿼리문 적용되지 않음!!
    @Transactional
    @Test
    public void testDeleteQueryMethods() {
        memoRepository.deleteMemoByMnoLessThan(10L);
    }

    @Test
    public void testJPQL1() {
        List<Memo> list = memoRepository.getListDesc();
        list.stream().forEach(m-> System.out.println(m));
    }

//    @Commit

    @Test
    public void testJPQL2() {
        memoRepository.updateMemoText(3L, "0915 update text");
    }

}
