import React, { useEffect, useState } from "react";

async function fetchToken() {
  const response = await fetch("/api/auth/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username: "demo", password: "demo123" }),
  });
  const data = await response.json();
  return data.token;
}

export default function OrdersWidget() {
  const [orders, setOrders] = useState([]);
  const [token, setToken] = useState("");

  const loadOrders = async (authToken) => {
    const response = await fetch("/api/orders", {
      headers: { Authorization: `Bearer ${authToken}` },
    });
    const data = await response.json();
    setOrders(data);
  };

  useEffect(() => {
    const load = async () => {
      const authToken = await fetchToken();
      setToken(authToken);
      await loadOrders(authToken);
    };
    load();
  }, []);

  const placeOrder = async () => {
    const response = await fetch("/api/orders", {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ item: "Nova Headphones", quantity: 1 }),
    });
    if (response.ok) {
      await loadOrders(token);
    }
  };

  return (
    <section style={{ border: "1px solid #ddd", padding: "1rem" }}>
      <h2>Order Service</h2>
      <button type="button" onClick={placeOrder}>
        Place sample order
      </button>
      <ul>
        {orders.map((order) => (
          <li key={order.id}>
            {order.item} x{order.quantity}
          </li>
        ))}
      </ul>
    </section>
  );
}
