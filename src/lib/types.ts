export type Visibility = "private" | "public";
export type Recipe = {
 id:string; title:string; description:string; image:string; category:string;
 author:string; authorInitials:string; minutes:number; servings:number; rating:number;
 difficulty:"Fácil"|"Médio"|"Avançado"; visibility:Visibility; ingredients:string[];
 steps:string[]; tags:string[]; createdAt:string;
};
