package az.edu.bsu.smsproject.controller;

import az.edu.bsu.smsproject.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/rector")
public class RectorController {

    private final OrderService orderService;

    @Autowired
    public RectorController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = {"/", "/index"})
    public String index(){
        return "/rector/index";
    }

    @GetMapping("/orders")
    public String orders(){
        return "/rector/orders";
    }

    @PostMapping("/file")
    public String uploadOrder( @RequestParam("file1") MultipartFile multipartFile ){
        System.out.println(multipartFile);
        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(multipartFile.getContentType());
        System.out.println(multipartFile.isEmpty());
        System.out.println(multipartFile.getSize());

        String separator = File.separator;
        System.out.println(File.pathSeparator);
        System.out.println(File.separator);

        String path = "C:"+File.separator+"Users"+separator+"isace"+separator+"Desktop"+separator+"Files"+separator+""+multipartFile.getOriginalFilename();
        System.out.println(path);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream( path );
            fileOutputStream.write(multipartFile.getBytes());
            orderService.add(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/rector/index";
    }

}
