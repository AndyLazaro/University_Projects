.global maxU32

.text

maxU32:
	CMP R0, R1
	MOVLT R0,R1
	BX LR
