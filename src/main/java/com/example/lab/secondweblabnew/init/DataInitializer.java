package com.example.lab.secondweblabnew.init;


import com.example.lab.secondweblabnew.enums.Category;
import com.example.lab.secondweblabnew.enums.Engine;
import com.example.lab.secondweblabnew.enums.Role;
import com.example.lab.secondweblabnew.enums.Transmission;
import com.example.lab.secondweblabnew.services.dtos.*;
import com.example.lab.secondweblabnew.services.impl.OfferServiceImpl;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@ToString
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private OfferServiceImpl offerService;

    @Override
    public void run(String... args) throws Exception {
        initData();
    }

    private void initData() throws IOException {
        BrandDTO firstBrandDTO = new BrandDTO("BMW");
        BrandDTO secondBrandDTO = new BrandDTO("Mercedes");
        BrandDTO thirdBrandDTO = new BrandDTO("Audi");

        ModelDTO firstModelDTO = new ModelDTO("BMW X5", Category.CAR, "https://someimage.ru/firtsCarImage.jpg", 2007, 2023, firstBrandDTO);
        ModelDTO secondModelDTO = new ModelDTO("Mercedes Actros", Category.TRUCK, "https://someimage.ru/secondTruckImage.jpg", 2010, 2020, secondBrandDTO);
        ModelDTO thirdModelDTO = new ModelDTO("Audi", Category.CAR, "https://someimage.ru/secondCarImage.jpg", 2020, 2023, thirdBrandDTO);

        UserRoleDTO firstRoleDTO = new UserRoleDTO(Role.ADMIN);
        UserRoleDTO secondRoleDTO = new UserRoleDTO(Role.USER);

        UserDTO firstUserDTO = new UserDTO("NotFoundNone", "qwerty", "Nikita", "Dema", true, "https://someimage.ru/firtsUserImage.jpg", firstRoleDTO);
        UserDTO secondUserDTO = new UserDTO("Jared", "12345", "Jared", "James", true, "https://someimage.ru/secondUserImage.jpg", secondRoleDTO);

        OfferDTO firstOfferDTO = new OfferDTO("first discription", Engine.HYBRID, "https://someimage.ru/firtsOfferImage.jpg", 20156, 1990000, Transmission.AUTOMATIC, 2020, firstModelDTO, firstUserDTO);
        OfferDTO secondOfferDTO = new OfferDTO("second discription", Engine.GASOLINE, "https://someimage.ru/secondOfferImage.jpg", 18764, 3100000, Transmission.MANUAL, 2017,secondModelDTO, secondUserDTO);
        OfferDTO thirdOfferDTO = new OfferDTO("third discription", Engine.GASOLINE, "https://someimage.ru/thirdOfferImage.jpg", 23567, 7100000, Transmission.MANUAL, 2020,thirdModelDTO, firstUserDTO);

//        offerService.add(firstOfferDTO);
//        offerService.add(secondOfferDTO);
//        offerService.add(thirdOfferDTO);;


//        offerService.findByPriceLessThan(10000000).forEach(System.out::println);


    }
}
