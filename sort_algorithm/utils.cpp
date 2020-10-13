/**
 * @file utils.cpp
 * @author c1rew
 * @brief
 * @version 0.1
 * @date 2020-07-31
 *
 * @copyright Copyright (c) 2020
 *
 */

#include "utils.h"

std::vector<int> generate_random_array(int n, int range_left, int range_right)
{
    assert(range_left <= range_right);
    std::vector<int> num_vec(n);
    std::set<int> num_set; // set ,避免重复
    srand(time(NULL)); // 随机种子

    while (!(num_set.size() == n)) {  // rand 可能生成重复的数，判断set大小为 n 时退出
        int random_num = rand() % (range_right - range_left + 1) + range_left;

        if (!num_set.count(random_num)) {   // set中无当前数，插入
            num_set.insert(random_num);
        }
    }

    std::copy(num_set.begin(), num_set.end(), num_vec.begin());  // 将set copy到vector
    random_shuffle(num_vec.begin(), num_vec.end());             // 上述拿到的vector是有序的，做一下混洗
    return num_vec;
}
