

import lombok.Setter;

@Setter
public class CustomerService {

    private CustomerRepository customerRepository;

    private NotificationService notificationService;

    public CustomerService(CustomerRepository customerRepository, NotificationService ns){
        this.customerRepository = customerRepository;
        this.notificationService = ns;
    }

    public Customer handleNewCustomer(String customerName, String email) {

        Customer customer = new Customer(customerName, email);
        giveWelcomeGifts(customer);
        customerRepository.save(customer);
        notificationService.sendWelcomeNotification(customerName, email);

        return customer;
    }

    private void giveWelcomeGifts(Customer customer) {
        customer.addGift(new Gift("welcome1"));
        customer.addGift(new Gift("welcome2"));
    }


    public void removeCustomer(String customerName) {

    }
    public void addCustomer(String customerName){

    }
    public void updateCustomer(String customerName) {

    }


}


