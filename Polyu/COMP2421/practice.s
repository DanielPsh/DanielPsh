    .data
str1: .asciiz "Enter the total: "
str2: .asciiz "Enter the number of ppl: "
str3: .asciiz "Individual Payment: "
    .globl main
    .text
main:
    #
    #
    la $a0, str1
    li $v0, 4
    syscall

    li $v0, 5
    syscall
    move $s0, $v0

    #
    #
    la $a0, str2
    li $v0, 4
    syscall

    li $v0, 5
    syscall
    move $s1, $v0

    #
    #
    div $s0, $s1
    mflo $t0

    la $a0, str3
    li $v0, 4
    syscall

    move $a0, $t0
    li $v0, 1
    syscall

    li $v0, 10
    syscall