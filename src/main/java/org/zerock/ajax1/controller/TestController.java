package org.zerock.ajax1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.ajax1.entity.Memo;
import org.zerock.ajax1.repository.MemoRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class TestController {

    @Autowired
    MemoRepository memoRepository;

    @GetMapping("/ajax")
    public String ajax1() {
        return "ajaxTest";
    }

    @GetMapping("/ajax2")
    public String ajax2() {
        return "ajaxTest2";
    }

    @GetMapping("/list/{page}")
    public String list(@PathVariable int page, Model model){
        Sort sort1 = Sort.by("mno").descending();
        Pageable pageable = PageRequest.of(page-1, 10, sort1);
        Page<Memo> list = memoRepository.findAll(pageable);
        model.addAttribute("list", list.getContent());
        return "page";
//        return list;
    }

    @GetMapping("/update/{mno}/{memoText}")
    @ResponseBody
    public Memo update1(@PathVariable Long mno, @PathVariable String memoText, Model model) {
        memoRepository.updateMemoText(mno, memoText);
        Optional<Memo> memo = memoRepository.findById(mno);
        return memo.get();
    }

}
