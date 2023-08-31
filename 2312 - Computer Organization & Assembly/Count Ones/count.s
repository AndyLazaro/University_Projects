.global countOnes32

.text
countOnes32:
	MOV R1, R0
	MOV R0, #0
	MOV R2, #0x80000000
co_loop:
	TST R1, R2
	ADDNE R0, R0, #1
	MOVS R2, R2, LSR #1
	BNE co_loop
	BX LR
