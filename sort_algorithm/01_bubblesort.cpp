#include <stdio.h>
#include <string>
#include <iostream>
#include <vector>

#include "utils.h"


std::vector<int> bubble_sort(std::vector<int> &num) {
    if (num.size() < 2) {
        return num;
    }
	int len = num.size();
    for (int i = 0; i < len; ++i) {
        for ( int j = 0; j < len - i - 1; ++j) {
            if (num[j + 1] < num[j]) {
                int temp = num[j];
                num[j] = num[j + 1];
                num[j + 1] = temp;
            }
        }
    }
    return num;
}



int main(int argc, char** argv)
{
    int size = 20;
    std::vector<int> rand_vec(size);
    rand_vec = generate_random_array(size, 1, 100);
	for (auto &num : rand_vec) {
	    std::cout << num << " ";
	}
	std::cout << std::endl;
	std::vector<int> ret(size);
    ret = bubble_sort(rand_vec);
	for (auto &num : ret) {
	    std::cout << num << " ";
	}
	std::cout << std::endl;

    return 0;
}
