package com.catalogue.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.catalogue.dao.ProductRepository;
import com.catalogue.entitie.Product;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping(path="/products")
	public String index(Model model, @RequestParam(name="motCle", defaultValue="")String motCle,
									 @RequestParam(name="page", defaultValue="0")int page,
									 @RequestParam(name="size", defaultValue="4")int size) {
		//Page<Product> pageProduct = productRepository.findAll(PageRequest.of(page, size));
		Page<Product> pageProduct = productRepository.findByDesignationContains(motCle, 
				PageRequest.of(page, size));
		model.addAttribute("pageProducts", pageProduct);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("motCle", motCle);
		model.addAttribute("pages",new int[pageProduct.getTotalPages()]);
		model.addAttribute("prd", new Product());
		return "products";
	}
	
	@GetMapping(path="/delete")
	public String Delete(long id, String motCle, int page, int size) {
		productRepository.deleteById(id);
		return "redirect:/products?motCle="+motCle+"&page="+page+"&size="+size;
	}
	
	@GetMapping(path="/update-form")
	public String Updateform(Model model, @RequestParam(name="id", defaultValue="") long id) {
		System.out.println("ID : "+id);
		model.addAttribute("ID_Upd", productRepository.findById(id).get());
		return "productUpdate";
	}
	
	@PostMapping(path="/updating")
	public String Update(@ModelAttribute("ID_Upd") Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }
		
	@PostMapping(path="/save")
	public String save(@ModelAttribute("prd") Product prod)
	{
		if(!prod.equals(null)) {
			productRepository.save(prod);
			return "redirect:/products";
		}
		return "redirect:/error_rise";
	}
}


