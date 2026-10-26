import type { Metadata, Viewport } from "next";
import "./globals.css";
export const metadata: Metadata = { title: { default: "Saborê — Receitas que conectam pessoas", template: "%s · Saborê" }, description: "Descubra receitas, organize seu livro culinário e conecte-se com quem ama cozinhar.", applicationName: "Saborê", manifest: "/manifest.webmanifest", appleWebApp: { capable: true, statusBarStyle: "default", title: "Saborê" }, robots: { index: true, follow: true } };
export const viewport: Viewport = { themeColor: "#f7fbf5", width: "device-width", initialScale: 1 };
export default function RootLayout({ children }: Readonly<{ children: React.ReactNode }>) { return <html lang="pt-BR"><body>{children}</body></html>; }
