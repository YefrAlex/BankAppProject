package de.telran_yefralex.BankAppProject.service;

import de.telran_yefralex.BankAppProject.dto.ClientFullInfoDto;
import de.telran_yefralex.BankAppProject.dto.ClientShortDto;
import de.telran_yefralex.BankAppProject.dto.ProductDto;
import de.telran_yefralex.BankAppProject.entity.Client;
import de.telran_yefralex.BankAppProject.entity.Employee;
import de.telran_yefralex.BankAppProject.entity.Product;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import de.telran_yefralex.BankAppProject.entity.enums.ProductType;
import de.telran_yefralex.BankAppProject.entity.enums.Role;
import de.telran_yefralex.BankAppProject.exceptions.ErrorMessage;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmployeeNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmptyProductsListException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.ProductNotFoundException;
import de.telran_yefralex.BankAppProject.mapper.ProductMapper;
import de.telran_yefralex.BankAppProject.repository.ProductRepository;
import de.telran_yefralex.BankAppProject.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepositoryMock;
    @Mock
    ProductMapper productMapperMock;

    @InjectMocks
    private ProductServiceImpl productServiceTest;
    private Product expectedProduct;
    private ProductDto expectedProductDto;
    List<Product> expectedProductList;
    List<ProductDto> expectedProductDtoList;


    @BeforeEach
    void init() {
        expectedProduct=new Product();
        expectedProduct.setId(1L);
        expectedProduct.setProductType(ProductType.CREDIT_CARD);
        expectedProduct.setCurrencyCode(CurrencyCode.EUR);
        expectedProduct.setInterestRate(BigDecimal.valueOf(1));
        expectedProduct.setLimit(BigDecimal.valueOf(100));
        expectedProduct.setLimitDuration(12);
        expectedProduct.setDescription("description");
        expectedProduct.setCreatedAt(LocalDateTime.now());
        expectedProduct.setUpdateAt(LocalDateTime.now());
        expectedProduct.setBlocked(false);
        expectedProductDto=new ProductDto();
        expectedProductDto.setProductType("CREDIT_CARD");
        expectedProductDto.setCurrency("euro");
        expectedProductDto.setInterestRate(BigDecimal.valueOf(1));
        expectedProductDto.setLimit(BigDecimal.valueOf(100));
        expectedProductDto.setLimitDuration(12);
        expectedProductDto.setDescription("description");
        expectedProductDtoList=new ArrayList<>();
        expectedProductDtoList.add(expectedProductDto);
        expectedProductList=new ArrayList<>();
        expectedProductList.add(expectedProduct);
    }

    @Test
    public void testGetAllActiveProductsDto() {
        when(productRepositoryMock.findAllActiveProducts()).thenReturn(expectedProductList);
        when(productMapperMock.toProductDto(expectedProductList.get(0))).thenReturn(expectedProductDtoList.get(0));
        List<ProductDto> result=productServiceTest.getAllActiveProductsDto();
        assertEquals(expectedProductDtoList, result);
    }

    @Test
    public void testGetAllActiveProductsDto_EmptyListException() {
        when(productRepositoryMock.findAllActiveProducts()).thenReturn(List.of());
        EmptyProductsListException exception=assertThrows(EmptyProductsListException.class, () -> {
            productServiceTest.getAllActiveProductsDto();
        });
        assertEquals(ErrorMessage.EMPTY_PRODUCTS_LIST, exception.getMessage());
        verify(productRepositoryMock).findAllActiveProducts();
        verifyNoInteractions(productMapperMock);
    }

    @Test
    public void testGetActiveProductsWithType() {
        when(productRepositoryMock.findActiveProductsWithType(any(ProductType.class))).thenReturn(expectedProductList);
        when(productMapperMock.toProductDto(expectedProductList.get(0))).thenReturn(expectedProductDtoList.get(0));
        List<ProductDto> result=productServiceTest.getActiveProductsWithType(ProductType.CREDIT_CARD);
        assertEquals(expectedProductDtoList, result);
    }

    @Test
    public void testGetActiveProductsWithType_EmptyListException() {
        when(productRepositoryMock.findActiveProductsWithType(any(ProductType.class))).thenReturn(List.of());
        EmptyProductsListException exception=assertThrows(EmptyProductsListException.class, () -> {
            productServiceTest.getActiveProductsWithType(ProductType.CREDIT_CARD);
        });
        assertEquals(ErrorMessage.EMPTY_PRODUCTS_LIST, exception.getMessage());
        verify(productRepositoryMock).findActiveProductsWithType(ProductType.CREDIT_CARD);
        verifyNoInteractions(productMapperMock);
    }

    @Test
    public void testGetProductDtoById() {
        when(productRepositoryMock.getProductDtoById(anyLong())).thenReturn(expectedProduct);
        when(productMapperMock.toProductDto(expectedProduct)).thenReturn(expectedProductDto);
        ProductDto result=productServiceTest.getProductDtoById(anyLong());
        assertEquals(expectedProductDto, result);
    }

    @Test
    public void testGetProductDtoById_WhenProductNotExists() {
        when(productRepositoryMock.getProductDtoById(anyLong())).thenReturn(null);
        assertThrows(ProductNotFoundException.class, () -> productServiceTest.getProductDtoById(anyLong()));
        verify(productRepositoryMock).getProductDtoById(anyLong());
        verify(productMapperMock, never()).toProductDto(any());
    }

    @Test
    void testUpdateProduct() {
        when(productRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(expectedProduct));
        doAnswer(invocation -> {
            BigDecimal interestRate=invocation.getArgument(1);
            BigDecimal limit=invocation.getArgument(2);
            Integer limitDuration=invocation.getArgument(3);
            Boolean isBlocked=invocation.getArgument(4);
            expectedProduct.setInterestRate(interestRate);
            expectedProduct.setLimit(limit);
            expectedProduct.setLimitDuration(limitDuration);
            expectedProduct.setBlocked(isBlocked);
            return null;
        }).when(productRepositoryMock).updateProduct(anyLong(), any(BigDecimal.class), any(BigDecimal.class), anyInt(), anyBoolean());
        productServiceTest.updateProduct(1l, BigDecimal.valueOf(2), BigDecimal.valueOf(200), 24, false);
        assertEquals(BigDecimal.valueOf(2), expectedProduct.getInterestRate());
        assertEquals(BigDecimal.valueOf(200), expectedProduct.getLimit());
        assertEquals(24, expectedProduct.getLimitDuration());
        assertEquals(false, expectedProduct.isBlocked());
        verify(productRepositoryMock).updateProduct(1l, BigDecimal.valueOf(2), BigDecimal.valueOf(200), 24, false);
    }

    @Test
    public void testUpdateProduct_ProductNotFound() {
        when(productRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> {
            productServiceTest.updateProduct(48l, BigDecimal.valueOf(2), BigDecimal.valueOf(200), 24, false);
        });
        verify(productRepositoryMock).findById(48l);
        verify(productRepositoryMock, never()).updateProduct(anyLong(), any(BigDecimal.class), any(BigDecimal.class), anyInt(), anyBoolean());
    }

    @Test
    public void testCreateNewProduct() {
        when(productMapperMock.toProduct(any(ProductDto.class))).thenReturn(expectedProduct);
        when(productRepositoryMock.save(any(Product.class))).thenReturn(expectedProduct);
        Product result=productServiceTest.createNewProduct(expectedProductDto);
        assertEquals(expectedProduct, result);
    }

}
