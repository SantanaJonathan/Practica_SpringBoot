package com.platzi.springboot;

import com.platzi.springboot.bean.MyBean;
import com.platzi.springboot.bean.MyBeanWithProperties;
import com.platzi.springboot.component.MyComponent;
import com.platzi.springboot.entity.User;
import com.platzi.springboot.pojo.UserPojo;
import com.platzi.springboot.repository.UserRepository;
import com.platzi.springboot.services.BeanWithDependencies;
import com.platzi.springboot.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final Log LOGGER= LogFactory.getLog(this.getClass());

    private BeanWithDependencies beanWithDependencies;
    private MyBean myBean;
    private MyComponent myComponent;

    private MyBeanWithProperties myBeanWithProperties;

    private UserPojo userPojo;

    private UserRepository userRepository;

    private UserService userService;

    public Application(MyBean myBean,
                       @Qualifier("cualquierNombre") MyComponent myComponent,
                       BeanWithDependencies beanWithDependencies,
                       MyBeanWithProperties myBeanWithProperties,
                       UserPojo userPojo,
                       UserRepository userRepository,
                       UserService userService) {
        this.myBean = myBean;
        this.myComponent = myComponent;
        this.beanWithDependencies = beanWithDependencies;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Bean
    public Function<String, String> uppercase() {
        return String::toUpperCase;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        //ejemplosAnteriores();
        saveUsersInDb();
        //getInformationJpqlFromUser();
        //saveWithErrorTransactional();
    }

    private void ejemplosAnteriores(){
        System.out.println(myBean.hello());
        myComponent.printSomething();

        //Function<String, String> function = uppercase();
        //System.out.println(function.apply("michael"));
        System.out.println(beanWithDependencies.operationWithDependencies());
        System.out.println(myBeanWithProperties.pruebaProperties());
        System.out.println(userPojo.getEmail());
    }

    //consultar por email por el metodo findMyUserByEmail de UserRepository
    private void getInformationJpqlFromUser(){
        LOGGER.info("Usuario con el metodo findByUserEmail" +
                userRepository.findMyUserByEmail("julie@domain.com").
                orElseThrow(()-> new RuntimeException("No se encontro el usuario!")));

        userRepository.findByAndSort("user", Sort.by("id").ascending())
                .stream()
                .forEach(user -> LOGGER.info("Usuario con metodo Sort" + user));

        userRepository.findByName("John")
                .stream()
                .forEach(user -> LOGGER.info("Usuario con query method " + user));


        LOGGER.info("User with method findByEmailAndName: " + userRepository.findByEmailAndName("john@domain.com", "John")
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario por el email dado")));


        userRepository.findByNameLike("%user%")
                .stream()
                .forEach(user -> LOGGER.info("User with method findByNameLike" + user));

        //LOGGER.info("User with method findUserByNameOrEmail: " +
        userRepository.findByNameOrEmail(null, "john@domain.com")
                .stream()
                .forEach(user -> LOGGER.info("User with method findByNameOrEmail" + user));


        userRepository.findByBirthDateBetween(LocalDate.of(2021, 03, 15), LocalDate.of(2021, 03, 25))
                .stream()
                .forEach(user -> LOGGER.info("User with method findByBirthDateBetween:" + user));

        userRepository.findByNameLikeOrderByIdDesc("%T%")
                .stream()
                .forEach(user -> LOGGER.info("User with method findByNameLikeOrderByIdDesc:" + user));

        LOGGER.info("User a partir del named parameter es: " +userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 03, 15),"john@domain.com")
                .orElseThrow(() ->
                        new RuntimeException("No se encontro el usuario a partir del named parameters ")));
        ;

    }

    private void saveWithErrorTransactional() {
        User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
        User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
        User test3 = new User("TestTransactional3", null, LocalDate.now());
//        User test3 = new User("TestTransactional3", "TestTransactional4@domain.com", LocalDate.now()); //ejemplo dos
        User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());
        List<User> users = Arrays.asList(test1, test2, test3, test4);

        try {
            userService.saveTransactional(users);

            userService.getAllUsers()
                    .stream()
                    .forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transacciona " + user));

        } catch (RuntimeException e) {
            LOGGER.error("La siguiente exepcion ocurrio durante la ejecución del metodo para registrar usuarios");
            LOGGER.error(e.getMessage());
        }

    }

    /*
    private void getUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> LOGGER.info(user.toString()));
    }*/

    private void saveUsersInDb() {
        User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 03, 15));
        User user2 = new User("Julie", "julie@domain.com", LocalDate.of(2021, 03, 20));
        User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 03, 25));
        User user4 = new User("Oscar", "oscar@domain.com", LocalDate.now());
        User user5 = new User("user1", "Test1@domain.com", LocalDate.now());
        User user6 = new User("Test2", "Test2@domain.com", LocalDate.now());
        User user7 = new User("Test3", "Test3@domain.com", LocalDate.now());
        User user8 = new User("Test4", "Test4@domain.com", LocalDate.now());
        User user9 = new User("Test5", "Test5@domain.com", LocalDate.now());
        User user10 = new User("Test6", "Test6@domain.com", LocalDate.now());
        User user11 = new User("Test7", "Test7@domain.com", LocalDate.now());
        User user12 = new User("Test8", "Test8@domain.com", LocalDate.now());
        User user13 = new User("Test9", "Test9@domain.com", LocalDate.now());
        List<User> list = Arrays.asList(user4, user1, user3, user2, user5, user6, user7, user8, user9, user10, user11, user12, user13);

        list.stream().forEach(userRepository::save);
        for (User u:list){
            System.out.println(u);
        }
        //postRepository.save(new Posts("Mi post test1", user12));
        //postRepository.save(new Posts("Mi post test2", user13));
        //postRepository.save(new Posts("Mi post test3", user13));
    }
}
