package clases;
import static clases.Tokens.*;
%%
%class Lexer
%type Tokens
LETRA=[a-z|A-Z]
DIGLET=[a-z|A-Z|_|0-9]*
ID=[LETRADIGLET*]
TD=[int|string|float|double|short|long|boolean|void|char]
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

{TD} {lexema=yytext(); return TD;}
 . {return ERLX_TD;}
{ID} {lexema=yytext(); return ID;}
 . {return ERLX_ID;}
{DEL}|{DELICOR} {lexema=yytext(); return DEL;}
 . {return ERLX_DEL;}
{SEP}|{SEP1} {lexema=yytext(); return SEP;}
 . {return ERLX_SEP;}
{AS} {lexema=yytext(); return AS;}
 . {return ERLX_AS;}
{SIG} {lexema=yytext(); return SIG;}
 . {return ERLX_SIG;}
{CNEPF} {lexema=yytext(); return CNEPF;}
 . {return ERLX_CNEPF;}
{OA} {lexema=yytext(); return OA;}
 . {return ERLX_OA;}
