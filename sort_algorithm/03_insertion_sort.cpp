#include <stdio.h>
#include <string>
#include <iostream>
#include <vector>

#include "utils.h"


std::vector<int> insertion_sort(std::vector<int> &num)
{
    if (num.size() < 2) {
        return num;
    }

    int len = num.size();
    int min_index = 0;

    for (int i = 1; i < len; ++i) {
        int target = num[i];
        int j = i - 1;
        for (; j >= 0 && num[j] > target; --j) {
            num[j + 1] = num[j];    // 较大的数往后移
        }
        // 对比完，上面做了--j，所以插入的位置要 +1
        num[j + 1] = target;
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
    ret = insertion_sort(rand_vec);

    for (auto &num : ret) {
        std::cout << num << " ";
    }

    std::cout << std::endl;
    return 0;
}
