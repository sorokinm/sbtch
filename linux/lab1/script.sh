#!/bin/bash

echo $0

echo "Hello World!!"

if [ $# -gt 0 ]; then
	case $1 in
		\/*)
			if [[ $1 == $HOME* ]]; then
				if [ -f "$1" ];then
					cat $1
				else
					if [ $# -gt 1 ]; then
						echo $2 > $1
					else
						echo "There's nothing to write to file!!!"
					fi
				fi
			fi
			;;
		* )
                        if [ -f "$1" ];then
                                        cat $1
                                else
                                        if [ $# -gt 1 ]; then
                                                echo $2 > $1
                                        else
                                                echo "There's nothing to write to file!!!"
                                        fi
                                fi

			;;
	esac
else
	echo 'Nothing has been entered!!!'
fi
