import React from "react";
import { createRoot } from "react-dom/client";
import OrdersWidget from "./OrdersWidget.jsx";

createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <OrdersWidget />
  </React.StrictMode>
);
