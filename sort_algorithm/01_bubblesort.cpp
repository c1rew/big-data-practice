#include <stdio.h>
#include <string>
#include <iostream>
#include <vector>

#include "utils.h"





int main(int argc, char** argv)
{
    int size = 20;
    std::vector<int> rand_vec(size);
    rand_vec = generate_random_array(size, 1, 100);

    return 0;
}
