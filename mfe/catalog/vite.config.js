import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import federation from "@originjs/vite-plugin-federation";

export default defineConfig({
  plugins: [
    react(),
    federation({
      name: "catalog",
      filename: "remoteEntry.js",
      exposes: {
        "./CatalogWidget": "./src/CatalogWidget.jsx",
      },
      shared: ["react", "react-dom"],
    }),
  ],
  build: {
    target: "esnext",
  },
});
