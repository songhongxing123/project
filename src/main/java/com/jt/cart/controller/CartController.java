package com.jt.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	@RequestMapping("/query/{userId}")
	@ResponseBody
	public SysResult queryMyCart(@PathVariable Long userId){
		List<Cart> cartList = cartService.queryMyCart(userId);
		return SysResult.ok(cartList);
	}
	
	//新增商品到购物车
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveCart(Cart cart){
		cartService.saveCart(cart);
		return SysResult.ok();
	}
	
	//更新商品数量
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	@ResponseBody
	public SysResult updateNum(@PathVariable Long userId,@PathVariable Long itemId,@PathVariable Integer num){
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cart.setNum(num);
		cartService.updateNum(cart);
		return SysResult.ok();
	}
	
	//删除
	@RequestMapping("/delete/{userId}/{itemId}")
	@ResponseBody
	public SysResult deleteCart(@PathVariable Long userId,@PathVariable Long itemId){
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cartService.deleteByWhere(cart);
		return SysResult.ok();
	}
	
}
