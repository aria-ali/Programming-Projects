###################################################################
#  Author: <Aria Ali>
#  Description: Shifts the character '0' in the hexadecimal string 
#'0x0eeeeeee'back and forward via logical shifts.
###################################################################
	.data
canvas: .word    0x0eeeeeee
canvas2: .word 	0xeeeeee0e
line:	.asciiz	"Time: "
nextln: .asciiz "\n"
	
	.globl 	main
	.text
main:

	li 	$s1,0		# index = 0;
	li	$s7,0	
	
	
	li	$s2,8	      # do {
	li 	$t1, 0
	
	li	$t2,7	      # do {
	li 	$t3, 0
loop1:

	lw	$s0,canvas
	
	ror   	$s0,$s0,$t1
	
	li	$v0,34
	la	$a0,($s0)
	syscall
	
	la $a0,nextln
	li  $v0,4
	syscall
	

	addi	$t1,$t1 4
	
	
	addi	$s1,$s1,1 	#   index++;
	bne	$s1,$s2,loop1   # } (until index = 256)
	
	li 	$s1,0		# index = 0;
	
loop2:

	lw	$s0,canvas2
	
	rol   	$s0,$s0,$t1
	
	li	$v0,34
	la	$a0,($s0)
	syscall
	
	la $a0,nextln
	li  $v0,4
	syscall
	

	addi	$t1,$t1 4
	
	
	addi	$s7,$s7,1 	#   index++;
	bne	$s7,$t2,loop2   # } (until index = 256)
	
	
	
	li	$v0,10		# System(exit)
	syscall