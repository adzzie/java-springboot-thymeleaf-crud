package com.test.demo.controller;

import com.test.demo.dao.UsersDao;
import com.test.demo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Description UsersController
 *
 * @author aji gojali
 */
@Controller @RequestMapping(value = {"/user",""})
public class UsersController {

    @Autowired
    private UsersDao ud;

    @GetMapping(value = {"/",""})
    public String list(ModelMap mm, @RequestParam(required = false, value = "search") String search, @PageableDefault Pageable pageable){
        Page<Users> datas;
        if (StringUtils.hasText(search) && search != null){
            datas = ud.searchAll(search,pageable);
        } else {
            datas = ud.findAll(pageable);
        }
        mm.addAttribute("datas",datas);
        return "master/user/list";
    }

    @GetMapping("/form")
    public String form(@RequestParam(value = "id", required = false) String id, ModelMap mm){
        Optional<Users> data = Optional.ofNullable(null);
        if (StringUtils.hasText(id) && id != null){
            data = ud.findById(id);
        }
        mm.addAttribute("data",data);
        return "master/user/form";
    }

    @PostMapping("/save")
    public String save(ModelMap mm, @Valid Users users, BindingResult bind, RedirectAttributes ra){
        if (bind.hasErrors()){
            users.setPassword("");
            mm.addAttribute("data",users);
            mm.addAttribute("error", "Error save data!");
            return "master/user/form";
        }
        ud.save(users);
        ra.addFlashAttribute("success","Success saved data!");
        return "redirect:/user";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam String id, RedirectAttributes ra){
        try {
            ud.deleteById(id);
            ra.addFlashAttribute("success","Success deleted data!");
        }catch (Exception e){
            ra.addFlashAttribute("error","Error : "+e.getMessage());
        }

        return "redirect:/user";
    }
}
