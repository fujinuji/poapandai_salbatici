package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) //1.void test_4() elimina _ si il inloc. cu " "
class PizzaServiceTest {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;
    private PizzaService pizzaService;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        menuRepo = new MenuRepository();
        payRepo = new PaymentRepository();
        pizzaService = new PizzaService(menuRepo,payRepo);

    }
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void addPayment() throws Exception {
        System.out.println("start");
        test5();
        test6();
        test7();
        System.out.println("end");
    }

    @Test
    @DisplayName("Se va rula testul 1: ECP valid") //2
    void test1() throws Exception {
        //System.out.println("test1");
        //ECP valid case 1
        int size = pizzaService.getPayments().size();
        pizzaService.addPayment(2,PaymentType.Cash,10);
        assertEquals(size+1,pizzaService.getPayments().size());
    }
    @Test
    @Order(2)
    void test2() throws Exception{
        //System.out.println("test2");
        //ECP valid case 2
        int size2 = pizzaService.getPayments().size();
        pizzaService.addPayment(6,PaymentType.Cash,10);
        assertEquals(size2+1,pizzaService.getPayments().size());
    }

    @Test
    @Order(1)
    void test3() throws Exception{
        //System.out.println("test3");
        //ECP non-valid case 3
        try {
            pizzaService.addPayment(9, PaymentType.Card, 10);
            fail();
        }catch (Exception e){
            assertEquals(e.getMessage(),"Error message: Invalid table\n");
        }
    }

    @Test
    void test_4() throws Exception{
        //System.out.println("test4");
        //ECP non-valid case 4
        try {

            pizzaService.addPayment(2, PaymentType.Card, -10);
            fail();
        }catch (Exception e){
            assertEquals(e.getMessage(),"Error message: Amount must be > 0\n");
        }
    }

    void test5() throws Exception{
        //System.out.println("test5");
        //BVA valid case 1  - TC no 9
        int size3 = pizzaService.getPayments().size();
        pizzaService.addPayment(1,PaymentType.Cash,10);
        assertEquals(size3+1,pizzaService.getPayments().size());

    }

    void test6() throws Exception{
        //System.out.println("test6");
        //BVA valid case 2  - TC no 12
        int size4 = pizzaService.getPayments().size();
        pizzaService.addPayment(1,PaymentType.Card,Integer.MAX_VALUE);
        assertEquals(size4+1,pizzaService.getPayments().size());

    }

    void test7() throws Exception{
        //System.out.println("test7");
        //BVA non - valid case 3  - TC no 11
        try {
            pizzaService.addPayment(0, PaymentType.Cash, 20);
            fail();
        }catch (Exception e){
            assertEquals(e.getMessage(),"Error message: Invalid table\n");
        }
    }

    @Test
    @Tag("testcuTag")
    void test8() throws Exception{
        //System.out.println("test8");
        //BVA non - valid case 3  - TC no 13
        try {
            pizzaService.addPayment(2, PaymentType.Card, 0);
            fail();
        }catch (Exception e){
            assertEquals(e.getMessage(),"Error message: Amount must be > 0\n");
        }
    }

    @Test
    @Disabled("Disabled - identic cu testul 8") //3
    void test9() throws Exception{
        //System.out.println("test9");
        //BVA non - valid case 3  - TC no 13
        try {
            pizzaService.addPayment(2, PaymentType.Card, 0);
            fail();
        }catch (Exception e){
            assertEquals(e.getMessage(),"Error message: Amount must be > 0\n");
        }
    }

}