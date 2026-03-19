import { API_URL } from "../config/config";
import type { Category } from "../types/Category";

export const getCategories = async (): Promise<Category[]> => {
  const response = await fetch(`${API_URL}/category`);
  if (!response.ok) {
    throw new Error("Ошибка при загрузке категорий");
  }
  return response.json();
};
