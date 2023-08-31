.global strRight
.text
strRight:	//extern void strRight(const char str[], const char str1[20], uint32_t chartoPrint);
	PUSH {R3,R4, R5}
	MOV R5, R0
	MOV R4, #0
strLen_Loop:	//Loop to find the length of the string
	LDRB R3, [R0], #1
	CMP R3, #0
	BEQ strRight_Find
	ADD R4, R4, #1
	BNE strLen_Loop
strRight_Find:
	SUBS R4, R4, R2 // R4 = index needing to print after
	BMI strEnd2
strRight_FLoop:
	LDRSB R3, [R5], #1
	SUBS R4, R4, #1
	BEQ strRight_FLoop2
	BNE strRight_FLoop
strRight_FLoop2:
	LDRSB R3, [R5], #1
	STRB R3, [R1], #1
	CMP R3, #0
	BEQ strEnd
	BNE strRight_FLoop2
strEnd:
	MOV R0, R1
	POP {R3,R4, R5}
	BX LR
strEnd2:
	MOV R0,#1
	POP {R3,R4,R5}
	BX LR
	