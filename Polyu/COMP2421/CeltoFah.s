    .data
str1: .asciiz "Enter Celsius: "
str2: .asciiz "Fahrenheit is: "
    .globl main

    .text
main:
    #
    #Input Celsius
    la $a0, str1 
    li $v0, 4
    syscall

    li $v0, 5
    syscall
    move $s0, $v0

    #
    #Calculate
    li $t0, 9
    li $t1, 5
    mult $t0, $s0
    mflo $t2
    div $t2, $t1
    mflo $s2
    addi $s2, $s2, 32

    la $a0, str2
    li $v0, 4
    syscall

    move $a0, $s2
    li $v0, 1
    syscall
    
    li $v0, 10
    syscall

