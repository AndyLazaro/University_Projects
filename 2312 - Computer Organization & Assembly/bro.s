.global bro32
.global countOnes32

.text
bro32:
	MOV R1, R0
	MOV R0, #0
	MOV R2, #0x80000000
	MOV R3, #0x00000001
bro_loop:
	TST R1,R2
	ORRNE R0,R0,R3
	MOVS R2,R2,LSR #1
	MOV R3,R3, LSL #1
	BNE bro_loop
	BX LR
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
