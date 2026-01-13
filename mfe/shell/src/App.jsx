import React, { Suspense, useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";

const CatalogWidget = React.lazy(() => import("catalog/CatalogWidget"));
const OrdersWidget = React.lazy(() => import("orders/OrdersWidget"));

export default function App() {
  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    const client = new Client({
      brokerURL: "ws://localhost:8080/ws/websocket",
      reconnectDelay: 5000,
    });

    client.onConnect = () => {
      client.subscribe("/topic/orders", (message) => {
        setNotifications((prev) => [message.body, ...prev].slice(0, 5));
      });
    };

    client.activate();
    return () => client.deactivate();
  }, []);

  return (
    <div style={{ fontFamily: "sans-serif", padding: "2rem" }}>
      <h1>NovaMart Control Center</h1>
      <p>Use demo/demo123 to access secured APIs.</p>
      <div style={{ display: "grid", gap: "1.5rem", gridTemplateColumns: "1fr 1fr" }}>
        <Suspense fallback={<div>Loading catalog...</div>}>
          <CatalogWidget />
        </Suspense>
        <Suspense fallback={<div>Loading orders...</div>}>
          <OrdersWidget />
        </Suspense>
      </div>
      <section style={{ marginTop: "2rem" }}>
        <h2>Live Order Notifications</h2>
        <ul>
          {notifications.map((note, index) => (
            <li key={`${note}-${index}`}>{note}</li>
          ))}
        </ul>
      </section>
    </div>
  );
}
