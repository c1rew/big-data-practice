#include <stdio.h>
#include <string>
#include <iostream>
#include <vector>

#include "utils.h"


std::vector<int> selection_sort(std::vector<int> &num)
{
    if (num.size() < 2) {
        return num;
    }

    int len = num.size();
    int min_index = 0;

    for (int i = 0; i < len - 1; ++i) {
        min_index = i;

        for (int j = i + 1; j < len; ++j) {
            if (num[j] < num[min_index]) {
                min_index = j;
            }
        }

        int temp = num[i];
        num[i] = num[min_index];
        num[min_index] = temp;
    }

    return num;
}



int main(int argc, char **argv)
{
    std::cout << __FILE__ << std::endl;
    int size = 20;
    std::vector<int> rand_vec(size);
    rand_vec = generate_random_array(size, 1, 100);

    for (auto &num : rand_vec) {
        std::cout << num << " ";
    }

    std::cout << std::endl;
    std::vector<int> ret(size);
    ret = selection_sort(rand_vec);

    for (auto &num : ret) {
        std::cout << num << " ";
    }

    std::cout << std::endl;
    return 0;
}
