package com.dh.PI.services;

import com.dh.PI.dto.CityDTO;
import com.dh.PI.dto.ProductCharacteristicDTO;
import com.dh.PI.dto.productsDTO.ProductRequestDTO;
import com.dh.PI.dto.productsDTO.ProductResponseDTO;
import com.dh.PI.model.*;
import com.dh.PI.repositories.ProductCharacteristicRepository;
import com.dh.PI.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    public static final Long ID                     = 1L;
    public static final String QUALIFICATION    = "Coupes";
    public static final String DESCRIPTION      = "coupes teste";
    public static final String IMAGE_URL        = "coupes.png";
    public static final String COUNTRY      = "Brasil";
    public static final String CITYNAME     = "Recife";
    public static final double LONGITUDE    = -43.1890741235295;
    public static final double LATITUDE     = -22.96879036165036;
    public static final String NAME         = "Motor";
    public static final String ICON         = "coupes.png";

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository repository;
    @Mock
    private CategoryService categoryService;
    @Mock
    private CityService cityService;
    @Mock
    private CharacteristicService characteristicService;
    @Mock
    private ProductCharacteristicRepository productCharacteristicRepository;
    @Mock
    private Pageable pageable;


    private ProductRequestDTO productRequestDTO;
    private Product product;
    private Category category;
    private City city;
    private Characteristic characteristic;
    private ProductCharacteristicDTO productCharacteristicDTO;
    private ProductCharacteristic productCharacteristic;
    private Page<Product> productPage;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSetup();
    }

    @Test
    void create() {
        when(categoryService.findByQualification(anyString())).thenReturn(category);
        when(cityService.findByName(anyString())).thenReturn(city);
        when(repository.save(any())).thenReturn(product);
        when(characteristicService.findByName(any())).thenReturn(characteristic);
        when(productCharacteristicRepository.save(any())).thenReturn(productCharacteristic);
        when(repository.save(any())).thenReturn(product);

        ProductResponseDTO result = productService.create(productRequestDTO);

        assertNotNull(result);
        assertEquals(ProductResponseDTO.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals("Produto1", result.getName());
        assertEquals(5.0, result.getScore());
        assertEquals(2, result.getCountScore());
        assertEquals("produto novo", result.getDescription());
        assertEquals(ID, result.getCategory().getId());
        assertEquals(ID, result.getImages().get(0).getId());
        assertEquals("Motor", result.getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.getCharacteristics().get(0).getDescription());
    }

    @Test
    void findAll() {
        when(repository.findAll(pageable)).thenReturn(productPage);
        product.getProductCharacteristics().add(productCharacteristic);

        Page<ProductResponseDTO> result = productService.findAll(pageable);

        assertNotNull(result);
        assertEquals(PageImpl.class, result.getClass());
        assertEquals(ProductResponseDTO.class,  result.get().collect(Collectors.toList()).get(0).getClass());
        assertEquals(ID, result.get().collect(Collectors.toList()).get(0).getId());
        assertEquals("Produto1", result.get().collect(Collectors.toList()).get(0).getName());
        assertEquals(5.0, result.get().collect(Collectors.toList()).get(0).getScore());
        assertEquals(2, result.get().collect(Collectors.toList()).get(0).getCountScore());
        assertEquals("produto novo", result.get().collect(Collectors.toList()).get(0).getDescription());
        assertEquals(ID,result.get().collect(Collectors.toList()).get(0).getCategory().getId());
        assertEquals(ID, result.get().collect(Collectors.toList()).get(0).getImages().get(0).getId());
        assertEquals("Motor", result.get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getName());
        assertEquals("Corre muito", result.get().collect(Collectors.toList()).get(0).getCharacteristics().get(0).getDescription());
    }

    @Test
    void update() {
    }

    @Test
    void addImages() {
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAllProductsByCategory() {
    }

    @Test
    void findAllProductsByCity() {
    }

    @Test
    void findByNameBetweenDate() {
    }

    private void startSetup(){
        productCharacteristicDTO = new ProductCharacteristicDTO(NAME, "Corre muito", "motor.png");
        category = new Category(ID, QUALIFICATION, DESCRIPTION, IMAGE_URL);
        city = new City(null,  CITYNAME, COUNTRY, LONGITUDE, LATITUDE);
        characteristic = new Characteristic(1L, NAME, ICON, Set.of());
        productRequestDTO = new ProductRequestDTO(
                null,
                "Novo",
                "Produto novo",
                "Coupes",
                "Recife",
                List.of(productCharacteristicDTO)
        );

        product = new Product(
                ID, "Produto1",
                5.0, 2,
                "produto novo",
                category, city,
                List.of(new Classification(ID, ID, ID, 5.0)),
                List.of(new Image(ID, "image.url")),
                new HashSet<>(), List.of()
        );
        productCharacteristic = new ProductCharacteristic(ID, "Corre muito" , product, characteristic);
        productPage = new PageImpl<>(List.of(product));
    }

}