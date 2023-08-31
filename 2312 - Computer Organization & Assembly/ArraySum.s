.global Sum32
.global Dot32
.global AbvCount
.text

Sum32:	//R0 contains address of array, R1 has count
	MOV R2, R0 // R2 <- R0
	MOV R0, #0 // R0 <- 0
SumLoop:
	CMP R1, #0 // set flags
	BEQ SumEnd
	LDR R3, [R2], #4 // grab value in address arr[0]
	ADD R0, R0, R3 // R0 <- R0 + R3
	SUB R1, R1, #1
	B SumLoop
SumEnd:
	BX LR

Dot32:	//R0 add of ar[0], R1 add of Y[0], R2 counts
	MOV R3, R0
	MOV R0, #0
	PUSH {R4,R5}
DotLoop:
	CMP R2, #0
	BEQ DotEnd
	LDR R4, [R3], #4
	LDR R5, [R1], #4
	MUL R4, R4, R5
	ADD R0, R0, R4
	SUB R2, R2, #1
	B DotLoop
DotEnd:
	POP {R4,R5}
	BX LR

AbvCount: //R0 contains Ar[0], R1<-Limit, R2 <-Count
	MOV R3, R0
	MOV R0, #0
	PUSH {R4}
CountLoop:
	CMP R2, #0
	BEQ CountEnd
	LDR R4, [R3], #4
	CMP R4,R1
	ADDGT R0, R0, #1
	SUB R2, R2, #1
	B CountLoop
CountEnd:
	Pop {R4}
	BX LR	
	