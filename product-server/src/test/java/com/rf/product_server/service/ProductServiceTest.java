package com.rf.product_server.service;

import com.rf.product_server.dto.AddProductRequest;
import com.rf.product_server.dto.ProductDto;
import com.rf.product_server.dto.UpdateProductRequest;
import com.rf.product_server.dto.User;
import com.rf.product_server.entity.Product;
import com.rf.product_server.error.exception.AuthorizationException;
import com.rf.product_server.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private  UserService userService;
    @Mock
    private  AuthService authService;
    @InjectMocks
    private ProductService productService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProduct() {
        //arrange
        AddProductRequest request=AddProductRequest.builder().name("name").price(BigDecimal.valueOf(10)).description("desc").build();
        Long userId=1L;
        User user=User.builder().id(userId).name("name").email("valid@mail.com").build();
        Mockito.when(userService.findByUserId(userId)).thenReturn(user);
        Mockito.when(authService.getIdOfLoggedInUser()).thenReturn(userId);
        //act
        ProductDto result=productService.addProduct(request,userId);
        //assert
        assertNotNull(result);
        assertDoesNotThrow(()->{
            if(authService.getIdOfLoggedInUser()!=userId) throw new AuthorizationException();
        });
        assertEquals(request.getName(),result.getName());
        assertEquals(request.getPrice(),result.getPrice());
        assertEquals(request.getDescription(),result.getDescription());

    }

    @Test
    void updateProduct() {
        //arrange
        UpdateProductRequest request=UpdateProductRequest.builder().name("name").price(BigDecimal.valueOf(10)).description("desc").build();
        Long productId=1L;
        Product product=Product.builder().userId(1L).id(productId).name(request.getName()).price(request.getPrice()).description(request.getDescription()).build();

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        Mockito.when(authService.getIdOfLoggedInUser()).thenReturn(product.getUserId());
        //act
        ProductDto result=productService.updateProduct(request,productId);
        //assert
        assertNotNull(result);
        assertDoesNotThrow(()->{
            if(!Objects.equals(authService.getIdOfLoggedInUser(), product.getUserId())) throw new AuthorizationException();
        });
        Mockito.verify(productRepository).save(product);
    }

    @Test
    void deleteProduct() {
        //arrange
        Long id=1L;
        Product product=Product.builder().id(id).name("name").description("desc").price(BigDecimal.valueOf(10)).userId(1L).build();

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));
        Mockito.when(authService.getIdOfLoggedInUser()).thenReturn(product.getUserId());
        //act
        String result=productService.deleteProduct(id);
        //assert
        assertNotNull(result);
        assertDoesNotThrow(()->{
            if(!Objects.equals(authService.getIdOfLoggedInUser(), product.getUserId())) throw new AuthorizationException();
        });
        Mockito.verify(productRepository).deleteById(id);
        assertEquals(result,"Ürün Başari ile Silindi");
    }


    @Test
    void getProductList() {
        // Arrange
        Product product1 = Product.builder().id(1L).name("name").description("desc").price(BigDecimal.valueOf(10)).userId(1L).build();
        Product product2 = Product.builder().id(1L).name("name2").description("desc2").price(BigDecimal.valueOf(20)).userId(1L).build();
        List<Product> productList = Arrays.asList(product1, product2);

        Mockito.when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<ProductDto> result = productService.getProductList();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(product1.getId(), result.get(0).getId());
        assertEquals(product1.getName(), result.get(0).getName());
        assertEquals(product2.getId(), result.get(1).getId());
        assertEquals(product2.getName(), result.get(1).getName());

        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getProductListByUserId() {
        // Arrange
        Long userId = 1L;
        Product product1 = Product.builder().id(1L).name("name").description("desc").price(BigDecimal.valueOf(10)).userId(1L).build();
        Product product2 = Product.builder().id(1L).name("name2").description("desc2").price(BigDecimal.valueOf(20)).userId(1L).build();
        List<Product> productList = Arrays.asList(product1, product2);

        Mockito.when(productRepository.findByUserId(userId)).thenReturn(productList);

        // Act
        List<ProductDto> result = productService.getProductListByUserId(userId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(product1.getId(), result.get(0).getId());
        assertEquals(product1.getName(), result.get(0).getName());
        assertEquals(product2.getId(), result.get(1).getId());
        assertEquals(product2.getName(), result.get(1).getName());

        Mockito.verify(productRepository, Mockito.times(1)).findByUserId(userId);
    }

    @Test
    void deleteByUserId() {
        //arrange
        Long userId=1L;
        Product product1 = Product.builder().id(1L).name("name").description("desc").price(BigDecimal.valueOf(10)).userId(userId).build();
        Product product2 = Product.builder().id(1L).name("name2").description("desc2").price(BigDecimal.valueOf(20)).userId(2L).build();
        List<Product> productList = Arrays.asList(product1, product2);

        Mockito.when(productRepository.findAll()).thenReturn(productList);
        //act
        productService.deleteByUserId(userId);
        //assert
        Mockito.verify(productRepository, Mockito.times(1)).findAll(); // findAll() metodunun çağrıldığını doğrula
        Mockito.verify(productRepository, Mockito.times(1)).delete(product1); // İlk ürünün silindiğini doğrula
        Mockito.verify(productRepository,Mockito.never()).delete(product2); // Diğer kullanıcının ürününün silinmediğini doğrula
    }
}