###################################################################
#  Author: <Aria Ali>
#  Description: Performs bitwise AND followed by bitwise OR on given
#  hex values.
###################################################################
	.data
intro: 	.asciiz  "0x3f & 0xaa | 0x03  = "
	
	.globl 	main
	.text
main:

	li $s1,0x3f
	li $s2, 0xaa
	li $s3, 0x03

	and $s4,$s1,$s2
	or $t1,$s4,$s3
	
	la $a0,intro
	li  $v0,4
	syscall
	
	la $a0, ($t1)
	li $v0, 34
	syscall
	
	

	li      $v0,10		   # exit
	syscall
