package cn.sc.gateway.controller;

import cn.sc.gateway.dto.OrderAddDTO;
import cn.sc.gateway.dto.OrderDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/get")
    public OrderDTO get(@RequestParam("id") Integer id) {
        return new OrderDTO().setId(id)
                .setName("订单编号：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

    @PostMapping("/add")
    public Integer add(OrderAddDTO addDTO) {
        return (int) (System.currentTimeMillis() / 1000); // 嘿嘿，随便返回一个 id
    }

}
