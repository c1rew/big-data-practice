/**
 * @file utils.h
 * @author c1rew
 * @brief 
 * @version 0.1
 * @date 2020-07-31
 * 
 * @copyright Copyright (c) 2020
 * 
 */
#ifndef __UTILS_H__
#define __UTILS_H__
#include <stdio.h>
#include <set>
#include <vector>
#include <algorithm>
#include <iostream>

/**
 * @brief generate random number array, not duplicate number
 * 
 * @param n array size 
 * @param range_left  left range eg. 1
 * @param range_right  right range eg. 100
 * @return std::vector<int> return vector array
 */
std::vector<int> generate_random_array(int n, int range_left, int range_right);

#endif //__UTILS_H__