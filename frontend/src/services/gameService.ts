import { API_URL } from "../config/config";
import type { Product } from "../types/Product";

export const getBattleProducts = async (): Promise<Product[]> => {
  const response = await fetch(`${API_URL}/game/battle-products`);
  if (!response.ok) {
    throw new Error("Ошибка загрузки товаров для битвы");
  }
  return response.json();
};
