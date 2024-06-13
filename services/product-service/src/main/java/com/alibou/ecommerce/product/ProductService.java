package com.alibou.ecommerce.product;

import com.alibou.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public ProductResponse findById(Integer id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + id));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Integer> productIds = request
                .stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();

        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }

        List<ProductPurchaseRequest> purchaseRequests = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();

        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();

        for (int i = 0; i < storedProducts.size(); i++) {
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productPurchaseRequest = purchaseRequests.get(i);
            if (product.getAvailableQuantity() < productPurchaseRequest.getQuantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with Id :: " + productPurchaseRequest.getProductId());
            }
            double newAvailableQuantity = product.getAvailableQuantity() - productPurchaseRequest.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productPurchaseRequest.getQuantity()));
        }

        return purchasedProducts;
    }

}
