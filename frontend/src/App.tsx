import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import CategoryPage from "./pages/CategotyPage"; // исправь название файла
import TasteBattle from "./pages/TasteBattle";
import TopPage from "./pages/TopPage"; // если есть страница топа

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/test" element={<TasteBattle />} />
        <Route path="/" element={<CategoryPage />} />
        <Route path="/top" element={<TopPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
