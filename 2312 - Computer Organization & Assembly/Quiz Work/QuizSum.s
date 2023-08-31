.global SumS32

.text


SumS32:	//R0 <- Array SumS32(const int32_t x[4])
	MOV R2, R0 // R2 <- &x[4]
	MOV R0, #0
	MOV R1, #0
SumCount:
	CMP R3, #0
	BEQ SumAdd
	LDR R3, [R2], #-4 // R3 <- *x[4], R2 <- (&x[4])--
	ADD R1, R1, #1
	B   SumCount
SumAdd:
	CMP R1, #0
	BEQ SumEnd
	LDR R3, [R2], #4
	ADD R0, R0, R3
	SUB R1,R1, #1
	B SumAdd
SumEnd:
	BX LR

	
