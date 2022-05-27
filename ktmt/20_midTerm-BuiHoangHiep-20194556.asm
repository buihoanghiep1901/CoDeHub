.data
string		: .space 50
Message1	: .asciiz "Nhap xau: "
Message2	: .asciiz "So ki tu khac nhau la: "
.text
main:
get_string: 
		li 	$v0, 54
		la 	$a0,  Message1
		la 	$a1, string
		la 	$a2, 50
		syscall
 		li 	$s0, 0			# count = 0
	 	la 	$a0,string 		# $a0 = address(string[0])
 		li 	$t0, 1			# $t0 = i = 1
 		
 		jal 	loop1
 		nop
 		
loop1: 	add 	$t1,$a0,$t0 			# $t1 = $a0 + $t0 = address(string[i]) 
		lb 	$t2, 0($t1) 			# $t2 = string[i]
		beq	$t2, 32, end_blank		# if string[i] != ' '
 		beq 	$t2, $zero, end_of_loop 	# is null char? 
		li 	$s1, 1				# temp = 1 , moi lan gap i temp = 1	
		subi	$t3, $t0 , 1			# t3 = j = i - 1
loop2:		
 		add 	$t4,$a0,$t3 			# $t4 = $a0 + $t3 = address(string[j]) 
		lb 	$t5, 0($t4) 			# $t5 = string[j]
 		bne 	$t5, $t2, new_loop2		# if string[j] == string[i]
 		li 	$s1, 0				# temp = 0
 		nop
 		j 	new_loop1

new_loop2:	subi 	$t3, $t3 , 1			# j--
		sge 	$v1, $t3, $zero 		# v0 = 1 if j >= 0
		bne	$v1, $zero, loop2 		# jump loop2 when j >= 0
		nop
		j 	new_loop1  
		nop	
		
		
new_loop1:	addi 	$t0, $t0 , 1			# i++
		
		bne	$s1, 1, loop1			# if temp != 1 , exit
		nop
		addi	$s0, $s0, 1			#else count ++
		
		j 	loop1
		nop
	
end_blank:	addi	$t0, $t0, 1			# i++
		j 	loop1
		nop

end_of_loop:
print_length: 
		add 	$a1, $zero, $s0
		li 	$v0, 56
		la 	$a0, Message2
		syscall
		
		li 	$v0, 10
		syscall
