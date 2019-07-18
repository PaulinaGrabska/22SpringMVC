package pl.javastart.productlist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping("/")
    public String home(){
        return "index";
    }


    @PostMapping("/lista")
    public String addProduct (@RequestParam (value = "nazwa")String name,
                              @RequestParam (value = "cena") Double price) {

        Product p = new Product(name,price);
        productRepository.addProduct(p);

       // model.addAttribute("produkt",p);

        return "redirect:/";
    }

    //na stronie /lista będzie wyświetlał listę produktów wraz z sumą ceny
    @GetMapping("/lista")
    public String showList(Model model){

        model.addAttribute("products", productRepository.getProdukty());

        return "list";
    }


    //na stronie /tabela będzie wyświetlał tabelę z wszystkimi produktami wraz z sumą ceny

    @GetMapping("/tabela")
    public String showTable(Model model){

        model.addAttribute("products", productRepository.getProdukty());

        return "table";
    }

}
