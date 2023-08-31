.global SumS32
.global Prod64
.global Dot64
.global Max64
.text

SumS32:
	CMP 		R1,#0
	BEQ 		SumExit
	VLDR.F32 	S0, [R0]
	ADD 		R0,R0,#4
	SUB 		R1,R1,#1
SumLoop:
	CMP 		R1,#0
	BEQ SumExit
	VLDR.F32 	S1,[R0]
	ADD 		R0,R0,#4
	VADD.F32 	S0,S0,S1
	SUB		R1,R1,#1
	B SumLoop
SumExit:
	BX LR

Prod64:
	VSUB.F64 D0,D0,D0
	CMP 	R1, #0
	BEQ ProdExit
	VLDR	D0, [R0]
	ADD R0, R0, #8
	SUB R1,R1,#1
loop:
	CMP R1,#0
	BEQ ProdExit
	VLDR D1, [R0]
	ADD R0,R0,#8
	VMUL.F64 D0,D0,D1
	SUB R1,R1,#1
	B loop
ProdExit:
	BX LR
	
Dot64:
	CMP R2,#0
	BEQ DotEnd
	VSUB.F64 D0,D0,D0
	VSUB.F64 D1,D1,D1
	VLDR D0, [R0]
	VLDR D1, [R1]
	ADD  R0,R0,#8
	ADD  R1,R1,#8
	SUB  R2,R2,#1
LOOP:
	CMP R2,#0
	BEQ DotEnd
	VMUL.F64 D2,D0,D1
	VADD.F64 D3,D2,D3
	VLDR D0, [R0]
	VLDR D1, [R1]
	ADD R0, R0, #8
	ADD R1,R1, #8
	SUB R2,R2,#1
	B LOOP
DotEnd:
	VMOV.F64 D0,D3
	BX LR

Max64:
	CMP R1,#0
	BEQ Exit
	VLDR	D0, [R0]
	ADD R0,R0,#8	
	SUB R1,R1,#1
Loop:
	CMP R1,#0
	BEQ Exit
	VLDR D1,[R0]
	VCMP.F64 D0,D1
	VMOVGT.F64 D1,D0
	ADD R0,R0,#8
	SUB R1,R1,#1
	B Loop
Exit:
	VMOV.F64 D0,D1
	BX LR 
