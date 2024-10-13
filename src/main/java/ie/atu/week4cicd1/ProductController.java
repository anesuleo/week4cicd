package ie.atu.week4cicd1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    private List<Product> productList = new ArrayList<>();

    public ProductController(){
        productList.add(new Product("100", "Tv", "Electric", 399));
        productList.add(new Product("101", "Radio", "Eelctric", 399));
    }


    @GetMapping("/getProducts")
    public List<Product> getProduct(){
        return productList;
    }

    @PostMapping("/addProduct")
        public ResponseEntity<List> addProduct(@RequestBody Product product){
        productList.add(product);
        return ResponseEntity.ok(productList);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<List> updateProduct(@PathVariable String id, @RequestBody Product product){
        for (Product p : productList){
            if(p.getId().equals(id)){
                p.setName(product.getName());
                p.setCategory(product.getCategory());
                p.setPrice(product.getPrice());
                return ResponseEntity.ok(productList);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<List> deleteProduct(@PathVariable String id) {
        for (Product p : productList){
            if(p.getId().equals(id)){
                productList.remove(p);
            }
        }
        return ResponseEntity.ok(productList);
    }

}
