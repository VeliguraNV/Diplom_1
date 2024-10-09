package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockSauce;

    @Mock
    private Ingredient mockFilling;

    private final float saucePrice;
    private final float fillingPrice;
    private final float expectedPrice;
    private final String expectedReceipt;

    // Конструктор для передачи параметров
    public BurgerTest(float saucePrice, float fillingPrice, float expectedPrice, String expectedReceipt) {
        this.saucePrice = saucePrice;
        this.fillingPrice = fillingPrice;
        this.expectedPrice = expectedPrice;
        this.expectedReceipt = expectedReceipt;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        burger.setBuns(mockBun);

        // Устанавливаем цену булки и ее название
        when(mockBun.getPrice()).thenReturn(1.0f);
        when(mockBun.getName()).thenReturn("Mock Sesame");

        // Устанавливаем цены и типы ингредиентов
        when(mockSauce.getPrice()).thenReturn(saucePrice);
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockSauce.getName()).thenReturn("Mock Sauce");

        when(mockFilling.getPrice()).thenReturn(fillingPrice);
        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockFilling.getName()).thenReturn("Mock Filling");
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        String newline = System.lineSeparator();  // Use correct line separator for the test environment
        return Arrays.asList(new Object[][]{
                {0.5f, 3.0f, 6.0f,
                        "(==== Mock Sesame ====)" + newline +
                                "= sauce Mock Sauce =" + newline +
                                "= filling Mock Filling =" + newline +
                                "(==== Mock Sesame ====)" + newline + newline +
                                "Price: 5,500000" + newline},
                {1.0f, 2.0f, 6.0f,
                        "(==== Mock Sesame ====)" + newline +
                                "= sauce Mock Sauce =" + newline +
                                "= filling Mock Filling =" + newline +
                                "(==== Mock Sesame ====)" + newline + newline +
                                "Price: 5,000000" + newline},
        });
    }

    @Test
    public void testGetPrice() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        assertEquals(expectedPrice, burger.getPrice(), 1);
    }

    @Test
    public void testSetBuns() {
        assertEquals("Mock Sesame", burger.bun.getName());
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockSauce);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockSauce);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void testGetReceipt() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        // Normalize line separators for comparison
        String normalizedExpected = normalizeLineSeparators(expectedReceipt);
        String normalizedActual = normalizeLineSeparators(burger.getReceipt());

        assertEquals(normalizedExpected, normalizedActual);
    }

    private String normalizeLineSeparators(String input) {
        return input.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
    }
}