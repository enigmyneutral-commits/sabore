import Link from "next/link";import {Sparkles} from "lucide-react";
export function TopBanner(){return <div className="pro-banner"><div className="pro-banner-icon"><Sparkles size={18}/></div><div><strong>Cozinhe sem interrupções</strong><span>Conheça o Saborê Pro e tenha uma experiência sem anúncios.</span></div><Link href="/pro">Conhecer Pro →</Link></div>}
