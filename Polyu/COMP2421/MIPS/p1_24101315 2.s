# ==========================================
# README
#
# COMP2421 MIPS Programming Assignment
#
# This program asks the user to enter a positive integer.
# It converts the number into Binary, quat and Octal using repeated division.
# I have used the buffer in order to store the digits and print it in reversed order
#
# Binary: divide by 2
# Quaternary: divide by 4
# Octal: divide by 8
#
# The remainders are stored and printed.
# The user can choose to continue or exit.
# ==========================================

.data
prompt: .asciiz "Enter a number: "
inputNum: .asciiz "\nInput number is "
binary: .asciiz "\nBinary: "
quat: .asciiz "\nQuaternary: "
octal: .asciiz "\nOctal: "
cont: .asciiz "\nContinue? (1=Yes/0=No): "
bye: .asciiz "\nBye!\n"

binbuf: .space 32      # buffer to store binary digits
quatbuf: .space 32     # buffer to store quaternary digits
octbuf: .space 32      # buffer to store octal digits

.text
.globl main

main:

loop:

# ask number
la $a0, prompt
li $v0, 4
syscall                 # print "Enter a number: "

li $v0, 5               # system call for reading integer
syscall
move $t0, $v0           # store input number in register $t0

# print input number
la $a0, inputNum
li $v0, 4
syscall                 # print "Input number is: "

move $a0, $t0
li $v0, 1
syscall                 # print the number

# BINARY CONVERSION
la $a0, binary
li $v0, 4
syscall                 # print "Binary: "

move $t1, $t0           # copy input number to $t1
la $t4, binbuf          # load address of binary buffer
li $t5, 0               # initialize buffer index

bin_loop:
beq $t1, $zero, bin_store_done  # stop loop when quotient becomes 0

li $t2, 2               # divisor = 2
div $t1, $t2            # divide number by 2
mfhi $t3                # remainder stored in $t3
mflo $t1                # quotient stored back into $t1

add $t6, $t4, $t5       # calculate buffer memory address
sb $t3, 0($t6)          # store remainder in buffer

addi $t5, $t5, 1        # increase buffer index
j bin_loop              # repeat division loop

bin_store_done:

# print leading zeros
li $t7, 32              # total binary digits required
sub $t7, $t7, $t5       # calculate number of leading zeros
li $t8, 0               # initialize zero counter

bin_zero_loop:
beq $t8, $t7, bin_print_start  # stop when all zeros printed

li $a0, 0
li $v0, 1
syscall                 # print leading zero

addi $t8, $t8, 1        # increase zero counter
j bin_zero_loop         # repeat

# print the digits in buffer reverse
bin_print_start:
addi $t5, $t5, -1       # move index to last stored digit

bin_print_loop:
beq $t5, -1, bin_end    # stop when all digits printed

add $t6, $t4, $t5       # locate stored digit in buffer
lb $a0, 0($t6)          # load digit from buffer

li $v0, 1
syscall                 # print digit

addi $t5, $t5, -1       # move backward in buffer
j bin_print_loop        # repeat printing

bin_end:

# quat CONVERSION
la $a0, quat
li $v0, 4
syscall                 # print "Quaternary: "

move $t1, $t0           # copy input number
la $t4, quatbuf         # load address of quaternary buffer
li $t5, 0               # reset buffer index

quat_loop:
beq $t1, $zero, quat_store_done  # stop when quotient becomes 0

li $t2, 4               # divisor = 4
div $t1, $t2            # divide number by 4
mfhi $t3                # remainder
mflo $t1                # quotient

add $t6, $t4, $t5       # compute buffer address
sb $t3, 0($t6)          # store remainder

addi $t5, $t5, 1        # increase buffer index
j quat_loop             # repeat loop

quat_store_done:

# print leading zeros
li $t7, 16              # fixed quaternary length
sub $t7, $t7, $t5       # calculate leading zeros needed
li $t8, 0               # zero counter

quat_zero_loop:
beq $t8, $t7, quat_print_start  # stop when zeros finished

li $a0, 0
li $v0, 1
syscall                 # print zero

addi $t8, $t8, 1        # increase counter
j quat_zero_loop        # repeat

quat_print_start:
addi $t5, $t5, -1       # move to last stored digit

quat_print_loop:
beq $t5, -1, quat_end   # stop when finished printing

add $t6, $t4, $t5       # locate buffer digit
lb $a0, 0($t6)          # load digit

li $v0, 1
syscall                 # print digit

addi $t5, $t5, -1       # move backward
j quat_print_loop       # repeat

quat_end:

# OCTAL CONVERSION
la $a0, octal
li $v0, 4
syscall                 # print "Octal: "

move $t1, $t0           # copy input number
la $t4, octbuf          # load octal buffer
li $t5, 0               # reset buffer index

oct_loop:
beq $t1, $zero, oct_store_done  # stop when quotient is 0

li $t2, 8               # divisor = 8
div $t1, $t2            # divide by 8
mfhi $t3                # remainder
mflo $t1                # quotient

add $t6, $t4, $t5       # calculate buffer address
sb $t3, 0($t6)          # store remainder

addi $t5, $t5, 1        # increase buffer index
j oct_loop              # repeat

oct_store_done:

li $t7, 11              # fixed octal length
sub $t7, $t7, $t5       # calculate leading zeros
li $t8, 0               # zero counter

oct_zero_loop:
beq $t8, $t7, oct_print_start  # stop when zeros printed

li $a0, 0
li $v0, 1
syscall                 # print zero

addi $t8, $t8, 1        # increase counter
j oct_zero_loop         # repeat

oct_print_start:
addi $t5, $t5, -1       # move to last stored digit

oct_print_loop:
beq $t5, -1, oct_end    # stop when finished

add $t6, $t4, $t5       # locate digit in buffer
lb $a0, 0($t6)          # load digit

li $v0, 1
syscall                 # print digit

addi $t5, $t5, -1       # move backward
j oct_print_loop        # repeat

oct_end:

# CONTINUE
la $a0, cont
li $v0, 4
syscall                 # ask user if they want to continue

li $v0, 5
syscall                 # read user input

beq $v0, 1, loop        # if input = 1 restart program


# EXIT
la $a0, bye
li $v0, 4
syscall                 # print "bye!"

li $v0, 10
syscall                 # terminate program