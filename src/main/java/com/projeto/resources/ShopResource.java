package com.projeto.resources;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.domain.Shop;
import com.projeto.domain.ShopItem;
import com.projeto.dto.ShopDTO;
import com.projeto.repositories.ShopRepository;
import com.projeto.services.KafkaClient;

@RestController
@RequestMapping(value = "/shop")
public class ShopResource {

	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private KafkaClient kafkaClient;

	@RequestMapping(method = RequestMethod.GET)
	public List<ShopDTO> getShop() {
		return shopRepository.findAll().stream().map(shop -> ShopDTO.convert(shop)).collect(Collectors.toList());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ShopDTO saveShop(@RequestBody ShopDTO shopDTO) {
		shopDTO.setIdentifier(UUID.randomUUID().toString());
		shopDTO.setDateShop(LocalDate.now());
		shopDTO.setStatus("PENDING");

		Shop shop = Shop.convert(shopDTO);
		for (ShopItem shopItem : shop.getItems()) {
			shopItem.setShop(shop);
		}
		shopDTO = ShopDTO.convert(shopRepository.save(shop));

		
		kafkaClient.sendMessage(shopDTO);
		return shopDTO;
	}
}
