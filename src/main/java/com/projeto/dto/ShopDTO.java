package com.projeto.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.projeto.domain.Shop;

public class ShopDTO {

	private String identifier;
	private LocalDate dateShop;
	private String status;
	private List<ShopItemDTO> items = new ArrayList<>();

	public static ShopDTO convert(Shop shop) {
		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setIdentifier(shop.getIdentifier());
		shopDTO.setDateShop(shop.getDateShop());
		shopDTO.setStatus(shop.getStatus());
		shopDTO.setItems(shop.getItems().stream().map(i -> ShopItemDTO.convert(i)).collect(Collectors.toList()));
		return shopDTO;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public LocalDate getDateShop() {
		return dateShop;
	}

	public void setDateShop(LocalDate dateShop) {
		this.dateShop = dateShop;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ShopItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ShopItemDTO> items) {
		this.items = items;
	}

}
