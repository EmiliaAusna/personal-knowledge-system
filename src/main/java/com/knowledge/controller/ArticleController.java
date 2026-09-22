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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String list(@RequestParam(required = false) Integer categoryId, Model model, HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        List<Article> articleList;
        if(categoryId != null){
            articleList = articleService.findByCategoryId(categoryId);
        }else{
            articleList = articleService.findAll();
        }
        // 登录用户：自己的文章排在最前面，方便区分"我的"和"其他人的"
        if(loginUser != null){
            final Integer userId = loginUser.getUserId();
            articleList.sort((a,b) -> {
                boolean aMine = userId.equals(a.getUserId());
                boolean bMine = userId.equals(b.getUserId());
                return aMine == bMine ? 0 : (aMine ? -1 : 1);
            });
        }
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("articleList",articleList);
        model.addAttribute("categoryList",categoryList);
        return "index";
    }

    @GetMapping("/detail")
    public String detail(Integer articleId, Model model, HttpSession session){
        Article article = articleService.findById(articleId);
        if(article == null){
            return "redirect:/article/list";
        }
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
    public String delete(Integer articleId, HttpSession session, RedirectAttributes redirectAttributes){
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser == null){
            return "redirect:/user/toLogin";
        }
        Article article = articleService.findById(articleId);
        if(article == null || !article.getUserId().equals(loginUser.getUserId())){
            redirectAttributes.addFlashAttribute("msg","无权删除该文章");
            return "redirect:/article/list";
        }
        articleService.delete(articleId);
        return "redirect:/article/list";
    }
}
