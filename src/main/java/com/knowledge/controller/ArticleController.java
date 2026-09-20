package com.knowledge.controller;
import com.knowledge.entity.Article;
import com.knowledge.entity.Category;
import com.knowledge.entity.User;
import com.knowledge.service.ArticleService;
import com.knowledge.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) Integer categoryId, Model model){
        List<Article> articleList;
        if(categoryId != null){
            articleList = articleService.findByCategoryId(categoryId);
        }else{
            articleList = articleService.findAll();
        }
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("articleList",articleList);
        model.addAttribute("categoryList",categoryList);
        return "index";
    }

    @GetMapping("/detail")
    public String detail(Integer articleId, Model model){
        Article article = articleService.findById(articleId);
        model.addAttribute("article",article);
        return "detail";
    }

    @GetMapping("/toAdd")
    public String toAdd(Model model, HttpSession session){
        if(session.getAttribute("loginUser") == null){
            return "redirect:/user/toLogin";
        }
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "addArticle";
    }

    @PostMapping("/add")
    public String add(Article article, HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser == null){
            return "redirect:/user/toLogin";
        }
        article.setUserId(loginUser.getUserId());
        articleService.add(article);
        return "redirect:/article/list";
    }

    @GetMapping("/delete")
    public String delete(Integer articleId){
        articleService.delete(articleId);
        return "redirect:/article/list";
    }
}
