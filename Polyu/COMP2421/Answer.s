# Program: sum of an array
	.data

array:  .word 15, 23, -13, -75, 44        # declare an array
strPN:  .asciiz "The Positive Sum is: "   # output prefix
strNN:  .asciiz "The Negative Sum is: "   # output prefix
cr:     .asciiz "\n"			 # new line

	.globl main
	.text

main:
	li $s1, 0 	   # zero the Negative Sum 
	li $s3, 0 	   # zero the Positive Sum
	li $t1, 0 	   # init index to 0
	li $t2, 0 	   # init loop counter
	li $s2, 0 	   # init flag

for:
	beq $t2, 5, endfor # for(i = 0; i < 5; i++)
	lw $v1, array($t1)
	slti $s2, $v1, 0   # if $v1<0: $s2=1; else: $s2=0.
	beq $s2, 0, else.  # if $s2=0: goto else
	add $s1, $s1, $v1  # sum_neg = sum_neg + array[i]
	addi $t1, $t1, 4   # index++
	addi $t2, $t2, 1   # counter++
	j for
	
else:	
	add $s3, $s3, $v1  # sum_pos = sum_pos + array[i]
	addi $t1, $t1, 4   # index++
	addi $t2, $t2, 1   # counter++
	j for

endfor: 

	li $v0, 4          # syscall number 4 prints string
	la $a0, strNN
    	syscall 
	
	move $a0, $s1 	   # load Negative Sum
	li $v0, 1 	   # call syscall 4 to print the Negative Sum 
	syscall

	la $a0, cr  	   # print a new line
	li $v0, 4
	syscall

	li $v0, 4          # syscall number 4 prints string
	la $a0, strPN
	syscall 

	move $a0, $s3 	   # load  Sum Positive
	li $v0, 1 	   # call syscall 4 to print Positive Sum
	syscall

	li $v0, 10 	   # exit
	syscall