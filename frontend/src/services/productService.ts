import type { Product } from "../types/Product";
import { API_URL } from "../config/config";

export const getCategoryProducts = async (): Promise<Product[]> => {
  const response = await fetch(`${API_URL}/categoryCard`);
  if (!response.ok) {
    throw new Error("Ошибка при загрузке карточек категорий ");
  }
  return response.json();
};
