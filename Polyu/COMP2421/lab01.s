#################################################################
# File: lab01.s                                                 #
#                                                               #
# This program is to perform two calculations, an addition and  #
# a subtraction.  It first asks to input 2 integers through the #
# console and then does the calculations.  Finally, it prints   #
# out the results on the console.                               #
#################################################################

####################
# The data segment #
####################
	
    .data

# Create some null terminated strings which are to be used in the program

strPromptFirst:  .asciiz "Enter the first operand (A): "
strPromptSecond: .asciiz "Enter the second operand (B): "
strResultAdd:    .asciiz "A + B is "
strResultSub:    .asciiz "A - B is "
strDone:         .asciiz "DONE\n"
strCR:           .asciiz "\n"

###############################################
# The text segment -- instructions start here #
###############################################

    .text
    .globl main

main:
    # STEP 1 -- get the first operand
    # Print a prompt asking user for input

    li $v0, 4                  # syscall number 4 prints string whose address is in $a0
    la $a0, strPromptFirst     # "load address" of the string
    syscall                    # actually print the string

    # Now read in the first operand

    li $v0, 5                  # syscall number 5 reads an int
    syscall                    # actually read the int
    move $s0, $v0              # save result in $s0 for later

    # STEP 2 -- repeat above steps to get the second operand
    # First print the prompt

    li $v0, 4                  # syscall number 4 prints string whose address is in $a0
    la $a0, strPromptSecond    # "load address" of the string
    syscall                    # actually print the string

    # Now read in the second operand

    li $v0, 5                  # syscall number 5 reads an int
    syscall                    # actually read the int
    move $s1, $v0              # save result in $s1 for later

    # STEP 3 -- print the sum
    # First print the string prelude

    li $v0, 4                  # syscall number 4 prints string
    la $a0, strResultAdd
    syscall                    # actually print the string

    # Then print the actual sum

    li $v0, 1                  # syscall number 1 prints int
    add $a0, $s0, $s1          # add operands and put result in $a0 for printing
    syscall                    # actually print the int

    # Finally print a carriage return

    li $v0, 4                  # syscall for printing string
    la $a0, strCR              # address of string with a carriage return
    syscall                    # actually print the string

    # STEP 4 -- print the difference
    # First print the string prelude

    li $v0, 4                  # syscall number 4 prints string
    la $a0, strResultSub
    syscall                    # actually print the string

    # Then print the actual sum

    li $v0, 1                  # syscall number 1 prints int
    sub $a0, $s0, $s1          # subtract operands and put result in $a0 for printing
    syscall                    # actually print the string

    # Finally print a carriage return

    li $v0, 4                  # syscall for printing string
    la $a0, strCR
    syscall

    # STEP 5 -- EXIT

    li $v0, 10                 # Syscall number 10 is to terminate the program
    syscall                    # exit now

##################
# End of Program #
##################