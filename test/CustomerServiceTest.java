import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {
    @Test
    public void behaviorTest() throws Exception {
        CustomerService customerService = Mockito.mock(CustomerService.class);
        Customer customer = Mockito.mock(Customer.class);
        customerService.handleNewCustomer("ekin", "ekolay@gmail.com");
        verify(customerService).handleNewCustomer("ekin", "ekolay@gmail.com");
    }

    @Test
    public void howManyTimeCalled() throws Exception {
        CustomerService customerService = Mockito.mock(CustomerService.class);
        Customer customer = Mockito.mock(Customer.class);
        customerService.handleNewCustomer("ekin", "ekolay@gmail.com");
        customerService.handleNewCustomer("ahmet", "ahmet@gmail.com");
        customerService.handleNewCustomer("mert", "merta@gmail.com");
        verify(customerService, times(1)).handleNewCustomer("ekin", "ekolay@gmail.com");
        verify(customerService, times(1)).handleNewCustomer("mert", "merta@gmail.com");
    }

    @Test
    public void howManyTimeCalled2() throws Exception {
        CustomerService customerService = Mockito.mock(CustomerService.class);
        Customer customer = Mockito.mock(Customer.class);
        customerService.handleNewCustomer("ekin", "ekolay@gmail.com");
        customerService.handleNewCustomer("ekin", "ekolay@gmail.com");
        customerService.handleNewCustomer("mert", "merta@gmail.com");

        verify(customerService, never()).handleNewCustomer("deniz", "deniz@gmail.com");

    }

    @Test
    public void NoMoreInteraction() {

        CustomerService customerService = mock(CustomerService.class);
        customerService.handleNewCustomer("ekin", "ekolay@gmail.com");
        customerService.handleNewCustomer("mert", "merta@gmail.com");
//        customerService.handleNewCustomer("deniz", "deniz@gmail.com");

        verify(customerService).handleNewCustomer("ekin", "ekolay@gmail.com");
        verify(customerService).handleNewCustomer("mert", "merta@gmail.com");

        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void NoInteraction() {
        CustomerService customerService2 = mock(CustomerService.class);
//        customerService2.handleNewCustomer("mert", "merta@gmail.com");
        verifyNoInteractions(customerService2);

    }

    @Test
    public void When() {
        NotificationService service = mock(NotificationService.class);
        when(service.getCustomer(eq("ekin"))).thenReturn("customerEkin");
        String customer = service.getCustomer("ekin");

    }

    @Test
    public void HandlenewCustomer() {
        CustomerRepository cr = mock(CustomerRepository.class);
        NotificationService ns = mock(NotificationService.class);
        CustomerService cs = new CustomerService(cr, ns);
        Customer customer = cs.handleNewCustomer("asd", "asdasd");
        verify(cr).save(customer);
        verify(ns).sendWelcomeNotification("asd", "asdasd");
    }

}






