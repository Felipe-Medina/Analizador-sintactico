package clases;
import static clases.Tokens.*;
%%
%class Lexer
%type Tokens
LETRA=[a-z|A-Z]
DIGLET=[a-z|A-Z|_|0-9]*
ID=[LETRADIGLET*]
TD=[i|n|t|s|r|g|f|l|o|a|d|u|b|e|h|v|c]+
SEP1=[,]
DEL=[(|)]
DELICOR=[{|}]
AS=[=]
NEG=[-]
SIG=[NEG?]
DIG=[0-9]
DEC=[.]
CNEPF=[DIG+(DECDIG+)?]
OA=[+|-|*|/|%|]
SEP=[;]
espacio=[ ,\t,\r,\n]+
%{
    public String lexema;
%}
%%
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}

{TD} {lexema=yytext(); return TD }
 . {return ERLX-TD;}
{ID} {lexema=yytext(); return ID;}
 . {return ERLX-ID;}
{DEL}|{DELICOR} {lexema=yytext(); return DEL;}
 . {return ERLX-DEL;}
{SEP}|{SEP1} {lexema=yytext(); return SEP;}
 . {return ERLX-SEP;}
{AS} {lexema=yytext(); return AS;}
 . {return ERLX-AS;}
{SIG} {lexema=yytext(); return SIG;}
 . {return ERLX-SIG;}
{CNEPF} {lexema=yytext(); return CNEPF;}
 . {return ERLX-CNEPF;}
{OA} {lexema=yytext(); return OA;}
 . {return ERLX-OA;}
