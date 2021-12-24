package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Tesla", 1)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("BMW", 2)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Audi", 3)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Samokat", 4)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car id = " + user.getUserCar().getId());
         System.out.println("Car model = " + user.getUserCar().getModel());
         System.out.println("Car series = " + user.getUserCar().getSeries());
         System.out.println();
      }

      User userCar = userService.getUserByCar("Audi", 3);
      System.out.println("First Name of the car owner = " + userCar.getFirstName());

      context.close();
   }
}
