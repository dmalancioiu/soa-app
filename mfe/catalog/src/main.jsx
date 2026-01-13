import React from "react";
import { createRoot } from "react-dom/client";
import CatalogWidget from "./CatalogWidget.jsx";

createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <CatalogWidget />
  </React.StrictMode>
);
