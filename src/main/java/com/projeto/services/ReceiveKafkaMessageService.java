package com.projeto.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.projeto.domain.Shop;
import com.projeto.dto.ShopDTO;
import com.projeto.repositories.ShopRepository;

@Service
public class ReceiveKafkaMessageService {

	private static final Logger LOG = LoggerFactory.getLogger(ReceiveKafkaMessageService.class);

	@Autowired
	private ShopRepository shopRepository;

	private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

	@KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group")
	public void listenShopEvents(ShopDTO shopDTO) {
		try {
			LOG.info("Status da compra recebida no tópico: {}.", shopDTO.getIdentifier());

			Optional<Shop> shop = shopRepository.findByIdentifier(shopDTO.getIdentifier());
			if (shop.isPresent()) {
				shop.get().setStatus(shopDTO.getStatus());
				shopRepository.save(shop.get());
			}

		} catch (Exception e) {
			LOG.error("Erro no processamento da compra {}", shopDTO.getIdentifier());
		}
	}

}
