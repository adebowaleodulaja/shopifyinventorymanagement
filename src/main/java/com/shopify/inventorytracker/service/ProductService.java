package com.shopify.inventorytracker.service;

import com.shopify.inventorytracker.model.DeletedProduct;
import com.shopify.inventorytracker.model.Product;
import com.shopify.inventorytracker.repository.ProductRepository;
import com.shopify.inventorytracker.repository.PurchasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchasesRepository purchasesRepository;

    @Autowired
    private DeletedProductService deletedProductService;

    public String displayAllProducts(Model model) {
        model.addAttribute("listOfProducts", productRepository.findAll());
        return "home";
    }

    public String saveProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        String feedbackMessage = checkProductName(product);
        product.setQuantityLeft(product.getQuantityReceived());

        if (feedbackMessage.contains("Product name")) {
            redirectAttributes.addFlashAttribute("error", feedbackMessage);
            return "redirect:/addnewproduct";
        }

        //if (product != null)
        productRepository.save(product);
        redirectAttributes.addFlashAttribute("success", feedbackMessage);

        return "redirect:/addnewproduct";

    }

    public String getProductToUpdate(long id, Model model) {
        Optional<Product> findProduct = productRepository.findById(id);
        if (findProduct.isPresent()) {
            Product product = findProduct.get();
            model.addAttribute("updateproduct", product);
            return "updateproduct";
        }

        return "";
    }

    public String getProductToDelete(long id, Model model) {
        Optional<Product> findProduct = productRepository.findById(id);
        if (findProduct.isPresent()) {
            Product product = findProduct.get();
            DeletedProduct deletedProduct = new DeletedProduct();
            model.addAttribute("productodelte", product);
            model.addAttribute("deleteproduct", deletedProduct);
            return "deleteproduct";
        }

        return "";
    }

    /*public void saveProduct(Product product) {
        if (product != null)
            productRepository.save(product);
    }*/

    public List<Product> displayListOfProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProduct(Long id) {
        return productRepository.findById(id);
    }


    public void updateQuantityReceived(int qntyreceived, long id) {
        productRepository.updateQuantityReceived(qntyreceived, id);
    }

    public void updateQuantityLeft(int qntyleft, long id) {
        productRepository.updateQuantityLeft(qntyleft, id);
    }

    public void updateUpdateTotalOrder(int quantityOrdered, long productId) {
        productRepository.updateTotalOrder(quantityOrdered, productId);
    }

    public void updateProduct(String snumber, int qreceived, int mtol, String notify, long id) {
        productRepository.updateProduct(snumber, qreceived, mtol, notify, id);
    }

    public void deleteProduct(DeletedProduct deletedProduct, RedirectAttributes redirectAttributes) {
        Optional<Product> getProduct = productRepository.findById(deletedProduct.getId());
        int totalOrdered = 0, quantityLeft = 0;
        if (getProduct.isPresent()) {
            Product product = getProduct.get();
            totalOrdered = product.getTotalOrdered();
            quantityLeft = product.getQuantityLeft();
        }
        purchasesRepository.deleteById(deletedProduct.getId());
        //purchasesService.deletePurchase(deletedProduct.getId());
        productRepository.deleteById(deletedProduct.getId());

        deletedProduct.setTotalOrdered(totalOrdered);
        deletedProduct.setQuantityLeft(quantityLeft);
        deletedProductService.saveDeletedProduct(deletedProduct);
        redirectAttributes.addFlashAttribute("success", "Product has been deleted.");
    }

    public String checkProductName(Product product) {
        String errorMessage = "";
        Product findProductByName = productRepository.findByName(product.getName());

        if (findProductByName == null)
            errorMessage = "New product '".concat(product.getName()).concat("' has been added successfully");
        else if (findProductByName.getName().equalsIgnoreCase(product.getName())) {
            errorMessage = "Product name '".concat(product.getName()).concat("' already exists");
        }

        return errorMessage;
    }
}
