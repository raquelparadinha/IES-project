#!/bin/bash

down_flg=0
build_flg=0
jar_flg=0
help_flg=0
sleeps="10"
time_per_sleep=3

while getopts hjda flag
do
	case ${flag} in
		h) 
			help_flg=1
		;;
		d) 
			down_flg=1
		;;
		b)
			build_flg=1
		;;
		j)
			jar_flg=1
		;;	
		
		a)
			jar_flg=1
			down_flg=1
			build_flg=1
		;;
	esac
done

if [[ "$help_flg" -eq 1 ]]; then
	printf "\nUSAGE: run.sh\n\tOPTIONS:\n\t\tno options : Only ups containers\n\t\t-d : Down containers\n\t\t-b : Build containers\n\t\t-j : Build Spring jar\n\t\t-a : All the options\n\n\n"
	exit 0
fi