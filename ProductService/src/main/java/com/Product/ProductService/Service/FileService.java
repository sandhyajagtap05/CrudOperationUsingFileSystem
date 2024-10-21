package com.Product.ProductService.Service;




import com.Product.ProductService.Entity.Product;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private static final String FILE_PATH = "products.txt";  // This is where data will be stored

    // Create or append a product to the file
    public void createProduct(Product product) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {  // 'true' for appending to the file
            writer.write(product.getId() + "," + product.getName() + "," + product.getPrice() + "\n");
        }
    }

    // Read all products from the file
    public List<Product> getAllProducts() throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");  // Split by commas
                Product product = new Product();
                product.setId(Long.parseLong(parts[0]));
                product.setName(parts[1]);
                product.setPrice(Double.parseDouble(parts[2]));
                products.add(product);
            }
        }
        return products;
    }

    // Update product in the file
    public void updateProduct(Product updatedProduct) throws IOException {
        List<Product> products = getAllProducts();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {  // Overwrite the file
            for (Product product : products) {
                if (product.getId().equals(updatedProduct.getId())) {
                    writer.write(updatedProduct.getId() + "," + updatedProduct.getName() + "," + updatedProduct.getPrice() + "\n");
                } else {
                    writer.write(product.getId() + "," + product.getName() + "," + product.getPrice() + "\n");
                }
            }
        }
    }

    // Delete a product from the file
    public void deleteProduct(Long id) throws IOException {
        List<Product> products = getAllProducts();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {  // Overwrite the file
            for (Product product : products) {
                if (!product.getId().equals(id)) {  // Only write back products that don't match the given ID
                    writer.write(product.getId() + "," + product.getName() + "," + product.getPrice() + "\n");
                }
            }
        }
    }
}

