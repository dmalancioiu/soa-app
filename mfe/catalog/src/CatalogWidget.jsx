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

export default function CatalogWidget() {
  const [items, setItems] = useState([]);

  useEffect(() => {
    const load = async () => {
      const token = await fetchToken();
      const response = await fetch("/api/catalog", {
        headers: { Authorization: `Bearer ${token}` },
      });
      const data = await response.json();
      setItems(data);
    };
    load();
  }, []);

  return (
    <section style={{ border: "1px solid #ddd", padding: "1rem" }}>
      <h2>Catalog Service</h2>
      <ul>
        {items.map((item) => (
          <li key={item.id}>
            {item.name} (stock: {item.stock})
          </li>
        ))}
      </ul>
    </section>
  );
}
